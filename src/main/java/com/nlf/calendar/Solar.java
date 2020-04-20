package com.nlf.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.nlf.calendar.util.SolarUtil;

/**
 * 阳历日期
 *
 * @author 6tail
 *
 */
public class Solar{
  /** 2000年儒略日数(2000-1-1 12:00:00 UTC) */
  public static final double J2000 = 2451545;
  /** 年 */
  private int year;
  /** 月 */
  private int month;
  /** 日 */
  private int day;
  /** 时 */
  private int hour;
  /** 分 */
  private int minute;
  /** 秒 */
  private int second;
  /** 日历 */
  private Calendar calendar;

  /**
   * 默认使用当前日期初始化
   */
  public Solar(){
    this(new Date());
  }

  /**
   * 通过年月日初始化
   *
   * @param year 年
   * @param month 月，1到12
   * @param day 日，1到31
   */
  public Solar(int year,int month,int day){
    this(year,month,day,0,0,0);
  }

  /**
   * 通过年月日初始化
   *
   * @param year 年
   * @param month 月，1到12
   * @param day 日，1到31
   * @param hour 小时，0到23
   * @param minute 分钟，0到59
   * @param second 秒钟，0到59
   */
  public Solar(int year,int month,int day,int hour,int minute,int second){
    calendar = Calendar.getInstance();
    calendar.set(year,month-1,day,hour,minute,second);
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  /**
   * 通过日期初始化
   *
   * @param date 日期
   */
  public Solar(Date date){
    calendar = Calendar.getInstance();
    calendar.setTime(date);
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH)+1;
    day = calendar.get(Calendar.DATE);
    hour = calendar.get(Calendar.HOUR_OF_DAY);
    minute = calendar.get(Calendar.MINUTE);
    second = calendar.get(Calendar.SECOND);
  }

