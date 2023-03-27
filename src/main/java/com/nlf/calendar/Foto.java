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
  private final Lunar lunar;

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

  /**
   * 获取因果犯忌
   * @return 因果犯忌
   */
  public List<FotoFestival> getFestivals() {
    List<FotoFestival> l = new ArrayList<FotoFestival>();
    List<FotoFestival> fs = FotoUtil.FESTIVAL.get(getMonth() + "-" + getDay());
    if (null != fs) {
      l.addAll(fs);
    }
    return l;
  }

  /**
   * 获取纪念日
   * @return 纪念日
   */
  public List<String> getOtherFestivals() {
    List<String> l = new ArrayList<String>();
    List<String> fs = FotoUtil.OTHER_FESTIVAL.get(getMonth() + "-" + getDay());
    if (null != fs) {
      l.addAll(fs);
    }
    return l;
  }

  /**
   * 是否月斋
   *
   * @return true/false
   */
  public boolean isMonthZhai() {
    int m = getMonth();
    return 1 == m || 5 == m || 9 == m;
  }

  /**
   * 是否杨公忌
   *
   * @return true/false
   */
  public boolean isDayYangGong() {
    for (FotoFestival f : getFestivals()) {
      if ("杨公忌".equals(f.getName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * 是否朔望斋
   *
   * @return true/false
   */
  public boolean isDayZhaiShuoWang() {
    int d = getDay();
    return 1 == d || 15 == d;
  }

  /**
   * 是否六斋日
   *
   * @return true/false
   */
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

  /**
   * 是否十斋日
   *
   * @return true/false
   */
  public boolean isDayZhaiTen() {
    int d = getDay();
    return 1 == d || 8 == d || 14 == d || 15 == d || 18 == d || 23 == d || 24 == d || 28 == d || 29 == d || 30 == d;
  }

  /**
   * 是否观音斋
   *
   * @return true/false
   */
  public boolean isDayZhaiGuanYin() {
    String k = getMonth() + "-" + getDay();
    for (String d : FotoUtil.DAY_ZHAI_GUAN_YIN) {
      if (k.equals(d)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 获取星宿
   *
   * @return 星宿
   */
  public String getXiu() {
    return FotoUtil.getXiu(getMonth(), getDay());
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

  @Override
  public String toString() {
    return getYearInChinese() + "年" + getMonthInChinese() + "月" + getDayInChinese();
  }

  public String toFullString() {
    StringBuilder s = new StringBuilder();
    s.append(this);
    for (FotoFestival f : getFestivals()) {
      s.append(" (");
      s.append(f);
      s.append(")");
    }
    return s.toString();
  }

}
