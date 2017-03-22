package com.lq.appsettings.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
//		String token = request.getParameter("token");
//		if (!TokenUtils.verifyToken(token)) {
//			response.setContentType("text/html; charset=UTF-8");
//			response.getWriter().write("{code: -1, msg: '访问未授权!'}");
//			return false;
//		}
		
//	    String reqUrl = request.getRequestURI();
//		if(reqUrl.indexOf("/ext/") > -1) {
//			String extkey = request.getParameter("extkey");
//			if(StringUtil.isNotBlank(extkey) && ExtConfigManager.isKeyInPro(extkey)){
//				
//			} else {
//				response.setContentType("text/html; charset=UTF-8");
//				response.getWriter().write("{code: -1, msg: '访问未授权!'}");
//				return false;
//			}
//		}
		
		
		return super.preHandle(request, response, handler);
	}

}
