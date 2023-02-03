package com.nlf.calendar.eightchar;

import com.nlf.calendar.EightChar;
import com.nlf.calendar.JieQi;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.util.LunarUtil;

/**
 * 运
 *
 * @author 6tail
 */
public class Yun {
  /**
   * 性别(1男，0女)
   */
  private final int gender;

  /**
   * 起运年数
   */
  private int startYear;

  /**
   * 起运月数
   */
  private int startMonth;

  /**
   * 起运天数
   */
  private int startDay;

  /**
   * 起运小时数
   */
  private int startHour;

  /**
   * 是否顺推
   */
  private final boolean forward;

  private final Lunar lunar;

  /**
   * 使用默认流派1初始化运
   * @param eightChar 八字
   * @param gender 性别，1男，0女
   */
  public Yun(EightChar eightChar, int gender) {
    this(eightChar, gender, 1);
  }

  /**
   * 初始化运
   * @param eightChar 八字
   * @param gender 性别，1男，0女
   * @param sect 流派，1按天数和时辰数计算，3天1年，1天4个月，1时辰10天；2按分钟数计算
   */
  public Yun(EightChar eightChar, int gender, int sect) {
    this.lunar = eightChar.getLunar();
    this.gender = gender;
    // 阳
    boolean yang = 0 == lunar.getYearGanIndexExact() % 2;
    // 男
    boolean man = 1 == gender;
    forward = (yang && man) || (!yang && !man);
    computeStart(sect);
  }

  /**
   * 起运计算
   */
  private void computeStart(int sect) {
    // 上节
    JieQi prev = lunar.getPrevJie();
    // 下节
    JieQi next = lunar.getNextJie();
    // 出生日期
    Solar current = lunar.getSolar();
    // 阳男阴女顺推，阴男阳女逆推
    Solar start = forward ? current : prev.getSolar();
    Solar end = forward ? next.getSolar() : current;

    int year;
    int month;
    int day;
    int hour = 0;

    if (2 == sect) {
      long minutes = end.subtractMinute(start);
      long y = minutes / 4320;
      minutes -= y * 4320;
      long m = minutes / 360;
      minutes -= m * 360;
      long d = minutes / 12;
      minutes -= d * 12;
      long h = minutes * 2;
      year = (int)y;
      month = (int)m;
      day = (int)d;
      hour = (int)h;
    } else {
      int endTimeZhiIndex = (end.getHour() == 23) ? 11 : LunarUtil.getTimeZhiIndex(end.toYmdHms().substring(11, 16));
      int startTimeZhiIndex = (start.getHour() == 23) ? 11 : LunarUtil.getTimeZhiIndex(start.toYmdHms().substring(11, 16));
      // 时辰差
      int hourDiff = endTimeZhiIndex - startTimeZhiIndex;
      // 天数差
      int dayDiff = end.subtract(start);
      if (hourDiff < 0) {
        hourDiff += 12;
        dayDiff--;
      }
      int monthDiff = hourDiff * 10 / 30;
      month = dayDiff * 4 + monthDiff;
      day = hourDiff * 10 - monthDiff * 30;
      year = month / 12;
      month = month - year * 12;
    }
    this.startYear = year;
    this.startMonth = month;
    this.startDay = day;
    this.startHour = hour;
  }

  /**
   * 获取性别
   *
   * @return 性别(1男 ， 0女)
   */
  public int getGender() {
    return gender;
  }

  /**
   * 获取起运年数
   *
   * @return 起运年数
   */
  public int getStartYear() {
    return startYear;
  }

  /**
   * 获取起运月数
   *
   * @return 起运月数
   */
  public int getStartMonth() {
    return startMonth;
  }

  /**
   * 获取起运天数
   *
   * @return 起运天数
   */
  public int getStartDay() {
    return startDay;
  }

  /**
   * 获取起运小时数
   *
   * @return 起运小时数
   */
  public int getStartHour() {
    return startHour;
  }

  /**
   * 是否顺推
   *
   * @return true/false
   */
  public boolean isForward() {
    return forward;
  }

  public Lunar getLunar() {
    return lunar;
  }

  /**
   * 获取起运的阳历日期
   *
   * @return 阳历日期
   */
  public Solar getStartSolar() {
    Solar solar = lunar.getSolar();
    solar = solar.nextYear(startYear);
    solar = solar.nextMonth(startMonth);
    solar = solar.next(startDay);
    return solar.nextHour(startHour);
  }

  /**
   * 获取10轮大运
   *
   * @return 大运
   */
  public DaYun[] getDaYun() {
    return getDaYun(10);
  }

  /**
   * 获取大运
   *
   * @param n 轮数
   * @return 大运
   */
  public DaYun[] getDaYun(int n) {
    DaYun[] l = new DaYun[n];
    for (int i = 0; i < n; i++) {
      l[i] = new DaYun(this, i);
    }
    return l;
  }
}

