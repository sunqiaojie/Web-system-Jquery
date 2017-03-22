package com.lq.linkcheer.system.model.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.linkcheer.system.model.entity.Visitorinfo;

public interface IVisitorinfoHome {

	void saveOrUpdate(Visitorinfo visitorinfo);
	
	List<Visitorinfo> getVisitorinfoList(Visitorinfo visitorinfo, Date startTime, Date endTime,
			Integer start, Integer end);

	long getVisitorinfoCount(Visitorinfo visitorinfo, Date startTime, Date endTime);

	void delOneById(String id);

	Visitorinfo getOneById(String id);
	
}
