package com.nlf.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 阳历年
 *
 * @author 6tail
 */
public class SolarYear {
  /**
   * 年
   */
  private final int year;

  /**
   * 一年的月数
   */
  public static final int MONTH_COUNT = 12;

  /**
   * 默认当年
   */
  public SolarYear() {
    this(new Date());
  }

  /**
   * 通过日期初始化
   */
  public SolarYear(Date date) {
    year = Solar.fromDate(date).getYear();
  }

  /**
   * 通过日历初始化
   */
  @Deprecated
  public SolarYear(Calendar calendar) {
    year = calendar.get(Calendar.YEAR);
  }

  /**
   * 通过年初始化
   *
   * @param year 年
   */
  public SolarYear(int year) {
    this.year = year;
  }

  /**
   * 通过指定日期获取阳历年
   *
   * @param date 日期
   * @return 阳历年
   */
  public static SolarYear fromDate(Date date) {
    return new SolarYear(date);
  }

  /**
   * 通过指定日历获取阳历年
   *
   * @param calendar 日历
   * @return 阳历年
   */
  @Deprecated
  public static SolarYear fromCalendar(Calendar calendar) {
    return new SolarYear(calendar);
  }

  /**
   * 通过指定年份获取阳历年
   *
   * @param year 年
   * @return 阳历年
   */
  public static SolarYear fromYear(int year) {
    return new SolarYear(year);
  }

  /**
   * 获取年
   *
   * @return 年
   */
  public int getYear() {
    return year;
  }

  /**
   * 获取本年的阳历月列表
   *
   * @return 阳历月列表
   */
  public List<SolarMonth> getMonths() {
    List<SolarMonth> l = new ArrayList<SolarMonth>(MONTH_COUNT);
    SolarMonth m = new SolarMonth(year, 1);
    l.add(m);
    for (int i = 1; i < MONTH_COUNT; i++) {
      l.add(m.next(i));
    }
    return l;
  }

  /**
   * 获取往后推几年的阳历年，如果要往前推，则年数用负数
   *
   * @param years 年数
   * @return 阳历年
   */
  public SolarYear next(int years) {
    return new SolarYear(year + years);
  }

  @Override
  public String toString() {
    return year + "";
  }

  public String toFullString() {
    return year + "年";
  }
}
