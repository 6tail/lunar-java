package com.nlf.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.nlf.calendar.util.HolidayUtil;
import com.nlf.calendar.util.LunarUtil;
import com.nlf.calendar.util.SolarUtil;

/**
 * 阳历日期
 *
 * @author 6tail
 */
public class Solar {
  /**
   * 2000年儒略日数(2000-1-1 12:00:00 UTC)
   */
  public static final double J2000 = 2451545;

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
   * 时
   */
  private final int hour;

  /**
   * 分
   */
  private final int minute;

  /**
   * 秒
   */
  private final int second;

  /**
   * 日历
   */
  private final Calendar calendar;

  /**
   * 默认使用当前日期初始化
   */
  public Solar() {
    this(new Date());
  }

  /**
   * 通过年月日初始化
   *
   * @param year  年
   * @param month 月，1到12
   * @param day   日，1到31
   */
  public Solar(int year, int month, int day) {
    this(year, month, day, 0, 0, 0);
  }

  /**
   * 通过年月日初始化
   *
   * @param year   年
   * @param month  月，1到12
   * @param day    日，1到31
   * @param hour   小时，0到23
   * @param minute 分钟，0到59
   * @param second 秒钟，0到59
   */
  public Solar(int year, int month, int day, int hour, int minute, int second) {
    calendar = ExactDate.fromYmdHms(year, month, day, hour, minute, second);
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  /**
   * 通过日期初始化
   *
   * @param date 日期
   */
  public Solar(Date date) {
    calendar = ExactDate.fromDate(date);
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH) + 1;
    day = calendar.get(Calendar.DATE);
    hour = calendar.get(Calendar.HOUR_OF_DAY);
    minute = calendar.get(Calendar.MINUTE);
    second = calendar.get(Calendar.SECOND);
  }

