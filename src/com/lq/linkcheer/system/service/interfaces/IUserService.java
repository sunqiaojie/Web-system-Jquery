package com.lq.linkcheer.system.service.interfaces;

import com.lq.linkcheer.system.model.entity.User;

public interface IUserService {
	
	void saveOrUpdate(User user);
	
	User getOne(String username);

}
