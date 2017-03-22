package com.lq.common.util.plugin.json;

import java.util.Collection;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtil {

	public static String fromObject(Object object) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		
		return JSONObject.fromObject(object, jsonConfig).toString();
	}
	
	public static String fromArray(Collection<?> collect) {
		if (collect == null) {
			return "[]";
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		return JSONArray.fromObject(collect, jsonConfig).toString();
	}
}
