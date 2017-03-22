package com.lq.linkcheer.system.web.controller;

import java.util.Date;
import java.util.List;

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
import com.lq.linkcheer.system.model.entity.News;
import com.lq.linkcheer.system.model.entity.Quarters;
import com.lq.linkcheer.system.model.entity.Recruit;
import com.lq.linkcheer.system.service.interfaces.IQuartersService;
import com.lq.linkcheer.system.service.interfaces.IRecruitService;


@Controller
@RequestMapping(value = "/system/recruit")
public class RecruitController {
	
	@Autowired private IRecruitService recruitService;
	@Autowired private IQuartersService quartersService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws ServletException {
			
        binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	//保存招聘信息
	@RequestMapping(value="/saver")
	@ResponseBody
	public String saveOrUpdateR(HttpServletRequest request,HttpServletResponse response, Recruit recruit){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		if(StringUtils.isBlank(recruit.getQuarters_content())){
			result.setData("");
			result.setMsg("内容为空！");
			result.setStatus("0");
			return JsonUtil.fromObject(result);
		}
		Date date = new Date();
		
		recruit.setTime(date);
		
		try {
			recruitService.saveOrUpdate(recruit);
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
	public String getRecruitList(HttpServletRequest request,HttpServletResponse response, Recruit recruit,Date startTime, Date endTime,Pager pager){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		PageDto pagedto = new PageDto();
		try {
			pagedto.setRows(recruitService.getRecruitList(recruit, startTime, endTime, pager));
			pagedto.setTotal(recruitService.getRecruitCount(recruit, startTime, endTime));
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
	
	
	//通过id获取Recruit
	@RequestMapping(value="/getone")
	@ResponseBody
	public String getOneById(HttpServletRequest request,HttpServletResponse response, String id){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		if(StringUtils.isBlank(id)){
			result.setData("");
			result.setMsg("内容为空！");
			result.setStatus("0");
			return JsonUtil.fromObject(result);
		}
		
		try {
			Recruit recruit = recruitService.getOneById(id);
			
			result.setData(recruit);
			if(recruit == null){
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
	
	//改变是否发布状态
	@RequestMapping(value="/ispublish")
	@ResponseBody
	public String updateIsPublishById(HttpServletRequest request,HttpServletResponse response, String id, String ispublish){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		if(StringUtils.isBlank(id)||StringUtils.isBlank(ispublish)){
			result.setData("");
			result.setMsg("内容为空！");
			result.setStatus("0");
			return JsonUtil.fromObject(result);
		}
		
		try {
			recruitService.updateIsPublishById(id, ispublish);
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
	
	//改变是否发布状态
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
			recruitService.delOneById(id);
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
	
	
	
	
	
		
		
	
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	//保存岗位信息
	@RequestMapping(value="/saveq")
	@ResponseBody
	public String saveOrUpdateQ(HttpServletRequest request,HttpServletResponse response, Quarters quarters){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		if(StringUtils.isBlank(quarters.getQuarters_name())){
			result.setData("");
			result.setMsg("内容为空！");
			result.setStatus("0");
			return JsonUtil.fromObject(result);
		}
		
		try {
			quartersService.saveOrUpdate(quarters);
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
	
	
	
	//获取岗位信息
	@RequestMapping(value="/listq")
	@ResponseBody
	public String getListQ(HttpServletRequest request,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		
		try {
			List<Quarters> list = quartersService.getQuartersList();
			result.setData(list);
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
}
