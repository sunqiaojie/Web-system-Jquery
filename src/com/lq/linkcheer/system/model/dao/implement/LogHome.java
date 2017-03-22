package com.lq.linkcheer.system.model.dao.implement;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lq.common.util.custom.SQLPagingUtil;
import com.lq.linkcheer.system.model.dao.interfaces.ILogHome;
import com.lq.linkcheer.system.model.entity.Log;


@Repository
public class LogHome implements ILogHome{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveOrUpdate(Log log) {
		
		this.sessionFactory.getCurrentSession().saveOrUpdate(log);
		
	}

	@Override
	public List<Object[]> getList(Date sTime, Date eTime,int start,int end) {
		String sql = "select l.id,to_char(l.logtime,'yyyy-MM-dd HH:mm:ss'),l.ip,l.menuno,l.content,u.name from t_common_log l,t_common_user u where l.manid = u.id";
		if(sTime != null){
			sql += " and logtime > "+sTime;
		}
		else{
			if(eTime != null){
				sql += " and logtime < "+eTime;
			}
		}
		sql = SQLPagingUtil.sQLPagingUtil(sql, start, end);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object[]> objsList = query.list();
		return objsList;
	}

	@Override
	public Log getOne(String id) {
		String hql = "from Log where id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, id);
		Log log = (Log) query.uniqueResult();
		return log;
	}

	@Override
	public int getCount(Date sTime, Date eTime) {
		String hql = "select count(*) from Log where 1 = 1";
		if(sTime != null){
			hql += " and logtime > "+sTime;
		}
		else{
			if(eTime != null){
				hql += " and logtime < "+eTime;
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Long count = (Long) query.uniqueResult();
		int c = count.intValue();
		return c;
	}

}
