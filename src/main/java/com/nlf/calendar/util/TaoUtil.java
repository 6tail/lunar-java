package com.nlf.calendar.util;

import com.nlf.calendar.TaoFestival;

import java.util.*;

/**
 * 道历工具
 *
 * @author 6tail
 */
public class TaoUtil {
  /**
   * 日期对应的节日
   */
  public static final Map<String, List<TaoFestival>> FESTIVAL = new HashMap<String, List<TaoFestival>>() {
    private static final long serialVersionUID = 1;

    {
      put("1-1", Collections.nCopies(1, new TaoFestival("天腊之辰", "天腊，此日五帝会于东方九炁青天")));
      put("1-3", Arrays.asList(new TaoFestival("郝真人圣诞"), new TaoFestival("孙真人圣诞")));
      put("1-5", Collections.nCopies(1, new TaoFestival("孙祖清静元君诞")));
      put("1-7", Collections.nCopies(1, new TaoFestival("举迁赏会", "此日上元赐福，天官同地水二官考校罪福")));
      put("1-9", Collections.nCopies(1, new TaoFestival("玉皇上帝圣诞")));
      put("1-13", Collections.nCopies(1, new TaoFestival("关圣帝君飞升")));
      put("1-15", Arrays.asList(new TaoFestival("上元天官圣诞"), new TaoFestival("老祖天师圣诞")));
      put("1-19", Collections.nCopies(1, new TaoFestival("长春邱真人(邱处机)圣诞")));
      put("1-28", Collections.nCopies(1, new TaoFestival("许真君(许逊天师)圣诞")));
      put("2-1", Arrays.asList(new TaoFestival("勾陈天皇大帝圣诞"), new TaoFestival("长春刘真人(刘渊然)圣诞")));
      put("2-2", Arrays.asList(new TaoFestival("土地正神诞"), new TaoFestival("姜太公圣诞")));
      put("2-3", Collections.nCopies(1, new TaoFestival("文昌梓潼帝君圣诞")));
      put("2-6", Collections.nCopies(1, new TaoFestival("东华帝君圣诞")));
      put("2-13", Collections.nCopies(1, new TaoFestival("度人无量葛真君圣诞")));
      put("2-15", Collections.nCopies(1, new TaoFestival("太清道德天尊(太上老君)圣诞")));
      put("2-19", Collections.nCopies(1, new TaoFestival("慈航真人圣诞")));
      put("3-1", Collections.nCopies(1, new TaoFestival("谭祖(谭处端)长真真人圣诞")));
      put("3-3", Collections.nCopies(1, new TaoFestival("玄天上帝圣诞")));
      put("3-6", Collections.nCopies(1, new TaoFestival("眼光娘娘圣诞")));
      put("3-15", Arrays.asList(new TaoFestival("天师张大真人圣诞"), new TaoFestival("财神赵公元帅圣诞")));
      put("3-16", Arrays.asList(new TaoFestival("三茅真君得道之辰"), new TaoFestival("中岳大帝圣诞")));
      put("3-18", Arrays.asList(new TaoFestival("王祖(王处一)玉阳真人圣诞"), new TaoFestival("后土娘娘圣诞")));
      put("3-19", Collections.nCopies(1, new TaoFestival("太阳星君圣诞")));
      put("3-20", Collections.nCopies(1, new TaoFestival("子孙娘娘圣诞")));
      put("3-23", Collections.nCopies(1, new TaoFestival("天后妈祖圣诞")));
      put("3-26", Collections.nCopies(1, new TaoFestival("鬼谷先师诞")));
      put("3-28", Collections.nCopies(1, new TaoFestival("东岳大帝圣诞")));
      put("4-1", Collections.nCopies(1, new TaoFestival("长生谭真君成道之辰")));
      put("4-10", Collections.nCopies(1, new TaoFestival("何仙姑圣诞")));
      put("4-14", Collections.nCopies(1, new TaoFestival("吕祖纯阳祖师圣诞")));
      put("4-15", Collections.nCopies(1, new TaoFestival("钟离祖师圣诞")));
      put("4-18", Arrays.asList(new TaoFestival("北极紫微大帝圣诞"), new TaoFestival("泰山圣母碧霞元君诞"), new TaoFestival("华佗神医先师诞")));
      put("4-20", Collections.nCopies(1, new TaoFestival("眼光圣母娘娘诞")));
      put("4-28", Collections.nCopies(1, new TaoFestival("神农先帝诞")));
      put("5-1", Collections.nCopies(1, new TaoFestival("南极长生大帝圣诞")));
      put("5-5", Arrays.asList(new TaoFestival("地腊之辰", "地腊，此日五帝会于南方三炁丹天"), new TaoFestival("南方雷祖圣诞"), new TaoFestival("地祗温元帅圣诞"), new TaoFestival("雷霆邓天君圣诞")));
      put("5-11", Collections.nCopies(1, new TaoFestival("城隍爷圣诞")));
      put("5-13", Arrays.asList(new TaoFestival("关圣帝君降神"), new TaoFestival("关平太子圣诞")));
      put("5-18", Collections.nCopies(1, new TaoFestival("张天师圣诞")));
      put("5-20", Collections.nCopies(1, new TaoFestival("马祖丹阳真人圣诞")));
      put("5-29", Collections.nCopies(1, new TaoFestival("紫青白祖师圣诞")));
      put("6-1", Collections.nCopies(1, new TaoFestival("南斗星君下降")));
      put("6-2", Collections.nCopies(1, new TaoFestival("南斗星君下降")));
      put("6-3", Collections.nCopies(1, new TaoFestival("南斗星君下降")));
      put("6-4", Collections.nCopies(1, new TaoFestival("南斗星君下降")));
      put("6-5", Collections.nCopies(1, new TaoFestival("南斗星君下降")));
      put("6-6", Collections.nCopies(1, new TaoFestival("南斗星君下降")));
      put("6-10", Collections.nCopies(1, new TaoFestival("刘海蟾祖师圣诞")));
      put("6-15", Collections.nCopies(1, new TaoFestival("灵官王天君圣诞")));
      put("6-19", Collections.nCopies(1, new TaoFestival("慈航(观音)成道日")));
      put("6-23", Collections.nCopies(1, new TaoFestival("火神圣诞")));
      put("6-24", Arrays.asList(new TaoFestival("南极大帝中方雷祖圣诞"), new TaoFestival("关圣帝君圣诞")));
      put("6-26", Collections.nCopies(1, new TaoFestival("二郎真君圣诞")));
      put("7-7", Arrays.asList(new TaoFestival("道德腊之辰", "道德腊，此日五帝会于西方七炁素天"), new TaoFestival("庆生中会", "此日中元赦罪，地官同天水二官考校罪福")));
      put("7-12", Collections.nCopies(1, new TaoFestival("西方雷祖圣诞")));
      put("7-15", Collections.nCopies(1, new TaoFestival("中元地官大帝圣诞")));
      put("7-18", Collections.nCopies(1, new TaoFestival("王母娘娘圣诞")));
      put("7-20", Collections.nCopies(1, new TaoFestival("刘祖(刘处玄)长生真人圣诞")));
      put("7-22", Collections.nCopies(1, new TaoFestival("财帛星君文财神增福相公李诡祖圣诞")));
      put("7-26", Collections.nCopies(1, new TaoFestival("张三丰祖师圣诞")));
      put("8-1", Collections.nCopies(1, new TaoFestival("许真君飞升日")));
      put("8-3", Collections.nCopies(1, new TaoFestival("九天司命灶君诞")));
      put("8-5", Collections.nCopies(1, new TaoFestival("北方雷祖圣诞")));
      put("8-10", Collections.nCopies(1, new TaoFestival("北岳大帝诞辰")));
      put("8-15", Collections.nCopies(1, new TaoFestival("太阴星君诞")));
      put("9-1", Collections.nCopies(1, new TaoFestival("北斗九皇降世之辰")));
      put("9-2", Collections.nCopies(1, new TaoFestival("北斗九皇降世之辰")));
      put("9-3", Collections.nCopies(1, new TaoFestival("北斗九皇降世之辰")));
      put("9-4", Collections.nCopies(1, new TaoFestival("北斗九皇降世之辰")));
      put("9-5", Collections.nCopies(1, new TaoFestival("北斗九皇降世之辰")));
      put("9-6", Collections.nCopies(1, new TaoFestival("北斗九皇降世之辰")));
      put("9-7", Collections.nCopies(1, new TaoFestival("北斗九皇降世之辰")));
      put("9-8", Collections.nCopies(1, new TaoFestival("北斗九皇降世之辰")));
      put("9-9", Arrays.asList(new TaoFestival("北斗九皇降世之辰"), new TaoFestival("斗姥元君圣诞"), new TaoFestival("重阳帝君圣诞"), new TaoFestival("玄天上帝飞升"), new TaoFestival("酆都大帝圣诞")));
      put("9-22", Collections.nCopies(1, new TaoFestival("增福财神诞")));
      put("9-23", Collections.nCopies(1, new TaoFestival("萨翁真君圣诞")));
      put("9-28", Collections.nCopies(1, new TaoFestival("五显灵官马元帅圣诞")));
      put("10-1", Arrays.asList(new TaoFestival("民岁腊之辰", "民岁腊，此日五帝会于北方五炁黑天"), new TaoFestival("东皇大帝圣诞")));
      put("10-3", Collections.nCopies(1, new TaoFestival("三茅应化真君圣诞")));
      put("10-6", Collections.nCopies(1, new TaoFestival("天曹诸司五岳五帝圣诞")));
      put("10-15", Arrays.asList(new TaoFestival("下元水官大帝圣诞"), new TaoFestival("建生大会", "此日下元解厄，水官同天地二官考校罪福")));
      put("10-18", Collections.nCopies(1, new TaoFestival("地母娘娘圣诞")));
      put("10-19", Collections.nCopies(1, new TaoFestival("长春邱真君飞升")));
      put("10-20", Collections.nCopies(1, new TaoFestival("虚靖天师(即三十代天师弘悟张真人)诞")));
      put("11-6", Collections.nCopies(1, new TaoFestival("西岳大帝圣诞")));
      put("11-9", Collections.nCopies(1, new TaoFestival("湘子韩祖圣诞")));
      put("11-11", Collections.nCopies(1, new TaoFestival("太乙救苦天尊圣诞")));
      put("11-26", Collections.nCopies(1, new TaoFestival("北方五道圣诞")));
      put("12-8", Collections.nCopies(1, new TaoFestival("王侯腊之辰", "王侯腊，此日五帝会于上方玄都玉京")));
      put("12-16", Arrays.asList(new TaoFestival("南岳大帝圣诞"), new TaoFestival("福德正神诞")));
      put("12-20", Collections.nCopies(1, new TaoFestival("鲁班先师圣诞")));
      put("12-21", Collections.nCopies(1, new TaoFestival("天猷上帝圣诞")));
      put("12-22", Collections.nCopies(1, new TaoFestival("重阳祖师圣诞")));
      put("12-23", Collections.nCopies(1, new TaoFestival("祭灶王", "最适宜谢旧年太岁，开启拜新年太岁")));
      put("12-25", Arrays.asList(new TaoFestival("玉帝巡天"), new TaoFestival("天神下降")));
      put("12-29", Collections.nCopies(1, new TaoFestival("清静孙真君(孙不二)成道")));
    }
  };

