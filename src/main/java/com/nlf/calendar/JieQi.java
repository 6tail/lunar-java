package com.nlf.calendar;

import com.nlf.calendar.util.LunarUtil;

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

  /** 是否节令 */
  private boolean jie;

  /** 是否气令 */
  private boolean qi;

  public JieQi() {
  }

  /**
   * 初始化
   * @param name 名称
   * @param solar 阳历日期
   */
  public JieQi(String name, Solar solar) {
    setName(name);
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
    for(String key: LunarUtil.JIE){
      if(key.equals(name)){
        this.jie = true;
        return;
      }
    }
    for(String key: LunarUtil.QI){
      if(key.equals(name)){
        this.qi = true;
        return;
      }
    }
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

  /**
   * 是否节令
   * @return true/false
   */
  public boolean isJie(){
    return jie;
  }

  /**
   * 是否气令
   * @return true/false
   */
  public boolean isQi() {
    return qi;
  }

  @Override
  public String toString(){
    return name;
  }
}
