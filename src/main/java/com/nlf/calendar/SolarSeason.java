package com.nlf.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 阳历季度
 */
public class SolarSeason {
  /** 年 */
  private int year;
  /** 月 */
  private int month;

  /**
   * 默认当月
   */
  public SolarSeason(){
    this(new Date());
  }

  /**
   * 通过日期初始化
   */
  public SolarSeason(Date date){
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    year = c.get(Calendar.YEAR);
    month = c.get(Calendar.MONTH)+1;
  }

  /**
   * 通过日历初始化
   */
  public SolarSeason(Calendar calendar){
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH)+1;
  }

  /**
   * 通过年月初始化
   *
   * @param year 年
   * @param month 月
   */
  public SolarSeason(int year, int month){
    this.year = year;
    this.month = month;
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
   * 获取当月是第几季度
   * @return 季度序号，从1开始
   */
  public int getIndex(){
    return (int)Math.ceil(month/3D);
  }

  /**
   * 季度推移
   * @param seasons 推移的季度数，负数为倒推
   * @return 推移后的季度
   */
  public SolarSeason next(int seasons){
    if(0==seasons){
      return new SolarSeason(year,month);
    }
    Calendar c = Calendar.getInstance();
    c.set(year,month-1,1);
    c.add(Calendar.MONTH,3*seasons);
    return new SolarSeason(c);
  }

  /**
   * 获取本季度的月份
   * @return 本季度的月份列表
   */
  public List<SolarMonth> getMonths(){
    List<SolarMonth> l = new ArrayList<SolarMonth>();
    int index = getIndex()-1;
    for(int i=0;i<3;i++){
      l.add(new SolarMonth(year,3*index+i+1));
    }
    return l;
  }

  public String toString(){
    return year+"."+getIndex();
  }

  public String toFullString(){
    return year+"年"+getIndex()+"季度";
  }
}