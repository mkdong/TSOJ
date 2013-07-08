package com.tsoj.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tsoj.web.dao.SolutionDao;
import com.tsoj.web.dao.SolutionMongoDao;
import com.tsoj.web.entity.Solution;

@Service
public class SolutionService {
	@Resource
	private SolutionMongoDao solutionDao;
	
	public void save(Solution solution) {
		solutionDao.save(solution);
	}
	public void delete(Solution solution) {
		solutionDao.delete(solution);
	}
	public void update(Solution solution) {
		solutionDao.update(solution);
	}
	public Solution findById(int pid) {
		return solutionDao.findById(pid);
	}
	public List<Solution> findInRange(int from, int to) {
		return solutionDao.findInRange(from, to);
	}
	public List<Solution> findInRangeByPid(int from, int to, int pid) {
		return solutionDao.findInRangeByPid(from, to, pid);
	}
	public List<Solution> findInRangeByUid(int from, int to, int uid) {
		return solutionDao.findInRangeByUid(from, to, uid);
	}
	public long countByPid(int pid) {
		return solutionDao.countByPid(pid);
	}
	public long countByUid(int uid) {
		return solutionDao.countByUid(uid);
	}
	public List<Solution> findInRangeByBothId(int from, int to, int uid, int pid) {
		return solutionDao.findInRangeByBothId(from, to, uid, pid);
	}
}
