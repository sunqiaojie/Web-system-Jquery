package com.lq.common.util.custom;

public class DB33Util {
	
	public static String subDB33(String DB33){
		
		String endWords = DB33.substring(DB33.length()-2, DB33.length());
		if(endWords.equals("00")){
			DB33 = DB33.substring(0, DB33.length()-2);
			DB33 = subDB33(DB33);
		}
		
		return DB33;
	}

}
