package com.lq.linkcheer.system.service.implement;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.dao.interfaces.IRecruitHome;
import com.lq.linkcheer.system.model.entity.News;
import com.lq.linkcheer.system.model.entity.Recruit;
import com.lq.linkcheer.system.service.interfaces.IRecruitService;

@Service
@Transactional
public class RecruitService implements IRecruitService {

	@Autowired private IRecruitHome recruitHome;
	
	@Override
	public void saveOrUpdate(Recruit recruit) {

		recruitHome.saveOrUpdate(recruit);
	}

	
	
	@Override
	public List<Recruit> getRecruitList(Recruit recruit, Date startTime,
			Date endTime, Pager pager) {
		if(pager != null){
			Integer start = (pager.getPage()-1) * pager.getRows();
			Integer end = pager.getPage() * pager.getRows();
			return recruitHome.getRecruitList(recruit, startTime, endTime, start, end);
		}else{
			return recruitHome.getRecruitList(recruit, startTime, endTime, null, null);
		}
	}

	@Override
	public long getRecruitCount(Recruit recruit, Date startTime, Date endTime) {
		return recruitHome.getRecruitCount(recruit, startTime, endTime);
	}
	
	

	@Override
	public void updateIsPublishById(String id, String ispublish){
		recruitHome.updateIsPublishById(id, ispublish);
	}
	
	@Override
	public Recruit getOneById(String id){
		
		Recruit recruit = recruitHome.getOneById(id);
		return recruit;
		
	}
	
	@Override
	public void delOneById(String id){
		recruitHome.delOneById(id);
	}



	@Override
	public List<Recruit> getAllRecruit() {

		List<Recruit> list = recruitHome.getAllRecruit();
		return list;
	}
}
