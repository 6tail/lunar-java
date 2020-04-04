package com.nlf.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.nlf.calendar.util.LunarUtil;
import com.nlf.calendar.util.SolarUtil;

/**
 * 农历日期
 *
 * @author 6tail
 *
 */
public class Lunar{
  /** 农历年 */
  private int year;
  /** 农历月，闰月为负，即闰2月=-2 */
  private int month;
  /** 农历日 */
  private int day;
  /** 对应阳历 */
  private Solar solar;

  /**
   * 默认使用当前日期初始化
   */
  public Lunar(){
    this(new Date());
  }

  /**
   * 通过农历年月日初始化
   *
   * @param year 年（农历）
   * @param month 月（农历），1到12，闰月为负，即闰2月=-2
   * @param day 日（农历），1到31
   */
  public Lunar(int year,int month,int day){
    this.year = year;
    this.month = month;
    this.day = day;
    this.solar = toSolar();
  }

  /**
   * 通过日期初始化
   * @param date 日期
   */
  public Lunar(Date date){
    solar = new Solar(date);
    int y = solar.getYear();
    int m = solar.getMonth();
    int d = solar.getDay();
    int startY,startM,startD;
    int lunarY,lunarM,lunarD;
    if(y<2000){
      startY = SolarUtil.BASE_YEAR;
      startM = SolarUtil.BASE_MONTH;
      startD = SolarUtil.BASE_DAY;
      lunarY = LunarUtil.BASE_YEAR;
      lunarM = LunarUtil.BASE_MONTH;
      lunarD = LunarUtil.BASE_DAY;
    }else{
      startY = SolarUtil.BASE_YEAR+99;
      startM = 1;
      startD = 1;
      lunarY = LunarUtil.BASE_YEAR+99;
      lunarM = 11;
      lunarD = 25;
    }
    int diff = 0;
    for(int i=startY;i<y;i++){
      diff += 365;
      if(SolarUtil.isLeapYear(i)){
        diff += 1;
      }
    }
    for(int i=startM;i<m;i++){
      diff += SolarUtil.getDaysOfMonth(y,i);
    }
    diff += d-startD;
    lunarD += diff;
    int lastDate = LunarUtil.getDaysOfMonth(lunarY,lunarM);
    while(lunarD>lastDate){
      lunarD -= lastDate;
      lunarM = LunarUtil.nextMonth(lunarY,lunarM);
      if(lunarM==1){
        lunarY++;
      }
      lastDate = LunarUtil.getDaysOfMonth(lunarY,lunarM);
    }
    year = lunarY;
    month = lunarM;
    day = lunarD;
  }

  /**
   * 通过指定日期获取农历
   *
   * @param date 日期
   * @return 农历
   */
  public static Lunar fromDate(Date date){
    return new Lunar(date);
  }

  /**
   * 通过指定农历年月日获取农历
   *
   * @param year 年（农历）
   * @param month 月（农历），1到12，闰月为负，即闰2月=-2
   * @param day 日（农历），1到31
   * @return 农历
   */
  public static Lunar fromYmd(int year,int month,int day){
    return new Lunar(year,month,day);
  }

  /**
   * 获取年份的天干
   *
   * @return 天干，如辛
   */
  public String getGan(){
    return LunarUtil.GAN[(year-4)%10+1];
  }

  /**
   * 获取年份的地支
   *
   * @return 地支，如亥
   */
  public String getZhi(){
    return LunarUtil.ZHI[(year-4)%12+1];
  }

  /**
   * 获取生肖
   *
   * @return 生肖，如虎
   */
  public String getShengxiao(){
    return LunarUtil.SHENGXIAO[(year-4)%12+1];
  }

  /**
   * 获取中文的月
   *
   * @return 中文月，如正月
   */
  public String getMonthInChinese(){
    if(month>0){
      return LunarUtil.MONTH[month];
    }else{
      return "闰"+LunarUtil.MONTH[-month];
    }
  }

  /**
   * 获取季节
   * @return 农历季节
   */
  public String getSeason(){
    return LunarUtil.SEASON[Math.abs(month)];
  }

  /**
   * 获取中文日
   *
   * @return 中文日，如初一
   */
  public String getDayInChinese(){
    return LunarUtil.DAY[day];
  }

  /**
   * 获取节
   *
   * @return 节
   */
  public String getJie(){
    String s = "";
    int solarYear = solar.getYear();
    int solarMonth = solar.getMonth();
    int solarDay = solar.getDay();
    int index = 0;
    int ry = solarYear-SolarUtil.BASE_YEAR+1;
    while(ry>=LunarUtil.JIE_YEAR[solarMonth-1][index]){
      index++;
    }
    int term = LunarUtil.JIE_MAP[solarMonth-1][4*index+ry%4];
    if(ry==121&&solarMonth==4){
      term = 5;
    }
    if(ry==132&&solarMonth==4){
      term = 5;
    }
    if(ry==194&&solarMonth==6){
      term = 6;
    }
    if(solarDay==term){
      s = LunarUtil.JIE[solarMonth-1];
    }
    return s;
  }

  /**
   * 获取气
   *
   * @return 气
   */
  public String getQi(){
    String s = "";
    int solarYear = solar.getYear();
    int solarMonth = solar.getMonth();
    int solarDay = solar.getDay();
    int index = 0;
    int ry = solarYear-SolarUtil.BASE_YEAR+1;
    while(ry>=LunarUtil.QI_YEAR[solarMonth-1][index]){
      index++;
    }
    int term = LunarUtil.QI_MAP[solarMonth-1][4*index+ry%4];
    if(ry==171&&solarMonth==3){
      term = 21;
    }
    if(ry==181&&solarMonth==5){
      term = 21;
    }
    if(solarDay==term){
      s = LunarUtil.QI[solarMonth-1];
    }
    return s;
  }

