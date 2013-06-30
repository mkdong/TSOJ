package com.tsoj.web.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class Dao<T> {
	@Resource(name="sessionFactory")
	protected SessionFactory sessionFactory;

	public void save(T t) {
		Session session = sessionFactory.getCurrentSession();  
        Transaction tx = session.beginTransaction();
        session.save(t);
        tx.commit();
	}
	
	public void delete(T t) {
		Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(t);
        tx.commit();
	}
	
	public void update(T t) {
		Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(t);
        tx.commit();
	}
}
