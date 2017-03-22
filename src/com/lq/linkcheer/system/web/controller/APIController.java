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
import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.common.util.plugin.json.JsonUtil;
import com.lq.common.util.spring.DateEditor;
import com.lq.linkcheer.system.model.entity.News;
import com.lq.linkcheer.system.model.entity.Notice;
import com.lq.linkcheer.system.model.entity.Picture;
import com.lq.linkcheer.system.model.entity.Recruit;
import com.lq.linkcheer.system.model.entity.Synopsis;
import com.lq.linkcheer.system.model.entity.Visitorinfo;
import com.lq.linkcheer.system.service.interfaces.INewsService;
import com.lq.linkcheer.system.service.interfaces.INoticeService;
import com.lq.linkcheer.system.service.interfaces.IPictureService;
import com.lq.linkcheer.system.service.interfaces.IRecruitService;
import com.lq.linkcheer.system.service.interfaces.ISynopsisService;
import com.lq.linkcheer.system.service.interfaces.IVisitorinfoService;

@Controller
@RequestMapping(value = "/api")
public class APIController {
	
	
	@Autowired private ISynopsisService synopsisService;
	@Autowired private INoticeService noticeService;
	@Autowired private INewsService newsService;
	@Autowired private IRecruitService recruitService;
	@Autowired private IPictureService pictureService;
	@Autowired private IVisitorinfoService visitorinfoService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws ServletException {
			
        binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	
	@RequestMapping(value="/synopsis/getSysnopsis")
	@ResponseBody
	public String getSynopsis(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		
		try {
			Synopsis synopsis = synopsisService.getPublishSynopsis();
			result.setData(synopsis);
			if(synopsis == null){
				result.setMsg("查询结果为空！");
				result.setStatus("0");
			}else{
				result.setMsg("success");
				result.setStatus("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(e);
			result.setMsg("error");
			result.setStatus("0");
		}
		
		return JsonUtil.fromObject(result);
	}
	
	
	
	@RequestMapping(value="/notice/getNotice")
	@ResponseBody
	public String getNotice(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		
		try {
			Notice notice = noticeService.getOneFirst();
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
			result.setData(e);
			result.setMsg("error");
			result.setStatus("0");
		}
		
		return JsonUtil.fromObject(result);
	}
	
	
	
	@RequestMapping(value="/news/getNews")
	@ResponseBody
	public String getNews(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		Pager pager = new Pager();
		News news = new News();
		pager.setPage(1);
		pager.setRows(15);
		try {
			List<News> newslist = newsService.getNewsList(news, null, null, pager);
			result.setData(newslist);
			if(newslist == null){
				result.setMsg("查询结果为空！");
				result.setStatus("0");
			}else{
				result.setMsg("success");
				result.setStatus("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(e);
			result.setMsg("error");
			result.setStatus("0");
		}
		
		return JsonUtil.fromObject(result);
	}
	
	
	@RequestMapping(value="/news/getOneNews")
	@ResponseBody
	public String getOneNews(HttpServletResponse response,String id){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		try {
			News news = newsService.getOneById(id);
			result.setData(news);
			if(news == null){
				result.setMsg("查询结果为空！");
				result.setStatus("0");
			}else{
				result.setMsg("success");
				result.setStatus("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(e);
			result.setMsg("error");
			result.setStatus("0");
		}
		
		return JsonUtil.fromObject(result);
	}
	
	@RequestMapping(value="/recruit/getRecruit")
	@ResponseBody
	public String getRecruit(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		Pager pager = new Pager();
		pager.setPage(1);
		pager.setRows(15);
		try {
			List<Recruit> list = recruitService.getAllRecruit();
			result.setData(list);
			if(list == null){
				result.setMsg("查询结果为空！");
				result.setStatus("0");
			}else{
				result.setMsg("success");
				result.setStatus("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(e);
			result.setMsg("error");
			result.setStatus("0");
		}
		
		return JsonUtil.fromObject(result);
	}
	
	
	@RequestMapping(value="/picture/getPicture")
	@ResponseBody
	public String getPicture(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		Pager pager = new Pager();
		Picture picture = new Picture();
		pager.setPage(1);
		pager.setRows(15);
		try {
			List<Picture> picturelist = pictureService.getPictureList(picture, null, null, pager);
			result.setData(picturelist);
			if(picturelist == null){
				result.setMsg("查询结果为空！");
				result.setStatus("0");
			}else{
				result.setMsg("success");
				result.setStatus("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(e);
			result.setMsg("error");
			result.setStatus("0");
		}
		
		return JsonUtil.fromObject(result);
	}
	
	@RequestMapping(value="/visitorinfo/save")
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request,HttpServletResponse response, Visitorinfo visitorinfo){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		if(StringUtils.isBlank(visitorinfo.getTelephone())){
			result.setData("");
			result.setMsg("电话为空！");
			result.setStatus("0");
			return JsonUtil.fromObject(result);
		}
		Date date = new Date();
		String ipadd = "";
		if (request.getHeader("x-forwarded-for") == null) {  
			ipadd = request.getRemoteAddr();
	    }else{
	    	 ipadd = request.getHeader("x-forwarded-for");  
	    }
		visitorinfo.setIp_add(ipadd);
		visitorinfo.setTime(date);
		
		try {
			visitorinfoService.saveOrUpdate(visitorinfo);
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
	
}
