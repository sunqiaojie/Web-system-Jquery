package com.lq.common.util.spring;

import java.beans.PropertyEditorSupport;

public class IntegerArrayEditor extends PropertyEditorSupport {

	private String seperator;
	
	public IntegerArrayEditor(String seperator) {
		this.seperator = seperator;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] values = text.split(this.seperator);
		Integer[] d = new Integer[values.length];
		for (int i = 0; i < values.length; i++) {
			try {
				d[i] = Integer.parseInt(values[i]);
			} catch (Exception e) {}
		}
		this.setValue(d);
	}

}
