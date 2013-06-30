package com.tsoj.web.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.tsoj.web.entity.Problem;

@Repository("ProblemDao")
public class ProblemDao extends Dao<Problem>{
	
	public Problem findById(int pid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Problem user = (Problem) session.get(Problem.class, pid);
		tx.commit();
		return user;
	}
	
	public List<Problem> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Problems");
		List<Problem> users = query.list();
		tx.commit();
		return users;
	}
	
	public List<Problem> findInRange(int from, int to) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from " + Problem.class.getName() + " where pid>=? and pid<=?");
		
		query.setInteger(0, from);
		query.setInteger(1, to);
		List<Problem> users = query.list();
		tx.commit();
		return users;
	}
}