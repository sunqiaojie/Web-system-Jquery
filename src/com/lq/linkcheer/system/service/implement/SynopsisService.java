package com.lq.linkcheer.system.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lq.linkcheer.system.model.dao.interfaces.ISynopsisHome;
import com.lq.linkcheer.system.model.entity.Synopsis;
import com.lq.linkcheer.system.service.interfaces.ISynopsisService;

@Service
@Transactional
public class SynopsisService implements ISynopsisService {

	@Autowired private ISynopsisHome synopsisHome; 
	
	@Override
	public void saveOrUpdate(Synopsis synopsis) {
		
		synopsisHome.saveOrUpdate(synopsis);

	}

	@Override
	public Synopsis getPublishSynopsis() {
		
		Synopsis synopsis = synopsisHome.getPublishSynopsis();
		
		return synopsis;
	}

}
