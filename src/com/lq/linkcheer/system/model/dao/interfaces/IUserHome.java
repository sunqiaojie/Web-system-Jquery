package com.lq.linkcheer.system.model.dao.interfaces;

import com.lq.linkcheer.system.model.entity.User;


public interface IUserHome {
	
	/**
	 * 
	 * 保存更新用户
	 */
	void saveOrUpdate(User user);
	
	/**
	 * 
	 * 查询某个用户
	 * @param 用户名 
	 */
	User getOne(String username);

}
