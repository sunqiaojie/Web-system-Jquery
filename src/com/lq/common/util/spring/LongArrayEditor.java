package com.lq.common.util.spring;

import java.beans.PropertyEditorSupport;

public class LongArrayEditor extends PropertyEditorSupport {

	private String seperator;
	
	public LongArrayEditor(String seperator) {
		this.seperator = seperator;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] values = text.split(this.seperator);
		long[] d = new long[values.length];
		for (int i = 0; i < values.length; i++) {
			try {
				d[i] = Long.parseLong(values[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.setValue(d);
	}

}
