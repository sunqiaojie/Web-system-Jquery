package com.lq.common.util.spring;

import java.beans.PropertyEditorSupport;

public class DoubleArrayEditor extends PropertyEditorSupport {

	private String seperator;
	
	public DoubleArrayEditor(String seperator) {
		this.seperator = seperator;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] values = text.split(this.seperator);
		double[] d = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			try {
				d[i] = Double.parseDouble(values[i]);
			} catch (Exception e) {}
		}
		this.setValue(d);
	}

}
