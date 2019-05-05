package com.nlf.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.nlf.calendar.util.SolarUtil;

/**
 * 阳历月
 *
 * @author 6tail
 *
 */
public class SolarMonth{
  /** 年 */
  private int year;
  /** 月 */
  private int month;

  /**
   * 默认当月
   */
  public SolarMonth(){
    this(new Date());
  }

  /**
   * 通过日期初始化
   */
  public SolarMonth(Date date){
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    year = c.get(Calendar.YEAR);
    month = c.get(Calendar.MONTH)+1;
  }

  /**
   * 通过日历初始化
   */
  public SolarMonth(Calendar calendar){
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH)+1;
  }

  /**
   * 通过年月初始化
   *
   * @param year 年
   * @param month 月
   */
  public SolarMonth(int year,int month){
    this.year = year;
    this.month = month;
  }

  /**
   * 通过指定日期获取阳历月
   *
   * @param date 日期
   * @return 阳历月
   */
  public static SolarMonth fromDate(Date date){
    return new SolarMonth(date);
  }

  /**
   * 通过指定日历获取阳历月
   *
   * @param calendar 日历
   * @return 阳历月
   */
  public static SolarMonth fromCalendar(Calendar calendar){
    return new SolarMonth(calendar);
  }

  /**
   * 通过指定年月获取阳历月
   *
   * @param year 年
   * @param month 月
   * @return 阳历月
   */
  public static SolarMonth fromYm(int year,int month){
    return new SolarMonth(year,month);
  }

  /**
   * 获取年
   *
   * @return 年
   */
  public int getYear(){
    return year;
  }

  /**
   * 获取月
   *
   * @return 月
   */
  public int getMonth(){
    return month;
  }

  /**
   * 获取本月的阳历日期列表
   *
   * @return 阳历日期列表
   */
  public List<Solar> getDays(){
    List<Solar> l = new ArrayList<Solar>(31);
    Solar d = new Solar(year,month,1);
    l.add(d);
    int days = SolarUtil.getDaysOfMonth(year,month);
    for(int i = 1;i<days;i++){
      l.add(d.next(i));
    }
    return l;
  }

  /**
   * 获取往后推几个月的阳历月，如果要往前推，则月数用负数
   * @param months 月数
   * @return 阳历月
   */
  public SolarMonth next(int months){
    Calendar c = Calendar.getInstance();
    c.set(year,month-1,1);
    c.add(Calendar.MONTH,months);
    return new SolarMonth(c);
  }

  @Override
  public String toString(){
    return year+"-"+month;
  }

  public String toFullString(){
    return year+"年"+month+"月";
  }
}
