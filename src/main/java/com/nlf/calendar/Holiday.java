package com.nlf.calendar;

/**
 * 节假日
 *
 * @author 6tail
 */
public class Holiday {

  /** 日期，YYYY-MM-DD格式 */
  private String day;

  /** 名称，如：国庆 */
  private String name;

  /** 是否调休，即是否要上班 */
  private boolean work;

  /** 关联的节日，YYYY-MM-DD格式 */
  private String target;

  public Holiday(){}

  /**
   * 初始化
   * @param day 日期
   * @param name 名称
   * @param work 是否调休
   * @param target 关联的节日
   */
  public Holiday(String day,String name,boolean work,String target){
    if(!day.contains("-")){
      this.day = day.substring(0,4)+"-"+day.substring(4,6)+"-"+day.substring(6);
    }else {
      this.day = day;
    }
    this.name = name;
    this.work = work;
    if(!target.contains("-")){
      this.target = target.substring(0,4)+"-"+target.substring(4,6)+"-"+target.substring(6);
    }else {
      this.target = target;
    }
  }

  /**
   * 获取日期
   * @return 日期
   */
  public String getDay() {
    return day;
  }

  /**
   * 设置日期
   * @param day 日期
   */
  public void setDay(String day) {
    this.day = day;
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
   * 是否调休
   * @return true/false
   */
  public boolean isWork() {
    return work;
  }

  /**
   * 设置是否调休
   * @param work true/false
   */
  public void setWork(boolean work) {
    this.work = work;
  }

  /**
   * 获取关联的节日
   * @return 节日
   */
  public String getTarget() {
    return target;
  }

  /**
   * 设置关联的节日
   * @param target 节日
   */
  public void setTarget(String target) {
    this.target = target;
  }

  @Override
  public String toString(){
    return day+" "+name+(work?"调休":"")+" "+target;
  }
}
