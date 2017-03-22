package com.lq.common.util.spring;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


public class DateEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		boolean success = false;
		Date value = null;
		if(StringUtils.trimToNull(text) != null){
			if(!success){
				try {
					value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(text);
					success = true;
				} catch (ParseException e) {
					success = false;
				}
			}

			if(!success){
				try {
					value = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(text);
					success = true;
				} catch (ParseException e1) {
					success = false;
				}
			}

			if(!success){
				try {
					value = new SimpleDateFormat("yyyy-MM-dd").parse(text);
					success = true;
				} catch (ParseException ee1) {
					success = false;
				}
			}

			if(!success){
				try {
					value = new SimpleDateFormat("yyyy-MM").parse(text);
					success = true;
				} catch (ParseException e) {
					success = false;
				}
			}
			
			super.setValue(success ? value : null);
		} else {
			super.setValue(null);
		}
	}

}
