package com.tsoj.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.tsoj.web.entity.Problem;
import com.tsoj.web.service.ProblemService;

@Controller
public class ProblemController {
	@Resource
	private ProblemService problemService;
	
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
		mv.addObject("prev", prev); 
		mv.addObject("next", next); 
		return mv;
	}

	@RequestMapping(value = "/problem/{id}")
	public ModelAndView problem(@PathVariable int id) {
		//init logger
		Logger logger = LogManager.getLogger(ProblemController.class.getName());
		logger.info("problem:");
		
		Problem problem = problemService.findById(id);
		logger.info("problem=> pid:" + problem.getPid());
		
		ModelAndView mv = new ModelAndView("problem");//, "problem", problem); 
		mv.addObject("problem", problem);
		
		return mv;
	}
	
	@RequestMapping(value = "/newproblem")
	public String problem() {
		//check permission 
		/*
		 * stub here
		 */
		return "newproblem";
	}
	
	@RequestMapping(value = "srv/newproblem", method=RequestMethod.POST)
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
