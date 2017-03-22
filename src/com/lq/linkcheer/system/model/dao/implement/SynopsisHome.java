package com.lq.linkcheer.system.model.dao.implement;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lq.linkcheer.system.model.dao.interfaces.ISynopsisHome;
import com.lq.linkcheer.system.model.entity.Synopsis;

@Repository
public class SynopsisHome implements ISynopsisHome {
	

	@Autowired 
	private SessionFactory sessionFactory; 
	
	@Override
	public void saveOrUpdate(Synopsis synopsis) {

		this.sessionFactory.getCurrentSession().saveOrUpdate(synopsis);
		
	}

	@Override
	public Synopsis getPublishSynopsis() {
		String hql = "from Synopsis";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Synopsis synopsis = (Synopsis)query.uniqueResult();
		return synopsis;
		
	}
	
	
	
	/*@Override
	public List<Map<String, Object>> getSynopsis(Synopsis synopsis, Date startTime,
			Date endTime, Integer start, Integer end) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT *")
		  .append(" FROM Synopsis a WHERE 1=1");
		if(StringUtils.isNotBlank(synopsis.getTitle())){
			sb.append(" AND a.title like :title");
		}
		if(StringUtils.isNotBlank(synopsis.getPublisher())){
			sb.append(" AND a.publisher like :publisher");
		}
		if(startTime != null){
			sb.append(" AND a.ctsj >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.ctsj <= :endTime");
		}
		sb.append(" ORDER BY a.time DESC");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(synopsis.getTitle())){
			query.setParameter("title", "%"+synopsis.getTitle()+"%");
		}
		if(StringUtils.isNotBlank(synopsis.getPublisher())){
			query.setParameter("publisher", "%"+synopsis.getPublisher()+"%");
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
		
		//query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = query.list();
		
		return list;
	}
	
	
	
	
	
	

	@Override
	public long getSynopsisCount(Synopsis synopsis, Date startTime, Date endTime) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT count(*)")
		  .append(" FROM Synopsis a WHERE 1=1");
		if(StringUtils.isNotBlank(synopsis.getTitle())){
			sb.append(" AND a.title like :title");
		}
		if(StringUtils.isNotBlank(synopsis.getPublisher())){
			sb.append(" AND a.publisher like :publisher");
		}
		if(startTime != null){
			sb.append(" AND a.ctsj >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.ctsj <= :endTime");
		}
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(synopsis.getTitle())){
			query.setParameter("title", "%"+synopsis.getTitle()+"%");
		}
		if(StringUtils.isNotBlank(synopsis.getPublisher())){
			query.setParameter("publisher", "%"+synopsis.getPublisher()+"%");
		}
		if(startTime != null){
			query.setParameter("startTime", startTime);
		}
		if(endTime != null){
			query.setParameter("endTime", endTime);
		}
		
		long count = ((BigDecimal) query.uniqueResult()).longValue();
		
		return count;
	}*/
	
	
	

}
