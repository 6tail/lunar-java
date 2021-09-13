package com.nlf.calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 精确日期
 *
 * @author 6tail
 */
public class ExactDate {

  /**
   * 时区
   */
  private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT+8");

  public static Calendar fromYmdHms(int year, int month, int day, int hour, int minute, int second) {
    Calendar c = Calendar.getInstance(TIME_ZONE);
    c.set(year, month - 1, day, hour, minute, second);
    c.set(Calendar.MILLISECOND, 0);
    return c;
  }

  public static Calendar fromYmd(int year, int month, int day) {
    return fromYmdHms(year, month, day, 0, 0, 0);
  }

  public static Calendar fromDate(Date date) {
    Calendar c = Calendar.getInstance(TIME_ZONE);
    c.setTime(date);
    c.set(Calendar.MILLISECOND, 0);
    return c;
  }
}
