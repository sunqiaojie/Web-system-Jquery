package com.lq.common.util.custom;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	//返回指定时间前一天零点时间
	@SuppressWarnings("static-access")
	public Date getLastDay(Date date){
		
		GregorianCalendar gc = new GregorianCalendar();  
	    gc.setTime(date);  
        if ((gc.get(gc.HOUR_OF_DAY) == 0) && (gc.get(gc.MINUTE) == 0)  
                && (gc.get(gc.SECOND) == 0)) {  
            return new Date(date.getTime() - (24 * 60 * 60 * 1000));  
        } else {  
            Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60  
                    * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND)  
                    * 1000 - 24 * 60 * 60 * 1000);  
            return date2;  
        }
	}
	
	//返回指定时间当天零点时间
	@SuppressWarnings("static-access")
	public static Date getToDay(Date date){
		
		GregorianCalendar gc = new GregorianCalendar();  
	    gc.setTime(date);  
        if ((gc.get(gc.HOUR_OF_DAY) == 0) && (gc.get(gc.MINUTE) == 0)  
                && (gc.get(gc.SECOND) == 0)) {  
            return date;  
        } else {  
            Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60  
                    * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND) * 1000);  
            return date2;  
        }
	}
	
	// 增加或减少天数
	public static Date addDay(Date date, int num) {
		
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		
		return startDT.getTime();
	}
	
	public static String formatMonthDate(Date date){
		 SimpleDateFormat dateformat =new SimpleDateFormat("MM月dd日");
		 String dateString = dateformat.format(date);
		 return dateString;
	}
	
	public static String formatDate(Date date, String fmt){
		 SimpleDateFormat dateformat =new SimpleDateFormat(fmt);
		 String dateString = dateformat.format(date);
		 return dateString;
	}
	
	public static Date formatStringtoDate(String time, String fmt){
		 SimpleDateFormat dateformat =new SimpleDateFormat(fmt);
		 Date date = null;
		 try {
			 date = dateformat.parse(time);
		} catch (Exception e) {
			System.out.println("时间转换错误");
		}
		 return date;
	}
	
	public static Date formatDatetoDay(Date time){
		
		String year = DateUtil.formatDate(time, "yyyy");
		String month = DateUtil.formatDate(time, "MM");
		String day = DateUtil.formatDate(time, "dd");
		time = formatStringtoDate((new StringBuffer()).append(year).append("-").append(month).append("-").append(day).toString(), "yyyy-MM-dd");
		return time;
	}
	
	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));  
        return cal.getTime();  
    }   
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();     
        cal.setTime(date); 
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));  
       return cal.getTime();  
    }  
    
    
    /**
	 * 
	 * @param Date begin 2016-04-06 16:44:23
	 * @param Date end 2016-04-08 16:44:27
	 * @return
	 * 	2016-04-06 16:44:23~2016-04-06 23:59:59;2016-04-07 00:00:00~2016-04-07 23:59:59;2016-04-08 00:00:00~2016-04-08 16:44:27
	 */
    public static String getDateInterval(Date begin, Date end) {
		// 开始日期不能大于结束日期
		if (!begin.before(end)) {
			return null;
		}
		Calendar cal_begin = Calendar.getInstance();
		cal_begin.setTime(begin);

		Calendar cal_end = Calendar.getInstance();
		cal_end.setTime(end);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuffer strbuf = new StringBuffer();

		while (true) {
			if (cal_begin.get(Calendar.YEAR) == cal_end.get(Calendar.YEAR)
					&& cal_begin.get(Calendar.MONTH) == cal_end.get(Calendar.MONTH)
					&& cal_begin.get(Calendar.DATE) == cal_end.get(Calendar.DATE)) {
				strbuf.append(sdf.format(cal_begin.getTime())).append(";")
						.append(sdf.format(cal_end.getTime())).append(";");
				break;
			}
			String str_begin = sdf.format(cal_begin.getTime());
			String str_end = getDayEnd(cal_begin.getTime());
			strbuf.append(str_begin).append(";").append(str_end).append(";");
			cal_begin.add(Calendar.DAY_OF_MONTH, 1);
			cal_begin.set(Calendar.HOUR_OF_DAY, 00);
			cal_begin.set(Calendar.MINUTE, 00);
			cal_begin.set(Calendar.SECOND, 00);
		}
		return strbuf.toString();
	}
	
	/**
	 * 取得指定月份的第一天
	 * 
	 * @param strdate String
	 * @return String
	 */
	public String getMonthBegin(Date date) {

		return formatDateByFormat(date, "yyyy-MM") + "-01";
	}
	
	/**
	 * 取得指定日期的最后时间
	 * 
	 * @param strdate String
	 * @return String
	 */
	public static String getDayEnd(Date date) {

		return formatDateByFormat(date, "yyyy-MM-dd") + " 23:59:59";
	}
	/**
	 * 取得指定日期的最开始时间
	 * 
	 * @param strdate String
	 * @return String
	 */
	public static String getDayBegin(Date date) {

		return formatDateByFormat(date, "yyyy-MM-dd") + " 00:00:00";
	}
	
	/**
	 * Date日期返回年份字符串
	 * @param date
	 * @return String year
	 */
	public static String getYearByDate(Date date){
		String year = "";
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			year = String.valueOf(cal.get(Calendar.YEAR));
		}
		return year; 
	}
	
	/**
	 * Date日期返回月份字符串
	 * @param date
	 * @return String month
	 */
	public static String getMonthByDate(Date date){
		String month = "";
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			month = cal.get(Calendar.MONTH)+1<10 ? "0" + String.valueOf(cal.get(Calendar.MONTH)+1):String.valueOf(cal.get(Calendar.MONTH)+1);
		}
		return month; 
	}
	
	/**
	 * Date日期返回日期字符串
	 * @param date
	 * @return String day
	 */
	public static String getDayByDate(Date date){
		String day = "";
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			day = cal.get(Calendar.DATE)<10 ? "0" + String.valueOf(cal.get(Calendar.DATE)):String.valueOf(cal.get(Calendar.DATE));
		}
		return day; 
	}
	
	/**
	 * 以指定的格式来格式化日期
	 * 
	 * @param date Date
	 * @param format String
	 * @return String
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
    
}
