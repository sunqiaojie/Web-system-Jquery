package com.lq.linkcheer.system.service.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.entity.Notice;

public interface INoticeService {
	
	void saveOrUpdate(Notice notice);
	
	List<Notice> getNoticeList(Notice notice, Date startTime, Date endTime, Pager pager);
	
	long getNoticeCount(Notice notice, Date startTime, Date endTime);
	
	void delOneById(String id);
	
	Notice getOneById(String id);
	
	Notice getOneFirst();

}
