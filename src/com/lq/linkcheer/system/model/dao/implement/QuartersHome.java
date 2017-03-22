package com.lq.linkcheer.system.model.dao.implement;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lq.linkcheer.system.model.dao.interfaces.IQuartersHome;
import com.lq.linkcheer.system.model.entity.Quarters;

@Repository
public class QuartersHome implements IQuartersHome {
	
	@Autowired private SessionFactory sessionFactory;
	

	@Override
	public void saveOrUpdate(Quarters quarters) {

		this.sessionFactory.getCurrentSession().saveOrUpdate(quarters);

	}

	@Override
	public List<Quarters> getQuartersList() {
		
		String hql = "FROM Quarters as a WHERE a.status = '1'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		//query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Quarters> list = query.list();
		return list;
	}

	@Override
	public Quarters getOneById(String id) {
		
		String hql = "FROM Quarters as a WHERE a.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, id);
		Quarters quarters = (Quarters)query.uniqueResult();
		return quarters;
	}

}
