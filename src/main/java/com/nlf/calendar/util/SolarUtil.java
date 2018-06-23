package com.nlf.calendar.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 阳历工具
 * @author 6tail
 *
 */
public class SolarUtil{
  /** 阳历基准年 */
  public static final int BASE_YEAR = 1901;
  /** 阳历基准月 */
  public static final int BASE_MONTH = 1;
  /** 阳历基准日 */
  public static final int BASE_DAY = 1;
  /** 星期*/
  public static final String[] WEEK = {"日","一","二","三","四","五","六"};
  /** 每月天数 */
  public static final int[] DAYS_OF_MONTH = {31,28,31,30,31,30,31,31,30,31,30,31};
  /** 星座 */
  public static final String[] XINGZUO = {"白羊","金牛","双子","巨蟹","狮子","处女","天秤","天蝎","射手","摩羯","水瓶","双鱼"};
  /** 日期对应的节日 */
  public static final Map<String,String> FESTIVAL = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("1-1","元旦节");
      put("2-14","情人节");
      put("3-8","妇女节");
      put("3-12","植树节");
      put("3-15","消费者权益日");
      put("4-1","愚人节");
      put("5-1","劳动节");
      put("5-4","青年节");
      put("6-1","儿童节");
      put("7-1","建党节");
      put("8-1","建军节");
      put("9-10","教师节");
      put("10-1","国庆节");
      put("12-24","平安夜");
      put("12-25","圣诞节");
    }
  };
  /** 几月第几个星期几对应的节日 */
  public static final Map<String,String> WEEK_FESTIVAL = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("5-2-0","母亲节");
      put("6-3-0","父亲节");
      put("11-4-4","感恩节");
    }
  };

  protected SolarUtil(){}

  /**
   * 是否闰年
   * 
   * @param year 年
   * @return true/false 闰年/非闰年
   */
  public static boolean isLeapYear(int year){
    boolean leap = false;
    if(year%4==0) leap = true;
    if(year%100==0) leap = false;
    if(year%400==0) leap = true;
    return leap;
  }

  /**
   * 获取某年某月有多少天
   * 
   * @param year 年
   * @param month 月
   * @return 天数
   */
  public static int getDaysOfMonth(int year,int month){
    int m = month-1;
    int d = DAYS_OF_MONTH[m];
    //公历闰年2月多一天
    if(m==Calendar.FEBRUARY&&isLeapYear(year)){
      d++;
    }
    return d;
  }

  /**
   * 获取某年某月有多少周
   *
   * @param year 年
   * @param month 月
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 周数
   */
  public static int getWeeksOfMonth(int year,int month,int start){
    int days = getDaysOfMonth(year,month);
    Calendar c = Calendar.getInstance();
    c.set(year,month-1,1);
    int week = c.get(Calendar.DAY_OF_WEEK)-1;
    return (int)Math.ceil((days+week-start)/7D);
  }
}