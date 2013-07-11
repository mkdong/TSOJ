package com.tsoj.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tsoj.web.dao.AvatarDao;

@Service
public class AvatarService {
	@Resource
	AvatarDao avatarDao;
	
	public void save(byte[] bytes, String fileName) {
		avatarDao.save(bytes, fileName);
	}
	
	public byte[] findOne(String fileName) {
		return avatarDao.findOne(fileName);
	}
}
