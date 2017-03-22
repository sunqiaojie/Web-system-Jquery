package com.lq.linkcheer.system.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lq.common.util.custom.GlobalConstant;
import com.lq.common.util.plugin.Result;
import com.lq.common.util.plugin.json.JsonUtil;
import com.lq.linkcheer.system.model.entity.Synopsis;
import com.lq.linkcheer.system.model.entity.User;
import com.lq.linkcheer.system.service.interfaces.ISynopsisService;

@Controller
@RequestMapping(value = "/system/synopsis")
public class SynopsisController {
	
	@Autowired private ISynopsisService synopsisService;

	@RequestMapping(value="/save")
	@ResponseBody
	public String saveOrUpdate(HttpSession session,HttpServletRequest request,HttpServletResponse response, Synopsis synopsis,String content){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		String aaa = request.getParameter("content");
		System.out.println(aaa);
		/*if(StringUtils.isBlank(synopsis.getContent())){
			result.setData("");
			result.setMsg("内容为空！");
			result.setStatus("0");
			return JsonUtil.fromObject(result);
		}*/
		User user = (User)session.getAttribute(GlobalConstant.SESSION_LOGIN_USER);
		Date date = new Date();
		
		synopsis.setPublisher(user.getUsername());//user.getUsername()
		synopsis.setPublisherid(user.getId());//user.getId()
		synopsis.setTime(date);
		
		try {
			synopsisService.saveOrUpdate(synopsis);
			result.setData("");
			result.setMsg("success");
			result.setStatus("1");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(e);
			result.setMsg("error");
			result.setStatus("0");
		}
		return JsonUtil.fromObject(result);
	}
	
	@RequestMapping(value="/getSynopsis")
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
	
}
