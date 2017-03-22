package com.lq.common.util.custom;

public class SeperatorMethod {

	public static long[] toLong(String text, String seperator){
		String[] values = text.split(seperator);
		long[] array = new long[values.length];
		for (int i = 0; i < values.length; i++) {
			try {
				array[i] = Long.parseLong(values[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return array;
	}

	public static String[] toString(String text, String seperator){
		
		return text.split(seperator);
	}
	
	public static String Array2String(Object[] array, String seperator){
		String str = "";
		for (Object object : array) {
			str += object + seperator;
		}
		
		if(str.length() > 0){
			str = str.substring(0, str.length()-1);
		}
		
		return str;
	}

}
