package com.lq.linkcheer.system.service.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.entity.News;

public interface INewsService {

	void saveOrUpdate(News news);

	long getNewsCount(News news, Date startTime, Date endTime);

	List<News> getNewsList(News news, Date startTime, Date endTime, Pager pager);
	
	void delOneById(String id);
	
	News getOneById(String id);
}
