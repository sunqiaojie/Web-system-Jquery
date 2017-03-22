package com.lq.config;

import java.io.IOException;
import java.util.Properties;

public class AppConfigManager {

	private static String config_file = "../../../Config.properties";
	private static Properties prop = new Properties();
	
	static {
		try {
			prop.load(AppConfigManager.class.getResourceAsStream(config_file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getConfig(String key) {
		return prop.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println(getConfig("lq.sctp"));
	}
}
