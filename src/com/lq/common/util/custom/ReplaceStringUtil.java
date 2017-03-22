package com.lq.common.util.custom;

import org.apache.commons.lang.StringUtils;

public class ReplaceStringUtil {
	
	 public static String replaceFirstInString(String str, Object[] keys, String placeHolder){
		 if ( StringUtils.isBlank(str) )
		      return null;
		 
		 if ( keys == null || keys.length == 0)
			 return str;
	    
		 for ( Object key:keys ) {
			 String k = (String) key;
	    	str = str.replaceFirst( "\\"+placeHolder, k );
		 }
		 return str;
	 }
}
