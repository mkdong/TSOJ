package com.tsoj.web.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.tsoj.web.entity.Problem;

@Repository("ProblemDao")
public class ProblemDao extends Dao<Problem> {
	
	public Problem findById(int pid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Problem problem = (Problem) session.get(Problem.class, pid);
		tx.commit();
		return problem;
	}
	
	public List<Problem> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Problems");
		List<Problem> problems = query.list();
		tx.commit();
		return problems;
	}
	
	public List<Problem> findInRange(int from, int to) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from " + Problem.class.getName() + " where pid>=? and pid<=?");
		
		query.setInteger(0, from);
		query.setInteger(1, to);
		List<Problem> problems = query.list();
		tx.commit();
		return problems;
	}
	
	public long count() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select count(*) from " + Problem.class.getName());
		long num = (long) query.iterate().next();
		tx.commit();
		return num;
	}
	
}