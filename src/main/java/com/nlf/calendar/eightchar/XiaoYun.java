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

  /**
   * 是否顺推
   */
  private final boolean forward;

  private final Lunar lunar;

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
}
