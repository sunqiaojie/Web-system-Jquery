package com.lq.linkcheer.system.model.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.linkcheer.system.model.entity.Log;


public interface ILogHome {
	
	/** 
	 * 
	 *  保存日志
	 * 
	 */
	void saveOrUpdate(Log log);
	
	/** 
	 * 
	 *   分页查询日志
	 * 
	 */
	List<Object[]> getList(Date sTime,Date eTime,int start,int end);
	
	/** 
	 * 
	 *   查询日志总数
	 * 
	 */
	int getCount(Date sTime,Date eTime);
	
	/** 
	 * 
	 *   查看单条日志
	 * 
	 */
	Log getOne(String id);
}
