package com.nlf.calendar;

/**
 * 佛历因果犯忌
 *
 * @author 6tail
 */
public class FotoFestival {

  /**
   * 是日何日，如：雷斋日
   */
  private final String name;

  /**
   * 犯之因果，如：犯者夺纪
   */
  private final String result;

  /**
   * 是否每月同
   */
  private final boolean everyMonth;

  /**
   * 备注，如：宜先一日即戒
   */
  private final String remark;

  public FotoFestival(String name, String result, boolean everyMonth, String remark) {
    this.name = name;
    this.result = null == result ? "" : result;
    this.everyMonth = everyMonth;
    this.remark = null == remark ? "" : remark;
  }

  public FotoFestival(String name) {
    this(name, null);
  }

  public FotoFestival(String name, String result) {
    this(name, result, false);
  }

  public FotoFestival(String name, String result, boolean everyMonth) {
    this(name, result, everyMonth, null);
  }

  public String getName() {
    return name;
  }

  public String getResult() {
    return result;
  }

  public boolean isEveryMonth() {
    return everyMonth;
  }

  public String getRemark() {
    return remark;
  }

  @Override
  public String toString() {
    return name;
  }

  public String toFullString() {
    StringBuilder s = new StringBuilder();
    s.append(name);
    if (null != result && result.length() > 0) {
      s.append(" ");
      s.append(result);
    }
    if (null != remark && remark.length() > 0) {
      s.append(" ");
      s.append(remark);
    }
    return s.toString();
  }
}
