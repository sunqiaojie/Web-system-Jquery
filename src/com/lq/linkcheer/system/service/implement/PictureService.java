package com.lq.linkcheer.system.service.implement;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.dao.interfaces.IPictureHome;
import com.lq.linkcheer.system.model.entity.Picture;
import com.lq.linkcheer.system.service.interfaces.IPictureService;

@Service
@Transactional
public class PictureService implements IPictureService {

	@Autowired private IPictureHome pictureHome;
	
	@Override
	public void saveOrUpdate(Picture picture) {

		pictureHome.saveOrUpdate(picture);

	}

	@Override
	public List<Picture> getPictureList(Picture picture, Date startTime,
			Date endTime, Pager pager) {
		if(pager != null){
			Integer start = (pager.getPage()-1) * pager.getRows();
			Integer end = pager.getPage() * pager.getRows();
			return pictureHome.getPictureList(picture, startTime, endTime, start, end);
		}else{
			return pictureHome.getPictureList(picture, startTime, endTime, null, null);
		}
	}

	@Override
	public long getPictureCount(Picture picture, Date startTime, Date endTime) {

		return pictureHome.getPictureCount(picture, startTime, endTime);
	}

	@Override
	public void delOneById(String id) {

		pictureHome.delOneById(id);

	}

	@Override
	public Picture getPicture(String name, String path, String type) {
		
		Picture picture = pictureHome.getPicture(name, path, type);
		return picture;
	}

	@Override
	public void del(Picture picture) {
		
		pictureHome.del(picture);
		
	}
	
	@Override
	public boolean picIsExist(String name, String path, String type){
		
		if(getPicture(name, path, type)==null){
			return true;
		}else{
			return false;
		}
			
	}

	public Picture getOne(String id){
		
		return  pictureHome.getOne(id);
	}
}
