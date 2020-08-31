package com.nlf.calendar.eightchar;

import com.nlf.calendar.EightChar;
import com.nlf.calendar.JieQi;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.util.LunarUtil;

import java.util.Calendar;

/**
 * 运
 *
 * @author 6tail
 */
public class Yun {
  /**
   * 性别(1男，0女)
   */
  private int gender;
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
   * 是否顺推
   */
  private boolean forward;
  private Lunar lunar;

  public Yun(EightChar eightChar, int gender) {
    this.lunar = eightChar.getLunar();
    this.gender = gender;
    // 阳
    boolean yang = 0 == lunar.getYearGanIndexExact() % 2;
    // 男
    boolean man = 1 == gender;
    forward = (yang && man) || (!yang && !man);
    computeStart();
  }

  /**
   * 起运计算
   */
  private void computeStart() {
    // 上节
    JieQi prev = lunar.getPrevJie();
    // 下节
    JieQi next = lunar.getNextJie();
    // 出生日期
    Solar current = lunar.getSolar();
    // 阳男阴女顺推，阴男阳女逆推
    Solar start = forward ? current : prev.getSolar();
    Solar end = forward ? next.getSolar() : current;
    // 时辰差
    int hourDiff = LunarUtil.getTimeZhiIndex(end.toYmdHms().substring(11, 16)) - LunarUtil.getTimeZhiIndex(start.toYmdHms().substring(11, 16));
    Calendar endCalendar = Calendar.getInstance();
    endCalendar.set(end.getYear(), end.getMonth() - 1, end.getDay(), 0, 0, 0);
    endCalendar.set(Calendar.MILLISECOND, 0);
    Calendar startCalendar = Calendar.getInstance();
    startCalendar.set(start.getYear(), start.getMonth() - 1, start.getDay(), 0, 0, 0);
    startCalendar.set(Calendar.MILLISECOND, 0);
    // 天数差
    int dayDiff = (int) ((endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis()) / (1000 * 3600 * 24));
    if (hourDiff < 0) {
      hourDiff += 12;
      dayDiff--;
    }
    int monthDiff = hourDiff * 10 / 30;
    int month = dayDiff * 4 + monthDiff;
    int day = hourDiff * 10 - monthDiff * 30;
    int year = month / 12;
    month = month - year * 12;
    this.startYear = year;
    this.startMonth = month;
    this.startDay = day;
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
    Solar birth = lunar.getSolar();
    Calendar c = Calendar.getInstance();
    c.set(birth.getYear() + startYear, birth.getMonth() - 1 + startMonth, birth.getDay() + startDay, 0, 0, 0);
    return Solar.fromCalendar(c);
  }

  /**
   * 获取大运
   *
   * @return 大运
   */
  public DaYun[] getDaYun() {
    int n = 10;
    DaYun[] l = new DaYun[n];
    for (int i = 0; i < n; i++) {
      l[i] = new DaYun(this, i);
    }
    return l;
  }
}

