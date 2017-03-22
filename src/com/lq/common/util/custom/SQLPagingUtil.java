package com.lq.common.util.custom;

public class SQLPagingUtil {
	
	public static String sQLPagingUtil(String sql, int start, int end){
		String sqll = "select * from ( "        //根据rn分页
			+ "select t.*,rownum rn from ( "      //取得所有rn    应为rn  只能从零开始，所以  必须用三层select来分页
			+ sql //按条件排序
			+ ") t) where rn >" + start + " and rn <"+ end ;
		return sqll;	
	}
}
