package com.lq.linkcheer.system.model.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.linkcheer.system.model.entity.News;

public interface INewsHome {

	
	/**
	 * 
	 * 保存更新新闻
	 */
	void saveOrUpdate(News news);
	
	/**
	 * 获取所有新闻
	 */
	List<News> getNewsList(News news);

	List<News> getNewsList(News news, Date startTime, Date endTime,
			Integer start, Integer end);

	long getNewsCount(News news, Date startTime, Date endTime);

	void delOneById(String id);

	News getOneById(String id);

}
