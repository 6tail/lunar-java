package com.nlf.calendar;

import com.nlf.calendar.util.LunarUtil;

import java.util.List;
import java.util.Map;

/**
 * 时辰
 *
 * @author 6tail
 */
public class LunarTime {

  /**
   * 天干下标，0-9
   */
  private final int ganIndex;

  /**
   * 地支下标，0-11
   */
  private final int zhiIndex;

  /**
   * 阴历
   */
  private final Lunar lunar;

  public LunarTime(int lunarYear, int lunarMonth, int lunarDay, int hour, int minute, int second) {
    this.lunar = Lunar.fromYmdHms(lunarYear, lunarMonth, lunarDay, hour, minute, second);
    this.zhiIndex = LunarUtil.getTimeZhiIndex(String.format("%02d:%02d", hour, minute));
    this.ganIndex = (lunar.getDayGanIndexExact() % 5 * 2 + zhiIndex) % 10;
  }

  public static LunarTime fromYmdHms(int lunarYear, int lunarMonth, int lunarDay, int hour, int minute, int second) {
    return new LunarTime(lunarYear, lunarMonth, lunarDay, hour, minute, second);
  }

  public int getGanIndex() {
    return ganIndex;
  }

  public int getZhiIndex() {
    return zhiIndex;
  }

  /**
   * 获取生肖
   *
   * @return 生肖，如虎
   */
  public String getShengXiao() {
    return LunarUtil.SHENGXIAO[zhiIndex + 1];
  }

  /**
   * 获取地支
   *
   * @return 地支
   */
  public String getZhi() {
    return LunarUtil.ZHI[zhiIndex + 1];
  }

  /**
   * 获取天干
   *
   * @return 天干
   */
  public String getGan() {
    return LunarUtil.GAN[ganIndex + 1];
  }

  /**
   * 获取干支（时柱）
   *
   * @return 干支（时柱）
   */
  public String getGanZhi() {
    return getGan() + getZhi();
  }

  /**
   * 获取喜神方位
   *
   * @return 喜神方位，如艮
   */
  public String getPositionXi() {
    return LunarUtil.POSITION_XI[ganIndex + 1];
  }

  /**
   * 获取喜神方位描述
   *
   * @return 喜神方位描述，如东北
   */
  public String getPositionXiDesc() {
    return LunarUtil.POSITION_DESC.get(getPositionXi());
  }

  /**
   * 获取阳贵神方位
   *
   * @return 阳贵神方位，如艮
   */
  public String getPositionYangGui() {
    return LunarUtil.POSITION_YANG_GUI[ganIndex + 1];
  }

  /**
   * 获取阳贵神方位描述
   *
   * @return 阳贵神方位描述，如东北
   */
  public String getPositionYangGuiDesc() {
    return LunarUtil.POSITION_DESC.get(getPositionYangGui());
  }

  /**
   * 获取阴贵神方位
   *
   * @return 阴贵神方位，如艮
   */
  public String getPositionYinGui() {
    return LunarUtil.POSITION_YIN_GUI[ganIndex + 1];
  }

  /**
   * 获取阴贵神方位描述
   *
   * @return 阴贵神方位描述，如东北
   */
  public String getPositionYinGuiDesc() {
    return LunarUtil.POSITION_DESC.get(getPositionYinGui());
  }

  /**
   * 获取福神方位（默认流派：2）
   *
   * @return 福神方位，如艮
   */
  public String getPositionFu() {
    return getPositionFu(2);
  }

  /**
   * 获取福神方位
   *
   * @param sect 流派，1或2
   * @return 福神方位，如艮
   */
  public String getPositionFu(int sect) {
    return (1 == sect ? LunarUtil.POSITION_FU : LunarUtil.POSITION_FU_2)[ganIndex + 1];
  }

  /**
   * 获取福神方位描述（默认流派：2）
   *
   * @return 福神方位描述，如东北
   */
  public String getPositionFuDesc() {
    return getPositionFuDesc(2);
  }

  /**
   * 获取福神方位描述
   *
   * @param sect 流派，1或2
   * @return 福神方位描述，如东北
   */
  public String getPositionFuDesc(int sect) {
    return LunarUtil.POSITION_DESC.get(getPositionFu(sect));
  }

  /**
   * 获取财神方位
   *
   * @return 财神方位，如艮
   */
  public String getPositionCai() {
    return LunarUtil.POSITION_CAI[ganIndex + 1];
  }

  /**
   * 获取财神方位描述
   *
   * @return 财神方位描述，如东北
   */
  public String getPositionCaiDesc() {
    return LunarUtil.POSITION_DESC.get(getPositionCai());
  }

  /**
   * 获取纳音
   *
   * @return 纳音，如剑锋金
   */
  public String getNaYin() {
    return LunarUtil.NAYIN.get(getGanZhi());
  }

