package com.nlf.calendar;

import java.util.*;

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
   * 时区
   */
  public static final TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT+8");

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
    if (1582 == year && 10 == month) {
      if (day > 4 && day < 15) {
        throw new IllegalArgumentException(String.format("wrong solar year %d month %d day %d", year, month, day));
      }
    }
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException(String.format("wrong month %d", month));
    }
    if (day < 1 || day > 31) {
      throw new IllegalArgumentException(String.format("wrong day %d", day));
    }
    if (hour < 0 || hour > 23) {
      throw new IllegalArgumentException(String.format("wrong hour %d", hour));
    }
    if (minute < 0 || minute > 59) {
      throw new IllegalArgumentException(String.format("wrong minute %d", minute));
    }
    if (second < 0 || second > 59) {
      throw new IllegalArgumentException(String.format("wrong second %d", second));
    }
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
    Calendar c = Calendar.getInstance(TIME_ZONE);
    c.setTime(date);
    c.set(Calendar.MILLISECOND, 0);
    year = c.get(Calendar.YEAR);
    month = c.get(Calendar.MONTH) + 1;
    day = c.get(Calendar.DATE);
    hour = c.get(Calendar.HOUR_OF_DAY);
    minute = c.get(Calendar.MINUTE);
    second = c.get(Calendar.SECOND);
  }

  /**
   * 通过日历初始化
   *
   * @param calendar 日历
   */
  public Solar(Calendar calendar) {
    calendar.set(Calendar.MILLISECOND, 0);
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
    if (hour > 23) {
      hour -= 24;
      day += 1;
    }
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
    List<Integer> years = new ArrayList<Integer>();
    Solar today = fromDate(new Date());
    int offsetYear = LunarUtil.getJiaZiIndex(today.getLunar().getYearInGanZhiExact())-LunarUtil.getJiaZiIndex(yearGanZhi);
    if(offsetYear < 0){
      offsetYear += 60;
    }
    int startYear = today.getYear() - offsetYear - 1;
    int minYear = baseYear - 2;
    while (startYear >= minYear) {
      years.add(startYear);
      startYear -= 60;
    }
    List<Integer> hours = new ArrayList<Integer>(2);
    String timeZhi = timeGanZhi.substring(1);
    for(int i = 1, j = LunarUtil.ZHI.length; i < j; i++){
      if(LunarUtil.ZHI[i].equals(timeZhi)){
        hours.add((i - 1) * 2);
        break;
      }
    }
    if ("子".equals(timeZhi)) {
      hours.add(23);
    }
    for (int hour: hours) {
      for (Integer y : years) {
        int maxYear = y + 3;
        int year = y;
        int month = 11;
        if (year < baseYear) {
          year = baseYear;
          month = 1;
        }
        Solar solar = fromYmdHms(year, month, 1, hour, 0, 0);
        while (solar.getYear() <= maxYear) {
          Lunar lunar = solar.getLunar();
          String dgz = (2 == sect) ? lunar.getDayInGanZhiExact2() : lunar.getDayInGanZhiExact();
          if (lunar.getYearInGanZhiExact().equals(yearGanZhi) && lunar.getMonthInGanZhiExact().equals(monthGanZhi) && dgz.equals(dayGanZhi) && lunar.getTimeInGanZhi().equals(timeGanZhi)) {
            l.add(solar);
            break;
          }
          solar = solar.next(1);
        }
      }
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
    Solar start = fromYmd(1582, 10, 15);
    int y = year;
    int m = month;
    int d = day;
    Solar current = fromYmd(y, m, d);
    // 蔡勒公式
    if (m < 3) {
      m += 12;
      y--;
    }
    int c = y / 100;
    y = y - c * 100;
    int x = y + y / 4 + c / 4 - 2 * c;
    int w;
    if (current.isBefore(start)) {
      w = (x + 13 * (m + 1) / 5 + d + 2) % 7;
    } else {
      w = (x + 26 * (m + 1) / 10 + d - 1) % 7;
    }
    return (w + 7) % 7;
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
    if (day + 7 > SolarUtil.getDaysOfMonth(year, month)) {
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
    return new Lunar(this);
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
   * 阳历日期相减，获得相差天数
   * @param solar 阳历
   * @return 天数
   */
  public int subtract(Solar solar) {
    return SolarUtil.getDaysBetween(solar.getYear(), solar.getMonth(), solar.getDay(), year, month, day);
  }

  /**
   * 阳历日期相减，获得相差分钟数
   * @param solar 阳历
   * @return 分钟数
   */
  public int subtractMinute(Solar solar) {
    int days = subtract(solar);
    int cm = hour * 60 + minute;
    int sm = solar.getHour() * 60 + solar.getMinute();
    int m = cm - sm;
    if (m < 0) {
      m += 1440;
      days--;
    }
    m += days * 1440;
    return m;
  }

  /**
   * 是否在指定日期之后
   * @param solar 阳历
   * @return true/false
   */
  public boolean isAfter(Solar solar) {
    if (year > solar.getYear()) {
      return true;
    }
    if (year < solar.getYear()) {
      return false;
    }
    if (month > solar.getMonth()) {
      return true;
    }
    if (month < solar.getMonth()) {
      return false;
    }
    if (day > solar.getDay()) {
      return true;
    }
    if (day < solar.getDay()) {
      return false;
    }
    if (hour > solar.getHour()) {
      return true;
    }
    if (hour < solar.getHour()) {
      return false;
    }
    if (minute > solar.getMinute()) {
      return true;
    }
    if (minute < solar.getMinute()) {
      return false;
    }
    return second > solar.getSecond();
  }

  /**
   * 是否在指定日期之前
   * @param solar 阳历
   * @return true/false
   */
  public boolean isBefore(Solar solar) {
    if (year > solar.getYear()) {
      return false;
    }
    if (year < solar.getYear()) {
      return true;
    }
    if (month > solar.getMonth()) {
      return false;
    }
    if (month < solar.getMonth()) {
      return true;
    }
    if (day > solar.getDay()) {
      return false;
    }
    if (day < solar.getDay()) {
      return true;
    }
    if (hour > solar.getHour()) {
      return false;
    }
    if (hour < solar.getHour()) {
      return true;
    }
    if (minute > solar.getMinute()) {
      return false;
    }
    if (minute < solar.getMinute()) {
      return true;
    }
    return second < solar.getSecond();
  }

  /**
   * 年推移
   * @param years 年数
   * @return 阳历
   */
  public Solar nextYear(int years) {
    int y = year + years;
    int m = month;
    int d = day;
    if (1582 == y && 10 == m) {
      if (d > 4 && d < 15) {
        d += 10;
      }
    } else if (2 == m) {
      if (d > 28) {
        if (!SolarUtil.isLeapYear(y)) {
          d = 28;
        }
      }
    }
    return fromYmdHms(y, m, d, hour, minute, second);
  }

  /**
   * 月推移
   * @param months 月数
   * @return 阳历
   */
  public Solar nextMonth(int months) {
    SolarMonth month = SolarMonth.fromYm(year, this.month).next(months);
    int y = month.getYear();
    int m = month.getMonth();
    int d = day;
    if (1582 == y && 10 == m) {
      if (d > 4 && d < 15) {
        d += 10;
      }
    } else {
      int maxDay = SolarUtil.getDaysOfMonth(y, m);
      if (d > maxDay) {
        d = maxDay;
      }
    }
    return fromYmdHms(y, m, d, hour, minute, second);
  }

  /**
   * 获取往后推几天的阳历日期，如果要往前推，则天数用负数
   *
   * @param days 天数
   * @return 阳历日期
   */
  public Solar next(int days) {
    int y = year;
    int m = month;
    int d = day;
    if (1582 == y && 10 == m) {
      if (d > 4) {
        d -= 10;
      }
    }
    if (days > 0) {
      d += days;
      int daysInMonth = SolarUtil.getDaysOfMonth(y, m);
      while (d > daysInMonth) {
        d -= daysInMonth;
        m++;
        if (m > 12) {
          m = 1;
          y++;
        }
        daysInMonth = SolarUtil.getDaysOfMonth(y, m);
      }
    } else if (days < 0) {
      while (d + days <= 0) {
        m--;
        if (m < 1) {
          m = 12;
          y--;
        }
        d += SolarUtil.getDaysOfMonth(y, m);
      }
      d += days;
    }
    if (1582 == y && 10 == m) {
      if (d > 4 ) {
        d += 10;
      }
    }
    return fromYmdHms(y, m, d, hour, minute, second);
  }

  /**
   * 取往后推几天的阳历日期，如果要往前推，则天数用负数
   *
   * @param days        天数
   * @param onlyWorkday 是否仅限工作日
   * @return 阳历日期
   */
  public Solar next(int days, boolean onlyWorkday) {
    if(!onlyWorkday) {
      return next(days);
    }
    Solar solar = fromYmdHms(year, month, day, hour, minute, second);
    if (days != 0) {
      int rest = Math.abs(days);
      int add = days < 0 ? -1 : 1;
      while (rest > 0) {
        solar = solar.next(add);
        boolean work = true;
        Holiday holiday = HolidayUtil.getHoliday(solar.getYear(), solar.getMonth(), solar.getDay());
        if (null == holiday) {
          int week = solar.getWeek();
          if (0 == week || 6 == week) {
            work = false;
          }
        } else {
          work = holiday.isWork();
        }
        if (work) {
          rest -= 1;
        }
      }
    }
    return solar;
  }

  /**
   * 小时推移
   * @param hours 小时数
   * @return 阳历
   */
  public Solar nextHour(int hours) {
    int h = hour + hours;
    int n = h < 0 ? -1 : 1;
    int hour = Math.abs(h);
    int days = hour / 24 * n;
    hour = (hour % 24) * n;
    if (hour < 0) {
      hour += 24;
      days--;
    }
    Solar solar = next(days);
    return fromYmdHms(solar.getYear(), solar.getMonth(), solar.getDay(), hour, solar.getMinute(), solar.getSecond());
  }

  /**
   * 获取薪资比例(感谢 https://gitee.com/smr1987)
   * @return 薪资比例：1/2/3
   */
  public int getSalaryRate() {
    // 元旦节
    if (month == 1 && day == 1) {
      return 3;
    }
    // 劳动节
    if (month == 5 && day == 1) {
      return 3;
    }
    // 国庆
    if (month == 10 && day >= 1 && day <= 3) {
      return 3;
    }
    Lunar lunar = getLunar();
    // 春节
    if (lunar.getMonth() == 1 && lunar.getDay() >= 1 && lunar.getDay() <= 3) {
      return 3;
    }
    // 端午
    if (lunar.getMonth() == 5 && lunar.getDay() == 5) {
      return 3;
    }
    // 中秋
    if (lunar.getMonth() == 8 && lunar.getDay() == 15) {
      return 3;
    }
    // 清明
    if ("清明".equals(lunar.getJieQi())) {
      return 3;
    }
    Holiday holiday = HolidayUtil.getHoliday(year, month, day);
    if (null != holiday) {
      // 法定假日非上班
      if (!holiday.isWork()) {
        return 2;
      }
    } else {
      // 周末
      int week = getWeek();
      if (week == 6 || week == 0) {
        return 2;
      }
    }
    // 工作日
    return 1;
  }
}