  /**
   * 通过日历初始化
   *
   * @param calendar 日历
   */
  public Solar(Calendar calendar){
    this.calendar = calendar;
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH)+1;
    day = calendar.get(Calendar.DATE);
    hour = calendar.get(Calendar.HOUR_OF_DAY);
    minute = calendar.get(Calendar.MINUTE);
    second = calendar.get(Calendar.SECOND);
  }

  /**
   * 通过儒略日初始化
   * @param julianDay 儒略日
   */
  public Solar(double julianDay){
    julianDay += 0.5;

    // 日数的整数部份
    double a = int2(julianDay);
    // 日数的小数部分
    double f = julianDay - a;
    double D;

    if (a > 2299161) {
      D = int2((a - 1867216.25) / 36524.25);
      a += 1 + D - int2(D / 4);
    }
    // 向前移4年零2个月
    a += 1524;
    double y = int2((a - 122.1) / 365.25);
    // 去除整年日数后余下日数
    D = a - int2(365.25 * y);
    double m = (int)int2(D / 30.6001);
    // 去除整月日数后余下日数
    double d = (int)int2(D - int2(m * 30.6001));
    y -= 4716;
    m--;
    if (m > 12) {
      m -= 12;
    }
    if (m <= 2) {
      y++;
    }

    // 日的小数转为时分秒
    f *= 24;
    double h = (int)int2(f);

    f -= h;
    f *= 60;
    double mi = int2(f);

    f -= mi;
    f *= 60;
    double s = int2(f);

    calendar = Calendar.getInstance();
    calendar.set((int)y,(int)m-1,(int)d,(int)h,(int)mi,(int)s);
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH)+1;
    day = calendar.get(Calendar.DATE);
    hour = calendar.get(Calendar.HOUR_OF_DAY);
    minute = calendar.get(Calendar.MINUTE);
    second = calendar.get(Calendar.SECOND);
  }

  /**
   * 通过指定日期获取阳历
   *
   * @param date 日期
   * @return 阳历
   */
  public static Solar fromDate(Date date){
    return new Solar(date);
  }

  /**
   * 通过指定日历获取阳历
   *
   * @param calendar 日历
   * @return 阳历
   */
  public static Solar fromCalendar(Calendar calendar){
    return new Solar(calendar);
  }

  /**
   * 通过指定儒略日获取阳历
   *
   * @param julianDay 儒略日
   * @return 阳历
   */
  public static Solar fromJulianDay(double julianDay){
    return new Solar(julianDay);
  }

  /**
   * 通过指定年月日获取阳历
   *
   * @param year 年
   * @param month 月，1到12
   * @param day 日，1到31
   * @return 阳历
   */
  public static Solar fromYmd(int year,int month,int day){
    return new Solar(year,month,day);
  }

  /**
   * 通过指定年月日时分获取阳历
   *
   * @param year 年
   * @param month 月，1到12
   * @param day 日，1到31
   * @param hour 小时，0到23
   * @param minute 分钟，0到59
   * @param second 秒钟，0到59
   * @return 阳历
   */
  public static Solar fromYmdHms(int year,int month,int day,int hour,int minute,int second){
    return new Solar(year,month,day,hour,minute,second);
  }

  /**
   * 是否闰年
   *
   * @return true/false 闰年/非闰年
   */
  public boolean isLeapYear(){
    return SolarUtil.isLeapYear(year);
  }

  /**
   * 获取星期，0代表周日，1代表周一
   *
   * @return 0123456
   */
  public int getWeek(){
    return calendar.get(Calendar.DAY_OF_WEEK)-1;
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
   * 获取节日，有可能一天会有多个节日
   *
   * @return 劳动节等
   */
  public List<String> getFestivals(){
    List<String> l = new ArrayList<String>();
    //获取几月几日对应的节日
    String f = SolarUtil.FESTIVAL.get(month+"-"+day);
    if(null!=f){
      l.add(f);
    }
    //计算几月第几个星期几对应的节日
    //第几周
    int weekInMonth = calendar.get(Calendar.WEEK_OF_MONTH);
    //星期几，0代表星期天
    int week = getWeek();
    //星期天很奇葩，会多算一周，需要减掉
    if(0==week){
      weekInMonth--;
    }
    f = SolarUtil.WEEK_FESTIVAL.get(month+"-"+weekInMonth+"-"+week);
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
    List<String> fs = SolarUtil.OTHER_FESTIVAL.get(month+"-"+day);
    if(null!=fs){
      l.addAll(fs);
    }
    return l;
  }

  /**
   * 获取星座
   *
   * @return 星座
   * @deprecated 使用getXingZuo
   */
  public String getXingzuo(){
    return getXingZuo();
  }

  /**
   * 获取星座
   *
   * @return 星座
   */
  public String getXingZuo(){
    int index = 11,m = month,d = day;
    int y = m*100+d;
    if(y>=321&&y<=419){
      index = 0;
    }else if(y>=420&&y<=520){
      index = 1;
    }else if(y>=521&&y<=620){
      index = 2;
    }else if(y>=621&&y<=722){
      index = 3;
    }else if(y>=723&&y<=822){
      index = 4;
    }else if(y>=823&&y<=922){
      index = 5;
    }else if(y>=923&&y<=1022){
      index = 6;
    }else if(y>=1023&&y<=1121){
      index = 7;
    }else if(y>=1122&&y<=1221){
      index = 8;
    }else if(y>=1222||y<=119){
      index = 9;
    }else if(y<=218){
      index = 10;
    }
    return SolarUtil.XINGZUO[index];
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
   * @return 1到12
   */
  public int getMonth(){
    return month;
  }

  /**
   * 获取日期
   *
   * @return 1到31之间的数字
   */
  public int getDay(){
    return day;
  }

  /**
   * 获取小时
   *
   * @return 0到23之间的数字
   */
  public int getHour(){
    return hour;
  }

  /**
   * 获取分钟
   *
   * @return 0到59之间的数字
   */
  public int getMinute(){
    return minute;
  }

  /**
   * 获取秒钟
   *
   * @return 0到59之间的数字
   */
  public int getSecond(){
    return second;
  }

  /**
   * 获取农历
   * @return 农历
   */
  public Lunar getLunar(){
    return new Lunar(calendar.getTime());
  }

  private double int2(double v){
    v = Math.floor(v);
    return v<0?v+1:v;
  }

  /**
   * 获取儒略日
   * @return 儒略日
   */
  public double getJulianDay(){
    double y = this.year;
    double m = this.month;
    double n = 0;

    if (m <= 2) {
      m += 12;
      y--;
    }

    // 判断是否为UTC日1582*372+10*31+15
    if (this.year * 372 + this.month * 31 + this.day >= 588829) {
      n = int2(y / 100);
      // 加百年闰
      n = 2 - n + int2(n / 4);
    }

    // 加上年引起的偏移日数
    n += int2(365.2500001 * (y + 4716));
    // 加上月引起的偏移日数及日偏移数
    n += int2(30.6 * (m + 1)) + this.day;
    n += ((this.second *1D / 60 + this.minute) / 60 + this.hour) / 24 - 1524.5;
    return n;
  }

  /**
   * 获取日历
   *
   * @return 日历
   */
  public Calendar getCalendar(){
    return calendar;
  }

  @Override
  public String toString(){
    return toYmd();
  }

  public String toYmd(){
    return year+"-"+(month<10?"0":"")+month+"-"+(day<10?"0":"")+day;
  }

  public String toYmdHms(){
    return toYmd()+" "+(hour<10?"0":"")+hour+":"+(minute<10?"0":"")+minute+":"+(second<10?"0":"")+second;
  }

  public String toFullString(){
    StringBuilder s = new StringBuilder();
    s.append(toYmdHms());
    if(isLeapYear()){
      s.append(" ");
      s.append("闰年");
    }
    s.append(" ");
    s.append("星期");
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
    s.append(" ");
    s.append(getXingZuo());
    s.append("座");
    return s.toString();
  }

  /**
   * 获取往后推几天的阳历日期，如果要往前推，则天数用负数
   * @param days 天数
   * @return 阳历日期
   */
  public Solar next(int days){
    Calendar c = Calendar.getInstance();
    c.set(year,month-1,day,hour,minute,second);
    c.add(Calendar.DATE,days);
    return new Solar(c);
  }

}
