package com.lq.linkcheer.system.model.dao.implement;

import java.util.Date;
import java.util.List;

import oracle.net.aso.q;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lq.linkcheer.system.model.dao.interfaces.INoticeHome;
import com.lq.linkcheer.system.model.entity.Notice;

@Repository
public class NoticeHome implements INoticeHome {
	
	@Autowired private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdate(Notice notice) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(notice);

	}

	@Override
	public List<Notice> getNoticeList(Notice notice, Date startTime,
			Date endTime, Integer start, Integer end) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM t_common_notice a")
		  .append(" WHERE 1 = 1");
		if(StringUtils.isNotBlank(notice.getTitle())){
			sb.append(" AND a.title like :title");
		}
		if(StringUtils.isNotBlank(notice.getContent())){
			sb.append(" AND a.content like :content");
		}
		if(startTime != null){
			sb.append(" AND a.time >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.time <= :endTime");
		}
		sb.append(" ORDER BY time DESC");
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(notice.getTitle())){
			query.setParameter("title", "%"+notice.getTitle()+"%");
		}
		if(StringUtils.isNotBlank(notice.getContent())){
			query.setParameter("content", "%"+notice.getContent()+"%");
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
		List<Notice> list = query.list();
		
		return list;
	}

	@Override
	public long getNoticeCount(Notice notice, Date startTime, Date endTime) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) FROM t_common_notice a")
		  .append(" WHERE 1 = 1");
		if(StringUtils.isNotBlank(notice.getTitle())){
			sb.append(" AND a.title like :title");
		}
		if(StringUtils.isNotBlank(notice.getContent())){
			sb.append(" AND a.content like :content");
		}
		if(startTime != null){
			sb.append(" AND a.time >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.time <= :endTime");
		}
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(notice.getTitle())){
			query.setParameter("title", "%"+notice.getTitle()+"%");
		}
		if(StringUtils.isNotBlank(notice.getContent())){
			query.setParameter("content", "%"+notice.getContent()+"%");
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
		
		String hql = "DELETE Notice as n where n.id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	    query.setParameter("id", id);
	    query.executeUpdate();
	}

	@Override
	public Notice getOneById(String id) {
		
		String hql = "FROM Notice as n where n.id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	    query.setParameter("id", id);
	    Notice notice = (Notice)query.uniqueResult();
		return notice;
	}

	@Override
	public Notice getOneFirst(){
		String hql = "FROM Notice order by time desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setMaxResults(1);
		Notice notice = (Notice)query.uniqueResult();
		return notice;
	}
	
}
