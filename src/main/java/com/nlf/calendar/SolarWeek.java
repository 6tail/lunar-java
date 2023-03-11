package com.nlf.calendar;

import com.nlf.calendar.util.SolarUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 阳历周
 *
 * @author 6tail
 */
public class SolarWeek {
  /**
   * 年
   */
  private final int year;

  /**
   * 月
   */
  private final int month;

  /**
   * 日
   */
  private final int day;

  /**
   * 星期几作为一周的开始，1234560分别代表星期一至星期天
   */
  private final int start;

  /**
   * 默认当月
   *
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   */
  public SolarWeek(int start) {
    this(new Date(), start);
  }

  /**
   * 通过日期初始化
   *
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   */
  public SolarWeek(Date date, int start) {
    Solar solar = Solar.fromDate(date);
    year = solar.getYear();
    month = solar.getMonth();
    day = solar.getDay();
    this.start = start;
  }

  /**
   * 通过日历初始化
   *
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   */
  @Deprecated
  public SolarWeek(Calendar calendar, int start) {
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH) + 1;
    day = calendar.get(Calendar.DATE);
    this.start = start;
  }

  /**
   * 通过年月初始化
   *
   * @param year  年
   * @param month 月，1到12
   * @param day   日，1到31
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   */
  public SolarWeek(int year, int month, int day, int start) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.start = start;
  }

  /**
   * 通过指定日期获取阳历周
   *
   * @param date  日期
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 阳历周
   */
  public static SolarWeek fromDate(Date date, int start) {
    return new SolarWeek(date, start);
  }

  /**
   * 通过指定日历获取阳历周
   *
   * @param calendar 日历
   * @param start    星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 阳历周
   */
  @Deprecated
  public static SolarWeek fromCalendar(Calendar calendar, int start) {
    return new SolarWeek(calendar, start);
  }

  /**
   * 通过指定年月日获取阳历周
   *
   * @param year  年
   * @param month 月，1到12
   * @param day   日，1到31
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 阳历周
   */
  public static SolarWeek fromYmd(int year, int month, int day, int start) {
    return new SolarWeek(year, month, day, start);
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
   * @return 1到12
   */
  public int getMonth() {
    return month;
  }

  /**
   * 获取日期
   *
   * @return 1到31之间的数字
   */
  public int getDay() {
    return day;
  }

  /**
   * 获取星期几作为一周的开始，1234560分别代表星期一至星期天
   *
   * @return 1234560分别代表星期一至星期天
   */
  public int getStart() {
    return start;
  }

  /**
   * 获取当前日期是在当月第几周
   *
   * @return 周序号，从1开始
   */
  public int getIndex() {
    int offset = Solar.fromYmd(year, month, 1).getWeek() - start;
    if(offset < 0) {
      offset += 7;
    }
    return (int) Math.ceil((day + offset) / 7D);
  }

  /**
   * 获取当前日期是在当年第几周
   *
   * @return 周序号，从1开始
   */
  public int getIndexInYear() {
    int offset = Solar.fromYmd(year, 1, 1).getWeek() - start;
    if(offset < 0) {
      offset += 7;
    }
    return (int) Math.ceil((SolarUtil.getDaysInYear(year, month, day) + offset) / 7D);
  }

  /**
   * 周推移
   *
   * @param weeks         推移的周数，负数为倒推
   * @param separateMonth 是否按月单独计算
   * @return 推移后的阳历周
   */
  public SolarWeek next(int weeks, boolean separateMonth) {
    if (0 == weeks) {
      return new SolarWeek(year, month, day, start);
    }
    Solar solar  = Solar.fromYmd(year, month, day);
    if (separateMonth) {
      int n = weeks;
      SolarWeek week = new SolarWeek(solar.getYear(), solar.getMonth(), solar.getDay(), start);
      int month = this.month;
      boolean plus = n > 0;
      while (0 != n) {
        solar = solar.next(plus ? 7 : -7);
        week = new SolarWeek(solar.getYear(), solar.getMonth(), solar.getDay(), start);
        int weekMonth = week.getMonth();
        if (month != weekMonth) {
          int index = week.getIndex();
          if (plus) {
            if (1 == index) {
              Solar firstDay = week.getFirstDay();
              week = new SolarWeek(firstDay.getYear(), firstDay.getMonth(), firstDay.getDay(), start);
              weekMonth = week.getMonth();
            } else {
              solar = Solar.fromYmd(week.getYear(), week.getMonth(), 1);
              week = new SolarWeek(solar.getYear(), solar.getMonth(), solar.getDay(), start);
            }
          } else {
            if (SolarUtil.getWeeksOfMonth(week.getYear(), week.getMonth(), start) == index) {
              Solar lastDay = week.getFirstDay().next(6);
              week = new SolarWeek(lastDay.getYear(), lastDay.getMonth(), lastDay.getDay(), start);
              weekMonth = week.getMonth();
            } else {
              solar = Solar.fromYmd(week.getYear(), week.getMonth(), SolarUtil.getDaysOfMonth(week.getYear(), week.getMonth()));
              week = new SolarWeek(solar.getYear(), solar.getMonth(), solar.getDay(), start);
            }
          }
          month = weekMonth;
        }
        n -= plus ? 1 : -1;
      }
      return week;
    } else {
      solar = solar.next(weeks * 7);
      return new SolarWeek(solar.getYear(), solar.getMonth(), solar.getDay(), start);
    }
  }

  /**
   * 获取本周第一天的阳历日期（可能跨月）
   *
   * @return 本周第一天的阳历日期
   */
  public Solar getFirstDay() {
    Solar solar = Solar.fromYmd(year, month, day);
    int prev = solar.getWeek() - start;
    if (prev < 0) {
      prev += 7;
    }
    return solar.next(-prev);
  }

  /**
   * 获取本周第一天的阳历日期（仅限当月）
   *
   * @return 本周第一天的阳历日期
   */
  public Solar getFirstDayInMonth() {
    List<Solar> days = getDays();
    for (Solar day : days) {
      if (month == day.getMonth()) {
        return day;
      }
    }
    return null;
  }

  /**
   * 获取本周的阳历日期列表（可能跨月）
   *
   * @return 本周的阳历日期列表
   */
  @SuppressWarnings("all")
  public List<Solar> getDays() {
    Solar firstDay = getFirstDay();
    List<Solar> l = new ArrayList<Solar>();
    l.add(firstDay);
    for (int i = 1; i < 7; i++) {
      l.add(firstDay.next(i));
    }
    return l;
  }

  /**
   * 获取本周的阳历日期列表（仅限当月）
   *
   * @return 本周的阳历日期列表（仅限当月）
   */
  public List<Solar> getDaysInMonth() {
    List<Solar> days = this.getDays();
    List<Solar> l = new ArrayList<Solar>();
    for (Solar day : days) {
      if (month != day.getMonth()) {
        continue;
      }
      l.add(day);
    }
    return l;
  }

  @Override
  public String toString() {
    return year + "." + month + "." + getIndex();
  }

  public String toFullString() {
    return year + "年" + month + "月第" + getIndex() + "周";
  }
}
