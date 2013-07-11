package com.tsoj.web;


import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
public class TestController {
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
	
	public String genString(int len) {
		Random random = new Random();
		String s = "";
		for (int i=0; i<len; ++i) {
			s += ('a' + random.nextInt(26));
		}
		System.out.println("GenString: " + s);
		return s;
	}
	
	List<User> userList = new ArrayList<User>(); 
	@RequestMapping(value = "/test")
	public ModelAndView handleFormUpload(
			HttpSession session) {
		Random random = new Random();
//		int size = 100000;
//		List<String> userNameList = new ArrayList<String>();
//		List<String> passwdList = new ArrayList<String>();
//		for (int i=0; i<size; ++i) {
//			userNameList.add(genString(10));
//			passwdList.add(genString(22));
//		}
//		System.out.println("generated");
//		for (int i=0; i<size; ++i) {
//			Solution s = new Solution();
//			s.setPid(random.nextInt(1000));
//			s.setUid(String.valueOf(random.nextInt(100)));
//			s.setSlang("CPP");
//			s.setSmemory(random.nextInt(10000));
//			s.setStester("G++");
//			s.setSresult(random.nextInt(2)==1?"AC":"WA");
//			s.setStime(random.nextInt(10000));
//			s.setStesttime(new java.util.Date());
//			solutionService.save(s);
//		}
		long timeBegin = System.currentTimeMillis();
		solutionService.findInRange(0, 100000);
		long timeEnd = System.currentTimeMillis();
		System.out.println(timeEnd - timeBegin);
		ModelAndView mv = new ModelAndView("test");
		mv.addObject("time", timeEnd-timeBegin);
		
//		Solution solution = new Solution();
//		solution.setUid(user.getUid());
//		solution.setPid(pid);
//		solution.setSlang("CPP");
//		solution.setScode(fileName);
//		solution.setSsbmttime(new java.util.Date());
//		solution.setStime(5000);
//		solution.setSmemory(1000000);
//		solution.setStester("xx");
//		solution.setSresult("PENDING");
//		solutionService.save(solution);
//		evaluator.append(solution);
		return mv;
	}
}
