package com.lq.config;

import java.io.IOException;
import java.util.Properties;

public class RetCodeConfig {
	private static String config_file = "../../../../com/dezh/settings/statusCode.properties";
	private static Properties prop = new Properties();
	
	static {
		try {
			prop.load(RetCodeConfig.class.getResourceAsStream(config_file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getMessage(int retCode) {
		return prop.getProperty(String.valueOf(retCode));
	}
	
	public static void main(String[] args) {
		System.out.println(getMessage(5000));
	}
}
