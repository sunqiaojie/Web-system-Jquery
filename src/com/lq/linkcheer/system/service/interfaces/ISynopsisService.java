package com.lq.linkcheer.system.service.interfaces;

import com.lq.linkcheer.system.model.entity.Synopsis;

public interface ISynopsisService {

	void saveOrUpdate(Synopsis synopsis);
	
	Synopsis getPublishSynopsis();
}
