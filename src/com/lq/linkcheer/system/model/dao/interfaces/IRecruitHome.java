package com.lq.linkcheer.system.model.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.linkcheer.system.model.entity.News;
import com.lq.linkcheer.system.model.entity.Recruit;

public interface IRecruitHome {
	
	void saveOrUpdate(Recruit recruit);

	List<Recruit> getRecruitList(Recruit recruit, Date startTime, Date endTime,
			Integer start, Integer end);

	long getRecruitCount(Recruit recruit, Date startTime, Date endTime);

	void updateIsPublishById(String id, String ispublish);

	Recruit getOneById(String id);

	void delOneById(String id);

	List<Recruit> getAllRecruit();

}
