package com.nlf.calendar.eightchar;

import com.nlf.calendar.util.LunarUtil;

/**
 * 流月
 *
 * @author 6tail
 */
public class LiuYue {
  /**
   * 序数，0-9
   */
  private final int index;

  private final LiuNian liuNian;

  public LiuYue(LiuNian liuNian, int index) {
    this.liuNian = liuNian;
    this.index = index;
  }

  public int getIndex() {
    return index;
  }

  /**
   * 获取中文的月
   *
   * @return 中文月，如正
   */
  public String getMonthInChinese() {
    return LunarUtil.MONTH[index + 1];
  }

  /**
   * 获取干支
   * <p>
   * 《五虎遁》
   * 甲己之年丙作首，
   * 乙庚之年戊为头，
   * 丙辛之年寻庚上，
   * 丁壬壬寅顺水流，
   * 若问戊癸何处走，
   * 甲寅之上好追求。
   *
   * @return 干支
   */
  public String getGanZhi() {
    int offset = 0;
    String yearGan = liuNian.getGanZhi().substring(0, 1);
    if ("甲".equals(yearGan) || "己".equals(yearGan)) {
      offset = 2;
    } else if ("乙".equals(yearGan) || "庚".equals(yearGan)) {
      offset = 4;
    } else if ("丙".equals(yearGan) || "辛".equals(yearGan)) {
      offset = 6;
    } else if ("丁".equals(yearGan) || "壬".equals(yearGan)) {
      offset = 8;
    }
    String gan = LunarUtil.GAN[(index + offset) % 10 + 1];
    String zhi = LunarUtil.ZHI[(index + LunarUtil.BASE_MONTH_ZHI_INDEX) % 12 + 1];
    return gan + zhi;
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
