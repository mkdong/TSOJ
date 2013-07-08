package com.tsoj.web;


import java.io.File;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.*;

import com.tsoj.web.entity.Comment;
import com.tsoj.web.entity.Problem;
import com.tsoj.web.entity.Solution;
import com.tsoj.web.entity.User;
import com.tsoj.web.evaluator.Evaluator;
import com.tsoj.web.service.CommentService;
import com.tsoj.web.service.ProblemService;
import com.tsoj.web.service.SolutionService;
import com.tsoj.web.service.UserService;

@Controller
public class ProblemController {
	@Resource
	private ProblemService problemService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private SolutionService solutionService;
	
	@Resource
	private Evaluator evaluator;
	
	@RequestMapping(value="/problemset", method=RequestMethod.GET)
	public String problemsetn() {
		return "redirect:/problemset/0";
	}
	
	@RequestMapping(value="/problemset/{id}", method=RequestMethod.GET)
	public ModelAndView problemset(@PathVariable int id) {
		if (id < 0) id = 0;
		Logger logger = LogManager.getLogger(ProblemController.class.getName());
		logger.info("problemset controller");
		List<Problem> problems = problemService.findInRange(id*100, id*100+100);
		ModelAndView mv = new ModelAndView("problemset");
		mv.addObject("problemset", problems);
		int prev = id - 1;
		if (prev < 0) prev++;
		int next = id + 1;
		if (problems.size() < 100) next--;
		mv.addObject("first", 0);
		mv.addObject("prev", prev); 
		mv.addObject("next", next);
		mv.addObject("last", (problemService.count()+99)/100);
		return mv;
	}

	@RequestMapping(value = "/problem/{id}")
	public ModelAndView problem(
			@PathVariable int id,
			HttpSession session) {
		//init logger
		Logger logger = LogManager.getLogger(ProblemController.class.getName());
		logger.info("problem:");
		
		Problem problem = problemService.findById(id);
		logger.info("problem=> pid:" + problem.getPid());
		
		ModelAndView mv = new ModelAndView("problem");//, "problem", problem); 
		mv.addObject("problem", problem);
		mv.addObject("psid", problem.getPid()/100);
		mv.addObject("guest", null == session.getAttribute("current_user"));
		return mv;
	}
	
	@RequestMapping(value = "/submit/{id}")
	public String handleFormUpload(
			@PathVariable("id") int pid,
			//@RequestParam("name") String name,
			@RequestParam("file") CommonsMultipartFile mFile,
			HttpSession session) {
		
		Logger logger = LogManager.getLogger(ProblemController.class.getName());
		logger.info("handling form upload");
		logger.info(mFile.getOriginalFilename());
		
		if (!mFile.getOriginalFilename().endsWith(".cpp")) {
			return "redirect:d";
		}
		if (mFile.isEmpty()) {
			return "redirect:problem";
		}
		logger.info("handling file");
		User user = (User) session.getAttribute("current_user");
		String path = session.getServletContext().getRealPath("/") + "submits";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		file = null;
		String fileName = path + "/" + user.getUid() + "_" + String.valueOf(pid) + ".cpp";
		logger.info(fileName);
		file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			mFile.getFileItem().write(file);
			Solution solution = new Solution();
			solution.setUid(user.getUid());
			solution.setPid(pid);
			solution.setSlang("CPP");
			solution.setScode(fileName);
			solution.setSsbmttime(new java.util.Date());
			solution.setStime(5000);
			solution.setSmemory(1000000);
			solution.setStester("xx");
			solution.setSresult("PENDING");
			solutionService.save(solution);
			evaluator.append(solution);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/status/0";
	}

	@RequestMapping(value = "/newproblem")
	public String problem() {
		//check permission 
		/*
		 * stub here
		 */
		return "newproblem";
	}
	
	@RequestMapping(value = "/srv/newproblem", method=RequestMethod.POST)
	@ResponseBody
	public String srvNewProblem(
			@RequestParam("ptitle") String ptitle,
			@RequestParam("pcontent") String pcontent,
			@RequestParam("pinput") String pinput,
			@RequestParam("poutput") String poutput,
			@RequestParam("psamplei") String psamplei,
			@RequestParam("psampleo") String psampleo,
			@RequestParam("ptime") int ptime,
			@RequestParam("pmemory") int pmemory,
			@RequestParam("plevel") int plevel,
			@RequestParam("pcategory") String pcategory,
			HttpSession session) {
		//init logger
		Logger logger = LogManager.getLogger(ProblemController.class.getName());
		logger.info("newproblem service");
		
		//create user 
		Problem p = new Problem();
//		p.setPid(pid);
		p.setPtitle(ptitle);
		p.setPcontent(pcontent);
		p.setPinput(pinput);
		p.setPoutput(poutput);
		p.setPsamplei(psamplei);
		p.setPsampleo(psampleo);
		p.setPtime(ptime);
		p.setPmemory(pmemory);
		p.setPlevel(plevel);
		p.setPcategory(pcategory);
		//write to db
		problemService.save(p);
		logger.info("newproblem service => written.");
		
		return "OK";
	}
	
	@RequestMapping(value = "/srv/newcomment", method=RequestMethod.POST)
	@ResponseBody
	public String srvNewComment(
			@RequestParam("cmcontent") String cmcontent,
			@RequestParam("pid") int pid,
			HttpSession session) {
		//init logger
		Logger logger = LogManager.getLogger(ProblemController.class.getName());
		logger.info("newproblem service");
		
		User user = (User) session.getAttribute("current_user");
		if (null == user) {
			return "please login";
		}
		
		//create comment
		Comment c = new Comment();
		c.setCmcontent(cmcontent);
		c.setCmtime(new java.sql.Date(new java.util.Date().getTime()));
		c.setPid(pid);
		c.setUid(user.getUid());
		//write to db
		commentService.save(c);
		logger.info("new comment service => written.");
		
		return "OK";
	}
	
	@RequestMapping(value = "/comment/{pid}/{id}")
	public ModelAndView comment(
			@PathVariable int pid,
			@PathVariable int id,
			HttpSession session) {
		//init logger
		Logger logger = LogManager.getLogger(ProblemController.class.getName());
		logger.info("comment:");
		
		Problem problem = problemService.findById(pid);
		logger.info("comment=> pid:" + problem.getPid());
		
		ModelAndView mv = new ModelAndView("comment");//, "problem", problem); 
		mv.addObject("problem", problem);
		mv.addObject("psid", problem.getPid()/100);
		mv.addObject("guest", null == session.getAttribute("current_user"));
		
		long tot = commentService.countByPid(pid);
		if (id < 0) id = 0;
		List<Comment> comments = commentService.findInRangeByPid(id*20, id*20+20, pid);
		int size = comments.size();
		logger.info(size);
		mv.addObject("comments", comments);
		int prev = id - 1;
		if (prev < 0) prev++;
		int next = id + 1;
		if (size < 20) next--;
		mv.addObject("first", 0);
		mv.addObject("prev", prev); 
		mv.addObject("next", next);
		mv.addObject("last", (tot+19)/20);
		return mv;
	}
	
	/*
	@RequestMapping(value = "/solution/{id}")
	public ModelAndView solution(@PathVariable int id) {
		//check session
		Problem problem = null;
		try {
			Problem.fetchProblem(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("problem", "", "");
		mv.addObject("problem", problem);
		return mv;
	}
	*/
}
