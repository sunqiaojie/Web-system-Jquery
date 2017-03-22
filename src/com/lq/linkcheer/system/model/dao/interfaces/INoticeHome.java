package com.lq.linkcheer.system.model.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.linkcheer.system.model.entity.Notice;

public interface INoticeHome {
	
	void saveOrUpdate(Notice notice);
	
	List<Notice> getNoticeList(Notice notice, Date startTime, Date endTime, Integer start, Integer end);
	
	long getNoticeCount(Notice notice, Date startTime, Date endTime);
	
	void delOneById(String id);
	
	Notice getOneById(String id);

	Notice getOneFirst();
	

}
