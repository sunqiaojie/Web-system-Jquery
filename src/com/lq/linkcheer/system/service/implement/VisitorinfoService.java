package com.lq.linkcheer.system.service.implement;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.dao.interfaces.IVisitorinfoHome;
import com.lq.linkcheer.system.model.entity.Visitorinfo;
import com.lq.linkcheer.system.service.interfaces.IVisitorinfoService;

@Service
@Transactional
public class VisitorinfoService implements IVisitorinfoService {
	
	@Autowired private IVisitorinfoHome visitorinfoHome;
	

	@Override
	public void saveOrUpdate(Visitorinfo visitorinfo) {
		
		visitorinfoHome.saveOrUpdate(visitorinfo);

	}

	@Override
	public List<Visitorinfo> getVisitorinfoList(Visitorinfo visitorinfo,
			Date startTime, Date endTime, Pager pager) {
		if(pager != null){
			Integer start = (pager.getPage()-1) * pager.getRows();
			Integer end = pager.getPage() * pager.getRows();
			return visitorinfoHome.getVisitorinfoList(visitorinfo, startTime, endTime, start, end);
		}else{
			return visitorinfoHome.getVisitorinfoList(visitorinfo, startTime, endTime, null, null);
		}
		
	}

	@Override
	public long getVisitorinfoCount(Visitorinfo visitorinfo, Date startTime,
			Date endTime) {
		return visitorinfoHome.getVisitorinfoCount(visitorinfo, startTime, endTime);
	}

	@Override
	public void delOneById(String id) {
		
		visitorinfoHome.delOneById(id);

	}

	@Override
	public Visitorinfo getOneById(String id) {
		
		Visitorinfo visitorinfo = visitorinfoHome.getOneById(id);
		
		return visitorinfo;
	}

}
