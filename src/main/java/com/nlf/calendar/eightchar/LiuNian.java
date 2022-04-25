package com.nlf.calendar.eightchar;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.util.LunarUtil;

/**
 * 流年
 *
 * @author 6tail
 */
public class LiuNian {
  /**
   * 序数，0-9
   */
  private final int index;

  /**
   * 大运
   */
  private final DaYun daYun;

  /**
   * 年
   */
  private final int year;

  /**
   * 年龄
   */
  private final int age;

  private final Lunar lunar;

  public LiuNian(DaYun daYun, int index) {
    this.daYun = daYun;
    this.lunar = daYun.getLunar();
    this.index = index;
    this.year = daYun.getStartYear() + index;
    this.age = daYun.getStartAge() + index;
  }

  public int getIndex() {
    return index;
  }

  public int getYear() {
    return year;
  }

  public int getAge() {
    return age;
  }

  /**
   * 获取干支
   *
   * @return 干支
   */
  public String getGanZhi() {
    // 干支与出生日期和起运日期都没关系
    int offset = LunarUtil.getJiaZiIndex(lunar.getJieQiTable().get("立春").getLunar().getYearInGanZhiExact()) + this.index;
    if (daYun.getIndex() > 0) {
      offset += daYun.getStartAge() - 1;
    }
    offset %= LunarUtil.JIA_ZI.length;
    return LunarUtil.JIA_ZI[offset];
  }

  /**
   * 获取所在旬
   * @return 旬
   */
  public String getXun(){
    return LunarUtil.getXun(getGanZhi());
  }

  /**
   * 获取旬空(空亡)
   * @return 旬空(空亡)
   */
  public String getXunKong(){
    return LunarUtil.getXunKong(getGanZhi());
  }

  /**
   * 获取流月
   * @return 流月
   */
  public LiuYue[] getLiuYue() {
    int n = 12;
    LiuYue[] l = new LiuYue[n];
    for (int i = 0; i < n; i++) {
      l[i] = new LiuYue(this, i);
    }
    return l;
  }
}
