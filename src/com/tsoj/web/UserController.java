package com.tsoj.web;


import java.io.File;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tsoj.web.entity.User;
import com.tsoj.web.service.AvatarService;
import com.tsoj.web.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private AvatarService avatarService;
	
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
	
	@RequestMapping(value = "/info")
	public String info(HttpSession session) {
		//check session
		User usr = (User) session.getAttribute("current_user");
		if (null == usr) {
			//show login page
			return "redirect:login";
		} else {
			//already logged in, show info
			return "info";
		}
	}
	
	@RequestMapping(value = "/srv/password", method=RequestMethod.POST)
	@ResponseBody
	public String srvPassword(
			@RequestParam("upasswd") String upasswd,
			HttpSession session) {
		
		//check session
		User user = (User) session.getAttribute("current_user");
		if (null == user) {
			//show login page
			return "please login first";
		}
		user.setUpasswd(upasswd);
		userService.update(user);
		//set session
		session.setAttribute("current_user", user);
		return "OK";
	}
	
	
	@RequestMapping(value = "/avatar", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getAvatar(
			HttpSession session) {
		
		//check session
		User user = (User) session.getAttribute("current_user");
		
		byte bytes[] = avatarService.findOne(user.getUid());
		
		return bytes;
	}
	
	@RequestMapping(value = "/avatar", method=RequestMethod.POST)
	public String avatar(
			@RequestParam("avatar") CommonsMultipartFile mFile,
			HttpSession session) {
		
		//check session
		User user = (User) session.getAttribute("current_user");
		if (null == user) {
			//show login page
			return "redirect:login";
		}
		
		Logger logger = LogManager.getLogger(ProblemController.class.getName());
		logger.info("handling form upload");
		logger.info(mFile.getOriginalFilename());
		
		String suffix = ""; 
		if (mFile.getOriginalFilename().endsWith(".jpg")) {
			suffix = "";
		} else if (mFile.getOriginalFilename().endsWith(".png")) {
			suffix = ".png";
		}
		
		if (suffix.equals("")) {
			return "redirect:info";
		}
		if (mFile.isEmpty()) {
			return "redirect:info";
		}
		
		String path = session.getServletContext().getRealPath("/") + "avatars";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		
		byte bytes[] = mFile.getBytes();
		avatarService.save(bytes, user.getUid());
		
		return "redirect:info";
	}
}
