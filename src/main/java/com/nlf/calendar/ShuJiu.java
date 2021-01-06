package com.nlf.calendar;

/**
 * 数九
 * @author 6tail
 */
public class ShuJiu {

  /**
   * 名称，如一九、二九
   */
  private String name;

  /**
   * 当前数九第几天，1-9
   */
  private int index;

  public ShuJiu() {
  }

  public ShuJiu(String name, int index) {
    this.name = name;
    this.index = index;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  @Override
  public String toString() {
    return name;
  }

  public String toFullString() {
    return name + "第" + index + "天";
  }
}
