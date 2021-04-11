package com.nlf.calendar;

import com.nlf.calendar.util.ShouXingUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 农历年
 *
 * @author 6tail
 */
public class LunarYear {

  /**
   * 农历年
   */
  private int year;

  /**
   * 农历月们
   */
  private List<LunarMonth> months = new ArrayList<LunarMonth>();

  /**
   * 节气儒略日们
   */
  private List<Double> jieQiJulianDays = new ArrayList<Double>();

  /**
   * 初始化
   *
   * @param lunarYear 农历年
   */
  public LunarYear(int lunarYear) {
    this.year = lunarYear;
    compute();
  }

  /**
   * 通过农历年初始化
   *
   * @param lunarYear 农历年
   * @return 农历年
   */
  public static LunarYear fromYear(int lunarYear) {
    return new LunarYear(lunarYear);
  }

  private void compute() {
    // 节气(中午12点)
    double[] jq = new double[25];
    // 合朔，即每月初一(中午12点)
    double[] hs = new double[15];
    // 每月天数
    int[] dayCounts = new int[hs.length - 1];

    int year = this.year - 2000;
    // 从上年的大雪到下年的大寒
    for (int i = 0, j = Lunar.JIE_QI_IN_USE.length; i < j; i++) {
      // 精确的节气
      double t = 36525 * ShouXingUtil.saLonT((year + (17 + i) * 15d / 360) * ShouXingUtil.PI_2);
      t += ShouXingUtil.ONE_THIRD - ShouXingUtil.dtT(t);
      jieQiJulianDays.add(t + Solar.J2000);
      // 按中午12点算的节气
      if (i > 0 && i < 26) {
        jq[i - 1] = Math.round(t);
      }
    }

    // 冬至前的初一
    double w = ShouXingUtil.calcShuo(jq[0]);
    if (w > jq[0]) {
      w -= 29.5306;
    }
    // 递推每月初一
    for (int i = 0, j = hs.length; i < j; i++) {
      hs[i] = ShouXingUtil.calcShuo(w + 29.5306 * i);
    }
    // 每月天数
    for (int i = 0, j = dayCounts.length; i < j; i++) {
      dayCounts[i] = (int) (hs[i + 1] - hs[i]);
    }

    int leap = -1;
    if (hs[13] <= jq[24]) {
      int i = 1;
      while (hs[i + 1] > jq[2 * i] && i < 13) {
        i++;
      }
      leap = i;
    }

    int y = this.year - 1;
    int m = 11;
    for (int i = 0, j = dayCounts.length; i < j; i++) {
      boolean isLeap = false;
      if (i == leap) {
        isLeap = true;
        m--;
      }
      this.months.add(new LunarMonth(y, isLeap ? -m : m, dayCounts[i], hs[i] + Solar.J2000));
      m++;
      if (m == 13) {
        m = 1;
        y++;
      }
    }
  }

  /**
   * 获取农历年
   *
   * @return 农历年
   */
  public int getYear() {
    return year;
  }

  /**
   * 获取农历月们
   *
   * @return 农历月们
   */
  public List<LunarMonth> getMonths() {
    return months;
  }

  /**
   * 获取节气儒略日们
   *
   * @return 节气儒略日们
   */
  public List<Double> getJieQiJulianDays() {
    return jieQiJulianDays;
  }

  /**
   * 获取农历月
   *
   * @param lunarMonth 月，1-12，闰月为负数，如闰2月为-2
   * @return 农历月
   */
  public LunarMonth getMonth(int lunarMonth) {
    for (LunarMonth m : months) {
      if (m.getYear() == year && m.getMonth() == lunarMonth) {
        return m;
      }
    }
    return null;
  }

  /**
   * 获取闰月
   *
   * @return 闰月数字，1代表闰1月，0代表无闰月
   */
  public int getLeapMonth() {
    for (LunarMonth m : months) {
      if (m.getYear() == year && m.isLeap()) {
        return Math.abs(m.getMonth());
      }
    }
    return 0;
  }

  @Override
  public String toString() {
    return year + "";
  }

  public String toFullString() {
    return year + "年";
  }
}
