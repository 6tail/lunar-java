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
  /** 相对于基准日的偏移天数 */
  private int dayOffset;
  /** 时对应的天干下标，0-9 */
  private int timeGanIndex;
  /** 时对应的地支下标，0-11 */
  private int timeZhiIndex;
  /** 日对应的天干下标，0-9 */
  private int dayGanIndex;
  /** 日对应的地支下标，0-11 */
  private int dayZhiIndex;
  /** 月对应的天干下标，0-9 */
  private int monthGanIndex;
  /** 月对应的地支下标，0-11 */
  private int monthZhiIndex;
  /** 年对应的天干下标，0-9 */
  private int yearGanIndex;
  /** 年对应的地支下标，0-11 */
  private int yearZhiIndex;
  /** 周下标，0-6 */
  private int weekIndex;
  /** 阳历小时 */
  private int hour;
  /** 阳历分钟 */
  private int minute;

  /**
   * 默认使用当前日期初始化
   */
  public Lunar(){
    this(new Date());
  }

  /**
   * 通过农历年月日初始化
   *
   * @param lunarYear 年（农历）
   * @param lunarMonth 月（农历），1到12，闰月为负，即闰2月=-2
   * @param lunarDay 日（农历），1到31
   */
  public Lunar(int lunarYear,int lunarMonth,int lunarDay){
    this(lunarYear,lunarMonth,lunarDay,0,0);
  }

  /**
   * 通过农历年月日时初始化
   *
   * @param lunarYear 年（农历）
   * @param lunarMonth 月（农历），1到12，闰月为负，即闰2月=-2
   * @param lunarDay 日（农历），1到31
   * @param hour 小时（阳历）
   * @param minute 分钟（阳历）
   */
  public Lunar(int lunarYear,int lunarMonth,int lunarDay,int hour,int minute){
    this.year = lunarYear;
    this.month = lunarMonth;
    this.day = lunarDay;
    this.hour = hour;
    this.minute = minute;
    compute();
    this.solar = toSolar();
  }

  /**
   * 通过阳历日期初始化
   * @param date 阳历日期
   */
  public Lunar(Date date){
    solar = new Solar(date);
    int y = solar.getYear();
    int m = solar.getMonth();
    int d = solar.getDay();
    int startYear,startMonth,startDay;
    int lunarYear,lunarMonth,lunarDay;
    if(y<2000){
      startYear = SolarUtil.BASE_YEAR;
      startMonth = SolarUtil.BASE_MONTH;
      startDay = SolarUtil.BASE_DAY;
      lunarYear = LunarUtil.BASE_YEAR;
      lunarMonth = LunarUtil.BASE_MONTH;
      lunarDay = LunarUtil.BASE_DAY;
    }else{
      startYear = SolarUtil.BASE_YEAR+99;
      startMonth = 1;
      startDay = 1;
      lunarYear = LunarUtil.BASE_YEAR+99;
      lunarMonth = 11;
      lunarDay = 25;
    }
    int diff = 0;
    for(int i=startYear;i<y;i++){
      diff += 365;
      if(SolarUtil.isLeapYear(i)){
        diff += 1;
      }
    }
    for(int i=startMonth;i<m;i++){
      diff += SolarUtil.getDaysOfMonth(y,i);
    }
    diff += d-startDay;
    lunarDay += diff;
    int lastDate = LunarUtil.getDaysOfMonth(lunarYear,lunarMonth);
    while(lunarDay>lastDate){
      lunarDay -= lastDate;
      lunarMonth = LunarUtil.nextMonth(lunarYear,lunarMonth);
      if(lunarMonth==1){
        lunarYear++;
      }
      lastDate = LunarUtil.getDaysOfMonth(lunarYear,lunarMonth);
    }
    year = lunarYear;
    month = lunarMonth;
    day = lunarDay;
    hour = solar.getHour();
    minute = solar.getMinute();
    compute();
  }

  private void compute(){
    yearGanIndex = (year-4)%10;
    yearZhiIndex = (year-4)%12;

    int m = Math.abs(month);
    int leapMonth = LunarUtil.getLeapMonth(year);
    if(0==leapMonth||m<leapMonth||month==leapMonth){
      m-=1;
    }
    monthGanIndex = (m+(yearGanIndex%5+1)*2)%10;
    monthZhiIndex = (m+LunarUtil.BASE_MONTH_ZHI_INDEX)%12;

    dayOffset = LunarUtil.computeAddDays(year,month,day);
    int addDays = (dayOffset + LunarUtil.BASE_DAY_GANZHI_INDEX)%60;
    dayGanIndex = addDays%10;
    dayZhiIndex = addDays%12;

    timeZhiIndex = LunarUtil.getTimeZhiIndex((hour<10?"0":"")+hour+":"+(minute<10?"0":"")+minute);
    timeGanIndex = timeZhiIndex%10;

    weekIndex = (dayOffset+LunarUtil.BASE_WEEK_INDEX)%7;
  }

  /**
   * 通过指定阳历日期获取农历
   *
   * @param date 阳历日期
   * @return 农历
   */
  public static Lunar fromDate(Date date){
    return new Lunar(date);
  }

  /**
   * 通过指定农历年月日获取农历
   *
   * @param lunarYear 年（农历）
   * @param lunarMonth 月（农历），1到12，闰月为负，即闰2月=-2
   * @param lunarDay 日（农历），1到31
   * @return 农历
   */
  public static Lunar fromYmd(int lunarYear,int lunarMonth,int lunarDay){
    return new Lunar(lunarYear,lunarMonth,lunarDay);
  }

  /**
   * 通过指定农历年月日获取农历
   *
   * @param lunarYear 年（农历）
   * @param lunarMonth 月（农历），1到12，闰月为负，即闰2月=-2
   * @param lunarDay 日（农历），1到31
   * @param hour 小时（阳历）
   * @param minute 分钟（阳历）
   * @return 农历
   */
  public static Lunar fromYmdHm(int lunarYear,int lunarMonth,int lunarDay,int hour,int minute){
    return new Lunar(lunarYear,lunarMonth,lunarDay,hour,minute);
  }

  /**
   * 获取年份的天干
   *
   * @return 天干，如辛
   * @deprecated 使用getYearGan
   */
  public String getGan(){
    return getYearGan();
  }

  /**
   * 获取年份的天干
   *
   * @return 天干，如辛
   */
  public String getYearGan(){
    return LunarUtil.GAN[yearGanIndex+1];
  }

  /**
   * 获取年份的地支
   *
   * @return 地支，如亥
   * @deprecated 使用getYearZhi
   */
  public String getZhi(){
    return getYearZhi();
  }

  /**
   * 获取年份的地支
   *
   * @return 地支，如亥
   */
  public String getYearZhi(){
    return LunarUtil.ZHI[yearZhiIndex+1];
  }

  /**
   * 获取干支纪年（年柱）
   * @return 年份的干支（年柱），如辛亥
   */
  public String getYearInGanZhi(){
    return getYearGan()+getYearZhi();
  }

  /**
   * 获取年生肖
   *
   * @return 年生肖，如虎
   * @deprecated 使用getYearShengXiao
   */
  public String getShengxiao(){
    return getYearShengXiao();
  }

  /**
   * 获取年生肖
   *
   * @return 年生肖，如虎
   */
  public String getYearShengXiao(){
    return LunarUtil.SHENGXIAO[yearZhiIndex+1];
  }

  /**
   * 获取月生肖
   *
   * @return 月生肖，如虎
   */
  public String getMonthShengXiao(){
    return LunarUtil.SHENGXIAO[monthZhiIndex+1];
  }

  /**
   * 获取日生肖
   *
   * @return 日生肖，如虎
   */
  public String getDayShengXiao(){
    return LunarUtil.SHENGXIAO[dayZhiIndex+1];
  }

  /**
   * 获取时辰生肖
   *
   * @return 时辰生肖，如虎
   */
  public String getTimeShengXiao(){
    return LunarUtil.SHENGXIAO[timeZhiIndex+1];
  }

  /**
   * 获取中文的年
   *
   * @return 中文年，如二零零一
   */
  public String getYearInChinese(){
    String y = (year+"");
    StringBuilder s = new StringBuilder();
    for(int i=0,j=y.length();i<j;i++){
      s.append(LunarUtil.NUMBER[y.charAt(i)-'0']);
    }
    return s.toString();
  }

  /**
   * 获取中文的月
   *
   * @return 中文月，如正
   */
  public String getMonthInChinese(){
    return (month<0?"闰":"")+LunarUtil.MONTH[Math.abs(month)];
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
   * 获取时辰（地支）
   * @return 时辰（地支）
   */
  public String getTimeZhi(){
    return LunarUtil.ZHI[timeZhiIndex+1];
  }

  /**
   * 获取时辰（天干）
   * @return 时辰（天干）
   */
  public String getTimeGan(){
    return LunarUtil.GAN[timeGanIndex+1];
  }

  /**
   * 获取时辰干支（时柱）
   * @return 时辰干支（时柱）
   */
  public String getTimeInGanZhi(){
    return getTimeGan()+getTimeZhi();
  }

  /**
   * 获取季节
   * @return 农历季节
   */
  public String getSeason(){
    return LunarUtil.SEASON[Math.abs(month)];
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
   * 获取星期，0代表周日，1代表周一
   *
   * @return 0123456
   */
  public int getWeek(){
    return weekIndex;
  }

  /**
   * 获取星期的中文
   *
   * @return 日一二三四五六
   */
  public String getWeekInChinese(){
    return SolarUtil.WEEK[getWeek()];
  }

  /**
   * 获取宿
   *
   * @return 宿
   */
  public String getXiu(){
    return LunarUtil.XIU.get(getDayZhi()+getWeek());
  }

  /**
   * 获取宿吉凶
   *
   * @return 吉/凶
   */
  public String getXiuLuck(){
    return LunarUtil.XIU_LUCK.get(getXiu());
  }

  /**
   * 获取宿歌诀
   *
   * @return 宿歌诀
   */
  public String getXiuSong(){
    return LunarUtil.XIU_SONG.get(getXiu());
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
    Calendar c = Calendar.getInstance();
    c.set(SolarUtil.BASE_YEAR,SolarUtil.BASE_MONTH-1,SolarUtil.BASE_DAY,hour,minute);
    c.add(Calendar.DATE,dayOffset);
    return new Solar(c);
  }

  /**
   * 获取干支纪月（月柱）
   * <p>月天干口诀：甲己丙寅首，乙庚戊寅头。丙辛从庚寅，丁壬壬寅求，戊癸甲寅居，周而复始流。</p>
   * <p>月地支：正月起寅</p>
   *
   * @return 干支纪月（月柱），如己卯
   */
  public String getMonthInGanZhi(){
    return getMonthGan()+getMonthZhi();
  }

  /**
   * 获取月天干
   * @return 月天干，如己
   */
  public String getMonthGan(){
    return LunarUtil.GAN[monthGanIndex+1];
  }

  /**
   * 获取月地支
   * @return 月地支，如卯
   */
  public String getMonthZhi(){
    return LunarUtil.ZHI[monthZhiIndex+1];
  }

  /**
   * 获取干支纪日（日柱）
   *
   * @return 干支纪日（日柱），如己卯
   */
  public String getDayInGanZhi(){
    return getDayGan()+getDayZhi();
  }

  /**
   * 获取日天干
   *
   * @return 日天干，如甲
   */
  public String getDayGan(){
    return LunarUtil.GAN[dayGanIndex+1];
  }

  /**
   * 获取日地支
   *
   * @return 日地支，如卯
   */
  public String getDayZhi(){
    return LunarUtil.ZHI[dayZhiIndex+1];
  }

  /**
   * 获取彭祖百忌天干
   * @return 彭祖百忌天干
   */
  public String getPengZuGan(){
    return LunarUtil.PENGZU_GAN[dayGanIndex+1];
  }

  /**
   * 获取彭祖百忌地支
   * @return 彭祖百忌地支
   */
  public String getPengZuZhi(){
    return LunarUtil.PENGZU_ZHI[dayZhiIndex+1];
  }

  /**
   * 获取喜神方位
   * @return 喜神方位，如艮
   */
  public String getPositionXi(){
    return LunarUtil.POSITION_XI[dayGanIndex+1];
  }

  /**
   * 获取喜神方位描述
   * @return 喜神方位描述，如东北
   */
  public String getPositionXiDesc(){
    return LunarUtil.POSITION_DESC.get(getPositionXi());
  }

  /**
   * 获取阳贵神方位
   * @return 阳贵神方位，如艮
   */
  public String getPositionYangGui(){
    return LunarUtil.POSITION_YANG_GUI[dayGanIndex+1];
  }

  /**
   * 获取阳贵神方位描述
   * @return 阳贵神方位描述，如东北
   */
  public String getPositionYangGuiDesc(){
    return LunarUtil.POSITION_DESC.get(getPositionYangGui());
  }

  /**
   * 获取阴贵神方位
   * @return 阴贵神方位，如艮
   */
  public String getPositionYinGui(){
    return LunarUtil.POSITION_YIN_GUI[dayGanIndex+1];
  }

  /**
   * 获取阴贵神方位描述
   * @return 阴贵神方位描述，如东北
   */
  public String getPositionYinGuiDesc(){
    return LunarUtil.POSITION_DESC.get(getPositionYinGui());
  }

  /**
   * 获取福神方位
   * @return 福神方位，如艮
   */
  public String getPositionFu(){
    return LunarUtil.POSITION_FU[dayGanIndex+1];
  }

  /**
   * 获取福神方位描述
   * @return 福神方位描述，如东北
   */
  public String getPositionFuDesc(){
    return LunarUtil.POSITION_DESC.get(getPositionFu());
  }

  /**
   * 获取财神方位
   * @return 财神方位，如艮
   */
  public String getPositionCai(){
    return LunarUtil.POSITION_CAI[dayGanIndex+1];
  }

  /**
   * 获取财神方位描述
   * @return 财神方位描述，如东北
   */
  public String getPositionCaiDesc(){
    return LunarUtil.POSITION_DESC.get(getPositionCai());
  }

  /**
   * 获取冲
   * @return 冲，如申
   */
  public String getChong(){
    return LunarUtil.CHONG[dayZhiIndex+1];
  }

  /**
   * 获取无情之克的冲天干
   * @return 无情之克的冲天干，如甲
   */
  public String getChongGan(){
    return LunarUtil.CHONG_GAN[dayGanIndex+1];
  }

  /**
   * 获取有情之克的冲天干
   * @return 有情之克的冲天干，如甲
   */
  public String getChongGanTie(){
    return LunarUtil.CHONG_GAN_TIE[dayGanIndex+1];
  }

  /**
   * 获取冲生肖
   * @return 冲生肖，如猴
   */
  public String getChongShengXiao(){
    String chong = getChong();
    for(int i=0,j=LunarUtil.ZHI.length;i<j;i++){
      if(LunarUtil.ZHI[i].equals(chong)){
        return LunarUtil.SHENGXIAO[i];
      }
    }
    return "";
  }

  /**
   * 获取冲描述
   * @return 冲描述，如(壬申)猴
   */
  public String getChongDesc(){
    return "("+getChongGan()+getChong()+")"+getChongShengXiao();
  }

  /**
   * 获取煞
   * @return 煞，如北
   */
  public String getSha(){
    return LunarUtil.SHA.get(getDayZhi());
  }

  /**
   * 获取年纳音
   * @return 年纳音，如剑锋金
   */
  public String getYearNaYin(){
    return LunarUtil.NAYIN.get(getYearInGanZhi());
  }

  /**
   * 获取月纳音
   * @return 月纳音，如剑锋金
   */
  public String getMonthNaYin(){
    return LunarUtil.NAYIN.get(getMonthInGanZhi());
  }

  /**
   * 获取日纳音
   * @return 日纳音，如剑锋金
   */
  public String getDayNaYin(){
    return LunarUtil.NAYIN.get(getDayInGanZhi());
  }

  /**
   * 获取时辰纳音
   * @return 时辰纳音，如剑锋金
   */
  public String getTimeNaYin(){
    return LunarUtil.NAYIN.get(getTimeInGanZhi());
  }

  /**
   * 获取八字，男性也称乾造，女性也称坤造
   * @return 八字（男性也称乾造，女性也称坤造）
   */
  public List<String> getBaZi(){
    List<String> l = new ArrayList<String>(4);
    String timeGan = LunarUtil.GAN[(dayGanIndex%5*12+timeZhiIndex)%10+1];
    l.add(getYearInGanZhi());
    l.add(getMonthInGanZhi());
    l.add(getDayInGanZhi());
    l.add(timeGan+getTimeZhi());
    return l;
  }

  /**
   * 获取八字五行
   * @return 八字五行
   */
  public List<String> getBaZiWuXing(){
    List<String> baZi = getBaZi();
    List<String> l = new ArrayList<String>(baZi.size());
    for(String ganZhi:baZi){
      String gan = ganZhi.substring(0,1);
      String zhi = ganZhi.substring(1);
      l.add(LunarUtil.WU_XING_GAN.get(gan)+LunarUtil.WU_XING_ZHI.get(zhi));
    }
    return l;
  }

  /**
   * 获取八字纳音
   * @return 八字纳音
   */
  public List<String> getBaZiNaYin(){
    List<String> baZi = getBaZi();
    List<String> l = new ArrayList<String>(baZi.size());
    for(String ganZhi:baZi){
      l.add(LunarUtil.NAYIN.get(ganZhi));
    }
    return l;
  }

  /**
   * 获取八字天干十神，日柱十神为日主，其余三柱根据天干十神表查询
   * @return 八字天干十神
   */
  public List<String> getBaZiShiShenGan(){
    List<String> baZi = getBaZi();
    String yearGan = baZi.get(0).substring(0,1);
    String monthGan = baZi.get(1).substring(0,1);
    String dayGan = baZi.get(2).substring(0,1);
    String timeGan = baZi.get(3).substring(0,1);
    List<String> l = new ArrayList<String>(baZi.size());
    l.add(LunarUtil.SHI_SHEN_GAN.get(dayGan+yearGan));
    l.add(LunarUtil.SHI_SHEN_GAN.get(dayGan+monthGan));
    l.add("日主");
    l.add(LunarUtil.SHI_SHEN_GAN.get(dayGan+timeGan));
    return l;
  }

  /**
   * 获取八字地支十神，根据地支十神表查询
   * @return 八字地支十神
   */
  public List<String> getBaZiShiShenZhi(){
    List<String> baZi = getBaZi();
    String dayGan = baZi.get(2).substring(0,1);
    List<String> l = new ArrayList<String>(baZi.size());
    for(String ganZhi:baZi){
      String zhi = ganZhi.substring(1);
      l.add(LunarUtil.SHI_SHEN_ZHI.get(dayGan+zhi+LunarUtil.ZHI_HIDE_GAN.get(zhi).get(0)));
    }
    return l;
  }

  /**
   * 获取十二执星：建、除、满、平、定、执、破、危、成、收、开、闭。当月支与日支相同即为建，依次类推
   * @return 执星
   */
  public String getZhiXing(){
    int offset = dayZhiIndex-monthZhiIndex;
    if(offset<0){
      offset += 12;
    }
    return LunarUtil.ZHI_XING[offset+1];
  }

  /**
   * 获取值日天神
   * @return 值日天神
   */
  public String getDayTianShen(){
    String monthZhi = getMonthZhi();
    int offset = LunarUtil.MONTH_ZHI_TIAN_SHEN_OFFSET.get(monthZhi);
    return LunarUtil.TIAN_SHEN[(dayZhiIndex+offset)%12+1];
  }

  /**
   * 获取值日天神类型：黄道/黑道
   * @return 值日天神类型：黄道/黑道
   */
  public String getDayTianShenType(){
    return LunarUtil.TIAN_SHEN_TYPE.get(getDayTianShen());
  }

  /**
   * 获取值日天神吉凶
   * @return 吉/凶
   */
  public String getDayTianShenLuck(){
    return LunarUtil.TIAN_SHEN_TYPE_LUCK.get(getDayTianShenType());
  }

  /**
   * 获取逐日胎神方位
   * @return 逐日胎神方位
   */
  public String getDayPositionTai(){
    int offset = dayGanIndex-dayZhiIndex;
    if(offset<0){
      offset += 12;
    }
    return LunarUtil.POSITION_TAI_DAY[offset*5+dayGanIndex];
  }

  /**
   * 获取逐月胎神方位，闰月无
   * @return 逐月胎神方位
   */
  public String getMonthPositionTai(){
    if(month<0){
      return "";
    }
    return LunarUtil.POSITION_TAI_MONTH[month-1];
  }

  public String toFullString(){
    StringBuilder s = new StringBuilder();
    s.append(toString());
    s.append(" ");
    s.append(getYearInGanZhi());
    s.append("(");
    s.append(getYearShengXiao());
    s.append(")年 ");
    s.append(getMonthInGanZhi());
    s.append("(");
    s.append(getMonthShengXiao());
    s.append(")月 ");
    s.append(getDayInGanZhi());
    s.append("(");
    s.append(getDayShengXiao());
    s.append(")日 ");
    s.append(getTimeZhi());
    s.append("(");
    s.append(getTimeShengXiao());
    s.append(")时 纳音[");
    s.append(getYearNaYin());
    s.append(" ");
    s.append(getMonthNaYin());
    s.append(" ");
    s.append(getDayNaYin());
    s.append(" ");
    s.append(getTimeNaYin());
    s.append("] 星期");
    s.append(getWeekInChinese());
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
    s.append(" 星宿[");
    s.append(getXiu());
    s.append(getZheng());
    s.append(getAnimal());
    s.append("](");
    s.append(getXiuLuck());
    s.append(") 彭祖百忌[");
    s.append(getPengZuGan());
    s.append(" ");
    s.append(getPengZuZhi());
    s.append("] 喜神方位[");
    s.append(getPositionXi());
    s.append("](");
    s.append(getPositionXiDesc());
    s.append(") 阳贵神方位[");
    s.append(getPositionYangGui());
    s.append("](");
    s.append(getPositionYangGuiDesc());
    s.append(") 阴贵神方位[");
    s.append(getPositionYinGui());
    s.append("](");
    s.append(getPositionYinGuiDesc());
    s.append(") 福神方位[");
    s.append(getPositionFu());
    s.append("](");
    s.append(getPositionFuDesc());
    s.append(") 财神方位[");
    s.append(getPositionCai());
    s.append("](");
    s.append(getPositionCaiDesc());
    s.append(") 冲[");
    s.append(getChongDesc());
    s.append("] 煞[");
    s.append(getSha());
    s.append("]");
    return s.toString();
  }

  @Override
  public String toString(){
    return getYearInChinese()+"年"+getMonthInChinese()+"月"+getDayInChinese();
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
