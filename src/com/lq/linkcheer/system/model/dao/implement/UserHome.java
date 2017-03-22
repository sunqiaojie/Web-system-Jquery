package com.lq.linkcheer.system.model.dao.implement;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lq.linkcheer.system.model.dao.interfaces.IUserHome;
import com.lq.linkcheer.system.model.entity.User;

@Repository
public class UserHome implements IUserHome {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveOrUpdate(User user) {
		
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
		
	}

	@Override
	public User getOne(String username) {
		
		String hql = "from User where username = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0,username);
		User user = (User)query.uniqueResult();
		return user;
	}
	
}
