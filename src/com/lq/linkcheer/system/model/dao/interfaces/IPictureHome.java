package com.lq.linkcheer.system.model.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.lq.linkcheer.system.model.entity.Picture;

public interface IPictureHome {
	
	void saveOrUpdate(Picture picture);
	
	List<Picture> getPictureList(Picture picture, Date startTime, Date endTime, Integer start, Integer end);
	
	long getPictureCount(Picture picture, Date startTime, Date endTime);
	
	void delOneById(String id);

	Picture getPicture(String name, String path, String type);

	void del(Picture picture);
	
	Picture getOne(String id); 

}
