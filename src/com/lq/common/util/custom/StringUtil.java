package com.lq.common.util.custom;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils {

	public static boolean isNull(String data)
	  {
	    return (data == null) || (data.trim().length() < 1);
	  }

	  public static boolean isNotNull(String data) {
	    return !isNull(data);
	  }

	  public static String toNull(String data) {
	    return isNull(data) ? null : data;
	  }

	  public static String toString(String data) {
	    return isNull(data) ? "" : data;
	  }

	  public static String toString(BigDecimal data) {
	    return data == null ? "" : data.toString();
	  }

	  public static BigDecimal nulltoZero(String data) {
	    BigDecimal rtData = new BigDecimal(0);
	    if (!isNull(data)) {
	      rtData = new BigDecimal(data);
	    }
	    return rtData;
	  }

	  public static BigDecimal nulltoZero(BigDecimal data) {
	    BigDecimal rtData = new BigDecimal(0);
	    if (data != null) {
	      rtData = data;
	    }
	    return rtData;
	  }

	  public static String getRandomString(int size)
	  {
	    char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 
	      'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < size; i++) {
	      sb.append(c[(java.lang.Math.abs(random.nextInt()) % c.length)]);
	    }
	    return sb.toString();
	  }

	  public static String toNumberFormat(String str)
	  {
	    NumberFormat n = NumberFormat.getNumberInstance();
	    n.setGroupingUsed(true);

	    String outStr = null;
	    try {
	      double d = Double.parseDouble(str);
	      outStr = n.format(d);
	    } catch (Exception localException) {
	    }
	    return outStr;
	  }

	  public static String toDecimalFormat(String str)
	  {
	    return toDecimalFormat(str, "##,###,###,###,##0.00000");
	  }

	  public static String toDecimalFormat(String str, String pattern)
	  {
	    if (isEmpty(pattern)) {
	      return str;
	    }
	    DecimalFormat fmt = new DecimalFormat(pattern);
	    fmt.setGroupingUsed(true);
	    String outStr = null;
	    try
	    {
	      double d = Double.parseDouble(str);
	      outStr = fmt.format(d);
	    } catch (Exception localException) {
	    }
	    return outStr;
	  }

	  public static String toCurrencyFormat(String str)
	  {
	    NumberFormat n = NumberFormat.getCurrencyInstance(Locale.CHINA);
	    n.setGroupingUsed(true);

	    String outStr = null;
	    try {
	      double d = Double.parseDouble(str);
	      outStr = n.format(d);
	    } catch (Exception localException) {
	    }
	    return outStr;
	  }

	  public static String toPercentFormat(String str)
	  {
	    NumberFormat n = NumberFormat.getPercentInstance();
	    n.setGroupingUsed(true);
	    n.setMinimumFractionDigits(2);
	    n.setMinimumIntegerDigits(1);

	    String outStr = null;
	    try {
	      double d = Double.parseDouble(str);
	      outStr = n.format(d);
	    } catch (Exception localException) {
	    }
	    return outStr;
	  }

	  public static String unCamelUpperCase(String source)
	  {
	    if (isEmpty(source)) {
	      return "";
	    }
	    String[] parts = split(source, "_");
	    StringBuilder sb = new StringBuilder();
	    for (String part : parts) {
	      sb.append(capitalize(part));
	    }
	    source = capitalize(sb.toString());
	    Pattern p = Pattern.compile("([A-Z]?[a-z0-9]*)");
	    Matcher m = p.matcher(source);
	    sb = new StringBuilder();
	    while (m.find()) {
	      sb.append(upperCase(m.group())).append("_");
	    }
	    return substringBefore(sb.toString(), "__");
	  }

	  public static String unCamelLowerCase(String source)
	  {
	    if (isEmpty(source)) {
	      return "";
	    }
	    String[] parts = split(source, "_");
	    StringBuilder sb = new StringBuilder();
	    for (String part : parts) {
	      sb.append(capitalize(part));
	    }
	    source = capitalize(sb.toString());
	    Pattern p = Pattern.compile("([A-Z]?[a-z0-9]*)");
	    Matcher m = p.matcher(source);
	    sb = new StringBuilder();
	    while (m.find()) {
	      sb.append(lowerCase(m.group())).append("_");
	    }
	    return substringBefore(sb.toString(), "__");
	  }

	  public static String camelUpperCase(String source)
	  {
	    if (isEmpty(source)) {
	      return "";
	    }
	    String[] parts = split(source, "_");
	    StringBuilder sb = new StringBuilder();
	    for (String part : parts) {
	      sb.append(capitalize(lowerCase(part)));
	    }
	    return sb.toString();
	  }

	  public static String camelLowerCase(String source)
	  {
	    if (isEmpty(source)) {
	      return "";
	    }
	    String[] parts = split(source, "_");
	    StringBuilder sb = new StringBuilder();
	    for (String part : parts) {
	      sb.append(capitalize(lowerCase(part)));
	    }
	    return uncapitalize(sb.toString());
	  }

	  public static String fixPath(String path) {
	    String tempPath = path;
	    if (isNotEmpty(tempPath)) {
	      tempPath = replace(tempPath, "\\", "/");
	      tempPath = tempPath + "/";
	    }
	    return tempPath;
	  }

	  public static String transToXmlStr(String text)
	  {
	    if (text == null)
	      return "";
	    String tmp = text.replace(">", "&rt;");
	    tmp = tmp.replace("\"", "&quot;");
	    tmp = tmp.replace("<", "&lt;");
	    tmp = tmp.replace("\r", "&#13;");
	    tmp = tmp.replace("\n", "&#10;");
	    tmp = tmp.replace("&", "&amp;");
	    return tmp;
	  }

	  public static String transFromXmlStr(String text)
	  {
	    if (text == null)
	      return "";
	    String tmp = text.replace("&rt;", ">");
	    tmp = tmp.replace("&quot;", "\"");
	    tmp = tmp.replace("&lt;", "<");
	    tmp = tmp.replace("&#13;", "\r");
	    tmp = tmp.replace("&#10;", "\n");
	    tmp = tmp.replace("&amp;", "&");
	    return tmp;
	  }

	  public static void main(String[] args)
	  {
	    String userName = "1' or '1'='1";
	    String password = "123456";
	    userName = StringEscapeUtils.escapeSql(userName);
	    password = StringEscapeUtils.escapeSql(password);
	    String sql = "SELECT COUNT(userId) FROM t_user WHERE userName='" + 
	      userName + "' AND password ='" + password + "'";
	    System.out.println(sql);

	    System.out.println("1:" + unCamelUpperCase("A"));
	    System.out.println("2:" + unCamelUpperCase("Aa"));
	    System.out.println("3:" + unCamelUpperCase("AA"));
	    System.out.println("4:" + unCamelUpperCase("AAa2"));
	    System.out.println("5:" + unCamelUpperCase("Aa1aB1"));
	    System.out.println("6:" + unCamelUpperCase("AaaB2a_"));
	    System.out.println("7:" + unCamelUpperCase("Abc1Def"));
	    System.out.println("8:" + unCamelUpperCase("abcDef"));
	    System.out.println("9:" + unCamelLowerCase("ABcDef"));
	    System.out.println("10:" + unCamelLowerCase("ABcDef_"));
	    System.out.println("11:" + unCamelLowerCase("ABc_Def_"));
	    System.out.println("12:" + unCamelLowerCase("A_Bc_de_f1_"));
	    System.out.println("13:" + camelUpperCase("ABCC_DEF"));
	    System.out.println("14:" + camelLowerCase("ABCC_DEF"));
	  }
	  
}
