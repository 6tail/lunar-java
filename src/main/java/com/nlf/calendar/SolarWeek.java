package com.nlf.calendar;

import com.nlf.calendar.util.SolarUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 阳历周
 *
 * @author 6tail
 */
public class SolarWeek {
  /** 年 */
  private int year;
  /** 月 */
  private int month;
  /** 日 */
  private int day;
  /** 星期几作为一周的开始，1234560分别代表星期一至星期天 */
  private int start;

  /**
   * 默认当月
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   */
  public SolarWeek(int start){
    this(new Date(),start);
  }

  /**
   * 通过日期初始化
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   */
  public SolarWeek(Date date,int start){
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    year = c.get(Calendar.YEAR);
    month = c.get(Calendar.MONTH)+1;
    day = c.get(Calendar.DATE);
    this.start = start;
  }

  /**
   * 通过日历初始化
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   */
  public SolarWeek(Calendar calendar,int start){
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH)+1;
    day = calendar.get(Calendar.DATE);
    this.start = start;
  }

  /**
   * 通过年月初始化
   *
   * @param year 年
   * @param month 月，1到12
   * @param day 日，1到31
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   */
  public SolarWeek(int year, int month ,int day,int start){
    this.year = year;
    this.month = month;
    this.day = day;
    this.start = start;
  }

  /**
   * 通过指定日期获取阳历周
   *
   * @param date 日期
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 阳历周
   */
  public static SolarWeek fromDate(Date date,int start){
    return new SolarWeek(date,start);
  }

  /**
   * 通过指定日历获取阳历周
   *
   * @param calendar 日历
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 阳历周
   */
  public static SolarWeek fromCalendar(Calendar calendar,int start){
    return new SolarWeek(calendar,start);
  }

  /**
   * 通过指定年月日获取阳历周
   *
   * @param year 年
   * @param month 月，1到12
   * @param day 日，1到31
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 阳历周
   */
  public static SolarWeek fromYmd(int year,int month,int day,int start){
    return new SolarWeek(year,month,day,start);
  }

  /**
   * 获取年
   *
   * @return 年
   */
  public int getYear(){
    return year;
  }

  /**
   * 获取月
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
   * 获取星期几作为一周的开始，1234560分别代表星期一至星期天
   *
   * @return 1234560分别代表星期一至星期天
   */
  public int getStart(){
    return start;
  }

  /**
   * 获取当前日期是在当月第几周
   * @return 周序号，从1开始
   */
  @SuppressWarnings("MagicConstant")
  public int getIndex(){
    Calendar c = Calendar.getInstance();
    c.set(year,month-1,1);
    int firstDayWeek = c.get(Calendar.DAY_OF_WEEK)-1;
    if(firstDayWeek==0){
      firstDayWeek = 7;
    }
    return (int)Math.ceil((day+firstDayWeek-start)/7D);
  }

  /**
   * 周推移
   * @param weeks 推移的周数，负数为倒推
   * @param separateMonth 是否按月单独计算
   * @return 推移后的阳历周
   */
  @SuppressWarnings("MagicConstant")
  public SolarWeek next(int weeks,boolean separateMonth){
    if(0==weeks){
      return new SolarWeek(year,month,day,start);
    }
    if(separateMonth){
      int n = weeks;
      Calendar c = Calendar.getInstance();
      c.set(year,month-1,day);
      SolarWeek week = new SolarWeek(c,start);
      int month = this.month;
      boolean plus = n>0;
      while(0!=n){
        c.add(Calendar.DATE,plus?7:-7);
        week = new SolarWeek(c,start);
        int weekMonth = week.getMonth();
        if(month!=weekMonth){
          int index = week.getIndex();
          if(plus){
            if(1==index){
              Solar firstDay = week.getFirstDay();
              week = new SolarWeek(firstDay.getYear(),firstDay.getMonth(),firstDay.getDay(),start);
              weekMonth = week.getMonth();
            }else{
              c.set(week.getYear(),week.getMonth()-1,1);
              week = new SolarWeek(c,start);
            }
          }else{
            int size = SolarUtil.getWeeksOfMonth(week.getYear(),week.getMonth(),start);
            if(size==index){
              Solar firstDay = week.getFirstDay();
              Solar lastDay = firstDay.next(6);
              week = new SolarWeek(lastDay.getYear(),lastDay.getMonth(),lastDay.getDay(),start);
              weekMonth = week.getMonth();
            }else{
              c.set(week.getYear(),week.getMonth()-1,SolarUtil.getDaysOfMonth(week.getYear(),week.getMonth()));
              week = new SolarWeek(c,start);
            }
          }
          month = weekMonth;
        }
        n-=plus?1:-1;
      }
      return week;
    }else{
      Calendar c = Calendar.getInstance();
      c.set(year,month-1,day);
      c.add(Calendar.DATE,weeks*7);
      return new SolarWeek(c,start);
    }
  }

  /**
   * 获取本周第一天的阳历日期（可能跨月）
   * @return 本周第一天的阳历日期
   */
  @SuppressWarnings("MagicConstant")
  public Solar getFirstDay(){
    Calendar c = Calendar.getInstance();
    c.set(year,month-1,day);
    int week = c.get(Calendar.DAY_OF_WEEK)-1;
    int prev = week - start;
    if(prev<0){
      prev += 7;
    }
    c.add(Calendar.DATE,-prev);
    return new Solar(c);
  }

  /**
   * 获取本周第一天的阳历日期（仅限当月）
   * @return 本周第一天的阳历日期
   */
  public Solar getFirstDayInMonth(){
    List<Solar> days = getDays();
    for(Solar day:days){
      if(month==day.getMonth()){
        return day;
      }
    }
    return null;
  }

  /**
   * 获取本周的阳历日期列表（可能跨月）
   * @return 本周的阳历日期列表
   */
  public List<Solar> getDays(){
    Solar firstDay = getFirstDay();
    List<Solar> l = new ArrayList<Solar>();
    l.add(firstDay);
    for(int i = 1;i<7;i++){
      l.add(firstDay.next(i));
    }
    return l;
  }

  /**
   * 获取本周的阳历日期列表（仅限当月）
   * @return 本周的阳历日期列表（仅限当月）
   */
  public List<Solar> getDaysInMonth(){
    List<Solar> days = this.getDays();
    List<Solar> l = new ArrayList<Solar>();
    for(Solar day:days){
      if(month!=day.getMonth()){
        continue;
      }
      l.add(day);
    }
    return l;
  }

  @Override
  public String toString(){
    return year+"."+month+"."+getIndex();
  }

  public String toFullString(){
    return year+"年"+month+"月第"+getIndex()+"周";
  }
}
