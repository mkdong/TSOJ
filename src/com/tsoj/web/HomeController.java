package com.tsoj.web;


import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;


@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public String index() {
		Logger logger = LogManager.getLogger(HomeController.class.getName());
		logger.info("heresdf");
		return "home";
	}
	
	@RequestMapping(value = "/home")
	public ModelAndView welcome() {
		
		ModelAndView mv = new ModelAndView("home", "", "");
//		mv.addObject("hello", "Hello");
		Logger logger = LogManager.getLogger(HomeController.class.getName());
		logger.info("here");
		return mv;
	}
	
	@RequestMapping(value = "/register")
	public String register(HttpSession session) {
		//check session
		User usr = (User) session.getAttribute("current_user");
		if (null == usr) {
			//show register page
			return "register";
		} else {
			//already logged in, redirect to home
			return "redirect:home";
		}
	}

	
	@RequestMapping(value = "/login")
	public String login(HttpSession session) {
		//check session
		User usr = (User) session.getAttribute("current_user");
		if (null == usr) {
			//show login page
			return "login";
		} else {
			//already logged in, redirect to home
			return "redirect:home";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		//remove session
		Enumeration<String> keys = session.getAttributeNames();
		while (keys.hasMoreElements()) {
			session.removeAttribute(keys.nextElement());
		}
		//redirect to home
		return "redirect:home";
	}
	
	@RequestMapping(value = "/services/register", method=RequestMethod.POST)
	@ResponseBody
	public Object serviceRegister(@RequestParam("uid") String uid,
			@RequestParam("upasswd") String upasswd) {
		//init logger
		Logger logger = LogManager.getLogger(HomeController.class.getName());
		logger.info("register service:");
		
		//create user 
		User usr = new User();
		usr.setUid(uid);
		usr.setUpasswd(upasswd);
		
		logger.info("register service => " + "uid:" + uid + " upasswd:" + upasswd);
		try {
			//write to db
			User.createUser(usr);
			logger.info("register service => created user.");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		}
		return "OK";
	}
	
	@RequestMapping(value = "/services/login", method=RequestMethod.POST)
	@ResponseBody
	public String serviceLogin(@RequestParam("uid") String uid,
			@RequestParam("upasswd") String upasswd,
			HttpSession session) {
		
		User usr = null;
		//init logger
		Logger logger = LogManager.getLogger(HomeController.class.getName());
		logger.info("login service");
		
		logger.info("login service => " + "uid:" + uid + " upasswd:" + upasswd);
		try {
			//fetch user from db
			usr = User.fetchUser(uid);
			
			logger.info("login service => " + "uid:" + usr.getUid() + " upasswd:" + usr.getUpasswd()==upasswd);
			
			//check passwd
			if (null != usr && usr.getUpasswd().equals(upasswd)) {
				//set session
				session.setAttribute("current_user", usr);
			} else {
				return "Wrong Password";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		}
		return "OK";
	}
	
	@RequestMapping(value="/problemset", method=RequestMethod.GET)
	public String problemsetn() {
		return "redirect:/problemset/0";
	}
	
	@RequestMapping(value="/problemset/{id}", method=RequestMethod.GET)
	public ModelAndView problemset(@PathVariable int id) {
		if (id < 0) id = 0;
		Logger logger = LogManager.getLogger(HomeController.class.getName());
		logger.info("problemset controller");
		ProblemSet problemSet = new ProblemSet();
		try {
			problemSet.fetchProblems(id);
			logger.info("problemset size:" + problemSet.getProblems().size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("problemset");
		mv.addObject("problemset", problemSet.getProblems());
		int prev = id - 1;
		if (prev < 0) prev++;
		int next = id + 1;
		if (problemSet.getProblems().size() < 100) next--;
		mv.addObject("prev", prev); 
		mv.addObject("next", next); 
		return mv;
	}

	@RequestMapping(value = "/problem/{id}")
	public ModelAndView problem(@PathVariable int id) {
		//init logger
		Logger logger = LogManager.getLogger(HomeController.class.getName());
		logger.info("problem:");
		
		Problem problem = null;
		try {
			problem = Problem.fetchProblem(id);
			logger.info("problem=> pid:" + problem.getPid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	@RequestMapping(value = "services/newproblem", method=RequestMethod.POST)
	@ResponseBody
	public String serviceNewProblem(
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
		Logger logger = LogManager.getLogger(HomeController.class.getName());
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
		
		try {
			//write to db
			Problem.createProblem(p);
			logger.info("newproblem service => written.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		}
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
