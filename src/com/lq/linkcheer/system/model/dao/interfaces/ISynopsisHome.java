package com.lq.linkcheer.system.model.dao.interfaces;

import com.lq.linkcheer.system.model.entity.Synopsis;

public interface ISynopsisHome {

	
	void saveOrUpdate(Synopsis synopsis);
	
	Synopsis getPublishSynopsis();
	
	/*long getSynopsisCount(Synopsis synopsis,Date startTime, Date endTime);

	List<Map<String, Object>> getSynopsis(Synopsis synopsis, Date startTime,
			Date endTime, Integer start, Integer end);*/
}
