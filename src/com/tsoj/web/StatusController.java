package com.tsoj.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.tsoj.web.entity.Solution;
import com.tsoj.web.service.SolutionService;

@Controller
public class StatusController {
	@Resource
	private SolutionService solutionService;
	
	@RequestMapping(value="/status/{id}")
	public ModelAndView statusAll(@PathVariable int id) {
		if (id < 0) id = 0;
		Logger logger = LogManager.getLogger(StatusController.class.getName());
		logger.info("statusall");
		List<Solution> solutions = solutionService.findInRange(id*20, id*20+20);
		ModelAndView mv = new ModelAndView("status");
		mv.addObject("solutions", solutions);
		int prev = id - 1;
		if (prev < 0) prev++;
		int next = id + 1;
		if (solutions.size() < 20) next--;
		mv.addObject("first", 0);
		mv.addObject("prev", prev); 
		mv.addObject("next", next);
		return mv;
	}

	@RequestMapping(value = "/status/{pid}/{id}")
	public ModelAndView status(
			@PathVariable int pid,
			@PathVariable int id,
			HttpSession session) {
		//init logger
		Logger logger = LogManager.getLogger(StatusController.class.getName());
		logger.info("status:");
		
		ModelAndView mv = new ModelAndView("status");//, "problem", problem); 
		mv.addObject("pid", pid);
		mv.addObject("psid", pid/100);
		
		if (id < 0) id = 0;
		List<Solution> solutions= solutionService.findInRangeByPid(id*20, id*20+20, pid);
		int size = solutions.size();
		logger.info(size);
		mv.addObject("solutions", solutions);
		int prev = id - 1;
		if (prev < 0) prev++;
		int next = id + 1;
		if (size < 20) next--;
		mv.addObject("first", 0);
		mv.addObject("prev", prev); 
		mv.addObject("next", next);
		return mv;
	}
}
