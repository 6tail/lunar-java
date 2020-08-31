package com.nlf.calendar.eightchar;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.util.LunarUtil;

/**
 * 小运
 *
 * @author 6tail
 */
public class XiaoYun {
  /**
   * 序数，0-9
   */
  private int index;
  /**
   * 大运
   */
  private DaYun daYun;
  /**
   * 年
   */
  private int year;
  /**
   * 年龄
   */
  private int age;
  /**
   * 是否顺推
   */
  private boolean forward;
  private Lunar lunar;

  public XiaoYun(DaYun daYun, int index, boolean forward) {
    this.daYun = daYun;
    this.lunar = daYun.getLunar();
    this.index = index;
    this.year = daYun.getStartYear() + index;
    this.age = daYun.getStartAge() + index;
    this.forward = forward;
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
    int offset = LunarUtil.getJiaZiIndex(lunar.getTimeInGanZhi());
    int add = this.index + 1;
    if (daYun.getIndex() > 0) {
      add += daYun.getStartAge() - 1;
    }
    offset += forward ? add : -add;
    int size = LunarUtil.JIA_ZI.length;
    while (offset < 0) {
      offset += size;
    }
    offset %= size;
    return LunarUtil.JIA_ZI[offset];
  }
}
