package com.lq.common.util.custom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StrOccurrenceTimes {
	
	public static int strAppearTimes(String str, String placeHolder){
		int count = 0;
		if ( StringUtils.isBlank(str) )
		      return count;
		else{
			Matcher mat=Pattern.compile("\\"+placeHolder).matcher(str);
			while(mat.find()){
				count++;
			}
			return count;
		}
	}
}
