package com.lq.common.util.plugin.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateJsonValueProcessor() { }

    public DateJsonValueProcessor(DateFormat df) {
        this.df = df;
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
    	if (o != null) {
    		return df.format(o);
    	}
    	return null;
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
    	if (o != null) {
    		return df.format(o);
    	}
    	return null;
    }
}
