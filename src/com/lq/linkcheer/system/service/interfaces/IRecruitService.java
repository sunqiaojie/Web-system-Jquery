package com.lq.linkcheer.system.service.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.entity.Recruit;

public interface IRecruitService {
	
	void saveOrUpdate(Recruit recruit);

	List<Recruit> getRecruitList(Recruit recruit, Date startTime, Date endTime,
			Pager pager);

	long getRecruitCount(Recruit recruit, Date startTime, Date endTime);

	void updateIsPublishById(String id, String ispublish);

	Recruit getOneById(String id);

	void delOneById(String id);
	
	List<Recruit> getAllRecruit();

}
