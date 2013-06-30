package com.tsoj.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tsoj.web.dao.UserDao;
import com.tsoj.web.entity.User;

@Service
public class UserService {
	@Resource
	private UserDao userDao;
	
	public void save(User user) {
		this.userDao.save(user);
	}
	public void delete(User user) {
		this.userDao.delete(user);
	}
	public void update(User user) {
		this.userDao.update(user);
	}
	public User findById(String uid) {
		return this.userDao.findById(uid);
	}
	public List<User> findAll() {
		return this.userDao.findAll();
	}

}
