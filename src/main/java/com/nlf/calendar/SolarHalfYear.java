package com.nlf.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 阳历半年
 *
 * @author 6tail
 */
public class SolarHalfYear {
  /**
   * 年
   */
  private final int year;
  /**
   * 月
   */
  private final int month;

  /**
   * 半年的月数
   */
  public static final int MONTH_COUNT = 6;

  /**
   * 默认当月
   */
  public SolarHalfYear() {
    this(new Date());
  }

  /**
   * 通过日期初始化
   */
  public SolarHalfYear(Date date) {
    Solar solar = Solar.fromDate(date);
    year = solar.getYear();
    month = solar.getMonth();
  }

  /**
   * 通过日历初始化
   */
  @Deprecated
  public SolarHalfYear(Calendar calendar) {
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH) + 1;
  }

  /**
   * 通过年月初始化
   *
   * @param year  年
   * @param month 月
   */
  public SolarHalfYear(int year, int month) {
    this.year = year;
    this.month = month;
  }

  /**
   * 通过指定日期获取阳历半年
   *
   * @param date 日期
   * @return 阳历半年
   */
  public static SolarHalfYear fromDate(Date date) {
    return new SolarHalfYear(date);
  }

  /**
   * 通过指定日历获取阳历半年
   *
   * @param calendar 日历
   * @return 阳历半年
   */
  @Deprecated
  public static SolarHalfYear fromCalendar(Calendar calendar) {
    return new SolarHalfYear(calendar);
  }

  /**
   * 通过指定年月获取阳历半年
   *
   * @param year  年
   * @param month 月
   * @return 阳历半年
   */
  public static SolarHalfYear fromYm(int year, int month) {
    return new SolarHalfYear(year, month);
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
   * 获取月
   *
   * @return 月
   */
  public int getMonth() {
    return month;
  }

  /**
   * 获取当月是第几半年
   *
   * @return 半年序号，从1开始
   */
  public int getIndex() {
    return (int) Math.ceil(month * 1D / MONTH_COUNT);
  }

  /**
   * 半年推移
   *
   * @param halfYears 推移的半年数，负数为倒推
   * @return 推移后的半年
   */
  public SolarHalfYear next(int halfYears) {
    SolarMonth m = SolarMonth.fromYm(year, month).next(MONTH_COUNT * halfYears);
    return new SolarHalfYear(m.getYear(), m.getMonth());
  }

  /**
   * 获取本半年的月份
   *
   * @return 本半年的月份列表
   */
  public List<SolarMonth> getMonths() {
    List<SolarMonth> l = new ArrayList<SolarMonth>();
    int index = getIndex() - 1;
    for (int i = 0; i < MONTH_COUNT; i++) {
      l.add(new SolarMonth(year, MONTH_COUNT * index + i + 1));
    }
    return l;
  }

  @Override
  public String toString() {
    return year + "." + getIndex();
  }

  public String toFullString() {
    return year + "年" + (getIndex() == 1 ? "上" : "下") + "半年";
  }
}
