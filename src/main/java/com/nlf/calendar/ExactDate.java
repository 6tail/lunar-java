package com.nlf.calendar;

import com.nlf.calendar.util.SolarUtil;

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

  @SuppressWarnings("MagicConstant")
  public static Calendar fromYmdHms(int year, int month, int day, int hour, int minute, int second) {
    Calendar c = Calendar.getInstance(TIME_ZONE);
    c.set(year, month - 1, day, hour, minute, second);
    c.set(Calendar.MILLISECOND, 0);
    if (0 == year) {
      c.add(Calendar.YEAR, 1);
    }
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

  /**
   * 获取两个日期之间相差的天数（如果日期a比日期b小，天数为正，如果日期a比日期b大，天数为负）
   *
   * @param ay 年a
   * @param am 月a
   * @param ad 日a
   * @param by 年b
   * @param bm 月b
   * @param bd 日b
   * @return 天数
   */
  public static int getDaysBetween(int ay, int am, int ad, int by, int bm, int bd) {
    return SolarUtil.getDaysBetween(ay, am, ad, by, bm, bd);
  }

  /**
   * 获取两个日期之间相差的天数（如果日期a比日期b小，天数为正，如果日期a比日期b大，天数为负）
   *
   * @param calendar0 日期a
   * @param calendar1 日期b
   * @return 天数
   */
  public static int getDaysBetween(Calendar calendar0, Calendar calendar1) {
    return SolarUtil.getDaysBetween(calendar0.get(Calendar.YEAR), calendar0.get(Calendar.MONTH) + 1, calendar0.get(Calendar.DATE), calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH) + 1, calendar1.get(Calendar.DATE));
  }
}
