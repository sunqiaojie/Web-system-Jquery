package com.lq.linkcheer.system.service.implement;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.linkcheer.system.model.dao.interfaces.INoticeHome;
import com.lq.linkcheer.system.model.entity.Notice;
import com.lq.linkcheer.system.service.interfaces.INoticeService;


@Service
@Transactional
public class NoticeService implements INoticeService {
	
	@Autowired INoticeHome noticeHome;
	

	@Override
	public void saveOrUpdate(Notice notice) {
		
		noticeHome.saveOrUpdate(notice);
		
	}

	@Override
	public List<Notice> getNoticeList(Notice notice, Date startTime,
			Date endTime, Pager pager) {
		if(pager != null){
			Integer start = (pager.getPage()-1) * pager.getRows();
			Integer end = pager.getPage() * pager.getRows();
			return noticeHome.getNoticeList(notice, startTime, endTime, start, end);
		}else{
			return noticeHome.getNoticeList(notice, startTime, endTime, null, null);
		}
	}

	@Override
	public long getNoticeCount(Notice notice, Date startTime, Date endTime) {
		
		return noticeHome.getNoticeCount(notice, startTime, endTime);
	}

	@Override
	public void delOneById(String id) {
		
		noticeHome.delOneById(id);
		
	}

	@Override
	public Notice getOneById(String id) {

		Notice notice = noticeHome.getOneById(id);
		return notice;
	}

	@Override
	public Notice getOneFirst() {
		Notice notice = noticeHome.getOneFirst();
		return notice;
	}

}
