package com.lq.linkcheer.system.service.implement;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.dao.interfaces.INewsHome;
import com.lq.linkcheer.system.model.entity.News;
import com.lq.linkcheer.system.service.interfaces.INewsService;


@Service
@Transactional
public class NewsService implements INewsService {

	@Autowired private INewsHome newshome;
	
	@Override
	public void saveOrUpdate(News news) {
		newshome.saveOrUpdate(news);
	}

	
	@Override
	public List<News> getNewsList(News news, Date startTime,
			Date endTime, Pager pager) {
		if(pager != null){
			Integer start = (pager.getPage()-1) * pager.getRows();
			Integer end = pager.getPage() * pager.getRows();
			return newshome.getNewsList(news, startTime, endTime, start, end);
		}else{
			return newshome.getNewsList(news, startTime, endTime, null, null);
		}
	}

	@Override
	public long getNewsCount(News news, Date startTime, Date endTime) {
		return newshome.getNewsCount(news, startTime, endTime);
	}


	@Override
	public void delOneById(String id) {
		
		newshome.delOneById(id);
		
	}


	@Override
	public News getOneById(String id) {
		
		News news = newshome.getOneById(id);
		return news;
	}
	
	
}