  /**
   * 八会日
   */
  public static final Map<String, String> BA_HUI = new HashMap<String, String>() {

    private static final long serialVersionUID = 1;

    {
      put("丙午", "天会");
      put("壬午", "地会");
      put("壬子", "人会");
      put("庚午", "日会");
      put("庚申", "月会");
      put("辛酉", "星辰会");
      put("甲辰", "五行会");
      put("甲戌", "四时会");
    }
  };

  /**
   * 八节日
   */
  public static final Map<String, String> BA_JIE = new HashMap<String, String>() {

    private static final long serialVersionUID = 1;

    {
      put("立春", "东北方度仙上圣天尊同梵炁始青天君下降");
      put("春分", "东方玉宝星上天尊同青帝九炁天君下降");
      put("立夏", "东南方好生度命天尊同梵炁始丹天君下降");
      put("夏至", "南方玄真万福天尊同赤帝三炁天君下降");
      put("立秋", "西南方太灵虚皇天尊同梵炁始素天君下降");
      put("秋分", "西方太妙至极天尊同白帝七炁天君下降");
      put("立冬", "西北方无量太华天尊同梵炁始玄天君下降");
      put("冬至", "北方玄上玉宸天尊同黑帝五炁天君下降");
    }
  };

  /**
   * 三会日
   */
  public static final String[] SAN_HUI = {"1-7", "7-7", "10-15"};

  /**
   * 三元日
   */
  public static final String[] SAN_YUAN = {"1-15", "7-15", "10-15"};

  /**
   * 五腊日
   */
  public static final String[] WU_LA = {"1-1", "5-5", "7-7", "10-1", "12-8"};

  /**
   * 暗戊
   */
  public static final String[] AN_WU = {"未", "戌", "辰", "寅", "午", "子", "酉", "申", "巳", "亥", "卯", "丑"};

}
