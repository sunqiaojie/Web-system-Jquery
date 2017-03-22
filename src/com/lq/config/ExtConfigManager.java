package com.lq.config;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class ExtConfigManager {

	private static String config_file = "../../../extConfig.properties";
	private static Properties prop = new Properties();
	
	static {
		try {
			prop.load(ExtConfigManager.class.getResourceAsStream(config_file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getConfig(String key) {
		return prop.getProperty(key).trim();
	}
	
	public static boolean isKeyInPro(String extkey){
		
		@SuppressWarnings("rawtypes")
		Enumeration enums = prop.propertyNames();
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			String value = prop.getProperty(key);
			if(value.equals(extkey)){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(getConfig("test"));
	}
}
