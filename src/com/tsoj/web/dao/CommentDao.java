package com.tsoj.web.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.tsoj.web.entity.Comment;


@Repository("CommentDao")
public class CommentDao extends Dao<Comment> {
	public Comment findById(int cmid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Comment comment = (Comment) session.get(Comment.class, cmid);
		tx.commit();
		return comment;
	}
	
	public List<Comment> findAllByPid(int pid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Comments where pid=?");
		query.setInteger(0, pid);
		List<Comment> comments = query.list();
		tx.commit();
		return comments;
	}
	
	public List<Comment> findInRangeByPid(int from, int to, int pid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from " + Comment.class.getName() + " where pid=? order by cmtime");
		query.setInteger(0, pid);
		query.setFirstResult(from);
		query.setMaxResults(to - from);
		List<Comment> comments = query.list();
		tx.commit();
		return comments;
	}
	
	public long countByPid(int pid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select count(*) from " + Comment.class.getName() + " where pid=?");
		query.setInteger(0, pid);
		long num = (long) query.iterate().next();
		tx.commit();
		return num;
	}

}
