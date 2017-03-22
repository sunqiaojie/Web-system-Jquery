package com.lq.linkcheer.system.service.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.entity.Picture;

public interface IPictureService {
	
	void saveOrUpdate(Picture picture);
	
	List<Picture> getPictureList(Picture picture, Date startTime, Date endTime, Pager pager);
	
	long getPictureCount(Picture picture, Date startTime, Date endTime);
	
	void delOneById(String id);
	
	Picture getPicture(String name, String path, String type);

	void del(Picture picture);

	boolean picIsExist(String name, String path, String type);

	Picture getOne(String id);

}