  /**
   * 获取值时天神
   *
   * @return 值时天神
   */
  public String getTianShen() {
    return LunarUtil.TIAN_SHEN[(zhiIndex + LunarUtil.ZHI_TIAN_SHEN_OFFSET.get(lunar.getDayZhiExact())) % 12 + 1];
  }

  /**
   * 获取值时天神类型：黄道/黑道
   *
   * @return 值时天神类型：黄道/黑道
   */
  public String getTianShenType() {
    return LunarUtil.TIAN_SHEN_TYPE.get(getTianShen());
  }

  /**
   * 获取值时天神吉凶
   *
   * @return 吉/凶
   */
  public String getTianShenLuck() {
    return LunarUtil.TIAN_SHEN_TYPE_LUCK.get(getTianShenType());
  }

  /**
   * 获取时冲
   *
   * @return 时冲，如申
   */
  public String getChong() {
    return LunarUtil.CHONG[zhiIndex];
  }

  /**
   * 获取时煞
   *
   * @return 时煞，如北
   */
  public String getSha() {
    return LunarUtil.SHA.get(getZhi());
  }

  /**
   * 获取时冲生肖
   *
   * @return 时冲生肖，如猴
   */
  public String getChongShengXiao() {
    String chong = getChong();
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
  public String getChongDesc() {
    return "(" + getChongGan() + getChong() + ")" + getChongShengXiao();
  }

  /**
   * 获取无情之克的时冲天干
   *
   * @return 无情之克的时冲天干，如甲
   */
  public String getChongGan() {
    return LunarUtil.CHONG_GAN[ganIndex];
  }

  /**
   * 获取有情之克的时冲天干
   *
   * @return 有情之克的时冲天干，如甲
   */
  public String getChongGanTie() {
    return LunarUtil.CHONG_GAN_TIE[ganIndex];
  }

  /**
   * 获取宜，如果没有，返回["无"]
   *
   * @return 宜
   */
  public List<String> getYi() {
    return LunarUtil.getTimeYi(lunar.getDayInGanZhiExact(), getGanZhi());
  }

  /**
   * 获取忌，如果没有，返回["无"]
   *
   * @return 忌
   */
  public List<String> getJi() {
    return LunarUtil.getTimeJi(lunar.getDayInGanZhiExact(), getGanZhi());
  }

  /**
   * 获取值时九星（时家紫白星歌诀：三元时白最为佳，冬至阳生顺莫差，孟日七宫仲一白，季日四绿发萌芽，每把时辰起甲子，本时星耀照光华，时星移入中宫去，顺飞八方逐细查。夏至阴生逆回首，孟归三碧季加六，仲在九宫时起甲，依然掌中逆轮跨。）
   *
   * @return 值时九星
   */
  public NineStar getNineStar() {
    //顺逆
    String solarYmd = lunar.getSolar().toYmd();
    Map<String, Solar> jieQi = lunar.getJieQiTable();
    boolean asc = solarYmd.compareTo(jieQi.get("冬至").toYmd()) >= 0 && solarYmd.compareTo(jieQi.get("夏至").toYmd()) < 0;
    int start = asc ? 7 : 3;
    String dayZhi = lunar.getDayZhi();
    if ("子午卯酉".contains(dayZhi)) {
      start = asc ? 1 : 9;
    } else if ("辰戌丑未".contains(dayZhi)) {
      start = asc ? 4 : 6;
    }
    int index = asc ? start + zhiIndex - 1 : start - zhiIndex - 1;
    if (index > 8) {
      index -= 9;
    }
    if (index < 0) {
      index += 9;
    }
    return new NineStar(index);
  }

  /**
   * 获取所在旬
   *
   * @return 旬
   */
  public String getXun() {
    return LunarUtil.getXun(getGanZhi());
  }

  /**
   * 获取值时空亡
   *
   * @return 空亡(旬空)
   */
  public String getXunKong() {
    return LunarUtil.getXunKong(getGanZhi());
  }

  /**
   * 获取当前时辰的最早时分
   *
   * @return 时分，如：21:00
   */
  public String getMinHm() {
    int hour = lunar.getHour();
    if (hour < 1) {
      return "00:00";
    } else if (hour > 22) {
      return "23:00";
    }
    return String.format("%02d:00", hour % 2 == 0 ? hour - 1 : hour);
  }

  /**
   * 获取当前时辰的最晚时分
   *
   * @return 时分，如：22:59
   */
  public String getMaxHm() {
    int hour = lunar.getHour();
    if (hour < 1) {
      return "00:59";
    } else if (hour > 22) {
      return "23:59";
    }
    return String.format("%02d:59", hour % 2 == 0 ? hour : hour + 1);
  }

  @Override
  public String toString() {
    return getGanZhi();
  }

}