  /**
   * 通过日历初始化
   *
   * @param calendar 日历
   */
  public Solar(Calendar calendar) {
    calendar.set(Calendar.MILLISECOND, 0);
    this.calendar = calendar;
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH) + 1;
    day = calendar.get(Calendar.DATE);
    hour = calendar.get(Calendar.HOUR_OF_DAY);
    minute = calendar.get(Calendar.MINUTE);
    second = calendar.get(Calendar.SECOND);
  }

  /**
   * 通过儒略日初始化
   *
   * @param julianDay 儒略日
   */
  public Solar(double julianDay) {
    int d = (int) (julianDay + 0.5);
    double f = julianDay + 0.5 - d;
    int c;

    if (d >= 2299161) {
      c = (int) ((d - 1867216.25) / 36524.25);
      d += 1 + c - (int) (c * 1D / 4);
    }
    d += 1524;
    int year = (int) ((d - 122.1) / 365.25);
    d -= (int) (365.25 * year);
    int month = (int) (d * 1D / 30.601);
    d -= (int) (30.601 * month);
    int day = d;
    if (month > 13) {
      month -= 13;
      year -= 4715;
    } else {
      month -= 1;
      year -= 4716;
    }
    f *= 24;
    int hour = (int) f;

    f -= hour;
    f *= 60;
    int minute = (int) f;

    f -= minute;
    f *= 60;
    int second = (int) Math.round(f);
    if (second > 59) {
      second -= 60;
      minute++;
    }
    if (minute > 59) {
      minute -= 60;
      hour++;
    }

    calendar = ExactDate.fromYmdHms(year, month, day, hour, minute, second);
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  /**
   * 通过指定日期获取阳历
   *
   * @param date 日期
   * @return 阳历
   */
  public static Solar fromDate(Date date) {
    return new Solar(date);
  }

  /**
   * 通过指定日历获取阳历
   *
   * @param calendar 日历
   * @return 阳历
   */
  public static Solar fromCalendar(Calendar calendar) {
    return new Solar(calendar);
  }

  /**
   * 通过指定儒略日获取阳历
   *
   * @param julianDay 儒略日
   * @return 阳历
   */
  public static Solar fromJulianDay(double julianDay) {
    return new Solar(julianDay);
  }

  /**
   * 通过指定年月日获取阳历
   *
   * @param year  年
   * @param month 月，1到12
   * @param day   日，1到31
   * @return 阳历
   */
  public static Solar fromYmd(int year, int month, int day) {
    return new Solar(year, month, day);
  }

  /**
   * 通过指定年月日时分获取阳历
   *
   * @param year   年
   * @param month  月，1到12
   * @param day    日，1到31
   * @param hour   小时，0到23
   * @param minute 分钟，0到59
   * @param second 秒钟，0到59
   * @return 阳历
   */
  public static Solar fromYmdHms(int year, int month, int day, int hour, int minute, int second) {
    return new Solar(year, month, day, hour, minute, second);
  }

  /**
   * 通过八字获取阳历列表（晚子时日柱按当天，起始年为1900）
   *
   * @param yearGanZhi  年柱
   * @param monthGanZhi 月柱
   * @param dayGanZhi   日柱
   * @param timeGanZhi  时柱
   * @return 符合的阳历列表
   */
  public static List<Solar> fromBaZi(String yearGanZhi, String monthGanZhi, String dayGanZhi, String timeGanZhi) {
    return fromBaZi(yearGanZhi, monthGanZhi, dayGanZhi, timeGanZhi, 2);
  }

  /**
   * 通过八字获取阳历列表（起始年为1900）
   *
   * @param yearGanZhi  年柱
   * @param monthGanZhi 月柱
   * @param dayGanZhi   日柱
   * @param timeGanZhi  时柱
   * @param sect        流派，2晚子时日柱按当天，1晚子时日柱按明天
   * @return 符合的阳历列表
   */
  public static List<Solar> fromBaZi(String yearGanZhi, String monthGanZhi, String dayGanZhi, String timeGanZhi, int sect) {
    return fromBaZi(yearGanZhi, monthGanZhi, dayGanZhi, timeGanZhi, sect, 1900);
  }

  /**
   * 通过八字获取阳历列表
   *
   * @param yearGanZhi  年柱
   * @param monthGanZhi 月柱
   * @param dayGanZhi   日柱
   * @param timeGanZhi  时柱
   * @param sect        流派，2晚子时日柱按当天，1晚子时日柱按明天
   * @param baseYear    起始年
   * @return 符合的阳历列表
   */
  public static List<Solar> fromBaZi(String yearGanZhi, String monthGanZhi, String dayGanZhi, String timeGanZhi, int sect, int baseYear) {
    sect = (1 == sect) ? 1 : 2;
    List<Solar> l = new ArrayList<Solar>();
    Solar today = new Solar();
    Lunar lunar = today.getLunar();
    int offsetYear = LunarUtil.getJiaZiIndex(lunar.getYearInGanZhiExact()) - LunarUtil.getJiaZiIndex(yearGanZhi);
    if (offsetYear < 0) {
      offsetYear = offsetYear + 60;
    }
    int startYear = lunar.getYear() - offsetYear;
    int hour = 0;
    String timeZhi = timeGanZhi.substring(1);
    for (int i = 0, j = LunarUtil.ZHI.length; i < j; i++) {
      if (LunarUtil.ZHI[i].equals(timeZhi)) {
        hour = (i - 1) * 2;
      }
    }
    while (startYear >= baseYear) {
      int year = startYear - 1;
      int counter = 0;
      int month = 12;
      int day;
      boolean found = false;
      while (counter < 15) {
        if (year >= baseYear) {
          day = 1;
          Solar solar = new Solar(year, month, day, hour, 0, 0);
          lunar = solar.getLunar();
          if (lunar.getYearInGanZhiExact().equals(yearGanZhi) && lunar.getMonthInGanZhiExact().equals(monthGanZhi)) {
            found = true;
            break;
          }
        }
        month++;
        if (month > 12) {
          month = 1;
          year++;
        }
        counter++;
      }
      if (found) {
        counter = 0;
        month--;
        if (month < 1) {
          month = 12;
          year--;
        }
        day = 1;
        Solar solar = new Solar(year, month, day, hour, 0, 0);
        while (counter < 61) {
          lunar = solar.getLunar();
          String dgz = (2 == sect) ? lunar.getDayInGanZhiExact2() : lunar.getDayInGanZhiExact();
          if (lunar.getYearInGanZhiExact().equals(yearGanZhi) && lunar.getMonthInGanZhiExact().equals(monthGanZhi) && dgz.equals(dayGanZhi) && lunar.getTimeInGanZhi().equals(timeGanZhi)) {
            l.add(solar);
            break;
          }
          solar = solar.next(1);
          counter++;
        }
      }
      startYear -= 60;
    }
    return l;
  }

  /**
   * 是否闰年
   *
   * @return true/false 闰年/非闰年
   */
  public boolean isLeapYear() {
    return SolarUtil.isLeapYear(year);
  }

  /**
   * 获取星期，0代表周日，1代表周一
   *
   * @return 0123456
   */
  public int getWeek() {
    return calendar.get(Calendar.DAY_OF_WEEK) - 1;
  }

  /**
   * 获取星期的中文
   *
   * @return 日一二三四五六
   */
  public String getWeekInChinese() {
    return SolarUtil.WEEK[getWeek()];
  }

  /**
   * 获取节日，有可能一天会有多个节日
   *
   * @return 劳动节等
   */
  public List<String> getFestivals() {
    List<String> l = new ArrayList<String>();
    //获取几月几日对应的节日
    String f = SolarUtil.FESTIVAL.get(month + "-" + day);
    if (null != f) {
      l.add(f);
    }
    //计算几月第几个星期几对应的节日
    int weeks = (int) Math.ceil(day / 7D);
    //星期几，0代表星期天
    int week = getWeek();
    f = SolarUtil.WEEK_FESTIVAL.get(month + "-" + weeks + "-" + week);
    if (null != f) {
      l.add(f);
    }
    if (day + 7 >= SolarUtil.getDaysOfMonth(year, month)) {
      f = SolarUtil.WEEK_FESTIVAL.get(month + "-0-" + week);
      if (null != f) {
        l.add(f);
      }
    }
    return l;
  }

  /**
   * 获取非正式的节日，有可能一天会有多个节日
   *
   * @return 非正式的节日列表，如中元节
   */
  public List<String> getOtherFestivals() {
    List<String> l = new ArrayList<String>();
    List<String> fs = SolarUtil.OTHER_FESTIVAL.get(month + "-" + day);
    if (null != fs) {
      l.addAll(fs);
    }
    return l;
  }

  /**
   * 获取星座
   *
   * @return 星座
   * @deprecated 使用getXingZuo
   */
  public String getXingzuo() {
    return getXingZuo();
  }

  /**
   * 获取星座
   *
   * @return 星座
   */
  public String getXingZuo() {
    int index = 11;
    int y = month * 100 + day;
    if (y >= 321 && y <= 419) {
      index = 0;
    } else if (y >= 420 && y <= 520) {
      index = 1;
    } else if (y >= 521 && y <= 621) {
      index = 2;
    } else if (y >= 622 && y <= 722) {
      index = 3;
    } else if (y >= 723 && y <= 822) {
      index = 4;
    } else if (y >= 823 && y <= 922) {
      index = 5;
    } else if (y >= 923 && y <= 1023) {
      index = 6;
    } else if (y >= 1024 && y <= 1122) {
      index = 7;
    } else if (y >= 1123 && y <= 1221) {
      index = 8;
    } else if (y >= 1222 || y <= 119) {
      index = 9;
    } else if (y <= 218) {
      index = 10;
    }
    return SolarUtil.XINGZUO[index];
  }

  /**
   * 获取年份
   *
   * @return 如2015
   */
  public int getYear() {
    return year;
  }

  /**
   * 获取月份
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
   * 获取小时
   *
   * @return 0到23之间的数字
   */
  public int getHour() {
    return hour;
  }

  /**
   * 获取分钟
   *
   * @return 0到59之间的数字
   */
  public int getMinute() {
    return minute;
  }

  /**
   * 获取秒钟
   *
   * @return 0到59之间的数字
   */
  public int getSecond() {
    return second;
  }

  /**
   * 获取农历
   *
   * @return 农历
   */
  public Lunar getLunar() {
    return new Lunar(calendar.getTime());
  }

  /**
   * 获取儒略日
   *
   * @return 儒略日
   */
  public double getJulianDay() {
    int y = this.year;
    int m = this.month;
    double d = this.day + ((this.second * 1D / 60 + this.minute) / 60 + this.hour) / 24;
    int n = 0;
    boolean g = y * 372 + m * 31 + (int) d >= 588829;
    if (m <= 2) {
      m += 12;
      y--;
    }
    if (g) {
      n = (int) (y * 1D / 100);
      n = 2 - n + (int) (n * 1D / 4);
    }
    return (int) (365.25 * (y + 4716)) + (int) (30.6001 * (m + 1)) + d + n - 1524.5;
  }

  /**
   * 获取日历
   *
   * @return 日历
   */
  public Calendar getCalendar() {
    return calendar;
  }

  @Override
  public String toString() {
    return toYmd();
  }

  public String toYmd() {
    return String.format("%04d-%02d-%02d", year, month, day);
  }

  public String toYmdHms() {
    return toYmd() + " " + String.format("%02d:%02d:%02d", hour, minute, second);
  }

  public String toFullString() {
    StringBuilder s = new StringBuilder();
    s.append(toYmdHms());
    if (isLeapYear()) {
      s.append(" ");
      s.append("闰年");
    }
    s.append(" ");
    s.append("星期");
    s.append(getWeekInChinese());
    for (String f : getFestivals()) {
      s.append(" (");
      s.append(f);
      s.append(")");
    }
    for (String f : getOtherFestivals()) {
      s.append(" (");
      s.append(f);
      s.append(")");
    }
    s.append(" ");
    s.append(getXingZuo());
    s.append("座");
    return s.toString();
  }

  /**
   * 获取往后推几天的阳历日期，如果要往前推，则天数用负数
   *
   * @param days 天数
   * @return 阳历日期
   */
  public Solar next(int days) {
    return next(days, false);
  }

  /**
   * 取往后推几天的阳历日期，如果要往前推，则天数用负数
   *
   * @param days        天数
   * @param onlyWorkday 是否仅限工作日
   * @return 阳历日期
   */
  public Solar next(int days, boolean onlyWorkday) {
    Calendar c = ExactDate.fromYmdHms(year, month, day, hour, minute, second);
    if (0 != days) {
      if (!onlyWorkday) {
        c.add(Calendar.DATE, days);
      } else {
        int rest = Math.abs(days);
        int add = days < 1 ? -1 : 1;
        while (rest > 0) {
          c.add(Calendar.DATE, add);
          boolean work = true;
          Holiday holiday = HolidayUtil.getHoliday(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
          if (null == holiday) {
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (1 == week || 7 == week) {
              work = false;
            }
          } else {
            work = holiday.isWork();
          }
          if (work) {
            rest--;
          }
        }
      }
    }
    return new Solar(c);
  }

}
