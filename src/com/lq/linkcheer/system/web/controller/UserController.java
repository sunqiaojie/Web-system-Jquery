package com.lq.linkcheer.system.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lq.common.util.custom.GlobalConstant;
import com.lq.common.util.custom.StringUtil;
import com.lq.common.util.plugin.Result;
import com.lq.common.util.plugin.json.JsonUtil;
import com.lq.linkcheer.system.model.entity.Log;
import com.lq.linkcheer.system.model.entity.User;
import com.lq.linkcheer.system.service.interfaces.ILogService;
import com.lq.linkcheer.system.service.interfaces.IUserService;


@Controller
@RequestMapping(value = "/system/user")
public class UserController {
	
	@Autowired private ILogService logService;
	
	@Autowired private IUserService userService;
	
	
	@RequestMapping(value="/login")
//	@ResponseBody
	public String loginui(HttpServletRequest request, Model model, String username, String password){
			
		if(StringUtils.isNotBlank(username)&&StringUtils.isNotBlank(password)){
			User user = userService.getOne(username);
			if(user!=null && user.getUsername().equals(username)&&user.getPassword().equals(password)){
			HttpSession session = request.getSession();
			String ip = request.getRemoteAddr();
			session.setAttribute(GlobalConstant.SESSION_LOGIN_USER, user);
			session.setAttribute(GlobalConstant.SESSION_LOGIN_IP, ip);
				
				Log log = new Log();
				log.setIp(ip);
				log.setManId(user.getId());
				log.setContent(username);
				Date now = new Date();
				log.setLogTime(now);
				logService.saveOrUpdate(log);
			} else {
				return "login";
			}
			return "/page/home/home";
		}else{
			return "login";
		}
		
		
	}

	@RequestMapping(value="/loginout")
	public String loginout(HttpServletRequest request){
		HttpSession session = request.getSession();
		String ip = (String) session.getAttribute(GlobalConstant.SESSION_LOGIN_IP);
		User currentUser = (User) session.getAttribute(GlobalConstant.SESSION_LOGIN_USER);
		Log log = new Log();
		log.setIp(ip);
		log.setManId(currentUser.getId());
		log.setContent("退出系统");
		Date now = new Date();
		log.setLogTime(now);
		logService.saveOrUpdate(log);
		session.setAttribute(GlobalConstant.SESSION_SYSTEMMENU_LIST, null);
		session.setAttribute(GlobalConstant.SESSION_LEFTMMENU_LIST, null);

		session.setAttribute(GlobalConstant.SESSION_LOGIN_USER, null);
		session.setAttribute(GlobalConstant.SESSION_LOGIN_ORGANIZATION, null);
		session.setAttribute(GlobalConstant.SESSION_LOGIN_ROLE, null);
		session.setAttribute(GlobalConstant.SESSION_LOGIN_ROLE_LIST, null);
		session.setAttribute(GlobalConstant.SESSION_LOGIN_MENUID_LIST, null);
		session.setAttribute(GlobalConstant.SESSION_LOGIN_IP, null);
		session.setAttribute(GlobalConstant.SESSION_GRDAPZ_LIST, null);
		
		return "login";
	}
	
	
	
	
	
	
	@RequestMapping(value="/save")
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request,User user){
		Result result = new Result();
		User usercheck = userService.getOne(user.getUsername());
		if(usercheck != null){
			result.setMsg("用户名重复！");
			result.setStatus("0");
		}
		else{
			try {
				userService.saveOrUpdate(user);
				result.setMsg("success");
				result.setStatus("1");
				
			} catch (Exception e) {
				e.printStackTrace();
				result.setMsg("error");
				result.setStatus("0");
			}
		}
		
		return JsonUtil.fromObject(result);
	}
	
	
	@RequestMapping(value="/changePwd")
	@ResponseBody
	public String changePwd(HttpServletRequest request,User user){
		Result result = new Result();
		if(user.getId() != null && user.getId() != ""){
			try {
				userService.saveOrUpdate(user);
				result.setMsg("success");
				result.setStatus("1");
				
			} catch (Exception e) {
				e.printStackTrace();
				result.setMsg("error");
				result.setStatus("0");
			}
		}else{
			result.setMsg("error");
			result.setStatus("0");
		}
		
		return JsonUtil.fromObject(result);
	}
	
	
	
	@RequestMapping(value="/getone")
	@ResponseBody
	public String getOne(String username){
		User user = userService.getOne(username);
		
		return JsonUtil.fromObject(user);
	}
	
}
