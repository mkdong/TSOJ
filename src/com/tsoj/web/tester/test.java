package com.tsoj.web.tester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.tsoj.web.entity.User;
import com.tsoj.web.service.SolutionService;
import com.tsoj.web.service.UserService;

@Controller
public class test {
	@Resource
	UserService userService;
	@Resource
	SolutionService solutionService;
	
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
	public void add(){
		
		long timeBegin = System.currentTimeMillis();
		for (int i=0; i<10000; ++i) {
			User user = new User();
			user.setUid(genString(10));
			user.setUpasswd(genString(22));
			userList.add(user);
			userService.save(user);
		}
		long timeEnd = System.currentTimeMillis();
		System.out.println(timeEnd - timeBegin);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
