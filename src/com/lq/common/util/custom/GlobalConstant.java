package com.lq.common.util.custom;

import java.text.SimpleDateFormat;

public class GlobalConstant {
	
	/** 超级管理员  sadmin */
	public static String CJGLY = "sadmin";
	
	/** 当前登录的用户  "SESSION_LOGIN_USER" */
	public static String SESSION_LOGIN_USER = "SESSION_LOGIN_USER";
	/** 当前用户的部门  "SESSION_LOGIN_ORGANIZATION" */
	public static String SESSION_LOGIN_ORGANIZATION = "SESSION_LOGIN_ORGANIZATION";
//	/** 当前用户的菜单  "SESSION_LOGIN_MENU" */
//	public static String SESSION_LOGIN_MENU = "SESSION_LOGIN_MENU";
	/** 当前用户的角色  "SESSION_LOGIN_ROLE_LIST" */
	public static String SESSION_LOGIN_ROLE = "SESSION_LOGIN_ROLE";
	/** 当前用户的角色  "SESSION_LOGIN_ROLE_LIST" */
	public static String SESSION_LOGIN_ROLE_LIST = "SESSION_LOGIN_ROLE_LIST";
	/** SESSION_LOGIN_MENUID_LIST */
	public static String SESSION_LOGIN_MENUID_LIST = "SESSION_LOGIN_MENUID_LIST";
	/** 当前用户的访问  SESSION_LOGIN_IP */
	public static String SESSION_LOGIN_IP = "SESSION_LOGIN_IP";
	/** 所有的系统菜单  SESSION_SYSTEMMENU_LIST */
	public static String SESSION_SYSTEMMENU_LIST = "SESSION_SYSTEMMENU_LIST";
	/** 所有子菜单  SESSION_LEFTMMENU_LIST */
	public static String SESSION_LEFTMMENU_LIST = "SESSION_LEFTMMENU_LIST";
	/** 所有的个人档案配置  SESSION_GRDAPZ_LIST */
	public static String SESSION_GRDAPZ_LIST = "SESSION_GRDAPZ_LIST";
	/** 所有的菜单按钮map  SESSION_MENUBUTTON_MAP_JSON */
	public static String SESSION_MENUBUTTON_MAP_JSON = "SESSION_MENUBUTTON_MAP_JSON";

	/** SUCCESS */
	public static String STRING_SUCCESS = "SUCCESS";
	/** FAILURE */
	public static String STRING_FAILURE = "FAILURE";
	/** TRUE */
	public static String STRING_TRUE = "TRUE";
	/** FAlSE*/
	public static String STRING_FALSE = "FALSE";
	
	/** 数据库中yes为 "1" */
	public static String DB_YES_ONE = "1";
	/** 数据库中no为 "0" */
	public static String DB_NO_ZERO = "0";
	
	public static String SEX_MALE_ONE = "1";
	
	public static String SEX_FEMALE_ZERO = "0";
	
	public static String ORGLEVEL_PROVINCE = "1";
	public static String ORGLEVEL_CITY = "2";
	public static String ORGLEVEL_COUNTY = "3";
	public static String ORGLEVEL_NORMAL = "4";
	
	/** 时间格式：yyyy-MM-dd */
	public static SimpleDateFormat DATE_FORMAT_BUSINESS_YMD = new SimpleDateFormat("yyyy-MM-dd");

	/** 时间格式：yyyyMMddHHmmssSSS */
	public static SimpleDateFormat DATE_FORMAT_SYSTEM_MILLISECOND = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	/** 上传文件操作的时候  保存附件信息错误 */
	public static String BCFJXX_ERROR = "0";
	/** 上传文件操作的时候  上传文件错误 */
	public static String SCWJ_ERROR = "1";
	/** 上传文件操作的时候  文件已存在 */
	public static String WJ_EXITS = "2";
	/** 上传文件操作的时候  上传文件类型错误 */
	public static String WJLX_ERROR = "3";
	
	/** 删除文件操作的时候  删除文件出错 */
	public static String DEL_FILE_ERROR = "0";
	/** 删除文件操作的时候  删除附件信息出错 */
	public static String DEL_FJXX_ERROR = "1";
	
	/** 方法类型----1 */
	public static String FUNCTION_TYPE_ONE = "1";
	/** 方法类型----2 */
	public static String FUNCTION_TYPE_TWO = "2";
	
	/** 预览文件保存地址---- */
	public static String FILE_VIEW_SAVEPATH = "D:/zjzf_jwweb_temp";
	/** 预览文件访问地址---- */
	public static String FILE_VIEW_PATH = "http://10.138.9.37:8480//";
	/** 预览文件访问地址---- */
	public static String FILE_VIEW_LOCALPATH = "http://localhost:8080//zjzf_jwweb_temp//";
	
	/** 纪委警种---- */
	public static String ORG_JZ_JW = "32";
}
