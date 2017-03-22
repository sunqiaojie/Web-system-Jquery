 package com.lq.appsettings.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.common.util.custom.GlobalConstant;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"/*"})
public class AuthorizationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		String token = request.getParameter("token");
//		if (!TokenUtils.verifyToken(token)) {
//			response.setContentType("text/html; charset=UTF-8");
//			response.getWriter().write("{code: -1, msg: '访问未授权!'}");
//			return;
//		}
		
		HttpServletRequest httpReq = (HttpServletRequest)request;
	    HttpServletResponse httpRes = (HttpServletResponse)response;
	    String reqUrl = httpReq.getRequestURI();
	    if(!reqUrl.contains("/resources")&&!reqUrl.contains("/portal")&&!reqUrl.contains("/upload")){
	    	if (httpReq.getSession().getAttribute(GlobalConstant.SESSION_LOGIN_USER) == null) {
	    		if (reqUrl.endsWith("/login.jsp") || reqUrl.endsWith("/login")||reqUrl.contains("/api/")) {
	    			
	    		} else {
	    			reqUrl = httpReq.getContextPath() + "/login.jsp";
	    			httpRes.sendRedirect(reqUrl);
	    			return;
	    		}
	    	}else {
	    		if (reqUrl.endsWith("/login.jsp") || reqUrl.endsWith("/login")) {
		    		reqUrl = httpReq.getContextPath() + "/page/home/home.jsp";
		    		httpRes.sendRedirect(reqUrl);
		    		return;
	    		}
		    }
	    } 
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
		
	}
}
