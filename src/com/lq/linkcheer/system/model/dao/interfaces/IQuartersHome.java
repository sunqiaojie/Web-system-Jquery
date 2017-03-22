package com.lq.linkcheer.system.model.dao.interfaces;

import java.util.List;

import com.lq.linkcheer.system.model.entity.Quarters;

public interface IQuartersHome {

	
	void saveOrUpdate(Quarters quarters);
	
	List<Quarters> getQuartersList();
	
	Quarters getOneById(String id); 
}
