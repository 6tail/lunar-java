package com.nlf.calendar;

/**
 * 节气
 *
 * @author 6tail
 */
public class JieQi {

  /** 名称 */
  private String name;

  /** 阳历日期 */
  private Solar solar;

  public JieQi() {
  }

  public JieQi(String name, Solar solar) {
    this.name = name;
    this.solar = solar;
  }

  /**
   * 获取名称
   * @return 名称
   */
  public String getName() {
    return name;
  }

  /**
   * 设置名称
   * @param name 名称
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 获取阳历日期
   * @return 阳历日期
   */
  public Solar getSolar() {
    return solar;
  }

  /**
   * 设置阳历日期
   * @param solar 阳历日期
   */
  public void setSolar(Solar solar) {
    this.solar = solar;
  }

}
