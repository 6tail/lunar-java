package com.nlf.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 阳历年
 * 
 * @author 6tail
 *
 */
public class SolarYear{
  /** 年 */
  private int year;

  /**
   * 默认当年
   */
  public SolarYear(){
    this(new Date());
  }

  /**
   * 通过日期初始化
   */
  public SolarYear(Date date){
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    year = c.get(Calendar.YEAR);
  }

  /**
   * 通过日历初始化
   */
  public SolarYear(Calendar calendar){
    year = calendar.get(Calendar.YEAR);
  }

  /**
   * 通过年
   * 
   * @param year 年
   */
  public SolarYear(int year){
    this.year = year;
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
   * 获取本年的阳历月列表
   * 
   * @return 阳历月列表
   */
  public List<SolarMonth> getMonths(){
    List<SolarMonth> l = new ArrayList<SolarMonth>(12);
    SolarMonth m = new SolarMonth(year,1);
    l.add(m);
    for(int i = 1;i<12;i++){
      l.add(m.next(i));
    }
    return l;
  }
  
  /**
   * 获取往后推几年的阳历年，如果要往前推，则年数用负数
   * @param years 年数
   * @return 阳历年
   */
  public SolarYear next(int years){
    Calendar c = Calendar.getInstance();
    c.set(year,0,1);
    c.add(Calendar.YEAR,years);
    return new SolarYear(c);
  }

  public String toString(){
    return year+"";
  }

  public String toFullString(){
    return year+"年";
  }
}