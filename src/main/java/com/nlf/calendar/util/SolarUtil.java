package com.nlf.calendar.util;

import com.nlf.calendar.Solar;

import java.util.*;

/**
 * 阳历工具
 *
 * @author 6tail
 */
public class SolarUtil {
  /**
   * 星期
   */
  public static final String[] WEEK = {"日", "一", "二", "三", "四", "五", "六"};
  /**
   * 每月天数
   */
  public static final int[] DAYS_OF_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  /**
   * 星座
   */
  public static final String[] XINGZUO = {"白羊", "金牛", "双子", "巨蟹", "狮子", "处女", "天秤", "天蝎", "射手", "摩羯", "水瓶", "双鱼"};
  /**
   * 日期对应的节日
   */
  public static final Map<String, String> FESTIVAL = new HashMap<String, String>() {
    private static final long serialVersionUID = -1;

    {
      put("1-1", "元旦节");
      put("2-14", "情人节");
      put("3-8", "妇女节");
      put("3-12", "植树节");
      put("3-15", "消费者权益日");
      put("4-1", "愚人节");
      put("5-1", "劳动节");
      put("5-4", "青年节");
      put("6-1", "儿童节");
      put("7-1", "建党节");
      put("8-1", "建军节");
      put("9-10", "教师节");
      put("10-1", "国庆节");
      put("10-31", "万圣节前夜");
      put("11-1", "万圣节");
      put("12-24", "平安夜");
      put("12-25", "圣诞节");
    }
  };
  /**
   * 几月第几个星期几对应的节日
   */
  public static final Map<String, String> WEEK_FESTIVAL = new HashMap<String, String>() {
    private static final long serialVersionUID = -1;

    {
      put("3-0-1", "全国中小学生安全教育日");
      put("5-2-0", "母亲节");
      put("6-3-0", "父亲节");
      put("11-4-4", "感恩节");
    }
  };
  /**
   * 日期对应的非正式节日
   */
  public static final Map<String, List<String>> OTHER_FESTIVAL = new HashMap<String, List<String>>() {
    private static final long serialVersionUID = -1;

    {
      put("1-8", Collections.nCopies(1, "周恩来逝世纪念日"));
      put("1-10", Arrays.asList("中国人民警察节", "中国公安110宣传日"));
      put("1-21", Collections.nCopies(1, "列宁逝世纪念日"));
      put("1-26", Collections.nCopies(1, "国际海关日"));
      put("2-2", Collections.nCopies(1, "世界湿地日"));
      put("2-4", Collections.nCopies(1, "世界抗癌日"));
      put("2-7", Collections.nCopies(1, "京汉铁路罢工纪念"));
      put("2-10", Collections.nCopies(1, "国际气象节"));
      put("2-19", Collections.nCopies(1, "邓小平逝世纪念日"));
      put("2-21", Collections.nCopies(1, "国际母语日"));
      put("2-24", Collections.nCopies(1, "第三世界青年日"));
      put("3-1", Collections.nCopies(1, "国际海豹日"));
      put("3-3", Collections.nCopies(1, "全国爱耳日"));
      put("3-5", Arrays.asList("周恩来诞辰纪念日", "中国青年志愿者服务日"));
      put("3-6", Collections.nCopies(1, "世界青光眼日"));
      put("3-12", Collections.nCopies(1, "孙中山逝世纪念日"));
      put("3-14", Collections.nCopies(1, "马克思逝世纪念日"));
      put("3-17", Collections.nCopies(1, "国际航海日"));
      put("3-18", Collections.nCopies(1, "全国科技人才活动日"));
      put("3-21", Arrays.asList("世界森林日", "世界睡眠日"));
      put("3-22", Collections.nCopies(1, "世界水日"));
      put("3-23", Collections.nCopies(1, "世界气象日"));
      put("3-24", Collections.nCopies(1, "世界防治结核病日"));
      put("4-2", Collections.nCopies(1, "国际儿童图书日"));
      put("4-7", Collections.nCopies(1, "世界卫生日"));
      put("4-22", Collections.nCopies(1, "列宁诞辰纪念日"));
      put("4-23", Collections.nCopies(1, "世界图书和版权日"));
      put("4-26", Collections.nCopies(1, "世界知识产权日"));
      put("5-3", Collections.nCopies(1, "世界新闻自由日"));
      put("5-5", Collections.nCopies(1, "马克思诞辰纪念日"));
      put("5-8", Collections.nCopies(1, "世界红十字日"));
      put("5-11", Collections.nCopies(1, "世界肥胖日"));
      put("5-25", Collections.nCopies(1, "525心理健康节"));
      put("5-27", Collections.nCopies(1, "上海解放日"));
      put("5-31", Collections.nCopies(1, "世界无烟日"));
      put("6-5", Collections.nCopies(1, "世界环境日"));
      put("6-6", Collections.nCopies(1, "全国爱眼日"));
      put("6-8", Collections.nCopies(1, "世界海洋日"));
      put("6-11", Collections.nCopies(1, "中国人口日"));
      put("6-14", Collections.nCopies(1, "世界献血日"));
      put("7-1", Collections.nCopies(1, "香港回归纪念日"));
      put("7-7", Collections.nCopies(1, "中国人民抗日战争纪念日"));
      put("7-11", Collections.nCopies(1, "世界人口日"));
      put("8-5", Collections.nCopies(1, "恩格斯逝世纪念日"));
      put("8-6", Collections.nCopies(1, "国际电影节"));
      put("8-12", Collections.nCopies(1, "国际青年日"));
      put("8-22", Collections.nCopies(1, "邓小平诞辰纪念日"));
      put("9-3", Collections.nCopies(1, "中国抗日战争胜利纪念日"));
      put("9-8", Collections.nCopies(1, "世界扫盲日"));
      put("9-9", Collections.nCopies(1, "毛泽东逝世纪念日"));
      put("9-14", Collections.nCopies(1, "世界清洁地球日"));
      put("9-18", Collections.nCopies(1, "九一八事变纪念日"));
      put("9-20", Collections.nCopies(1, "全国爱牙日"));
      put("9-21", Collections.nCopies(1, "国际和平日"));
      put("9-27", Collections.nCopies(1, "世界旅游日"));
      put("10-4", Collections.nCopies(1, "世界动物日"));
      put("10-10", Collections.nCopies(1, "辛亥革命纪念日"));
      put("10-13", Collections.nCopies(1, "中国少年先锋队诞辰日"));
      put("10-25", Collections.nCopies(1, "抗美援朝纪念日"));
      put("11-12", Collections.nCopies(1, "孙中山诞辰纪念日"));
      put("11-17", Collections.nCopies(1, "国际大学生节"));
      put("11-28", Collections.nCopies(1, "恩格斯诞辰纪念日"));
      put("12-1", Collections.nCopies(1, "世界艾滋病日"));
      put("12-12", Collections.nCopies(1, "西安事变纪念日"));
      put("12-13", Collections.nCopies(1, "国家公祭日"));
      put("12-26", Collections.nCopies(1, "毛泽东诞辰纪念日"));
    }
  };

