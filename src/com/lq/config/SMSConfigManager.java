package com.lq.config;

import java.io.IOException;
import java.util.Properties;

public class SMSConfigManager {
	
	private static String config_file = "../../../smsConfig.properties";
	private static Properties prop = new Properties();
	
	static {
		try {
			prop.load(SMSConfigManager.class.getResourceAsStream(config_file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getConfig(String key) {
		return prop.getProperty(key).trim();
	}
	
	public static void main(String[] args) {
		System.out.println(getConfig("ip"));
	}
}
