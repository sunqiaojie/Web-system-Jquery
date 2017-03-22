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

import com.lq.linkcheer.system.model.dao.interfaces.IVisitorinfoHome;
import com.lq.linkcheer.system.model.entity.News;
import com.lq.linkcheer.system.model.entity.Visitorinfo;


@Repository
public class VisitorinfoHome implements IVisitorinfoHome {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void saveOrUpdate(Visitorinfo visitorinfo) {

		this.sessionFactory.getCurrentSession().saveOrUpdate(visitorinfo);

	}
	@Override
	public List<Visitorinfo> getVisitorinfoList(Visitorinfo visitorinfo,
			Date startTime, Date endTime, Integer start, Integer end) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM t_common_visitorinfo a")
		  .append(" WHERE 1 = 1");
		if(StringUtils.isNotBlank(visitorinfo.getName())){
			sb.append(" AND a.name like :name");
		}
		if(StringUtils.isNotBlank(visitorinfo.getJob())){
			sb.append(" AND a.job like :job");
		}
		if(StringUtils.isNotBlank(visitorinfo.getTelephone())){
			sb.append(" AND a.telephone like :telephone");
		}
		if(startTime != null){
			sb.append(" AND a.time >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.time <= :endTime");
		}
		sb.append(" ORDER BY time DESC");
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(visitorinfo.getName())){
			query.setParameter("name", "%"+visitorinfo.getName()+"%");
		}
		if(StringUtils.isNotBlank(visitorinfo.getJob())){
			query.setParameter("job", "%"+visitorinfo.getJob()+"%");
		}
		if(StringUtils.isNotBlank(visitorinfo.getTelephone())){
			query.setParameter("telephone", "%"+visitorinfo.getTelephone()+"%");
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
		List<Visitorinfo> list = query.list();
		
		return list;
	}

	@Override
	public long getVisitorinfoCount(Visitorinfo visitorinfo, Date startTime,
			Date endTime) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) FROM t_common_visitorinfo a")
		  .append(" WHERE 1 = 1");
		if(StringUtils.isNotBlank(visitorinfo.getName())){
			sb.append(" AND a.name like :name");
		}
		if(StringUtils.isNotBlank(visitorinfo.getJob())){
			sb.append(" AND a.job like :job");
		}
		if(StringUtils.isNotBlank(visitorinfo.getTelephone())){
			sb.append(" AND a.telephone like :telephone");
		}
		if(startTime != null){
			sb.append(" AND a.time >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.time <= :endTime");
		}
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(visitorinfo.getName())){
			query.setParameter("name", "%"+visitorinfo.getName()+"%");
		}
		if(StringUtils.isNotBlank(visitorinfo.getJob())){
			query.setParameter("job", "%"+visitorinfo.getJob()+"%");
		}
		if(StringUtils.isNotBlank(visitorinfo.getTelephone())){
			query.setParameter("telephone", "%"+visitorinfo.getTelephone()+"%");
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
		String hql = "DELETE Visitorinfo as n where n.id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	    query.setParameter("id", id);
	    query.executeUpdate();
	}

	
	@Override
	public Visitorinfo getOneById(String id) {
		
		String hql = "FROM Visitorinfo as n where n.id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	    query.setParameter("id", id);
	    Visitorinfo visitorinfo = (Visitorinfo)query.uniqueResult();
		return visitorinfo;
	}
}
