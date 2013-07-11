package com.tsoj.web;


import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public String index() {
		return "home";
	}
	
	@RequestMapping(value = "/home")
	public String welcome() {
		return "home";
	}
	
	@RequestMapping(value = "/about")
	public String about() {
		return "about";
	}
}
