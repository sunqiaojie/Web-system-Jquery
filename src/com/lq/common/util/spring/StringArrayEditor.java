package com.lq.common.util.spring;

import java.beans.PropertyEditorSupport;

public class StringArrayEditor extends PropertyEditorSupport {

	private String seperator;
	
	public StringArrayEditor(String seperator) {
		this.seperator = seperator;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] values = text.split(this.seperator);
		this.setValue(values);
	}

}
