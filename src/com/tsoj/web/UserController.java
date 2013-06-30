package com.tsoj.web;


import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.tsoj.web.entity.User;
import com.tsoj.web.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userService;
	
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
	
	@RequestMapping(value = "/srv/register", method=RequestMethod.POST)
	@ResponseBody
	public Object srvRegister(@RequestParam("uid") String uid,
			@RequestParam("upasswd") String upasswd) {
		//init logger
		Logger logger = LogManager.getLogger(UserController.class.getName());
		logger.info("register service:");
		
		//create existing user
		User user = userService.findById(uid);
		if (null != user) {
			logger.info("register service => existed user.");
			return "existed user";
		}
		user = new User();
		user.setUid(uid);
		user.setUpasswd(upasswd);
		//write to db
		logger.info("register service => " + "uid:" + uid + " upasswd:" + upasswd);
		userService.save(user);
		logger.info("register service => created user.");
		return "OK";
	}
	
	@RequestMapping(value = "/srv/login", method=RequestMethod.POST)
	@ResponseBody
	public String srvLogin(@RequestParam("uid") String uid,
			@RequestParam("upasswd") String upasswd,
			HttpSession session) {
		
		//init logger
		Logger logger = LogManager.getLogger(UserController.class.getName());
		logger.info("login service");
		logger.info("login service => " + "uid:" + uid + " upasswd:" + upasswd);
		//fetch user from db
		User user = userService.findById(uid);
		
		if (null != user) {
			logger.info("login service => " + "uid:" + user.getUid() + " upasswd:" + user.getUpasswd() == upasswd);
		}
		//check passwd
		if (null != user && user.getUpasswd().equals(upasswd)) {
			//set session
			session.setAttribute("current_user", user);
		} else {
			return "Wrong Password";
		}
		return "OK";
	}
}
