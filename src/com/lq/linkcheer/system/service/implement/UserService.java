package com.lq.linkcheer.system.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lq.linkcheer.system.model.dao.interfaces.IUserHome;
import com.lq.linkcheer.system.model.entity.User;
import com.lq.linkcheer.system.service.interfaces.IUserService;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired private IUserHome userHome;
	
	@Override
	public void saveOrUpdate(User user) {

		userHome.saveOrUpdate(user);
	}

	@Override
	public User getOne(String username) {
		return userHome.getOne(username);
	}

}
