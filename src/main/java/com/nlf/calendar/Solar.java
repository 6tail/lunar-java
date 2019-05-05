package com.nlf.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.nlf.calendar.util.SolarUtil;

/**
 * 阳历日期
 *
 * @author 6tail
 *
 */
public class Solar{
  /** 年 */
  private int year;
  /** 月 */
  private int month;
  /** 日 */
  private int day;
  /** 日历 */
  private Calendar calendar;

  /**
   * 默认使用当前日期初始化
   */
  public Solar(){
    this(new Date());
  }

  /**
   * 通过年月日初始化
   *
   * @param year 年
   * @param month 月，1到12
   * @param day 日，1到31
   */
  public Solar(int year,int month,int day){
    calendar = Calendar.getInstance();
    calendar.set(year,month-1,day);
    this.year = year;
    this.month = month;
    this.day = day;
  }

  /**
   * 通过日期初始化
   *
   * @param date 日期
   */
  public Solar(Date date){
    calendar = Calendar.getInstance();
    calendar.setTime(date);
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH)+1;
    day = calendar.get(Calendar.DATE);
  }

  /**
   * 通过日历初始化
   *
   * @param calendar 日历
   */
  public Solar(Calendar calendar){
    this.calendar = calendar;
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH)+1;
    day = calendar.get(Calendar.DATE);
  }

  /**
   * 通过指定日期获取阳历
   *
   * @param date 日期
   * @return 阳历
   */
  public static Solar fromDate(Date date){
    return new Solar(date);
  }

  /**
   * 通过指定日历获取阳历
   *
   * @param calendar 日历
   * @return 阳历
   */
  public static Solar fromCalendar(Calendar calendar){
    return new Solar(calendar);
  }

  /**
   * 通过指定年月日获取阳历
   *
   * @param year 年
   * @param month 月，1到12
   * @param day 日，1到31
   * @return 阳历
   */
  public static Solar fromYmd(int year,int month,int day){
    return new Solar(year,month,day);
  }

  /**
   * 是否闰年
   *
   * @return true/false 闰年/非闰年
   */
  public boolean isLeapYear(){
    return SolarUtil.isLeapYear(year);
  }

  /**
   * 获取星期，0代表周日，1代表周一
   *
   * @return 0123456
   */
  public int getWeek(){
    return calendar.get(Calendar.DAY_OF_WEEK)-1;
  }

  /**
   * 获取星期的中文
   *
   * @return 日一二三四五六
   */
  public String getWeekInChinese(){
    return SolarUtil.WEEK[getWeek()];
  }

  /**
   * 获取节日，有可能一天会有多个节日
   *
   * @return 劳动节等
   */
  public List<String> getFestivals(){
    List<String> l = new ArrayList<String>();
    //获取几月几日对应的节日
    String f = SolarUtil.FESTIVAL.get(month+"-"+day);
    if(null!=f){
      l.add(f);
    }
    //计算几月第几个星期几对应的节日
    //第几周
    int weekInMonth = calendar.get(Calendar.WEEK_OF_MONTH);
    //星期几，0代表星期天
    int week = getWeek();
    //星期天很奇葩，会多算一周，需要减掉
    if(0==week){
      weekInMonth--;
    }
    f = SolarUtil.WEEK_FESTIVAL.get(month+"-"+weekInMonth+"-"+week);
    if(null!=f){
      l.add(f);
    }
    return l;
  }

  /**
   * 获取星座
   *
   * @return 星座
   */
  public String getXingzuo(){
    int index = 11,m = month,d = day;
    int y = m*100+d;
    if(y>=321&&y<=419){
      index = 0;
    }else if(y>=420&&y<=520){
      index = 1;
    }else if(y>=521&&y<=620){
      index = 2;
    }else if(y>=621&&y<=722){
      index = 3;
    }else if(y>=723&&y<=822){
      index = 4;
    }else if(y>=823&&y<=922){
      index = 5;
    }else if(y>=923&&y<=1022){
      index = 6;
    }else if(y>=1023&&y<=1121){
      index = 7;
    }else if(y>=1122&&y<=1221){
      index = 8;
    }else if(y>=1222||y<=119){
      index = 9;
    }else if(y<=218){
      index = 10;
    }
    return SolarUtil.XINGZUO[index];
  }

  /**
   * 获取年份
   *
   * @return 如2015
   */
  public int getYear(){
    return year;
  }

  /**
   * 获取月份
   *
   * @return 1到12
   */
  public int getMonth(){
    return month;
  }

  /**
   * 获取日期
   *
   * @return 1到31之间的数字
   */
  public int getDay(){
    return day;
  }

  /**
   * 获取农历
   * @return 农历
   */
  public Lunar getLunar(){
    return new Lunar(calendar.getTime());
  }

  /**
   * 获取日历
   *
   * @return 日历
   */
  public Calendar getCalendar(){
    return calendar;
  }

  @Override
  public String toString(){
    return year+"-"+(month<10?"0":"")+month+"-"+(day<10?"0":"")+day;
  }

  public String toFullString(){
    StringBuilder s = new StringBuilder();
    s.append(toString());
    if(isLeapYear()){
      s.append(" ");
      s.append("闰年");
    }
    s.append(" ");
    s.append("星期");
    s.append(getWeekInChinese());
    for(String f:getFestivals()){
      s.append(" (");
      s.append(f);
      s.append(")");
    }
    s.append(" ");
    s.append(getXingzuo());
    s.append("座");
    return s.toString();
  }

  /**
   * 获取往后推几天的阳历日期，如果要往前推，则天数用负数
   * @param days 天数
   * @return 阳历日期
   */
  public Solar next(int days){
    Calendar c = Calendar.getInstance();
    c.set(year,month-1,day);
    c.add(Calendar.DATE,days);
    return new Solar(c);
  }

}
