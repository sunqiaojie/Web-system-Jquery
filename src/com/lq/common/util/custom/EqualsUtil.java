package com.lq.common.util.custom;



public class EqualsUtil {
	
	public static Boolean equals(Object a, Object b){
		
		if((a == null && b == null) || (a != null && b != null && a.equals(b))) {
			return true;
		} else {
			return false;
		}
		
	}
	/*public static Boolean dateEquals(Object a, Object b){
		
		if((a == null && b == null) || (a != null && b != null && ((Date) a).compareTo((Date) b)==0)) {
			return true;
		} else {
			return false;
		}
		
	}*/
}
