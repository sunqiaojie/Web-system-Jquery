package com.lq.linkcheer.system.model.dao.implement;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lq.linkcheer.system.model.dao.interfaces.INewsHome;
import com.lq.linkcheer.system.model.entity.News;

@Repository
public class NewsHome implements INewsHome {

	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void saveOrUpdate(News news) {

		this.sessionFactory.getCurrentSession().saveOrUpdate(news);

	}

	@Override
	public List<News> getNewsList(News news) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<News> getNewsList(News news, Date startTime, Date endTime, Integer start, Integer end) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM t_common_news a")
		  .append(" WHERE 1 = 1");
		if(StringUtils.isNotBlank(news.getTitle())){
			sb.append(" AND a.title like :title");
		}
		if(StringUtils.isNotBlank(news.getContent())){
			sb.append(" AND a.content like :content");
		}
		if(StringUtils.isNotBlank(news.getPublisher())){
			sb.append(" AND a.publisher like :publisher");
		}
		if(startTime != null){
			sb.append(" AND a.time >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.time <= :endTime");
		}
		sb.append(" ORDER BY time DESC");
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(news.getTitle())){
			query.setParameter("title", "%"+news.getTitle()+"%");
		}
		if(StringUtils.isNotBlank(news.getContent())){
			query.setParameter("content", "%"+news.getContent()+"%");
		}
		if(StringUtils.isNotBlank(news.getPublisher())){
			query.setParameter("publisher", "%"+news.getPublisher()+"%");
		}
		if(startTime != null){
			query.setParameter("startTime", startTime);
		}
		if(endTime != null){
			query.setParameter("endTime", endTime);
		}
		if(start != null && end != null){
			query.setFirstResult(start);
			query.setMaxResults(end - start);
		}
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		@SuppressWarnings("unchecked")
		List<News> list = query.list();
		
		return list;
	}
	
	@Override
	public long getNewsCount(News news, Date startTime, Date endTime) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) FROM t_common_news a")
		  .append(" WHERE 1 = 1");
		if(StringUtils.isNotBlank(news.getTitle())){
			sb.append(" AND a.title like :title");
		}
		if(StringUtils.isNotBlank(news.getContent())){
			sb.append(" AND a.content like :content");
		}
		if(StringUtils.isNotBlank(news.getPublisher())){
			sb.append(" AND a.publisher like :publisher");
		}
		if(startTime != null){
			sb.append(" AND a.time >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.time <= :endTime");
		}
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(news.getTitle())){
			query.setParameter("title", "%"+news.getTitle()+"%");
		}
		if(StringUtils.isNotBlank(news.getContent())){
			query.setParameter("content", "%"+news.getContent()+"%");
		}
		if(StringUtils.isNotBlank(news.getPublisher())){
			query.setParameter("publisher", "%"+news.getPublisher()+"%");
		}
		if(startTime != null){
			query.setParameter("startTime", startTime);
		}
		if(endTime != null){
			query.setParameter("endTime", endTime);
		}
		
		long count = ((Number) query.uniqueResult()).longValue();
		
		return count;
	}
	
	@Override
	public void delOneById(String id) {
		
		String hql = "DELETE News as n where n.id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	    query.setParameter("id", id);
	    query.executeUpdate();
	}
	
	

	@Override
	public News getOneById(String id) {
		
		String hql = "FROM News as n where n.id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	    query.setParameter("id", id);
	    News news = (News)query.uniqueResult();
		return news;
	}
}