  protected SolarUtil() {
  }

  /**
   * 是否闰年
   *
   * @param year 年
   * @return true/false 闰年/非闰年
   */
  public static boolean isLeapYear(int year) {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
  }

  /**
   * 获取某年有多少天（平年365天，闰年366天）
   *
   * @param year 年
   * @return 天数
   */
  public static int getDaysOfYear(int year) {
    if (1582 == year) {
      return 355;
    }
    return isLeapYear(year) ? 366 : 365;
  }

  /**
   * 获取某年某月有多少天
   *
   * @param year  年
   * @param month 月
   * @return 天数
   */
  public static int getDaysOfMonth(int year, int month) {
    if (1582 == year && 10 == month) {
      return 21;
    }
    int m = month - 1;
    int d = DAYS_OF_MONTH[m];
    //公历闰年2月多一天
    if (m == Calendar.FEBRUARY && isLeapYear(year)) {
      d++;
    }
    return d;
  }

  /**
   * 获取某天为当年的第几天
   *
   * @param year 年
   * @param month 月
   * @param day 日
   * @return 第几天
   */
  public static int getDaysInYear(int year, int month, int day) {
    int days = 0;
    for (int i = 1; i < month; i++) {
      days += getDaysOfMonth(year, i);
    }
    int d = day;
    if (1582 == year && 10 == month) {
      if (day >= 15) {
        d -= 10;
      } else if (day > 4) {
        throw new IllegalArgumentException(String.format("wrong solar year %d month %d day %d", year, month, day));
      }
    }
    days += d;
    return days;
  }

  /**
   * 获取某年某月有多少周
   *
   * @param year  年
   * @param month 月
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 周数
   */
  public static int getWeeksOfMonth(int year, int month, int start) {
    return (int) Math.ceil((getDaysOfMonth(year, month) + Solar.fromYmd(year, month, 1).getWeek() - start) * 1D / WEEK.length);
  }

  /**
   * 获取两个日期之间相差的天数（如果日期a比日期b小，天数为正，如果日期a比日期b大，天数为负）
   *
   * @param ay 年a
   * @param am 月a
   * @param ad 日a
   * @param by 年b
   * @param bm 月b
   * @param bd 日b
   * @return 天数
   */
  public static int getDaysBetween(int ay, int am, int ad, int by, int bm, int bd) {
    int n;
    int days;
    int i;
    if (ay == by) {
      n = getDaysInYear(by, bm, bd) - getDaysInYear(ay, am, ad);
    } else if (ay > by) {
      days = getDaysOfYear(by) - getDaysInYear(by, bm, bd);
      for (i = by + 1; i < ay; i++) {
        days += getDaysOfYear(i);
      }
      days += getDaysInYear(ay, am, ad);
      n = -days;
    } else {
      days = getDaysOfYear(ay) - getDaysInYear(ay, am, ad);
      for (i = ay + 1; i < by; i++) {
        days += getDaysOfYear(i);
      }
      days += getDaysInYear(by, bm, bd);
      n = days;
    }
    return n;
  }

}
