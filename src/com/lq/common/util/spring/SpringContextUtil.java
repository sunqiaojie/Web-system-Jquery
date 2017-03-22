package com.lq.common.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

    public static void setApplicationContextStaticlly(WebApplicationContext vApplicationContext) {  
        SpringContextUtil.applicationContext = vApplicationContext;  
    }

    public static Object getBean(String beanName) throws BeansException {
    	Object obj = null;
        if (applicationContext.containsBean(beanName)) {
        	try {
        		obj = applicationContext.getBean(beanName);
        	} catch (BeansException localBeansException) {
        	}
        }
        return obj;
    }
    
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName, Class<T> clazz) {
    	Object obj = null;
    	if (applicationContext.containsBean(beanName)) {
    		try {
    			obj = applicationContext.getBean(beanName, clazz);
    		} catch (BeansException localBeansException) {
    		}
    	}
    	return (T)obj;
    }
    
}
