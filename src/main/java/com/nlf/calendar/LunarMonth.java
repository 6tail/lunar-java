package com.nlf.calendar;

import com.nlf.calendar.util.LunarUtil;

import java.util.List;

/**
 * 农历月
 *
 * @author 6tail
 */
public class LunarMonth {

  /**
   * 农历年
   */
  private int year;

  /**
   * 农历月：1-12，闰月为负数，如闰2月为-2
   */
  private int month;

  /**
   * 天数，大月30天，小月29天
   */
  private int dayCount;

  /**
   * 初一的儒略日
   */
  private double firstJulianDay;

  /**
   * 初始化
   *
   * @param lunarYear      农历年
   * @param lunarMonth     农历月：1-12，闰月为负数，如闰2月为-2
   * @param dayCount       天数
   * @param firstJulianDay 初一的儒略日
   */
  public LunarMonth(int lunarYear, int lunarMonth, int dayCount, double firstJulianDay) {
    this.year = lunarYear;
    this.month = lunarMonth;
    this.dayCount = dayCount;
    this.firstJulianDay = firstJulianDay;
  }

  /**
   * 通过农历年月初始化
   *
   * @param lunarYear  农历年
   * @param lunarMonth 农历月：1-12，闰月为负数，如闰2月为-2
   * @return 农历月
   */
  public static LunarMonth fromYm(int lunarYear, int lunarMonth) {
    return LunarYear.fromYear(lunarYear).getMonth(lunarMonth);
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
   * 获取农历月
   *
   * @return 农历月：1-12，闰月为负数，如闰2月为-2
   */
  public int getMonth() {
    return month;
  }

  /**
   * 是否闰月
   *
   * @return true/false
   */
  public boolean isLeap() {
    return month < 0;
  }

  /**
   * 获取天数
   *
   * @return 天数
   */
  public int getDayCount() {
    return dayCount;
  }

  /**
   * 获取初一的儒略日
   *
   * @return 初一的儒略日
   */
  public double getFirstJulianDay() {
    return firstJulianDay;
  }

  /**
   * 获取太岁方位
   *
   * @return 方位，如艮
   */
  public String getPositionTaiSui() {
    String p;
    int m = Math.abs(month) % 4;
    switch (m) {
      case 0:
        p = "巽";
        break;
      case 1:
        p = "艮";
        break;
      case 3:
        p = "坤";
        break;
      default:
        p = LunarUtil.POSITION_GAN[Solar.fromJulianDay(this.getFirstJulianDay()).getLunar().getMonthGanIndex()];
    }
    return p;
  }

  /**
   * 获取太岁方位描述
   *
   * @return 方位描述，如东北
   */
  public String getPositionTaiSuiDesc() {
    return LunarUtil.POSITION_DESC.get(getPositionTaiSui());
  }

  /**
   * 获取月九星
   *
   * @return 九星
   */
  public NineStar getNineStar() {
    int index = LunarYear.fromYear(year).getZhiIndex() % 3;
    int m = Math.abs(month);
    int monthZhiIndex = (13 + m) % 12;
    int n = 27 - index * 3;
    if (monthZhiIndex < LunarUtil.BASE_MONTH_ZHI_INDEX) {
      n -= 3;
    }
    int offset = (n - monthZhiIndex) % 9;
    return NineStar.fromIndex(offset);
  }

  @Override
  public String toString() {
    return year + "年" + (isLeap() ? "闰" : "") + LunarUtil.MONTH[Math.abs(month)] + "月(" + dayCount + "天)";
  }

  /**
   * 获取往后推几个月的阴历月，如果要往前推，则月数用负数
   *
   * @param n 月数
   * @return 阴历月
   */
  public LunarMonth next(int n) {
    if (0 == n) {
      return LunarMonth.fromYm(year, month);
    } else if (n > 0) {
      int rest = n;
      int ny = year;
      int iy = ny;
      int im = month;
      int index = 0;
      List<LunarMonth> months = LunarYear.fromYear(ny).getMonths();
      while (true) {
        int size = months.size();
        for (int i = 0; i < size; i++) {
          LunarMonth m = months.get(i);
          if (m.getYear() == iy && m.getMonth() == im) {
            index = i;
            break;
          }
        }
        int more = size - index - 1;
        if (rest < more) {
          break;
        }
        rest -= more;
        LunarMonth lastMonth = months.get(size - 1);
        iy = lastMonth.getYear();
        im = lastMonth.getMonth();
        ny++;
        months = LunarYear.fromYear(ny).getMonths();
      }
      return months.get(index + rest);
    } else {
      int rest = -n;
      int ny = year;
      int iy = ny;
      int im = month;
      int index = 0;
      List<LunarMonth> months = LunarYear.fromYear(ny).getMonths();
      while (true) {
        int size = months.size();
        for (int i = 0; i < size; i++) {
          LunarMonth m = months.get(i);
          if (m.getYear() == iy && m.getMonth() == im) {
            index = i;
            break;
          }
        }
        if (rest <= index) {
          break;
        }
        rest -= index;
        LunarMonth firstMonth = months.get(0);
        iy = firstMonth.getYear();
        im = firstMonth.getMonth();
        ny--;
        months = LunarYear.fromYear(ny).getMonths();
      }
      return months.get(index - rest);
    }
  }

}
