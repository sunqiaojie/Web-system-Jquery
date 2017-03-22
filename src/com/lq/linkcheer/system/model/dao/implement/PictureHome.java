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

import com.lq.linkcheer.system.model.dao.interfaces.IPictureHome;
import com.lq.linkcheer.system.model.entity.Picture;

@Repository
public class PictureHome implements IPictureHome {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public void saveOrUpdate(Picture picture) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(picture);

	}

	@Override
	public List<Picture> getPictureList(Picture picture, Date startTime,
			Date endTime, Integer start, Integer end) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM t_common_picture a")
		  .append(" WHERE 1 = 1");
		if(StringUtils.isNotBlank(picture.getName())){
			sb.append(" AND a.name like :name");
		}
		if(StringUtils.isNotBlank(picture.getActivity_name())){
			sb.append(" AND a.activity_name like :activity_name");
		}
		if(StringUtils.isNotBlank(picture.getIsshow())){
			sb.append(" AND a.isshow = :isshow");
		}
		if(startTime != null){
			sb.append(" AND a.time >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.time <= :endTime");
		}
		sb.append(" ORDER BY time DESC");
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(picture.getName())){
			query.setParameter("name", "%"+picture.getName()+"%");
		}
		if(StringUtils.isNotBlank(picture.getActivity_name())){
			query.setParameter("activity_name", "%"+picture.getActivity_name()+"%");
		}
		if(StringUtils.isNotBlank(picture.getIsshow())){
			query.setParameter("isshow", "%"+picture.getIsshow()+"%");
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
		List<Picture> list = query.list();
		
		return list;
	}

	@Override
	public long getPictureCount(Picture picture, Date startTime, Date endTime) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) FROM t_common_picture a")
		  .append(" WHERE 1 = 1");
		if(StringUtils.isNotBlank(picture.getName())){
			sb.append(" AND a.name like :name");
		}
		if(StringUtils.isNotBlank(picture.getActivity_name())){
			sb.append(" AND a.activity_name like :activity_name");
		}
		if(StringUtils.isNotBlank(picture.getIsshow())){
			sb.append(" AND a.isshow = :isshow");
		}
		if(startTime != null){
			sb.append(" AND a.time >= :startTime");
		}
		if(endTime != null){
			sb.append(" AND a.time <= :endTime");
		}
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		
		if(StringUtils.isNotBlank(picture.getName())){
			query.setParameter("name", "%"+picture.getName()+"%");
		}
		if(StringUtils.isNotBlank(picture.getActivity_name())){
			query.setParameter("activity_name", "%"+picture.getActivity_name()+"%");
		}
		if(StringUtils.isNotBlank(picture.getIsshow())){
			query.setParameter("isshow", "%"+picture.getIsshow()+"%");
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
		
		String hql = "DELETE Picture as p where p.id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	    query.setParameter("id", id);
	    query.executeUpdate();

	}
	
	public Picture getOne(String id) {
		String hql = "from Picture where id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		Picture picture = (Picture) query.uniqueResult();
		return picture;
	}

	
	@Override
	public Picture getPicture(String name, String path,String type) {

		String hql = "from Picture where name = :name and path = :path and type = :type";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("name", name);
		query.setParameter("path", path);
		query.setParameter("type", type);
		Picture picture = (Picture) query.uniqueResult();
		return picture;
	}
	
	@Override
	public void del(Picture picture) {
		
		this.sessionFactory.getCurrentSession().delete(picture);
		
	}
	
}
