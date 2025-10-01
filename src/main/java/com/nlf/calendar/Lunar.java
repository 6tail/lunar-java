package com.nlf.calendar;

import com.nlf.calendar.util.LunarUtil;
import com.nlf.calendar.util.SolarUtil;

import java.util.*;

/**
 * 农历日期
 *
 * @author 6tail
 */
public class Lunar {
  /**
   * 节气表，国标以冬至为首个气令
   */
  public static final String[] JIE_QI = {"冬至", "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪"};
  /**
   * 实际的节气表
   */
  public static final String[] JIE_QI_IN_USE = {"DA_XUE", "冬至", "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "DONG_ZHI", "XIAO_HAN", "DA_HAN", "LI_CHUN", "YU_SHUI", "JING_ZHE"};

  /**
   * 农历年
   */
  private int year;
  /**
   * 农历月，闰月为负，即闰2月=-2
   */
  private int month;
  /**
   * 农历日
   */
  private int day;
  /**
   * 对应阳历
   */
  private final Solar solar;
  /**
   * 时对应的天干下标，0-9
   */
  private int timeGanIndex;
  /**
   * 时对应的地支下标，0-11
   */
  private int timeZhiIndex;
  /**
   * 日对应的天干下标，0-9
   */
  private int dayGanIndex;
  /**
   * 日对应的地支下标，0-11
   */
  private int dayZhiIndex;
  /**
   * 日对应的天干下标（八字流派1，晚子时日柱算明天），0-9
   */
  private int dayGanIndexExact;
  /**
   * 日对应的地支下标（八字流派1，晚子时日柱算明天），0-11
   */
  private int dayZhiIndexExact;
  /**
   * 日对应的天干下标（八字流派2，晚子时日柱算当天），0-9
   */
  private int dayGanIndexExact2;
  /**
   * 日对应的地支下标（八字流派2，晚子时日柱算当天），0-11
   */
  private int dayZhiIndexExact2;
  /**
   * 月对应的天干下标（以节交接当天起算），0-9
   */
  private int monthGanIndex;
  /**
   * 月对应的地支下标（以节交接当天起算），0-11
   */
  private int monthZhiIndex;
  /**
   * 月对应的天干下标（最精确的，供八字用，以节交接时刻起算），0-9
   */
  private int monthGanIndexExact;
  /**
   * 月对应的地支下标（最精确的，供八字用，以节交接时刻起算），0-11
   */
  private int monthZhiIndexExact;
  /**
   * 年对应的天干下标（国标，以正月初一为起点），0-9
   */
  private int yearGanIndex;
  /**
   * 年对应的地支下标（国标，以正月初一为起点），0-11
   */
  private int yearZhiIndex;
  /**
   * 年对应的天干下标（月干计算用，以立春为起点），0-9
   */
  private int yearGanIndexByLiChun;
  /**
   * 年对应的地支下标（月支计算用，以立春为起点），0-11
   */
  private int yearZhiIndexByLiChun;
  /**
   * 年对应的天干下标（最精确的，供八字用，以立春交接时刻为起点），0-9
   */
  private int yearGanIndexExact;
  /**
   * 年对应的地支下标（最精确的，供八字用，以立春交接时刻为起点），0-11
   */
  private int yearZhiIndexExact;

  /**
   * 周下标，0-6
   */
  private int weekIndex;
  /**
   * 阳历小时
   */
  private final int hour;
  /**
   * 阳历分钟
   */
  private final int minute;
  /**
   * 阳历秒钟
   */
  private final int second;
  /**
   * 八字
   */
  private EightChar eightChar;
  /**
   * 24节气表（对应阳历的准确时刻）
   */
  private final Map<String, Solar> jieQi = new LinkedHashMap<String, Solar>();

  /**
   * 默认使用当前日期初始化
   */
  public Lunar() {
    this(new Date());
  }

  /**
   * 通过农历年月日初始化
   *
   * @param lunarYear  年（农历）
   * @param lunarMonth 月（农历），1到12，闰月为负，即闰2月=-2
   * @param lunarDay   日（农历），1到30
   */
  public Lunar(int lunarYear, int lunarMonth, int lunarDay) {
    this(lunarYear, lunarMonth, lunarDay, 0, 0, 0);
  }

  /**
   * 通过农历年月日时初始化
   *
   * @param lunarYear  年（农历）
   * @param lunarMonth 月（农历），1到12，闰月为负，即闰2月=-2
   * @param lunarDay   日（农历），1到30
   * @param hour       小时（阳历）
   * @param minute     分钟（阳历）
   * @param second     秒钟（阳历）
   */
  public Lunar(int lunarYear, int lunarMonth, int lunarDay, int hour, int minute, int second) {
    LunarYear y = LunarYear.fromYear(lunarYear);
    LunarMonth m = y.getMonth(lunarMonth);
    if (null == m) {
      throw new IllegalArgumentException(String.format("wrong lunar year %d month %d", lunarYear, lunarMonth));
    }
    if (lunarDay < 1) {
      throw new IllegalArgumentException("lunar day must bigger than 0");
    }
    int days = m.getDayCount();
    if (lunarDay > days) {
      throw new IllegalArgumentException(String.format("only %d days in lunar year %d month %d", days, lunarYear, lunarMonth));
    }
    this.year = lunarYear;
    this.month = lunarMonth;
    this.day = lunarDay;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
    Solar noon = Solar.fromJulianDay(m.getFirstJulianDay() + lunarDay - 1);
    this.solar = Solar.fromYmdHms(noon.getYear(), noon.getMonth(), noon.getDay(), hour, minute, second);
    if (noon.getYear() != lunarYear) {
      y = LunarYear.fromYear(noon.getYear());
    }
    compute(y);
  }

  /**
   * 通过阳历初始化
   *
   * @param solar 阳历
   */
  public Lunar(Solar solar) {
    LunarYear ly = LunarYear.fromYear(solar.getYear());
    for (LunarMonth m : ly.getMonths()) {
      int days = solar.subtract(Solar.fromJulianDay(m.getFirstJulianDay()));
      if (days < m.getDayCount()) {
        year = m.getYear();
        month = m.getMonth();
        day = days + 1;
        break;
      }
    }
    hour = solar.getHour();
    minute = solar.getMinute();
    second = solar.getSecond();
    this.solar = solar;
    compute(ly);
  }

  /**
   * 通过阳历日期初始化
   *
   * @param date 阳历日期
   */
  public Lunar(Date date) {
    this(Solar.fromDate(date));
  }

  /**
   * 计算节气表
   */
  private void computeJieQi(LunarYear lunarYear) {
    List<Double> julianDays = lunarYear.getJieQiJulianDays();
    for (int i = 0, j = JIE_QI_IN_USE.length; i < j; i++) {
      jieQi.put(JIE_QI_IN_USE[i], Solar.fromJulianDay(julianDays.get(i)));
    }
  }

  /**
   * 计算干支纪年
   */
  private void computeYear() {
    //以正月初一开始
    int offset = year - 4;
    yearGanIndex = offset % 10;
    yearZhiIndex = offset % 12;

    if (yearGanIndex < 0) {
      yearGanIndex += 10;
    }

    if (yearZhiIndex < 0) {
      yearZhiIndex += 12;
    }

    //以立春作为新一年的开始的干支纪年
    int g = yearGanIndex;
    int z = yearZhiIndex;

    //精确的干支纪年，以立春交接时刻为准
    int gExact = yearGanIndex;
    int zExact = yearZhiIndex;

    int solarYear = solar.getYear();
    String solarYmd = solar.toYmd();
    String solarYmdHms = solar.toYmdHms();

    //获取立春的阳历时刻
    Solar liChun = jieQi.get("立春");
    if (liChun.getYear() != solarYear) {
      liChun = jieQi.get("LI_CHUN");
    }
    String liChunYmd = liChun.toYmd();
    String liChunYmdHms = liChun.toYmdHms();

    //阳历和阴历年份相同代表正月初一及以后
    if (year == solarYear) {
      //立春日期判断
      if (solarYmd.compareTo(liChunYmd) < 0) {
        g--;
        z--;
      }
      //立春交接时刻判断
      if (solarYmdHms.compareTo(liChunYmdHms) < 0) {
        gExact--;
        zExact--;
      }
    } else if (year < solarYear) {
      if (solarYmd.compareTo(liChunYmd) >= 0) {
        g++;
        z++;
      }
      if (solarYmdHms.compareTo(liChunYmdHms) >= 0) {
        gExact++;
        zExact++;
      }
    }

    yearGanIndexByLiChun = (g < 0 ? g + 10 : g) % 10;
    yearZhiIndexByLiChun = (z < 0 ? z + 12 : z) % 12;

    yearGanIndexExact = (gExact < 0 ? gExact + 10 : gExact) % 10;
    yearZhiIndexExact = (zExact < 0 ? zExact + 12 : zExact) % 12;
  }

  /**
   * 计算干支纪月
   */
  private void computeMonth() {
    Solar start = null;
    Solar end;
    String ymd = solar.toYmd();
    String time = solar.toYmdHms();
    int size = JIE_QI_IN_USE.length;

    //序号：大雪以前-3，大雪到小寒之间-2，小寒到立春之间-1，立春之后0
    int index = -3;
    for (int i = 0; i < size; i += 2) {
      end = jieQi.get(JIE_QI_IN_USE[i]);
      String symd = null == start ? ymd : start.toYmd();
      if (ymd.compareTo(symd) >= 0 && ymd.compareTo(end.toYmd()) < 0) {
        break;
      }
      start = end;
      index++;
    }

    //干偏移值（以立春当天起算）
    int offset = (((yearGanIndexByLiChun + (index < 0 ? 1 : 0)) % 5 + 1) * 2) % 10;
    monthGanIndex = ((index < 0 ? index + 10 : index) + offset) % 10;
    monthZhiIndex = ((index < 0 ? index + 12 : index) + LunarUtil.BASE_MONTH_ZHI_INDEX) % 12;

    start = null;
    index = -3;
    for (int i = 0; i < size; i += 2) {
      end = jieQi.get(JIE_QI_IN_USE[i]);
      String stime = null == start ? time : start.toYmdHms();
      if (time.compareTo(stime) >= 0 && time.compareTo(end.toYmdHms()) < 0) {
        break;
      }
      start = end;
      index++;
    }

    //干偏移值（以立春交接时刻起算）
    offset = (((yearGanIndexExact + (index < 0 ? 1 : 0)) % 5 + 1) * 2) % 10;
    monthGanIndexExact = ((index < 0 ? index + 10 : index) + offset) % 10;
    monthZhiIndexExact = ((index < 0 ? index + 12 : index) + LunarUtil.BASE_MONTH_ZHI_INDEX) % 12;
  }

  /**
   * 计算干支纪日
   */
  private void computeDay() {
    Solar noon = Solar.fromYmdHms(solar.getYear(), solar.getMonth(), solar.getDay(), 12, 0, 0);
    int offset = (int) noon.getJulianDay() - 11;
    dayGanIndex = offset % 10;
    dayZhiIndex = offset % 12;

    int dayGanExact = dayGanIndex;
    int dayZhiExact = dayZhiIndex;

    // 八字流派2，晚子时（夜子/子夜）日柱算当天
    dayGanIndexExact2 = dayGanExact;
    dayZhiIndexExact2 = dayZhiExact;

    // 八字流派1，晚子时（夜子/子夜）日柱算明天
    String hm = (hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute;
    if (hm.compareTo("23:00") >= 0 && hm.compareTo("23:59") <= 0) {
      dayGanExact++;
      if (dayGanExact >= 10) {
        dayGanExact -= 10;
      }
      dayZhiExact++;
      if (dayZhiExact >= 12) {
        dayZhiExact -= 12;
      }
    }

    dayGanIndexExact = dayGanExact;
    dayZhiIndexExact = dayZhiExact;
  }

  /**
   * 计算干支纪时
   */
  private void computeTime() {
    String hm = (hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute;
    timeZhiIndex = LunarUtil.getTimeZhiIndex(hm);
    timeGanIndex = (dayGanIndexExact % 5 * 2 + timeZhiIndex) % 10;
  }

  /**
   * 计算星期
   */
  private void computeWeek() {
    this.weekIndex = solar.getWeek();
  }

  private void compute(LunarYear lunarYear) {
    computeJieQi(lunarYear);
    computeYear();
    computeMonth();
    computeDay();
    computeTime();
    computeWeek();
  }

  /**
   * 通过指定阳历日期获取农历
   *
   * @param date 阳历日期
   * @return 农历
   */
  public static Lunar fromDate(Date date) {
    return new Lunar(date);
  }

  /**
   * 通过指定农历年月日获取农历
   *
   * @param lunarYear  年（农历）
   * @param lunarMonth 月（农历），1到12，闰月为负，即闰2月=-2
   * @param lunarDay   日（农历），1到31
   * @return 农历
   */
  public static Lunar fromYmd(int lunarYear, int lunarMonth, int lunarDay) {
    return new Lunar(lunarYear, lunarMonth, lunarDay);
  }

  /**
   * 通过指定农历年月日获取农历
   *
   * @param lunarYear  年（农历）
   * @param lunarMonth 月（农历），1到12，闰月为负，即闰2月=-2
   * @param lunarDay   日（农历），1到31
   * @param hour       小时（阳历）
   * @param minute     分钟（阳历）
   * @param second     秒钟（阳历）
   * @return 农历
   */
  public static Lunar fromYmdHms(int lunarYear, int lunarMonth, int lunarDay, int hour, int minute, int second) {
    return new Lunar(lunarYear, lunarMonth, lunarDay, hour, minute, second);
  }

  /**
   * 获取年份的天干（以正月初一作为新年的开始）
   *
   * @return 天干，如辛
   * @deprecated 使用getYearGan
   */
  @Deprecated
  public String getGan() {
    return getYearGan();
  }

  /**
   * 获取年份的天干（以正月初一作为新年的开始）
   *
   * @return 天干，如辛
   */
  public String getYearGan() {
    return LunarUtil.GAN[yearGanIndex + 1];
  }

  /**
   * 获取年份的天干（以立春当天作为新年的开始）
   *
   * @return 天干，如辛
   */
  public String getYearGanByLiChun() {
    return LunarUtil.GAN[yearGanIndexByLiChun + 1];
  }

  /**
   * 获取最精确的年份天干（以立春交接的时刻作为新年的开始）
   *
   * @return 天干，如辛
   */
  public String getYearGanExact() {
    return LunarUtil.GAN[yearGanIndexExact + 1];
  }

  /**
   * 获取年份的地支（以正月初一作为新年的开始）
   *
   * @return 地支，如亥
   * @deprecated 使用getYearZhi
   */
  @Deprecated
  public String getZhi() {
    return getYearZhi();
  }

  /**
   * 获取年份的地支（以正月初一作为新年的开始）
   *
   * @return 地支，如亥
   */
  public String getYearZhi() {
    return LunarUtil.ZHI[yearZhiIndex + 1];
  }

  /**
   * 获取年份的地支（以立春当天作为新年的开始）
   *
   * @return 地支，如亥
   */
  public String getYearZhiByLiChun() {
    return LunarUtil.ZHI[yearZhiIndexByLiChun + 1];
  }

  /**
   * 获取最精确的年份地支（以立春交接的时刻作为新年的开始）
   *
   * @return 地支，如亥
   */
  public String getYearZhiExact() {
    return LunarUtil.ZHI[yearZhiIndexExact + 1];
  }

  /**
   * 获取干支纪年（年柱）（以正月初一作为新年的开始）
   *
   * @return 年份的干支（年柱），如辛亥
   */
  public String getYearInGanZhi() {
    return getYearGan() + getYearZhi();
  }

  /**
   * 获取干支纪年（年柱）（以立春当天作为新年的开始）
   *
   * @return 年份的干支（年柱），如辛亥
   */
  public String getYearInGanZhiByLiChun() {
    return getYearGanByLiChun() + getYearZhiByLiChun();
  }

  /**
   * 获取干支纪年（年柱）（以立春交接的时刻作为新年的开始）
   *
   * @return 年份的干支（年柱），如辛亥
   */
  public String getYearInGanZhiExact() {
    return getYearGanExact() + getYearZhiExact();
  }

  /**
   * 获取干支纪月（月柱）（以节交接当天起算）
   * <p>月天干口诀：甲己丙寅首，乙庚戊寅头。丙辛从庚寅，丁壬壬寅求，戊癸甲寅居，周而复始流。</p>
   * <p>月地支：正月起寅</p>
   *
   * @return 干支纪月（月柱），如己卯
   */
  public String getMonthInGanZhi() {
    return getMonthGan() + getMonthZhi();
  }

  /**
   * 获取精确的干支纪月（月柱）（以节交接时刻起算）
   * <p>月天干口诀：甲己丙寅首，乙庚戊寅头。丙辛从庚寅，丁壬壬寅求，戊癸甲寅居，周而复始流。</p>
   * <p>月地支：正月起寅</p>
   *
   * @return 干支纪月（月柱），如己卯
   */
  public String getMonthInGanZhiExact() {
    return getMonthGanExact() + getMonthZhiExact();
  }

  /**
   * 获取月天干（以节交接当天起算）
   *
   * @return 月天干，如己
   */
  public String getMonthGan() {
    return LunarUtil.GAN[monthGanIndex + 1];
  }

  /**
   * 获取精确的月天干（以节交接时刻起算）
   *
   * @return 月天干，如己
   */
  public String getMonthGanExact() {
    return LunarUtil.GAN[monthGanIndexExact + 1];
  }

  /**
   * 获取月地支（以节交接当天起算）
   *
   * @return 月地支，如卯
   */
  public String getMonthZhi() {
    return LunarUtil.ZHI[monthZhiIndex + 1];
  }

  /**
   * 获取精确的月地支（以节交接时刻起算）
   *
   * @return 月地支，如卯
   */
  public String getMonthZhiExact() {
    return LunarUtil.ZHI[monthZhiIndexExact + 1];
  }

  /**
   * 获取干支纪日（日柱）
   *
   * @return 干支纪日（日柱），如己卯
   */
  public String getDayInGanZhi() {
    return getDayGan() + getDayZhi();
  }

  /**
   * 获取干支纪日（日柱，晚子时日柱算明天）
   *
   * @return 干支纪日（日柱），如己卯
   */
  public String getDayInGanZhiExact() {
    return getDayGanExact() + getDayZhiExact();
  }

  /**
   * 获取干支纪日（日柱，晚子时日柱算当天）
   *
   * @return 干支纪日（日柱），如己卯
   */
  public String getDayInGanZhiExact2() {
    return getDayGanExact2() + getDayZhiExact2();
  }

  /**
   * 获取日天干
   *
   * @return 日天干，如甲
   */
  public String getDayGan() {
    return LunarUtil.GAN[dayGanIndex + 1];
  }

  /**
   * 获取日天干（晚子时日柱算明天）
   *
   * @return 日天干，如甲
   */
  public String getDayGanExact() {
    return LunarUtil.GAN[dayGanIndexExact + 1];
  }

  /**
   * 获取日天干（晚子时日柱算当天）
   *
   * @return 日天干，如甲
   */
  public String getDayGanExact2() {
    return LunarUtil.GAN[dayGanIndexExact2 + 1];
  }

  /**
   * 获取日地支
   *
   * @return 日地支，如卯
   */
  public String getDayZhi() {
    return LunarUtil.ZHI[dayZhiIndex + 1];
  }

  /**
   * 获取日地支（晚子时日柱算明天）
   *
   * @return 日地支，如卯
   */
  public String getDayZhiExact() {
    return LunarUtil.ZHI[dayZhiIndexExact + 1];
  }

  /**
   * 获取日地支（晚子时日柱算当天）
   *
   * @return 日地支，如卯
   */
  public String getDayZhiExact2() {
    return LunarUtil.ZHI[dayZhiIndexExact2 + 1];
  }

  /**
   * 获取年生肖
   *
   * @return 年生肖，如虎
   * @deprecated 使用getYearShengXiao
   */
  @Deprecated
  public String getShengxiao() {
    return getYearShengXiao();
  }

  /**
   * 获取年生肖（以正月初一起算）
   *
   * @return 年生肖，如虎
   */
  public String getYearShengXiao() {
    return LunarUtil.SHENGXIAO[yearZhiIndex + 1];
  }

  /**
   * 获取年生肖（以立春当天起算）
   *
   * @return 年生肖，如虎
   */
  public String getYearShengXiaoByLiChun() {
    return LunarUtil.SHENGXIAO[yearZhiIndexByLiChun + 1];
  }

  /**
   * 获取精确的年生肖（以立春交接时刻起算）
   *
   * @return 年生肖，如虎
   */
  public String getYearShengXiaoExact() {
    return LunarUtil.SHENGXIAO[yearZhiIndexExact + 1];
  }

  /**
   * 获取月生肖
   *
   * @return 月生肖，如虎
   */
  public String getMonthShengXiao() {
    return LunarUtil.SHENGXIAO[monthZhiIndex + 1];
  }

  /**
   * 获取日生肖
   *
   * @return 日生肖，如虎
   */
  public String getDayShengXiao() {
    return LunarUtil.SHENGXIAO[dayZhiIndex + 1];
  }

  /**
   * 获取时辰生肖
   *
   * @return 时辰生肖，如虎
   */
  public String getTimeShengXiao() {
    return LunarUtil.SHENGXIAO[timeZhiIndex + 1];
  }

  /**
   * 获取中文的年
   *
   * @return 中文年，如二零零一
   */
  public String getYearInChinese() {
    String y = year + "";
    StringBuilder s = new StringBuilder();
    for (int i = 0, j = y.length(); i < j; i++) {
      s.append(LunarUtil.NUMBER[y.charAt(i) - '0']);
    }
    return s.toString();
  }

  /**
   * 获取中文的月
   *
   * @return 中文月，如正
   */
  public String getMonthInChinese() {
    return (month < 0 ? "闰" : "") + LunarUtil.MONTH[Math.abs(month)];
  }

  /**
   * 获取中文日
   *
   * @return 中文日，如初一
   */
  public String getDayInChinese() {
    return LunarUtil.DAY[day];
  }

  /**
   * 获取时辰（地支）
   *
   * @return 时辰（地支）
   */
  public String getTimeZhi() {
    return LunarUtil.ZHI[timeZhiIndex + 1];
  }

  /**
   * 获取时辰（天干）
   *
   * @return 时辰（天干）
   */
  public String getTimeGan() {
    return LunarUtil.GAN[timeGanIndex + 1];
  }

  /**
   * 获取时辰干支（时柱），支持早子时和晚子时
   *
   * @return 时辰干支（时柱）
   */
  public String getTimeInGanZhi() {
    return getTimeGan() + getTimeZhi();
  }

  /**
   * 获取季节
   *
   * @return 农历季节
   */
  public String getSeason() {
    return LunarUtil.SEASON[Math.abs(month)];
  }

  protected String convertJieQi(String name) {
    String jq = name;
    if ("DONG_ZHI".equals(jq)) {
      jq = "冬至";
    } else if ("DA_HAN".equals(jq)) {
      jq = "大寒";
    } else if ("XIAO_HAN".equals(jq)) {
      jq = "小寒";
    } else if ("LI_CHUN".equals(jq)) {
      jq = "立春";
    } else if ("DA_XUE".equals(jq)) {
      jq = "大雪";
    } else if ("YU_SHUI".equals(jq)) {
      jq = "雨水";
    } else if ("JING_ZHE".equals(jq)) {
      jq = "惊蛰";
    }
    return jq;
  }

  /**
   * 获取节令
   *
   * @return 节令
   */
  public String getJie() {
    for (int i = 0, j = JIE_QI_IN_USE.length; i < j; i += 2) {
      String key = JIE_QI_IN_USE[i];
      Solar d = jieQi.get(key);
      if (d.getYear() == solar.getYear() && d.getMonth() == solar.getMonth() && d.getDay() == solar.getDay()) {
        return convertJieQi(key);
      }
    }
    return "";
  }

  /**
   * 获取气令
   *
   * @return 气令
   */
  public String getQi() {
    for (int i = 1, j = JIE_QI_IN_USE.length; i < j; i += 2) {
      String key = JIE_QI_IN_USE[i];
      Solar d = jieQi.get(key);
      if (d.getYear() == solar.getYear() && d.getMonth() == solar.getMonth() && d.getDay() == solar.getDay()) {
        return convertJieQi(key);
      }
    }
    return "";
  }

  /**
   * 获取星期，0代表周日，1代表周一
   *
   * @return 0123456
   */
  public int getWeek() {
    return weekIndex;
  }

  /**
   * 获取星期的中文
   *
   * @return 日一二三四五六
   */
  public String getWeekInChinese() {
    return SolarUtil.WEEK[getWeek()];
  }

  /**
   * 获取宿
   *
   * @return 宿
   */
  public String getXiu() {
    return LunarUtil.XIU.get(getDayZhi() + getWeek());
  }

  /**
   * 获取宿吉凶
   *
   * @return 吉/凶
   */
  public String getXiuLuck() {
    return LunarUtil.XIU_LUCK.get(getXiu());
  }

  /**
   * 获取宿歌诀
   *
   * @return 宿歌诀
   */
  public String getXiuSong() {
    return LunarUtil.XIU_SONG.get(getXiu());
  }

  /**
   * 获取政
   *
   * @return 政
   */
  public String getZheng() {
    return LunarUtil.ZHENG.get(getXiu());
  }

  /**
   * 获取动物
   *
   * @return 动物
   */
  public String getAnimal() {
    return LunarUtil.ANIMAL.get(getXiu());
  }

  /**
   * 获取宫
   *
   * @return 宫
   */
  public String getGong() {
    return LunarUtil.GONG.get(getXiu());
  }

  /**
   * 获取兽
   *
   * @return 兽
   */
  public String getShou() {
    return LunarUtil.SHOU.get(getGong());
  }

  /**
   * 获取节日，有可能一天会有多个节日
   *
   * @return 节日列表，如春节
   */
  public List<String> getFestivals() {
    List<String> l = new ArrayList<String>();
    String f = LunarUtil.FESTIVAL.get(month + "-" + day);
    if (null != f) {
      l.add(f);
    }
    if (Math.abs(month) == 12 && day >= 29 && year != next(1).getYear()) {
      l.add("除夕");
    }
    return l;
  }

  /**
   * 获取非正式的节日，有可能一天会有多个节日
   *
   * @return 非正式的节日列表，如中元节
   */
  public List<String> getOtherFestivals() {
    List<String> l = new ArrayList<String>();
    List<String> fs = LunarUtil.OTHER_FESTIVAL.get(month + "-" + day);
    if (null != fs) {
      l.addAll(fs);
    }
    String solarYmd = solar.toYmd();
    if (solarYmd.equals(jieQi.get("清明").next(-1).toYmd())) {
      l.add("寒食节");
    }
    Solar jq = jieQi.get("立春");
    int offset = 4 - jq.getLunar().getDayGanIndex();
    if (offset < 0) {
      offset += 10;
    }
    if (solarYmd.equals(jq.next(offset + 40).toYmd())) {
      l.add("春社");
    }

    jq = jieQi.get("立秋");
    offset = 4 - jq.getLunar().getDayGanIndex();
    if (offset < 0) {
      offset += 10;
    }
    if (solarYmd.equals(jq.next(offset + 40).toYmd())) {
      l.add("秋社");
    }
    return l;
  }

  /**
   * 获取彭祖百忌天干
   *
   * @return 彭祖百忌天干
   */
  public String getPengZuGan() {
    return LunarUtil.PENGZU_GAN[dayGanIndex + 1];
  }

  /**
   * 获取彭祖百忌地支
   *
   * @return 彭祖百忌地支
   */
  public String getPengZuZhi() {
    return LunarUtil.PENGZU_ZHI[dayZhiIndex + 1];
  }

  /**
   * 获取日喜神方位
   *
   * @return 方位，如艮
   * @deprecated 使用getDayPositionXi
   */
  public String getPositionXi() {
    return getDayPositionXi();
  }

  /**
   * 获取喜神方位描述
   *
   * @return 方位描述，如东北
   * @deprecated 使用getDayPositionXiDesc
   */
  public String getPositionXiDesc() {
    return getDayPositionXiDesc();
  }

  /**
   * 获取阳贵神方位
   *
   * @return 阳贵神方位，如艮
   * @deprecated 使用getDayPositionYangGui
   */
  public String getPositionYangGui() {
    return getDayPositionYangGui();
  }

  /**
   * 获取阳贵神方位描述
   *
   * @return 方位描述，如东北
   * @deprecated 使用getDayPositionYangGuiDesc
   */
  public String getPositionYangGuiDesc() {
    return getDayPositionYangGuiDesc();
  }

  /**
   * 获取阴贵神方位
   *
   * @return 方位，如艮
   * @deprecated 使用getDayPositionYinGui
   */
  public String getPositionYinGui() {
    return getDayPositionYinGui();
  }

  /**
   * 获取阴贵神方位描述
   *
   * @return 阴贵神方位描述，如东北
   * @deprecated 使用getDayPositionYinGuiDesc
   */
  public String getPositionYinGuiDesc() {
    return getDayPositionYinGuiDesc();
  }

  /**
   * 获取福神方位
   *
   * @return 方位，如艮
   * @deprecated 使用getDayPositionFu
   */
  public String getPositionFu() {
    return getDayPositionFu();
  }

  /**
   * 获取福神方位描述
   *
   * @return 方位描述，如东北
   * @deprecated 使用getDayPositionFuDesc
   */
  public String getPositionFuDesc() {
    return getDayPositionFuDesc();
  }

  /**
   * 获取财神方位
   *
   * @return 方位，如艮
   * @deprecated 使用getDayPositionCai
   */
  public String getPositionCai() {
    return getDayPositionCai();
  }

  /**
   * 获取财神方位描述
   *
   * @return 方位描述，如东北
   * @deprecated 使用getDayPositionCaiDesc
   */
  public String getPositionCaiDesc() {
    return getDayPositionCaiDesc();
  }

  /**
   * 获取日喜神方位
   *
   * @return 方位，如艮
   */
  public String getDayPositionXi() {
    return LunarUtil.POSITION_XI[dayGanIndex + 1];
  }

  /**
   * 获取日喜神方位描述
   *
   * @return 方位描述，如东北
   */
  public String getDayPositionXiDesc() {
    return LunarUtil.POSITION_DESC.get(getDayPositionXi());
  }

  /**
   * 获取日阳贵神方位
   *
   * @return 方位，如艮
   */
  public String getDayPositionYangGui() {
    return LunarUtil.POSITION_YANG_GUI[dayGanIndex + 1];
  }

  /**
   * 获取日阳贵神方位描述
   *
   * @return 方位描述，如东北
   */
  public String getDayPositionYangGuiDesc() {
    return LunarUtil.POSITION_DESC.get(getDayPositionYangGui());
  }

  /**
   * 获取日阴贵神方位
   *
   * @return 阴贵神方位，如艮
   */
  public String getDayPositionYinGui() {
    return LunarUtil.POSITION_YIN_GUI[dayGanIndex + 1];
  }

  /**
   * 获取日阴贵神方位描述
   *
   * @return 方位描述，如东北
   */
  public String getDayPositionYinGuiDesc() {
    return LunarUtil.POSITION_DESC.get(getDayPositionYinGui());
  }

  /**
   * 获取日福神方位（默认流派：2）
   *
   * @return 福神方位，如艮
   */
  public String getDayPositionFu() {
    return getDayPositionFu(2);
  }

  /**
   * 获取日福神方位
   *
   * @param sect 流派，1或2
   * @return 方位，如艮
   */
  public String getDayPositionFu(int sect) {
    return (1 == sect ? LunarUtil.POSITION_FU : LunarUtil.POSITION_FU_2)[dayGanIndex + 1];
  }

  /**
   * 获取日福神方位描述（默认流派：2）
   *
   * @return 方位描述，如东北
   */
  public String getDayPositionFuDesc() {
    return getDayPositionFuDesc(2);
  }

  /**
   * 获取日福神方位描述
   *
   * @param sect 流派，1或2
   * @return 方位描述，如东北
   */
  public String getDayPositionFuDesc(int sect) {
    return LunarUtil.POSITION_DESC.get(getDayPositionFu(sect));
  }

  /**
   * 获取日财神方位
   *
   * @return 方位，如艮
   */
  public String getDayPositionCai() {
    return LunarUtil.POSITION_CAI[dayGanIndex + 1];
  }

  /**
   * 获取日财神方位描述
   *
   * @return 方位描述，如东北
   */
  public String getDayPositionCaiDesc() {
    return LunarUtil.POSITION_DESC.get(getDayPositionCai());
  }

  /**
   * 获取年太岁方位（默认流派2新年以立春零点起算）
   *
   * @return 方位，如艮
   */
  public String getYearPositionTaiSui() {
    return getYearPositionTaiSui(2);
  }

  /**
   * 获取年太岁方位
   *
   * @param sect 流派：2为新年以立春零点起算；1为新年以正月初一起算；3为新年以立春节气交接的时刻起算
   * @return 方位，如艮
   */
  public String getYearPositionTaiSui(int sect) {
    int yearZhiIndex;
    switch (sect) {
      case 1:
        yearZhiIndex = this.yearZhiIndex;
        break;
      case 3:
        yearZhiIndex = this.yearZhiIndexExact;
        break;
      default:
        yearZhiIndex = this.yearZhiIndexByLiChun;
    }
    return LunarUtil.POSITION_TAI_SUI_YEAR[yearZhiIndex];
  }

  /**
   * 获取年太岁方位描述（默认流派2新年以立春零点起算）
   *
   * @return 方位描述，如东北
   */
  public String getYearPositionTaiSuiDesc() {
    return getYearPositionTaiSuiDesc(2);
  }

  /**
   * 获取年太岁方位描述
   *
   * @param sect 流派：2为新年以立春零点起算；1为新年以正月初一起算；3为新年以立春节气交接的时刻起算
   * @return 方位描述，如东北
   */
  public String getYearPositionTaiSuiDesc(int sect) {
    return LunarUtil.POSITION_DESC.get(getYearPositionTaiSui(sect));
  }

  protected String getMonthPositionTaiSui(int monthZhiIndex, int monthGanIndex) {
    String p;
    int m = monthZhiIndex - LunarUtil.BASE_MONTH_ZHI_INDEX;
    if (m < 0) {
      m += 12;
    }
    m = m % 4;
    switch (m) {
      case 0:
        p = "艮";
        break;
      case 2:
        p = "坤";
        break;
      case 3:
        p = "巽";
        break;
      default:
        p = LunarUtil.POSITION_GAN[monthGanIndex];
    }
    return p;
  }

  /**
   * 获取月太岁方位（默认流派2新的一月以节交接当天零点起算）
   *
   * @return 太岁方位，如艮
   */
  public String getMonthPositionTaiSui() {
    return getMonthPositionTaiSui(2);
  }

  /**
   * 获取月太岁方位
   *
   * @param sect 流派：2为新的一月以节交接当天零点起算；3为新的一月以节交接准确时刻起算
   * @return 太岁方位，如艮
   */
  public String getMonthPositionTaiSui(int sect) {
    int monthZhiIndex;
    int monthGanIndex;
    if (sect == 3) {
      monthZhiIndex = this.monthZhiIndexExact;
      monthGanIndex = this.monthGanIndexExact;
    } else {
      monthZhiIndex = this.monthZhiIndex;
      monthGanIndex = this.monthGanIndex;
    }
    return getMonthPositionTaiSui(monthZhiIndex, monthGanIndex);
  }

  /**
   * 获取月太岁方位描述（默认流派2新的一月以节交接当天零点起算）
   *
   * @return 太岁方位描述，如东北
   */
  public String getMonthPositionTaiSuiDesc() {
    return getMonthPositionTaiSuiDesc(2);
  }

  /**
   * 获取月太岁方位描述
   *
   * @param sect 流派：2为新的一月以节交接当天零点起算；3为新的一月以节交接准确时刻起算
   * @return 太岁方位描述，如东北
   */
  public String getMonthPositionTaiSuiDesc(int sect) {
    return LunarUtil.POSITION_DESC.get(getMonthPositionTaiSui(sect));
  }

  protected String getDayPositionTaiSui(String dayInGanZhi, int yearZhiIndex) {
    String p;
    if ("甲子,乙丑,丙寅,丁卯,戊辰,己巳".contains(dayInGanZhi)) {
      p = "震";
    } else if ("丙子,丁丑,戊寅,己卯,庚辰,辛巳".contains(dayInGanZhi)) {
      p = "离";
    } else if ("戊子,己丑,庚寅,辛卯,壬辰,癸巳".contains(dayInGanZhi)) {
      p = "中";
    } else if ("庚子,辛丑,壬寅,癸卯,甲辰,乙巳".contains(dayInGanZhi)) {
      p = "兑";
    } else if ("壬子,癸丑,甲寅,乙卯,丙辰,丁巳".contains(dayInGanZhi)) {
      p = "坎";
    } else {
      p = LunarUtil.POSITION_TAI_SUI_YEAR[yearZhiIndex];
    }
    return p;
  }

  /**
   * 获取日太岁方位（默认流派2新年以立春零点起算）
   *
   * @return 太岁方位，如艮
   */
  public String getDayPositionTaiSui() {
    return getDayPositionTaiSui(2);
  }

  /**
   * 获取日太岁方位
   *
   * @param sect 流派：2新年以立春零点起算；1新年以正月初一起算；3新年以立春节气交接的时刻起算
   * @return 太岁方位，如艮
   */
  public String getDayPositionTaiSui(int sect) {
    String dayInGanZhi;
    int yearZhiIndex;
    switch (sect) {
      case 1:
        dayInGanZhi = getDayInGanZhi();
        yearZhiIndex = this.yearZhiIndex;
        break;
      case 3:
        dayInGanZhi = getDayInGanZhi();
        yearZhiIndex = this.yearZhiIndexExact;
        break;
      default:
        dayInGanZhi = getDayInGanZhiExact2();
        yearZhiIndex = this.yearZhiIndexByLiChun;
    }
    return getDayPositionTaiSui(dayInGanZhi, yearZhiIndex);
  }

  /**
   * 获取日太岁方位描述（默认流派2新年以立春零点起算）
   *
   * @return 太岁方位描述，如东北
   */
  public String getDayPositionTaiSuiDesc() {
    return getDayPositionTaiSuiDesc(2);
  }

  /**
   * 获取日太岁方位描述
   *
   * @param sect 流派：2新年以立春零点起算；1新年以正月初一起算；3新年以立春节气交接的时刻起算
   * @return 太岁方位描述，如东北
   */
  public String getDayPositionTaiSuiDesc(int sect) {
    return LunarUtil.POSITION_DESC.get(getDayPositionTaiSui(sect));
  }

  /**
   * 获取时辰喜神方位
   *
   * @return 喜神方位，如艮
   */
  public String getTimePositionXi() {
    return LunarUtil.POSITION_XI[timeGanIndex + 1];
  }

  /**
   * 获取时辰喜神方位描述
   *
   * @return 喜神方位描述，如东北
   */
  public String getTimePositionXiDesc() {
    return LunarUtil.POSITION_DESC.get(getTimePositionXi());
  }

  /**
   * 获取时辰阳贵神方位
   *
   * @return 阳贵神方位，如艮
   */
  public String getTimePositionYangGui() {
    return LunarUtil.POSITION_YANG_GUI[timeGanIndex + 1];
  }

  /**
   * 获取时辰阳贵神方位描述
   *
   * @return 阳贵神方位描述，如东北
   */
  public String getTimePositionYangGuiDesc() {
    return LunarUtil.POSITION_DESC.get(getTimePositionYangGui());
  }

  /**
   * 获取时辰阴贵神方位
   *
   * @return 阴贵神方位，如艮
   */
  public String getTimePositionYinGui() {
    return LunarUtil.POSITION_YIN_GUI[timeGanIndex + 1];
  }

  /**
   * 获取时辰阴贵神方位描述
   *
   * @return 阴贵神方位描述，如东北
   */
  public String getTimePositionYinGuiDesc() {
    return LunarUtil.POSITION_DESC.get(getTimePositionYinGui());
  }

  /**
   * 获取时辰福神方位，默认流派2
   *
   * @return 福神方位，如艮
   */
  public String getTimePositionFu() {
    return getTimePositionFu(2);
  }

  /**
   * 获取时辰福神方位
   *
   * @param sect 流派，1或2
   * @return 福神方位，如艮
   */
  public String getTimePositionFu(int sect) {
    return (1 == sect ? LunarUtil.POSITION_FU : LunarUtil.POSITION_FU_2)[timeGanIndex + 1];
  }

  /**
   * 获取时辰福神方位描述，默认流派2
   *
   * @return 福神方位描述，如东北
   */
  public String getTimePositionFuDesc() {
    return getTimePositionFuDesc(2);
  }

  /**
   * 获取时辰福神方位描述
   *
   * @param sect 流派，1或2
   * @return 福神方位描述，如东北
   */
  public String getTimePositionFuDesc(int sect) {
    return LunarUtil.POSITION_DESC.get(getTimePositionFu(sect));
  }

  /**
   * 获取时辰财神方位
   *
   * @return 财神方位，如艮
   */
  public String getTimePositionCai() {
    return LunarUtil.POSITION_CAI[timeGanIndex + 1];
  }

  /**
   * 获取时辰财神方位描述
   *
   * @return 财神方位描述，如东北
   */
  public String getTimePositionCaiDesc() {
    return LunarUtil.POSITION_DESC.get(getTimePositionCai());
  }

  /**
   * 获取冲
   *
   * @return 冲，如申
   * @deprecated 使用getDayChong
   */
  @Deprecated
  public String getChong() {
    return getDayChong();
  }

  /**
   * 获取无情之克的冲天干
   *
   * @return 无情之克的冲天干，如甲
   * @deprecated 使用getDayChongGan
   */
  @Deprecated
  public String getChongGan() {
    return getDayChongGan();
  }

  /**
   * 获取有情之克的冲天干
   *
   * @return 有情之克的冲天干，如甲
   * @deprecated 使用getDayChongGanTie
   */
  @Deprecated
  public String getChongGanTie() {
    return getDayChongGanTie();
  }

  /**
   * 获取冲生肖
   *
   * @return 冲生肖，如猴
   * @deprecated 使用getDayChongShengXiao
   */
  @Deprecated
  public String getChongShengXiao() {
    return getDayChongShengXiao();
  }

  /**
   * 获取冲描述
   *
   * @return 冲描述，如(壬申)猴
   * @deprecated 使用getDayChongDesc
   */
  @Deprecated
  public String getChongDesc() {
    return getDayChongDesc();
  }

  /**
   * 获取煞
   *
   * @return 煞，如北
   * @deprecated 使用getDaySha
   */
  @Deprecated
  public String getSha() {
    return getDaySha();
  }

  /**
   * 获取年纳音
   *
   * @return 年纳音，如剑锋金
   */
  public String getYearNaYin() {
    return LunarUtil.NAYIN.get(getYearInGanZhi());
  }

  /**
   * 获取月纳音
   *
   * @return 月纳音，如剑锋金
   */
  public String getMonthNaYin() {
    return LunarUtil.NAYIN.get(getMonthInGanZhi());
  }

  /**
   * 获取日纳音
   *
   * @return 日纳音，如剑锋金
   */
  public String getDayNaYin() {
    return LunarUtil.NAYIN.get(getDayInGanZhi());
  }

  /**
   * 获取时辰纳音
   *
   * @return 时辰纳音，如剑锋金
   */
  public String getTimeNaYin() {
    return LunarUtil.NAYIN.get(getTimeInGanZhi());
  }

  /**
   * 获取八字，男性也称乾造，女性也称坤造（以立春交接时刻作为新年的开始）
   *
   * @return 八字（男性也称乾造，女性也称坤造）
   * @deprecated 使用getEightChar
   */
  @Deprecated
  public List<String> getBaZi() {
    List<String> l = new ArrayList<String>(4);
    EightChar eightChar = getEightChar();
    l.add(eightChar.getYear());
    l.add(eightChar.getMonth());
    l.add(eightChar.getDay());
    l.add(eightChar.getTime());
    return l;
  }

  /**
   * 获取八字五行
   *
   * @return 八字五行
   * @deprecated 使用getEightChar
   */
  @Deprecated
  public List<String> getBaZiWuXing() {
    List<String> l = new ArrayList<String>(4);
    EightChar eightChar = getEightChar();
    l.add(eightChar.getYearWuXing());
    l.add(eightChar.getMonthWuXing());
    l.add(eightChar.getDayWuXing());
    l.add(eightChar.getTimeWuXing());
    return l;
  }

  /**
   * 获取八字纳音
   *
   * @return 八字纳音
   * @deprecated 使用getEightChar
   */
  @Deprecated
  public List<String> getBaZiNaYin() {
    List<String> l = new ArrayList<String>(4);
    EightChar eightChar = getEightChar();
    l.add(eightChar.getYearNaYin());
    l.add(eightChar.getMonthNaYin());
    l.add(eightChar.getDayNaYin());
    l.add(eightChar.getTimeNaYin());
    return l;
  }

  /**
   * 获取八字天干十神，日柱十神为日主，其余三柱根据天干十神表查询
   *
   * @return 八字天干十神
   * @deprecated 使用getEightChar
   */
  @Deprecated
  public List<String> getBaZiShiShenGan() {
    List<String> l = new ArrayList<String>(4);
    EightChar eightChar = getEightChar();
    l.add(eightChar.getYearShiShenGan());
    l.add(eightChar.getMonthShiShenGan());
    l.add(eightChar.getDayShiShenGan());
    l.add(eightChar.getTimeShiShenGan());
    return l;
  }

  /**
   * 获取八字地支十神，根据地支十神表查询
   *
   * @return 八字地支十神
   * @deprecated 使用getEightChar
   */
  @Deprecated
  public List<String> getBaZiShiShenZhi() {
    List<String> l = new ArrayList<String>(4);
    EightChar eightChar = getEightChar();
    l.add(eightChar.getYearShiShenZhi().get(0));
    l.add(eightChar.getMonthShiShenZhi().get(0));
    l.add(eightChar.getDayShiShenZhi().get(0));
    l.add(eightChar.getTimeShiShenZhi().get(0));
    return l;
  }

  /**
   * 获取八字年支十神
   *
   * @return 八字年支十神
   * @deprecated 使用getEightChar
   */
  @Deprecated
  public List<String> getBaZiShiShenYearZhi() {
    return getEightChar().getYearShiShenZhi();
  }

  /**
   * 获取八字月支十神
   *
   * @return 八字月支十神
   * @deprecated 使用getEightChar
   */
  @Deprecated
  public List<String> getBaZiShiShenMonthZhi() {
    return getEightChar().getMonthShiShenZhi();
  }

  /**
   * 获取八字日支十神
   *
   * @return 八字日支十神
   * @deprecated 使用getEightChar
   */
  @Deprecated
  public List<String> getBaZiShiShenDayZhi() {
    return getEightChar().getDayShiShenZhi();
  }

  /**
   * 获取八字时支十神
   *
   * @return 八字时支十神
   * @deprecated 使用getEightChar
   */
  @Deprecated
  public List<String> getBaZiShiShenTimeZhi() {
    return getEightChar().getTimeShiShenZhi();
  }

  /**
   * 获取十二执星：建、除、满、平、定、执、破、危、成、收、开、闭。当月支与日支相同即为建，依次类推
   *
   * @return 执星
   */
  public String getZhiXing() {
    int offset = dayZhiIndex - monthZhiIndex;
    if (offset < 0) {
      offset += 12;
    }
    return LunarUtil.ZHI_XING[offset + 1];
  }

  /**
   * 获取值日天神
   *
   * @return 值日天神
   */
  public String getDayTianShen() {
    return LunarUtil.TIAN_SHEN[(dayZhiIndex + LunarUtil.ZHI_TIAN_SHEN_OFFSET.get(getMonthZhi())) % 12 + 1];
  }

  /**
   * 获取值时天神
   *
   * @return 值时天神
   */
  public String getTimeTianShen() {
    return LunarUtil.TIAN_SHEN[(timeZhiIndex + LunarUtil.ZHI_TIAN_SHEN_OFFSET.get(getDayZhiExact())) % 12 + 1];
  }

  /**
   * 获取值日天神类型：黄道/黑道
   *
   * @return 值日天神类型：黄道/黑道
   */
  public String getDayTianShenType() {
    return LunarUtil.TIAN_SHEN_TYPE.get(getDayTianShen());
  }

  /**
   * 获取值时天神类型：黄道/黑道
   *
   * @return 值时天神类型：黄道/黑道
   */
  public String getTimeTianShenType() {
    return LunarUtil.TIAN_SHEN_TYPE.get(getTimeTianShen());
  }

  /**
   * 获取值日天神吉凶
   *
   * @return 吉/凶
   */
  public String getDayTianShenLuck() {
    return LunarUtil.TIAN_SHEN_TYPE_LUCK.get(getDayTianShenType());
  }

  /**
   * 获取值时天神吉凶
   *
   * @return 吉/凶
   */
  public String getTimeTianShenLuck() {
    return LunarUtil.TIAN_SHEN_TYPE_LUCK.get(getTimeTianShenType());
  }

  /**
   * 获取逐日胎神方位
   *
   * @return 逐日胎神方位
   */
  public String getDayPositionTai() {
    return LunarUtil.POSITION_TAI_DAY[LunarUtil.getJiaZiIndex(getDayInGanZhi())];
  }

  /**
   * 获取逐月胎神方位，闰月无
   *
   * @return 逐月胎神方位
   */
  public String getMonthPositionTai() {
    if (month < 0) {
      return "";
    }
    return LunarUtil.POSITION_TAI_MONTH[month - 1];
  }

  /**
   * 使用默认流派1（以节交接当天起算月）获取每日宜，如果没有，返回["无"]
   *
   * @return 宜
   */
  public List<String> getDayYi() {
    return getDayYi(1);
  }

  /**
   * 获取每日宜，如果没有，返回["无"]
   *
   * @param sect 流派，1以节交接当天起算月，2以节交接时刻起算月
   * @return 宜
   */
  public List<String> getDayYi(int sect) {
    return LunarUtil.getDayYi(2 == sect ? getMonthInGanZhiExact() : getMonthInGanZhi(), getDayInGanZhi());
  }

  /**
   * 使用默认流派1（以节交接当天起算月）获取每日忌，如果没有，返回["无"]
   *
   * @return 忌
   */
  public List<String> getDayJi() {
    return getDayJi(1);
  }

  /**
   * 获取每日忌，如果没有，返回["无"]
   *
   * @param sect 流派，1以节交接当天起算月，2以节交接时刻起算月
   * @return 忌
   */
  public List<String> getDayJi(int sect) {
    return LunarUtil.getDayJi(2 == sect ? getMonthInGanZhiExact() : getMonthInGanZhi(), getDayInGanZhi());
  }

  /**
   * 获取日吉神（宜趋），如果没有，返回["无"]
   *
   * @return 日吉神
   */
  public List<String> getDayJiShen() {
    return LunarUtil.getDayJiShen(getMonthZhiIndex(), getDayInGanZhi());
  }

  /**
   * 获取日凶煞（宜忌），如果没有，返回["无"]
   *
   * @return 日凶煞
   */
  public List<String> getDayXiongSha() {
    return LunarUtil.getDayXiongSha(getMonthZhiIndex(), getDayInGanZhi());
  }

  /**
   * 获取日冲
   *
   * @return 日冲，如申
   */
  public String getDayChong() {
    return LunarUtil.CHONG[dayZhiIndex];
  }

  /**
   * 获取日煞
   *
   * @return 日煞，如北
   */
  public String getDaySha() {
    return LunarUtil.SHA.get(getDayZhi());
  }

  /**
   * 获取日冲描述
   *
   * @return 日冲描述，如(壬申)猴
   */
  public String getDayChongDesc() {
    return "(" + getDayChongGan() + getDayChong() + ")" + getDayChongShengXiao();
  }

  /**
   * 获取日冲生肖
   *
   * @return 日冲生肖，如猴
   */
  public String getDayChongShengXiao() {
    String chong = getDayChong();
    for (int i = 0, j = LunarUtil.ZHI.length; i < j; i++) {
      if (LunarUtil.ZHI[i].equals(chong)) {
        return LunarUtil.SHENGXIAO[i];
      }
    }
    return "";
  }

  /**
   * 获取无情之克的日冲天干
   *
   * @return 无情之克的日冲天干，如甲
   */
  public String getDayChongGan() {
    return LunarUtil.CHONG_GAN[dayGanIndex];
  }

  /**
   * 获取有情之克的日冲天干
   *
   * @return 有情之克的日冲天干，如甲
   */
  public String getDayChongGanTie() {
    return LunarUtil.CHONG_GAN_TIE[dayGanIndex];
  }

  /**
   * 获取时冲
   *
   * @return 时冲，如申
   */
  public String getTimeChong() {
    return LunarUtil.CHONG[timeZhiIndex];
  }

  /**
   * 获取时煞
   *
   * @return 时煞，如北
   */
  public String getTimeSha() {
    return LunarUtil.SHA.get(getTimeZhi());
  }

  /**
   * 获取时冲生肖
   *
   * @return 时冲生肖，如猴
   */
  public String getTimeChongShengXiao() {
    String chong = getTimeChong();
    for (int i = 0, j = LunarUtil.ZHI.length; i < j; i++) {
      if (LunarUtil.ZHI[i].equals(chong)) {
        return LunarUtil.SHENGXIAO[i];
      }
    }
    return "";
  }

  /**
   * 获取时冲描述
   *
   * @return 时冲描述，如(壬申)猴
   */
  public String getTimeChongDesc() {
    return "(" + getTimeChongGan() + getTimeChong() + ")" + getTimeChongShengXiao();
  }

  /**
   * 获取无情之克的时冲天干
   *
   * @return 无情之克的时冲天干，如甲
   */
  public String getTimeChongGan() {
    return LunarUtil.CHONG_GAN[timeGanIndex];
  }

  /**
   * 获取有情之克的时冲天干
   *
   * @return 有情之克的时冲天干，如甲
   */
  public String getTimeChongGanTie() {
    return LunarUtil.CHONG_GAN_TIE[timeGanIndex];
  }

  /**
   * 获取时辰宜，如果没有，返回["无"]
   *
   * @return 宜
   */
  public List<String> getTimeYi() {
    return LunarUtil.getTimeYi(getDayInGanZhiExact(), getTimeInGanZhi());
  }

  /**
   * 获取时辰忌，如果没有，返回["无"]
   *
   * @return 忌
   */
  public List<String> getTimeJi() {
    return LunarUtil.getTimeJi(getDayInGanZhiExact(), getTimeInGanZhi());
  }

  /**
   * 获取月相
   *
   * @return 月相
   */
  public String getYueXiang() {
    return LunarUtil.YUE_XIANG[day];
  }

  protected NineStar getYearNineStar(String yearInGanZhi) {
    int indexExact = LunarUtil.getJiaZiIndex(yearInGanZhi) + 1;
    int index = LunarUtil.getJiaZiIndex(this.getYearInGanZhi()) + 1;
    int yearOffset = indexExact - index;
    if (yearOffset > 1) {
      yearOffset -= 60;
    } else if (yearOffset < -1) {
      yearOffset += 60;
    }
    int yuan = ((this.year + yearOffset + 2696) / 60) % 3;
    int offset = (62 + yuan * 3 - indexExact) % 9;
    if (0 == offset) {
      offset = 9;
    }
    return NineStar.fromIndex(offset - 1);
  }

  /**
   * 获取值年九星（默认流派2新年以立春零点起算。流年紫白星起例歌诀：年上吉星论甲子，逐年星逆中宫起；上中下作三元汇，一上四中七下兑。）
   *
   * @return 九星
   */
  public NineStar getYearNineStar() {
    return getYearNineStar(2);
  }

  /**
   * 获取值年九星（流年紫白星起例歌诀：年上吉星论甲子，逐年星逆中宫起；上中下作三元汇，一上四中七下兑。）
   *
   * @param sect 流派：2为新年以立春零点起算；1为新年以正月初一起算；3为新年以立春节气交接的时刻起算
   * @return 九星
   */
  public NineStar getYearNineStar(int sect) {
    String yearInGanZhi;
    switch (sect) {
      case 1:
        yearInGanZhi = this.getYearInGanZhi();
        break;
      case 3:
        yearInGanZhi = this.getYearInGanZhiExact();
        break;
      default:
        yearInGanZhi = this.getYearInGanZhiByLiChun();
    }
    return getYearNineStar(yearInGanZhi);
  }

  protected NineStar getMonthNineStar(int yearZhiIndex, int monthZhiIndex) {
    int index = yearZhiIndex % 3;
    int n = 27 - index * 3;
    if (monthZhiIndex < LunarUtil.BASE_MONTH_ZHI_INDEX) {
      n -= 3;
    }
    int offset = (n - monthZhiIndex) % 9;
    return NineStar.fromIndex(offset);
  }

  /**
   * 获取值月九星（流派2新的一月以节交接当天零点起算。月紫白星歌诀：子午卯酉八白起，寅申巳亥二黑求，辰戌丑未五黄中。）
   *
   * @return 九星
   */
  public NineStar getMonthNineStar() {
    return getMonthNineStar(2);
  }

  /**
   * 获取值月九星（月紫白星歌诀：子午卯酉八白起，寅申巳亥二黑求，辰戌丑未五黄中。）
   *
   * @param sect 流派：2为新的一月以节交接当天零点起算；3为新的一月以节交接准确时刻起算
   * @return 九星
   */
  public NineStar getMonthNineStar(int sect) {
    int yearZhiIndex;
    int monthZhiIndex;
    switch (sect) {
      case 1:
        yearZhiIndex = this.yearZhiIndex;
        monthZhiIndex = this.monthZhiIndex;
        break;
      case 3:
        yearZhiIndex = this.yearZhiIndexExact;
        monthZhiIndex = this.monthZhiIndexExact;
        break;
      default:
        yearZhiIndex = this.yearZhiIndexByLiChun;
        monthZhiIndex = this.monthZhiIndex;
    }
    return getMonthNineStar(yearZhiIndex, monthZhiIndex);
  }

  /**
   * 获取值日九星（日家紫白星歌诀：日家白法不难求，二十四气六宫周；冬至雨水及谷雨，阳顺一七四中游；夏至处暑霜降后，九三六星逆行求。）
   *
   * @return 九星
   */
  public NineStar getDayNineStar() {
    String solarYmd = solar.toYmd();
    Solar dongZhi = jieQi.get("冬至");
    Solar dongZhi2 = jieQi.get("DONG_ZHI");
    Solar xiaZhi = jieQi.get("夏至");
    int dongZhiIndex = LunarUtil.getJiaZiIndex(dongZhi.getLunar().getDayInGanZhi());
    int dongZhiIndex2 = LunarUtil.getJiaZiIndex(dongZhi2.getLunar().getDayInGanZhi());
    int xiaZhiIndex = LunarUtil.getJiaZiIndex(xiaZhi.getLunar().getDayInGanZhi());
    Solar solarShunBai;
    Solar solarShunBai2;
    Solar solarNiZi;
    if (dongZhiIndex > 29) {
      solarShunBai = dongZhi.next(60 - dongZhiIndex);
    } else {
      solarShunBai = dongZhi.next(-dongZhiIndex);
    }
    String solarShunBaiYmd = solarShunBai.toYmd();
    if (dongZhiIndex2 > 29) {
      solarShunBai2 = dongZhi2.next(60 - dongZhiIndex2);
    } else {
      solarShunBai2 = dongZhi2.next(-dongZhiIndex2);
    }
    String solarShunBaiYmd2 = solarShunBai2.toYmd();
    if (xiaZhiIndex > 29) {
      solarNiZi = xiaZhi.next(60 - xiaZhiIndex);
    } else {
      solarNiZi = xiaZhi.next(-xiaZhiIndex);
    }
    String solarNiZiYmd = solarNiZi.toYmd();
    int offset = 0;
    if (solarYmd.compareTo(solarShunBaiYmd) >= 0 && solarYmd.compareTo(solarNiZiYmd) < 0) {
      offset = solar.subtract(solarShunBai) % 9;
    } else if (solarYmd.compareTo(solarNiZiYmd) >= 0 && solarYmd.compareTo(solarShunBaiYmd2) < 0) {
      offset = 8 - (solar.subtract(solarNiZi) % 9);
    } else if (solarYmd.compareTo(solarShunBaiYmd2) >= 0) {
      offset = solar.subtract(solarShunBai2) % 9;
    } else if (solarYmd.compareTo(solarShunBaiYmd) < 0) {
      offset = (8 + solarShunBai.subtract(solar)) % 9;
    }
    return NineStar.fromIndex(offset);
  }

  /**
   * 获取值时九星（时家紫白星歌诀：三元时白最为佳，冬至阳生顺莫差，孟日七宫仲一白，季日四绿发萌芽，每把时辰起甲子，本时星耀照光华，时星移入中宫去，顺飞八方逐细查。夏至阴生逆回首，孟归三碧季加六，仲在九宫时起甲，依然掌中逆轮跨。）
   *
   * @return 九星
   */
  public NineStar getTimeNineStar() {
    String solarYmd = solar.toYmd();
    boolean asc = false;
    if ((solarYmd.compareTo(jieQi.get("冬至").toYmd()) >= 0 && solarYmd.compareTo(jieQi.get("夏至").toYmd()) < 0)) {
      asc = true;
    } else if (solarYmd.compareTo(jieQi.get("DONG_ZHI").toYmd()) >= 0) {
      asc = true;
    }
    int start = asc ? 6 : 2;
    String dayZhi = getDayZhi();
    if ("子午卯酉".contains(dayZhi)) {
      start = asc ? 0 : 8;
    } else if ("辰戌丑未".contains(dayZhi)) {
      start = asc ? 3 : 5;
    }
    int index = asc ? start + timeZhiIndex : start + 9 - timeZhiIndex;
    return new NineStar(index % 9);
  }

  /**
   * 获取节气表（节气名称:阳历），节气交接时刻精确到秒，以冬至开头，按先后顺序排列
   *
   * @return 节气表
   */
  public Map<String, Solar> getJieQiTable() {
    return jieQi;
  }

  /**
   * 获取下一节令（顺推的第一个节令）
   *
   * @return 节气
   */
  public JieQi getNextJie() {
    return getNextJie(false);
  }

  /**
   * 获取下一节令（顺推的第一个节令）
   *
   * @param wholeDay 是否按天计
   * @return 节气
   */
  public JieQi getNextJie(boolean wholeDay) {
    int l = JIE_QI_IN_USE.length / 2;
    String[] conditions = new String[l];
    for (int i = 0; i < l; i++) {
      conditions[i] = JIE_QI_IN_USE[i * 2];
    }
    return getNearJieQi(true, conditions, wholeDay);
  }

  /**
   * 获取上一节令（逆推的第一个节令）
   *
   * @return 节气
   */
  public JieQi getPrevJie() {
    return getPrevJie(false);
  }

  /**
   * 获取上一节令（逆推的第一个节令）
   *
   * @param wholeDay 是否按天计
   * @return 节气
   */
  public JieQi getPrevJie(boolean wholeDay) {
    int l = JIE_QI_IN_USE.length / 2;
    String[] conditions = new String[l];
    for (int i = 0; i < l; i++) {
      conditions[i] = JIE_QI_IN_USE[i * 2];
    }
    return getNearJieQi(false, conditions, wholeDay);
  }

  /**
   * 获取下一气令（顺推的第一个气令）
   *
   * @return 节气
   */
  public JieQi getNextQi() {
    return getNextQi(false);
  }

  /**
   * 获取下一气令（顺推的第一个气令）
   *
   * @param wholeDay 是否按天计
   * @return 节气
   */
  public JieQi getNextQi(boolean wholeDay) {
    int l = JIE_QI_IN_USE.length / 2;
    String[] conditions = new String[l];
    for (int i = 0; i < l; i++) {
      conditions[i] = JIE_QI_IN_USE[i * 2 + 1];
    }
    return getNearJieQi(true, conditions, wholeDay);
  }

  /**
   * 获取上一气令（逆推的第一个气令）
   *
   * @return 节气
   */
  public JieQi getPrevQi() {
    return getPrevQi(false);
  }

  /**
   * 获取上一气令（逆推的第一个气令）
   *
   * @param wholeDay 是否按天计
   * @return 节气
   */
  public JieQi getPrevQi(boolean wholeDay) {
    int l = JIE_QI_IN_USE.length / 2;
    String[] conditions = new String[l];
    for (int i = 0; i < l; i++) {
      conditions[i] = JIE_QI_IN_USE[i * 2 + 1];
    }
    return getNearJieQi(false, conditions, wholeDay);
  }

  /**
   * 获取下一节气（顺推的第一个节气）
   *
   * @return 节气
   */
  public JieQi getNextJieQi() {
    return getNextJieQi(false);
  }

  /**
   * 获取下一节气（顺推的第一个节气）
   *
   * @param wholeDay 是否按天计
   * @return 节气
   */
  public JieQi getNextJieQi(boolean wholeDay) {
    return getNearJieQi(true, null, wholeDay);
  }

  /**
   * 获取上一节气（逆推的第一个节气）
   *
   * @return 节气
   */
  public JieQi getPrevJieQi() {
    return getPrevJieQi(false);
  }

  /**
   * 获取上一节气（逆推的第一个节气）
   *
   * @param wholeDay 是否按天计
   * @return 节气
   */
  public JieQi getPrevJieQi(boolean wholeDay) {
    return getNearJieQi(false, null, wholeDay);
  }

  /**
   * 获取最近的节气，如果未找到匹配的，返回null
   *
   * @param forward    是否顺推，true为顺推，false为逆推
   * @param conditions 过滤条件，如果设置过滤条件，仅返回匹配该名称的
   * @param wholeDay   是否按天计
   * @return 节气
   */
  protected JieQi getNearJieQi(boolean forward, String[] conditions, boolean wholeDay) {
    String name = null;
    Solar near = null;
    Set<String> filters = new HashSet<String>();
    if (null != conditions) {
      Collections.addAll(filters, conditions);
    }
    boolean filter = !filters.isEmpty();
    String today = wholeDay ? solar.toYmd() : solar.toYmdHms();
    for (Map.Entry<String, Solar> entry : jieQi.entrySet()) {
      String jq = convertJieQi(entry.getKey());
      if (filter) {
        if (!filters.contains(jq)) {
          continue;
        }
      }
      Solar solar = entry.getValue();
      String day = wholeDay ? solar.toYmd() : solar.toYmdHms();
      if (forward) {
        if (day.compareTo(today) <= 0) {
          continue;
        }
        if (null == near) {
          name = jq;
          near = solar;
        } else {
          String nearDay = wholeDay ? near.toYmd() : near.toYmdHms();
          if (day.compareTo(nearDay) < 0) {
            name = jq;
            near = solar;
          }
        }
      } else {
        if (day.compareTo(today) > 0) {
          continue;
        }
        if (null == near) {
          name = jq;
          near = solar;
        } else {
          String nearDay = wholeDay ? near.toYmd() : near.toYmdHms();
          if (day.compareTo(nearDay) > 0) {
            name = jq;
            near = solar;
          }
        }
      }
    }
    if (null == near) {
      return null;
    }
    return new JieQi(name, near);
  }

  /**
   * 获取节气名称，如果无节气，返回空字符串
   *
   * @return 节气名称
   */
  public String getJieQi() {
    for (Map.Entry<String, Solar> jq : jieQi.entrySet()) {
      Solar d = jq.getValue();
      if (d.getYear() == solar.getYear() && d.getMonth() == solar.getMonth() && d.getDay() == solar.getDay()) {
        return convertJieQi(jq.getKey());
      }
    }
    return "";
  }

  /**
   * 获取当天节气对象，如果无节气，返回null
   *
   * @return 节气对象
   */
  public JieQi getCurrentJieQi() {
    for (Map.Entry<String, Solar> jq : jieQi.entrySet()) {
      Solar d = jq.getValue();
      if (d.getYear() == solar.getYear() && d.getMonth() == solar.getMonth() && d.getDay() == solar.getDay()) {
        return new JieQi(convertJieQi(jq.getKey()), d);
      }
    }
    return null;
  }

  /**
   * 获取当天节令对象，如果无节令，返回null
   *
   * @return 节气对象
   */
  public JieQi getCurrentJie() {
    for (int i = 0, j = JIE_QI_IN_USE.length; i < j; i += 2) {
      String key = JIE_QI_IN_USE[i];
      Solar d = jieQi.get(key);
      if (d.getYear() == solar.getYear() && d.getMonth() == solar.getMonth() && d.getDay() == solar.getDay()) {
        return new JieQi(convertJieQi(key), d);
      }
    }
    return null;
  }

  /**
   * 获取当天气令对象，如果无气令，返回null
   *
   * @return 节气对象
   */
  public JieQi getCurrentQi() {
    for (int i = 1, j = JIE_QI_IN_USE.length; i < j; i += 2) {
      String key = JIE_QI_IN_USE[i];
      Solar d = jieQi.get(key);
      if (d.getYear() == solar.getYear() && d.getMonth() == solar.getMonth() && d.getDay() == solar.getDay()) {
        return new JieQi(convertJieQi(key), d);
      }
    }
    return null;
  }

  public String toFullString() {
    StringBuilder s = new StringBuilder();
    s.append(this);
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
    for (String f : getFestivals()) {
      s.append(" (");
      s.append(f);
      s.append(")");
    }
    for (String f : getOtherFestivals()) {
      s.append(" (");
      s.append(f);
      s.append(")");
    }
    String jq = getJieQi();
    if (jq.length() > 0) {
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
    s.append(getDayPositionXi());
    s.append("](");
    s.append(getDayPositionXiDesc());
    s.append(") 阳贵神方位[");
    s.append(getDayPositionYangGui());
    s.append("](");
    s.append(getDayPositionYangGuiDesc());
    s.append(") 阴贵神方位[");
    s.append(getDayPositionYinGui());
    s.append("](");
    s.append(getDayPositionYinGuiDesc());
    s.append(") 福神方位[");
    s.append(getDayPositionFu());
    s.append("](");
    s.append(getDayPositionFuDesc());
    s.append(") 财神方位[");
    s.append(getDayPositionCai());
    s.append("](");
    s.append(getDayPositionCaiDesc());
    s.append(") 冲[");
    s.append(getDayChongDesc());
    s.append("] 煞[");
    s.append(getDaySha());
    s.append("]");
    return s.toString();
  }

  @Override
  public String toString() {
    return getYearInChinese() + "年" + getMonthInChinese() + "月" + getDayInChinese();
  }

  /**
   * 获取年份
   *
   * @return 如2015
   */
  public int getYear() {
    return year;
  }

  /**
   * 获取月份
   *
   * @return 1到12，负数为闰月
   */
  public int getMonth() {
    return month;
  }

  /**
   * 获取日期
   *
   * @return 日期
   */
  public int getDay() {
    return day;
  }

  /**
   * 获取小时
   *
   * @return 0到23之间的数字
   */
  public int getHour() {
    return hour;
  }

  /**
   * 获取分钟
   *
   * @return 0到59之间的数字
   */
  public int getMinute() {
    return minute;
  }

  /**
   * 获取秒钟
   *
   * @return 0到59之间的数字
   */
  public int getSecond() {
    return second;
  }

  public int getTimeGanIndex() {
    return timeGanIndex;
  }

  public int getTimeZhiIndex() {
    return timeZhiIndex;
  }

  public int getDayGanIndex() {
    return dayGanIndex;
  }

  public int getDayZhiIndex() {
    return dayZhiIndex;
  }

  public int getMonthGanIndex() {
    return monthGanIndex;
  }

  public int getMonthZhiIndex() {
    return monthZhiIndex;
  }

  public int getYearGanIndex() {
    return yearGanIndex;
  }

  public int getYearZhiIndex() {
    return yearZhiIndex;
  }

  public int getYearGanIndexByLiChun() {
    return yearGanIndexByLiChun;
  }

  public int getYearZhiIndexByLiChun() {
    return yearZhiIndexByLiChun;
  }

  public int getDayGanIndexExact() {
    return dayGanIndexExact;
  }

  public int getDayGanIndexExact2() {
    return dayGanIndexExact2;
  }

  public int getDayZhiIndexExact() {
    return dayZhiIndexExact;
  }

  public int getDayZhiIndexExact2() {
    return dayZhiIndexExact2;
  }

  public int getMonthGanIndexExact() {
    return monthGanIndexExact;
  }

  public int getMonthZhiIndexExact() {
    return monthZhiIndexExact;
  }

  public int getYearGanIndexExact() {
    return yearGanIndexExact;
  }

  public int getYearZhiIndexExact() {
    return yearZhiIndexExact;
  }

  public Solar getSolar() {
    return solar;
  }

  public EightChar getEightChar() {
    if (null == eightChar) {
      eightChar = new EightChar(this);
    }
    return eightChar;
  }

  /**
   * 获取往后推几天的农历日期，如果要往前推，则天数用负数
   *
   * @param days 天数
   * @return 农历日期
   */
  public Lunar next(int days) {
    return solar.next(days).getLunar();
  }

  /**
   * 获取年所在旬（以正月初一作为新年的开始）
   *
   * @return 旬
   */
  public String getYearXun() {
    return LunarUtil.getXun(getYearInGanZhi());
  }

  /**
   * 获取年所在旬（以立春当天作为新年的开始）
   *
   * @return 旬
   */
  public String getYearXunByLiChun() {
    return LunarUtil.getXun(getYearInGanZhiByLiChun());
  }

  /**
   * 获取年所在旬（以立春交接时刻作为新年的开始）
   *
   * @return 旬
   */
  public String getYearXunExact() {
    return LunarUtil.getXun(getYearInGanZhiExact());
  }

  /**
   * 获取值年空亡（以正月初一作为新年的开始）
   *
   * @return 空亡(旬空)
   */
  public String getYearXunKong() {
    return LunarUtil.getXunKong(getYearInGanZhi());
  }

  /**
   * 获取值年空亡（以立春当天作为新年的开始）
   *
   * @return 空亡(旬空)
   */
  public String getYearXunKongByLiChun() {
    return LunarUtil.getXunKong(getYearInGanZhiByLiChun());
  }

  /**
   * 获取值年空亡（以立春交接时刻作为新年的开始）
   *
   * @return 空亡(旬空)
   */
  public String getYearXunKongExact() {
    return LunarUtil.getXunKong(getYearInGanZhiExact());
  }

  /**
   * 获取月所在旬（以节交接当天起算）
   *
   * @return 旬
   */
  public String getMonthXun() {
    return LunarUtil.getXun(getMonthInGanZhi());
  }

  /**
   * 获取月所在旬（以节交接时刻起算）
   *
   * @return 旬
   */
  public String getMonthXunExact() {
    return LunarUtil.getXun(getMonthInGanZhiExact());
  }

  /**
   * 获取值月空亡（以节交接当天起算）
   *
   * @return 空亡(旬空)
   */
  public String getMonthXunKong() {
    return LunarUtil.getXunKong(getMonthInGanZhi());
  }

  /**
   * 获取值月空亡（以节交接时刻起算）
   *
   * @return 空亡(旬空)
   */
  public String getMonthXunKongExact() {
    return LunarUtil.getXunKong(getMonthInGanZhiExact());
  }

  /**
   * 获取日所在旬（以节交接当天起算）
   *
   * @return 旬
   */
  public String getDayXun() {
    return LunarUtil.getXun(getDayInGanZhi());
  }

  /**
   * 获取日所在旬（晚子时日柱算明天）
   *
   * @return 旬
   */
  public String getDayXunExact() {
    return LunarUtil.getXun(getDayInGanZhiExact());
  }

  /**
   * 获取日所在旬（晚子时日柱算当天）
   *
   * @return 旬
   */
  public String getDayXunExact2() {
    return LunarUtil.getXun(getDayInGanZhiExact2());
  }

  /**
   * 获取值日空亡
   *
   * @return 空亡(旬空)
   */
  public String getDayXunKong() {
    return LunarUtil.getXunKong(getDayInGanZhi());
  }

  /**
   * 获取值日空亡（晚子时日柱算明天）
   *
   * @return 空亡(旬空)
   */
  public String getDayXunKongExact() {
    return LunarUtil.getXunKong(getDayInGanZhiExact());
  }

  /**
   * 获取值日空亡（晚子时日柱算当天）
   *
   * @return 空亡(旬空)
   */
  public String getDayXunKongExact2() {
    return LunarUtil.getXunKong(getDayInGanZhiExact2());
  }

  /**
   * 获取时辰所在旬
   *
   * @return 旬
   */
  public String getTimeXun() {
    return LunarUtil.getXun(getTimeInGanZhi());
  }

  /**
   * 获取值时空亡
   *
   * @return 空亡(旬空)
   */
  public String getTimeXunKong() {
    return LunarUtil.getXunKong(getTimeInGanZhi());
  }

  /**
   * 获取数九
   *
   * @return 数九，如果不是数九天，返回null
   */
  public ShuJiu getShuJiu() {
    Solar current = Solar.fromYmd(solar.getYear(), solar.getMonth(), solar.getDay());
    Solar start = jieQi.get("DONG_ZHI");
    start = Solar.fromYmd(start.getYear(), start.getMonth(), start.getDay());

    if (current.isBefore(start)) {
      start = jieQi.get("冬至");
      start = Solar.fromYmd(start.getYear(), start.getMonth(), start.getDay());
    }

    Solar end = Solar.fromYmd(start.getYear(), start.getMonth(), start.getDay()).next(81);

    if (current.isBefore(start) || !current.isBefore(end)) {
      return null;
    }

    int days = current.subtract(start);
    return new ShuJiu(LunarUtil.NUMBER[days / 9 + 1] + "九", days % 9 + 1);
  }

  /**
   * 获取三伏
   *
   * @return 三伏，如果不是伏天，返回null
   */
  public Fu getFu() {
    Solar current = Solar.fromYmd(solar.getYear(), solar.getMonth(), solar.getDay());
    Solar xiaZhi = jieQi.get("夏至");
    Solar liQiu = jieQi.get("立秋");
    Solar start = Solar.fromYmd(xiaZhi.getYear(), xiaZhi.getMonth(), xiaZhi.getDay());
    // 第1个庚日
    int add = 6 - xiaZhi.getLunar().getDayGanIndex();
    if (add < 0) {
      add += 10;
    }
    // 第3个庚日，即初伏第1天
    add += 20;
    start = start.next(add);

    // 初伏以前
    if (current.isBefore(start)) {
      return null;
    }

    int days = current.subtract(start);
    if (days < 10) {
      return new Fu("初伏", days + 1);
    }

    // 第4个庚日，中伏第1天
    start = start.next(10);
    days = current.subtract(start);
    if (days < 10) {
      return new Fu("中伏", days + 1);
    }

    // 第5个庚日，中伏第11天或末伏第1天
    start = start.next(10);
    days = current.subtract(start);
    Solar liQiuSolar = Solar.fromYmd(liQiu.getYear(), liQiu.getMonth(), liQiu.getDay());
    // 末伏
    if (liQiuSolar.isAfter(start)) {
      // 中伏
      if (days < 10) {
        return new Fu("中伏", days + 11);
      }
      // 末伏第1天
      start = start.next(10);
      days = current.subtract(start);
    }
    if (days < 10) {
      return new Fu("末伏", days + 1);
    }
    return null;
  }

  /**
   * 获取六曜
   *
   * @return 六曜
   */
  public String getLiuYao() {
    return LunarUtil.LIU_YAO[(Math.abs(month) - 1 + day - 1) % 6];
  }

  /**
   * 获取物候
   *
   * @return 物候
   */
  public String getWuHou() {
    JieQi jieQi = getPrevJieQi(true);
    int offset = 0;
    for (int i = 0, j = JIE_QI.length; i < j; i++) {
      if (jieQi.getName().equals(JIE_QI[i])) {
        offset = i;
        break;
      }
    }
    int index = solar.subtract(jieQi.getSolar()) / 5;
    if (index > 2) {
      index = 2;
    }
    return LunarUtil.WU_HOU[(offset * 3 + index) % LunarUtil.WU_HOU.length];
  }

  /**
   * 获取候
   *
   * @return 候
   */
  public String getHou() {
    JieQi jieQi = getPrevJieQi(true);
    int max = LunarUtil.HOU.length - 1;
    int offset = solar.subtract(jieQi.getSolar()) / 5;
    if (offset > max) {
      offset = max;
    }
    return String.format("%s %s", jieQi.getName(), LunarUtil.HOU[offset]);
  }

  /**
   * 获取日禄
   *
   * @return 日禄
   */
  public String getDayLu() {
    String gan = LunarUtil.LU.get(getDayGan());
    String zhi = LunarUtil.LU.get(getDayZhi());
    String lu = gan + "命互禄";
    if (null != zhi) {
      lu += " " + zhi + "命进禄";
    }
    return lu;
  }

  /**
   * 获取时辰
   *
   * @return 时辰
   */
  public LunarTime getTime() {
    return new LunarTime(year, month, day, hour, minute, second);
  }

  /**
   * 获取当天的时辰列表
   *
   * @return 时辰列表
   */
  public List<LunarTime> getTimes() {
    List<LunarTime> l = new ArrayList<LunarTime>();
    l.add(new LunarTime(year, month, day, 0, 0, 0));
    for (int i = 0; i < 12; i++) {
      l.add(new LunarTime(year, month, day, (i + 1) * 2 - 1, 0, 0));
    }
    return l;
  }

  /**
   * 获取佛历
   *
   * @return 佛历
   */
  public Foto getFoto() {
    return Foto.fromLunar(this);
  }

  /**
   * 获取道历
   *
   * @return 佛历
   */
  public Tao getTao() {
    return Tao.fromLunar(this);
  }

}
