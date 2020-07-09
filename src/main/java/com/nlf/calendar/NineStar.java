package com.nlf.calendar;

import com.nlf.calendar.util.LunarUtil;

/**
 * 九星
 * <p>玄空九星、奇门九星都来源于北斗九星，九数、七色、五行、后天八卦方位都是相通的。</p>
 * @author 6tail
 */
public class NineStar {

  /** 九数 */
  public static final String[] NUMBER = {"一","二","三","四","五","六","七","八","九"};

  /** 七色 */
  public static final String[] COLOR = {"白","黒","碧","绿","黄","白","赤","白","紫"};

  /** 五行 */
  public static final String[] WU_XING = {"水","土","木","木","土","金","金","土","火"};

  /** 后天八卦方位 */
  public static final String[] POSITION = {"坎","坤","震","巽","中","乾","兑","艮","离"};

  /** 北斗九星 */
  public static final String[] NAME_BEI_DOU = {"天枢","天璇","天玑","天权","玉衡","开阳","摇光","洞明","隐元"};

  /** 玄空九星（玄空风水） */
  public static final String[] NAME_XUAN_KONG = {"贪狼","巨门","禄存","文曲","廉贞","武曲","破军","左辅","右弼"};

  /** 奇门九星（奇门遁甲，也称天盘九星） */
  public static final String[] NAME_QI_MEN = {"天蓬","天芮","天冲","天辅","天禽","天心","天柱","天任","天英"};

  /** 八门（奇门遁甲） */
  public static final String[] BA_MEN_QI_MEN = {"休","死","伤","杜","","开","惊","生","景"};

  /** 太乙九神（太乙神数） */
  public static final String[] NAME_TAI_YI = {"太乙","摄提","轩辕","招摇","天符","青龙","咸池","太阴","天乙"};

  /** 太乙九神对应类型 */
  public static final String[] TYPE_TAI_YI = {"吉神","凶神","安神","安神","凶神","吉神","凶神","吉神","吉神"};

  /** 吉凶（玄空风水） */
  public static final String[] LUCK_XUAN_KONG = {"吉","凶","凶","吉","凶","吉","凶","吉","吉"};

  /** 吉凶（奇门遁甲） */
  public static final String[] LUCK_QI_MEN = {"大凶","大凶","小吉","大吉","大吉","大吉","小凶","小吉","小凶"};

  /** 阴阳（奇门遁甲） */
  public static final String[] YIN_YANG_QI_MEN = {"阳","阴","阳","阳","阳","阴","阴","阳","阴"};

  /** 序号，0到8 */
  protected int index;

  public NineStar(int index) {
    this.index = index;
  }

  /**
   * 获取九数
   * @return 九数
   */
  public String getNumber(){
    return NUMBER[index];
  }

  /**
   * 获取七色
   * @return 七色
   */
  public String getColor(){
    return COLOR[index];
  }

  /**
   * 获取五行
   * @return 五行
   */
  public String getWuXing(){
    return WU_XING[index];
  }


  /**
   * 获取方位
   * @return 方位
   */
  public String getPosition(){
    return POSITION[index];
  }

  /**
   * 获取方位描述
   * @return 方位描述
   */
  public String getPositionDesc(){
    return LunarUtil.POSITION_DESC.get(getPosition());
  }

  /**
   * 获取玄空九星名称
   * @return 玄空九星名称
   */
  public String getNameInXuanKong(){
    return NAME_XUAN_KONG[index];
  }

  /**
   * 获取北斗九星名称
   * @return 北斗九星名称
   */
  public String getNameInBeiDou(){
    return NAME_BEI_DOU[index];
  }

  /**
   * 获取奇门九星名称
   * @return 奇门九星名称
   */
  public String getNameInQiMen(){
    return NAME_QI_MEN[index];
  }

  /**
   * 获取太乙九神名称
   * @return 太乙九神名称
   */
  public String getNameInTaiYi(){
    return NAME_TAI_YI[index];
  }

  /**
   * 获取奇门九星吉凶
   * @return 大吉/小吉/大凶/小凶
   */
  public String getLuckInQiMen(){
    return LUCK_QI_MEN[index];
  }

  /**
   * 获取玄空九星吉凶
   * @return 吉/凶
   */
  public String getLuckInXuanKong(){
    return LUCK_XUAN_KONG[index];
  }

  /**
   * 获取奇门九星阴阳
   * @return 阴/阳
   */
  public String getYinYangInQiMen(){
    return YIN_YANG_QI_MEN[index];
  }

  /**
   * 获取太乙九神类型
   * @return 吉神/凶神/安神
   */
  public String getTypeInTaiYi(){
    return TYPE_TAI_YI[index];
  }

  /**
   * 获取八门（奇门遁甲）
   * @return 八门
   */
  public String getBaMenInQiMen(){
    return BA_MEN_QI_MEN[index];
  }

  /**
   * 获取九星序号，从0开始
   * @return 序号
   */
  public int getIndex(){
    return index;
  }

  @Override
  public String toString(){
    return getNumber()+getColor()+getWuXing()+getNameInBeiDou();
  }

  /**
   * 获取详细描述
   * @return 详细描述
   */
  public String toFullString(){
    StringBuilder s = new StringBuilder();
    s.append(getNumber());
    s.append(getColor());
    s.append(getWuXing());
    s.append(" ");
    s.append(getPosition());
    s.append("(");
    s.append(getPositionDesc());
    s.append(") ");
    s.append(getNameInBeiDou());
    s.append(" 玄空[");
    s.append(getNameInXuanKong());
    s.append(" ");
    s.append(getLuckInXuanKong());
    s.append("] 奇门[");
    s.append(getNameInQiMen());
    s.append(" ");
    s.append(getLuckInQiMen());
    if(getBaMenInQiMen().length()>0) {
      s.append(" ");
      s.append(getBaMenInQiMen());
      s.append("门");
    }
    s.append(" ");
    s.append(getYinYangInQiMen());
    s.append("] 太乙[");
    s.append(getNameInTaiYi());
    s.append(" ");
    s.append(getTypeInTaiYi());
    s.append("]");
    return s.toString();
  }
}
