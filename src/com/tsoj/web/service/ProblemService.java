package com.tsoj.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tsoj.web.dao.ProblemDao;
import com.tsoj.web.entity.Problem;

@Service
public class ProblemService {
	@Resource
	private ProblemDao problemDao;
	
	public void save(Problem problem) {
		problemDao.save(problem);
	}
	public void delete(Problem problem) {
		problemDao.delete(problem);
	}
	public void update(Problem problem) {
		problemDao.update(problem);
	}
	public Problem findById(int pid) {
		return problemDao.findById(pid);
	}
	public List<Problem> findAll() {
		return problemDao.findAll();
	}
	public List<Problem> findInRange(int from, int to) {
		return problemDao.findInRange(from, to);
	}
}
