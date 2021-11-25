package com.nlf.calendar;

import com.nlf.calendar.util.FotoUtil;
import com.nlf.calendar.util.LunarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 佛历
 *
 * @author 6tail
 */
public class Foto {

  public static final int DEAD_YEAR = -543;

  /**
   * 阴历
   */
  private Lunar lunar;

  public Foto(Lunar lunar) {
    this.lunar = lunar;
  }

  public static Foto fromLunar(Lunar lunar) {
    return new Foto(lunar);
  }

  public static Foto fromYmdHms(int year, int month, int day, int hour, int minute, int second) {
    return Foto.fromLunar(Lunar.fromYmdHms(year + DEAD_YEAR - 1, month, day, hour, minute, second));
  }

  public static Foto fromYmd(int year, int month, int day) {
    return fromYmdHms(year, month, day, 0, 0, 0);
  }

  public Lunar getLunar() {
    return lunar;
  }

  public int getYear() {
    int sy = lunar.getSolar().getYear();
    int y = sy - DEAD_YEAR;
    if (sy == lunar.getYear()) {
      y++;
    }
    return y;
  }

  public int getMonth() {
    return lunar.getMonth();
  }

  public int getDay() {
    return lunar.getDay();
  }

  public String getYearInChinese() {
    String y = getYear() + "";
    StringBuilder s = new StringBuilder();
    for (int i = 0, j = y.length(); i < j; i++) {
      s.append(LunarUtil.NUMBER[y.charAt(i) - '0']);
    }
    return s.toString();
  }

  public String getMonthInChinese() {
    return lunar.getMonthInChinese();
  }

  public String getDayInChinese() {
    return lunar.getDayInChinese();
  }

  public List<FotoFestival> getFestivals() {
    List<FotoFestival> l = new ArrayList<FotoFestival>();
    List<FotoFestival> fs = FotoUtil.FESTIVAL.get(getMonth() + "-" + getDay());
    if (null != fs) {
      l.addAll(fs);
    }
    return l;
  }

  public boolean isMonthZhai() {
    int m = getMonth();
    return 1 == m || 5 == m || 9 == m;
  }

  public boolean isDayYangGong() {
    for (FotoFestival f : getFestivals()) {
      if ("杨公忌".equals(f.getName())) {
        return true;
      }
    }
    return false;
  }

  public boolean isDayZhaiShuoWang() {
    int d = getDay();
    return 1 == d || 15 == d;
  }

  public boolean isDayZhaiSix() {
    int d = getDay();
    if (8 == d || 14 == d || 15 == d || 23 == d || 29 == d || 30 == d) {
      return true;
    } else if (28 == d) {
      LunarMonth m = LunarMonth.fromYm(lunar.getYear(), getMonth());
      return null != m && 30 != m.getDayCount();
    }
    return false;
  }

  public boolean isDayZhaiTen() {
    int d = getDay();
    return 1 == d || 8 == d || 14 == d || 15 == d || 18 == d || 23 == d || 24 == d || 28 == d || 29 == d || 30 == d;
  }

  public boolean isDayZhaiGuanYin() {
    String k = getMonth() + "-" + getDay();
    for (String d : FotoUtil.DAY_ZHAI_GUAN_YIN) {
      if (k.equals(d)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return getYearInChinese() + "年" + getMonthInChinese() + "月" + getDayInChinese();
  }

  public String toFullString() {
    StringBuilder s = new StringBuilder();
    s.append(toString());
    for (FotoFestival f : getFestivals()) {
      s.append(" (");
      s.append(f);
      s.append(")");
    }
    return s.toString();
  }

}
