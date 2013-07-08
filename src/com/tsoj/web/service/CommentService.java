package com.tsoj.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tsoj.web.dao.CommentDao;
import com.tsoj.web.entity.Comment;

@Service
public class CommentService {
	@Resource
	private CommentDao commentDao;
	
	public void save(Comment comment) {
		commentDao.save(comment);
	}
	public void delete(Comment comment) {
		commentDao.delete(comment);
	}
	public void update(Comment comment) {
		commentDao.update(comment);
	}
	public Comment findById(int pid) {
		return commentDao.findById(pid);
	}
	public List<Comment> findAllByPid(int pid) {
		return commentDao.findAllByPid(pid);
	}
	public List<Comment> findInRangeByPid(int from, int to, int pid) {
		return commentDao.findInRangeByPid(from, to, pid);
	}
	public long countByPid(int pid) {
		return commentDao.countByPid(pid);
	}
}
