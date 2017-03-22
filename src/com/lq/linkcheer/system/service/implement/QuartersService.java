package com.lq.linkcheer.system.service.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lq.linkcheer.system.model.dao.interfaces.IQuartersHome;
import com.lq.linkcheer.system.model.entity.Quarters;
import com.lq.linkcheer.system.service.interfaces.IQuartersService;

@Service
@Transactional
public class QuartersService implements IQuartersService {
	
	@Autowired private IQuartersHome quartersHome;
	

	@Override
	public void saveOrUpdate(Quarters quarters) {

		quartersHome.saveOrUpdate(quarters);

	}

	@Override
	public List<Quarters> getQuartersList() {
		
		List<Quarters> list = quartersHome.getQuartersList();
		return list;
	}

	@Override
	public Quarters getOneById(String id) {
		
		Quarters quarters = quartersHome.getOneById(id);
		return quarters;
	}

}
