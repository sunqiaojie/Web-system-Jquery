package com.lq.linkcheer.system.web.controller;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lq.common.util.plugin.Result;
import com.lq.common.util.plugin.easyui.datagrid.PageDto;
import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.common.util.plugin.json.JsonUtil;
import com.lq.common.util.spring.DateEditor;
import com.lq.linkcheer.system.model.entity.Notice;
import com.lq.linkcheer.system.service.interfaces.INoticeService;


@Controller
@RequestMapping(value = "/system/notice")
public class NoticeController {
	
	@Autowired private INoticeService noticeService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws ServletException {
			
        binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	//保存通知信息
	@RequestMapping(value="/save")
	@ResponseBody
	public String saveOrUpdateR(HttpServletRequest request,HttpServletResponse response, Notice notice){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		if(StringUtils.isBlank(notice.getContent()) || StringUtils.isBlank(notice.getTitle())){
			result.setData("");
			result.setMsg("内容为空！");
			result.setStatus("0");
			return JsonUtil.fromObject(result);
		}
		Date date = new Date();
		
		notice.setTime(date);
		
		try {
			noticeService.saveOrUpdate(notice);
			result.setData("");
			result.setMsg("success");
			result.setStatus("1");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData("");
			result.setMsg("error");
			result.setStatus("0");
		}
		return JsonUtil.fromObject(result);
	}
	

	
	@RequestMapping(value="/list")
	@ResponseBody
	public String getNoticeList(HttpServletRequest request,HttpServletResponse response, Notice notice,Date startTime, Date endTime,Pager pager){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		PageDto pagedto = new PageDto();
		try {
			pagedto.setRows(noticeService.getNoticeList(notice, startTime, endTime, pager));
			pagedto.setTotal(noticeService.getNoticeCount(notice, startTime, endTime));
			result.setData(pagedto);
			result.setMsg("获取数据成功！");
			result.setStatus("1");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(e);
			result.setMsg("获取数据失败！");
			result.setStatus("0");
		}
		
		return JsonUtil.fromObject(result);
	}
	
	@RequestMapping(value="/del")
	@ResponseBody
	public String delOneByid(HttpServletRequest request,HttpServletResponse response, String id){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		if(StringUtils.isBlank(id)){
			result.setData("");
			result.setMsg("内容为空！");
			result.setStatus("0");
			return JsonUtil.fromObject(result);
		}
		
		try {
			noticeService.delOneById(id);
			result.setData("");
			result.setMsg("success");
			result.setStatus("1");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData("");
			result.setMsg("error");
			result.setStatus("0");
		}
		return JsonUtil.fromObject(result);
		
	}
	@RequestMapping(value="/getone")
	@ResponseBody
	public String getOneByid(HttpServletRequest request,HttpServletResponse response, String id){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		if(StringUtils.isBlank(id)){
			result.setData("");
			result.setMsg("内容为空！");
			result.setStatus("0");
			return JsonUtil.fromObject(result);
		}
		
		try {
			Notice notice = noticeService.getOneById(id);
			
			result.setData(notice);
			if(notice == null){
				result.setMsg("查询结果为空！");
				result.setStatus("0");
			}else{
				result.setMsg("success");
				result.setStatus("1");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setData("");
			result.setMsg("error");
			result.setStatus("0");
		}
		return JsonUtil.fromObject(result);
		
	}
	
}