  /**
   * 获取宿
   *
   * @return 宿
   */
  public String getXiu(){
    return LunarUtil.XIU[day-1][Math.abs(month)-1];
  }

  /**
   * 获取政
   *
   * @return 政
   */
  public String getZheng(){
    return LunarUtil.ZHENG.get(getXiu());
  }

  /**
   * 获取动物
   * @return 动物
   */
  public String getAnimal(){
    return LunarUtil.ANIMAL.get(getXiu());
  }

  /**
   * 获取宫
   * @return 宫
   */
  public String getGong(){
    return LunarUtil.GONG.get(getXiu());
  }

  /**
   * 获取兽
   * @return 兽
   */
  public String getShou(){
    return LunarUtil.SHOU.get(getGong());
  }

  /**
   * 获取节日，有可能一天会有多个节日
   *
   * @return 节日列表，如春节
   */
  public List<String> getFestivals(){
    List<String> l = new ArrayList<String>();
    String f = LunarUtil.FESTIVAL.get(month+"-"+day);
    if(null!=f){
      l.add(f);
    }
    return l;
  }

  /**
   * 获取非正式的节日，有可能一天会有多个节日
   *
   * @return 非正式的节日列表，如中元节
   */
  public List<String> getOtherFestivals(){
    List<String> l = new ArrayList<String>();
    List<String> fs = LunarUtil.OTHER_FESTIVAL.get(month+"-"+day);
    if(null!=fs){
      l.addAll(fs);
    }
    return l;
  }

  /**
   * 转换为阳历日期
   *
   * @return 阳历日期
   */
  private Solar toSolar(){
    int diff = LunarUtil.computeAddDays(year,month,day);
    Calendar c = Calendar.getInstance();
    c.set(SolarUtil.BASE_YEAR,SolarUtil.BASE_MONTH-1,SolarUtil.BASE_DAY);
    c.add(Calendar.DATE,diff);
    return new Solar(c);
  }

  /**
   * 获取干支纪月
   * <p>月天干口诀：甲己丙寅首，乙庚戊寅头。丙辛从庚寅，丁壬壬寅求，戊癸甲寅居，周而复始流。</p>
   * <p>月地支：正月起寅</p>
   *
   * @return 干支纪月，如己卯
   */
  public String getMonthInGanZhi(){
    int m = Math.abs(month)-1;
    int yearGanIndex = (year-4)%10;
    int offset = (yearGanIndex%5+1)*2;
    String monthGan = LunarUtil.GAN[(m+offset)%10+1];
    String monthZhi = LunarUtil.ZHI[(m+LunarUtil.BASE_MONTH_ZHI_INDEX)%12+1];
    return monthGan+monthZhi;
  }

  /**
   * 获取干支纪日
   *
   * @return 干支纪日，如己卯
   */
  public String getDayInGanZhi(){
    int diff = LunarUtil.computeAddDays(year,month,day);
    diff += LunarUtil.BASE_DAY_GANZHI_INDEX;
    diff = diff%60;
    return LunarUtil.GAN[diff%10+1]+LunarUtil.ZHI[diff%12+1];
  }

  /**
   * 获取彭祖百忌天干
   * @return 彭祖百忌天干
   */
  public String getPengZuGan(){
    int diff = LunarUtil.computeAddDays(year,month,day);
    diff += LunarUtil.BASE_DAY_GANZHI_INDEX;
    diff = diff%60;
    return LunarUtil.PENGZU_GAN[diff%10+1];
  }

  /**
   * 获取彭祖百忌地支
   * @return 彭祖百忌地支
   */
  public String getPengZuZhi(){
    int diff = LunarUtil.computeAddDays(year,month,day);
    diff += LunarUtil.BASE_DAY_GANZHI_INDEX;
    diff = diff%60;
    return LunarUtil.PENGZU_ZHI[diff%12+1];
  }

  public String toFullString(){
    StringBuilder s = new StringBuilder();
    s.append(toString());
    s.append(" ");
    s.append(getShengxiao());
    s.append("年");
    s.append(getMonthInGanZhi());
    s.append("月");
    s.append(getDayInGanZhi());
    s.append("日");
    for(String f:getFestivals()){
      s.append(" (");
      s.append(f);
      s.append(")");
    }
    for(String f:getOtherFestivals()){
      s.append(" (");
      s.append(f);
      s.append(")");
    }
    String jq = getJie()+getQi();
    if(jq.length()>0){
      s.append(" [");
      s.append(jq);
      s.append("]");
    }
    s.append(" ");
    s.append(getGong());
    s.append("方");
    s.append(getShou());
    s.append(" ");
    s.append(getXiu());
    s.append(getZheng());
    s.append(getAnimal());
    s.append(" 彭祖[");
    s.append(getPengZuGan());
    s.append(" ");
    s.append(getPengZuZhi());
    s.append("]");
    return s.toString();
  }

  @Override
  public String toString(){
    return getGan()+getZhi()+"年"+getMonthInChinese()+"月"+getDayInChinese();
  }

  /**
   * 获取年份
   *
   * @return 如2015
   */
  public int getYear(){
    return year;
  }

  /**
   * 获取月份
   *
   * @return 1到12，负数为闰月
   */
  public int getMonth(){
    return month;
  }

  /**
   * 获取日期
   *
   * @return 日期
   */
  public int getDay(){
    return day;
  }

  public Solar getSolar(){
    return solar;
  }
}
