package com.lq.appsettings.application;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

public class AppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		//container.setInitParameter("contextConfigLocation", "/WEB-INF/applicationContext*.xml");

		EnumSet<DispatcherType> dispatcherTypes = EnumSet
				.allOf(DispatcherType.class);
		dispatcherTypes.add(DispatcherType.REQUEST);
		dispatcherTypes.add(DispatcherType.FORWARD);
		
		FilterRegistration.Dynamic characterEncodingFilter = container.addFilter("CharacterEncodingFilter", 
				new org.springframework.web.filter.CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
		
		/*FilterRegistration.Dynamic securityFilter = container.addFilter("springSecurityFilterChain", 
				new org.springframework.web.filter.DelegatingFilterProxy());
		securityFilter.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
		*/
		
		//FilterRegistration.Dynamic expiresFilter = container.addFilter(
		//		"ExpiresFilter", new org.apache.catalina.filters.ExpiresFilter());
		//expiresFilter.setInitParameter("ExpiresByType image", "access plus 10 minutes");
		//expiresFilter.setInitParameter("ExpiresByType application/javascript", "access plus 10 minutes");
		//expiresFilter.setInitParameter("ExpiresByType text/css", "access plus 10 minutes");
		
//		ServletRegistration.Dynamic dispatcher = container.addServlet(
//				"SpringMVC", new DispatcherServlet());
//		dispatcher.setLoadOnStartup(1);
//		dispatcher.addMapping("/");
//		
//		container.addListener(org.springframework.web.context.ContextLoaderListener.class);
	}

}
