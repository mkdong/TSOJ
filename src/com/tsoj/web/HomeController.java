package com.tsoj.web;


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
