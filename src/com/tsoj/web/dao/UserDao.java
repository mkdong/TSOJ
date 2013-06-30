package com.tsoj.web.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.tsoj.web.entity.User;

@Repository("UserDao")
public class UserDao extends Dao<User>{
	
	public User findById(String uid) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.get(User.class, uid);
		tx.commit();
		return user;
	}
	
	public List<User> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Users");
		List<User> users = query.list();
		tx.commit();
		return users;
	}
}