package com.lq.linkcheer.system.service.interfaces;

import java.util.List;

import com.lq.linkcheer.system.model.entity.Quarters;

public interface IQuartersService {

	
	void saveOrUpdate(Quarters quarters);
	
	List<Quarters> getQuartersList();
	
	Quarters getOneById(String id);
}
