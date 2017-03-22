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

import com.lq.linkcheer.system.model.dao.interfaces.IRecruitHome;
import com.lq.linkcheer.system.model.entity.Recruit;

@Repository
public class RecruitHome implements IRecruitHome {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public void saveOrUpdate(Recruit recruit) {

		this.sessionFactory.getCurrentSession().saveOrUpdate(recruit);
	}

	@Override
	public List<Recruit> getRecruitList(Recruit recruit, Date startTime, Date endTime, Integer start, Integer end) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM t_common_recruit a")
		  .append(" WHERE 1 = 1");
		if(StringUtils.isNotBlank(recruit.getQuarters_name())){
			sb.append(" AND a.quarters_name like :quarters_name");
		}
		if(StringUtils.isNotBlank(recruit.getQuarters_content())){
			sb.append(" AND a.quarters_content like :quarters_content");
		}
		if(StringUtils.isNotBlank(recruit.getIspublish())){
			sb.append(" AND a.ispublish like :ispublish");
		}
		if(startTime != null){
			sb.append(" AND a.time >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.time <= :endTime");
		}
		sb.append(" ORDER BY time DESC");
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(recruit.getQuarters_name())){
			query.setParameter("quarters_name", "%"+recruit.getQuarters_name()+"%");
		}
		if(StringUtils.isNotBlank(recruit.getQuarters_content())){
			query.setParameter("quarters_content", "%"+recruit.getQuarters_content()+"%");
		}
		if(StringUtils.isNotBlank(recruit.getIspublish())){
			query.setParameter("ispublish", "%"+recruit.getIspublish()+"%");
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
		List<Recruit> list = query.list();
		
		return list;
	}
	
	@Override
	public long getRecruitCount(Recruit recruit, Date startTime, Date endTime) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) FROM t_common_recruit a")
		  .append(" WHERE 1 = 1");
		if(StringUtils.isNotBlank(recruit.getQuarters_name())){
			sb.append(" AND a.quarters_name like :quarters_name");
		}
		if(StringUtils.isNotBlank(recruit.getQuarters_content())){
			sb.append(" AND a.quarters_content like :quarters_content");
		}
		if(StringUtils.isNotBlank(recruit.getIspublish())){
			sb.append(" AND a.ispublish like :ispublish");
		}
		if(startTime != null){
			sb.append(" AND a.time >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.time <= :endTime");
		}
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(recruit.getQuarters_name())){
			query.setParameter("quarters_name", "%"+recruit.getQuarters_name()+"%");
		}
		if(StringUtils.isNotBlank(recruit.getQuarters_content())){
			query.setParameter("quarters_content", "%"+recruit.getQuarters_content()+"%");
		}
		if(StringUtils.isNotBlank(recruit.getIspublish())){
			query.setParameter("ispublish", "%"+recruit.getIspublish()+"%");
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
	public void updateIsPublishById(String id, String ispublish){
		
		Query query = sessionFactory.getCurrentSession().createQuery("update Recruit as r set r.ispublish = :ispublish where r.id = :id");
		query.setParameter("ispublish", ispublish);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	@Override
	public Recruit getOneById(String id){
		
		String hql = "FROM Recruit where id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		Recruit recruit = (Recruit)query.uniqueResult();
		
		return recruit;
	}
	
	@Override
	public void delOneById(String id){
		
		String hql = "DELETE Recruit as r where r.id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	    query.setParameter("id", id);
	    query.executeUpdate();
		
	}
	
	
	@Override
	public List<Recruit> getAllRecruit(){
		String hql = "From Recruit as r WHERE r.ispublish = '1' order by time";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Recruit> list =  query.list();
		return list;
		
	}
	
}
