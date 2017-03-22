package com.lq.linkcheer.system.service.implement;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.dao.interfaces.ILogHome;
import com.lq.linkcheer.system.model.entity.Log;
import com.lq.linkcheer.system.service.interfaces.ILogService;


@Service
@Transactional
public class LogService implements ILogService{

	@Autowired
	private ILogHome logHome;
	
	@Override
	public List<Object[]> getList(Date sTime, Date eTime,Pager pager ) {
		int start = (pager.getPage() - 1) * pager.getRows();
		int end = pager.getPage() * pager.getRows();
		return logHome.getList(sTime, eTime,start,end);
	}

	@Override
	public Log getOne(String id) {

		return logHome.getOne(id);
	}

	@Override
	public int getCount(Date sTime, Date eTime) {

		return logHome.getCount(sTime, eTime);
	}

	@Override
	public void saveOrUpdate(Log log) {

		logHome.saveOrUpdate(log); 
	}

}
