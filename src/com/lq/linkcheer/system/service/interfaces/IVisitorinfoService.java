package com.lq.linkcheer.system.service.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.entity.Visitorinfo;

public interface IVisitorinfoService {

	
	void saveOrUpdate(Visitorinfo visitorinfo);
	
	List<Visitorinfo> getVisitorinfoList(Visitorinfo visitorinfo, Date startTime, Date endTime,
			Pager pager);

	long getVisitorinfoCount(Visitorinfo visitorinfo, Date startTime, Date endTime);

	void delOneById(String id);

	Visitorinfo getOneById(String id);
}
