package com.hlsofttech.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @author Administrator 系统时间工具类
 */
public class SysDateUtil {
  /**
   * 得到系统当前时间
   * @return 
   */
  public static Date getNowDate() {
    return Calendar.getInstance().getTime();
  }

  /**
   * 得到时间戳
   * @return
   * @author ZhaoJC
   * @date 2018年9月19日下午3:35:37
   */
  public static String getTimeStr() {
    return getNowStr("yyyyMMddHHmmssSSS");
  }

  /**
   * 返回当前时间格式化后的字符串，默认格式"yyyy-MM-dd HH:mm:ss"
   * @param dateFormat
   * @return
   * @author ZhaoJC
   * @date 2018年12月27日下午2:29:03
   */
  public static String getNowStr(String... dateFormat) {
    String pattern = "yyyy-MM-dd HH:mm:ss";
    if (null != dateFormat && dateFormat.length > 0) {
      pattern = dateFormat[0];
    }
    Calendar c = Calendar.getInstance();
    return DateFormatUtils.format(c, pattern);
  }
  
  /**
   * 转换日期字符串为日期Date
   * @param dtstr
   * @param dateFormat
   * @return
   * @author ZhaoJC
   * @date 2018年12月27日下午2:25:20
   */
  public static Date parseDate(String dtstr, String... dateFormat) {
    String[] parsePatterns = null;
    Date date = null;
    if(StringUtils.isBlank(dtstr)) {
      return date;
    }
    if (null != dateFormat && dateFormat.length > 0) {
      parsePatterns = dateFormat;
    } else {
      parsePatterns = new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd", "yyyy-MM", "yyyy/MM", "yyyy"};
    }
    try {
      date = DateUtils.parseDate(dtstr, parsePatterns);
    } catch (ParseException e) {
      return date;
    }
    return date;
  }

  /**
   * 转换为字符串
   * @param dt
   * @param dateFormat
   * @return
   */
  public static String format(Date dt, String... dateFormat) {
    if(null == dt) {
      return "";
    }
    String pattern = "yyyy-MM-dd HH:mm:ss";
    if (null != dateFormat && dateFormat.length == 1) {
      pattern = dateFormat[0];
    }
    return DateFormatUtils.format(dt, pattern);
  }

}
