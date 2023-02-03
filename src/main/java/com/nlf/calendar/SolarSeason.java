package com.nlf.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 阳历季度
 *
 * @author 6tail
 */
public class SolarSeason {
  /**
   * 年
   */
  private final int year;

  /**
   * 月
   */
  private final int month;

  /**
   * 一个季度的月数
   */
  public static final int MONTH_COUNT = 3;

  /**
   * 默认当月
   */
  public SolarSeason() {
    this(new Date());
  }

  /**
   * 通过日期初始化
   */
  public SolarSeason(Date date) {
    Solar solar = Solar.fromDate(date);
    year = solar.getYear();
    month = solar.getMonth();
  }

  /**
   * 通过日历初始化
   */
  @Deprecated
  public SolarSeason(Calendar calendar) {
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH) + 1;
  }

  /**
   * 通过年月初始化
   *
   * @param year  年
   * @param month 月
   */
  public SolarSeason(int year, int month) {
    this.year = year;
    this.month = month;
  }

  /**
   * 通过指定日期获取阳历季度
   *
   * @param date 日期
   * @return 阳历季度
   */
  public static SolarSeason fromDate(Date date) {
    return new SolarSeason(date);
  }

  /**
   * 通过指定日历获取阳历季度
   *
   * @param calendar 日历
   * @return 阳历季度
   */
  @Deprecated
  public static SolarSeason fromCalendar(Calendar calendar) {
    return new SolarSeason(calendar);
  }

  /**
   * 通过指定年月获取阳历季度
   *
   * @param year  年
   * @param month 月
   * @return 阳历季度
   */
  public static SolarSeason fromYm(int year, int month) {
    return new SolarSeason(year, month);
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
   * 获取当月是第几季度
   *
   * @return 季度序号，从1开始
   */
  public int getIndex() {
    return (int) Math.ceil(month * 1D / MONTH_COUNT);
  }

  /**
   * 季度推移
   *
   * @param seasons 推移的季度数，负数为倒推
   * @return 推移后的季度
   */
  public SolarSeason next(int seasons) {
    SolarMonth m = SolarMonth.fromYm(year, month).next(MONTH_COUNT * seasons);
    return new SolarSeason(m.getYear(), m.getMonth());
  }

  /**
   * 获取本季度的月份
   *
   * @return 本季度的月份列表
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
    return year + "年" + getIndex() + "季度";
  }
}
