package com.lq.linkcheer.system.service.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.entity.Log;


public interface ILogService {
	
	void saveOrUpdate(Log log);
	
	List<Object[]> getList(Date sTime,Date eTime,Pager pager);
	
	int getCount(Date sTime,Date eTime);
	
	Log getOne(String id);
}
