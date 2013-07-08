package com.tsoj.web.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.tsoj.web.entity.Solution;


@Repository("SolutionDao")
public class SolutionDao extends Dao<Solution> {
	public Solution findById(int sid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Solution solution = (Solution) session.get(Solution.class, sid);
		tx.commit();
		return solution;
	}

	public List<Solution> findInRange(int from, int to) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from " + Solution.class.getName() + " order by ssbmttime desc");
		query.setFirstResult(from);
		query.setMaxResults(to - from);
		List<Solution> solutions = query.list();
		tx.commit();
		return solutions;
	}
	
	public List<Solution> findInRangeByPid(int from, int to, int pid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from " + Solution.class.getName() + " where pid=? order by ssbmttime desc");
		query.setInteger(0, pid);
		query.setFirstResult(from);
		query.setMaxResults(to - from);
		List<Solution> solutions = query.list();
		tx.commit();
		return solutions;
	}
	
	public long countByPid(int pid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select count(*) from " + Solution.class.getName() + " where pid=?");
		query.setInteger(0, pid);
		long num = (long) query.iterate().next();
		tx.commit();
		return num;
	}
	
	public List<Solution> findInRangeByUid(int from, int to, int uid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from " + Solution.class.getName() + " where uid=? order by ssbmttime desc");
		query.setInteger(0, uid);
		query.setFirstResult(from);
		query.setMaxResults(to - from);
		List<Solution> solutions = query.list();
		tx.commit();
		return solutions;
	}
	
	public long countByUid(int uid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select count(*) from " + Solution.class.getName() + " where uid=?");
		query.setInteger(0, uid);
		long num = (long) query.iterate().next();
		tx.commit();
		return num;
	}

	public List<Solution> findInRangeByBothId(int from, int to, int uid, int pid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from " + Solution.class.getName() + " where uid=? and pid=? order by ssbmttime desc");
		query.setInteger(0, uid);
		query.setInteger(1, pid);
		query.setFirstResult(from);
		query.setMaxResults(to - from);
		List<Solution> solutions = query.list();
		tx.commit();
		return solutions;
	}
}
