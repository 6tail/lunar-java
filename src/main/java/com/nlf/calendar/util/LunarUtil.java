package com.nlf.calendar.util;

import java.util.*;

/**
 * 农历工具，基准日期为1900年十一月十一，对应阳历1901年1月1日，最远仅支持到2099年
 *
 * @author 6tail
 *
 */
public class LunarUtil{
  /** 农历基准年 */
  public static final int BASE_YEAR = 1900;
  /** 农历基准月 */
  public static final int BASE_MONTH = 11;
  /** 农历基准日 */
  public static final int BASE_DAY = 11;
  /** 农历与阳历年偏移量 */
  public static final int BASE_INDEX = 0;
  /** 基准对应的干支偏移量 */
  public static final int BASE_DAY_GANZHI_INDEX = 15;
  /** 月份地支偏移量，因正月起寅 */
  public static final int BASE_MONTH_ZHI_INDEX = 2;
  /** 闰年表 */
  public static final int[] LEAP_MONTH_YEAR = {6,14,19,25,33,36,38,41,44,52,55,79,117,136,147,150,155,158,185,193};
  /** 闰月表 */
  public static final int[] LUNAR_MONTH = {0x00,0x04,0xad,0x08,0x5a,0x01,0xd5,0x54,0xb4,0x09,0x64,0x05,0x59,0x45,0x95,0x0a,0xa6,0x04,0x55,0x24,0xad,0x08,0x5a,0x62,0xda,0x04,0xb4,0x05,0xb4,0x55,0x52,0x0d,0x94,0x0a,0x4a,0x2a,0x56,0x02,0x6d,0x71,0x6d,0x01,0xda,0x02,0xd2,0x52,0xa9,0x05,0x49,0x0d,0x2a,0x45,0x2b,0x09,0x56,0x01,0xb5,0x20,0x6d,0x01,0x59,0x69,0xd4,0x0a,0xa8,0x05,0xa9,0x56,0xa5,0x04,0x2b,0x09,0x9e,0x38,0xb6,0x08,0xec,0x74,0x6c,0x05,0xd4,0x0a,0xe4,0x6a,0x52,0x05,0x95,0x0a,0x5a,0x42,0x5b,0x04,0xb6,0x04,0xb4,0x22,0x6a,0x05,0x52,0x75,0xc9,0x0a,0x52,0x05,0x35,0x55,0x4d,0x0a,0x5a,0x02,0x5d,0x31,0xb5,0x02,0x6a,0x8a,0x68,0x05,0xa9,0x0a,0x8a,0x6a,0x2a,0x05,0x2d,0x09,0xaa,0x48,0x5a,0x01,0xb5,0x09,0xb0,0x39,0x64,0x05,0x25,0x75,0x95,0x0a,0x96,0x04,0x4d,0x54,0xad,0x04,0xda,0x04,0xd4,0x44,0xb4,0x05,0x54,0x85,0x52,0x0d,0x92,0x0a,0x56,0x6a,0x56,0x02,0x6d,0x02,0x6a,0x41,0xda,0x02,0xb2,0xa1,0xa9,0x05,0x49,0x0d,0x0a,0x6d,0x2a,0x09,0x56,0x01,0xad,0x50,0x6d,0x01,0xd9,0x02,0xd1,0x3a,0xa8,0x05,0x29,0x85,0xa5,0x0c,0x2a,0x09,0x96,0x54,0xb6,0x08,0x6c,0x09,0x64,0x45,0xd4,0x0a,0xa4,0x05,0x51,0x25,0x95,0x0a,0x2a,0x72,0x5b,0x04,0xb6,0x04,0xac,0x52,0x6a,0x05,0xd2,0x0a,0xa2,0x4a,0x4a,0x05,0x55,0x94,0x2d,0x0a,0x5a,0x02,0x75,0x61,0xb5,0x02,0x6a,0x03,0x61,0x45,0xa9,0x0a,0x4a,0x05,0x25,0x25,0x2d,0x09,0x9a,0x68,0xda,0x08,0xb4,0x09,0xa8,0x59,0x54,0x03,0xa5,0x0a,0x91,0x3a,0x96,0x04,0xad,0xb0,0xad,0x04,0xda,0x04,0xf4,0x62,0xb4,0x05,0x54,0x0b,0x44,0x5d,0x52,0x0a,0x95,0x04,0x55,0x22,0x6d,0x02,0x5a,0x71,0xda,0x02,0xaa,0x05,0xb2,0x55,0x49,0x0b,0x4a,0x0a,0x2d,0x39,0x36,0x01,0x6d,0x80,0x6d,0x01,0xd9,0x02,0xe9,0x6a,0xa8,0x05,0x29,0x0b,0x9a,0x4c,0xaa,0x08,0xb6,0x08,0xb4,0x38,0x6c,0x09,0x54,0x75,0xd4,0x0a,0xa4,0x05,0x45,0x55,0x95,0x0a,0x9a,0x04,0x55,0x44,0xb5,0x04,0x6a,0x82,0x6a,0x05,0xd2,0x0a,0x92,0x6a,0x4a,0x05,0x55,0x0a,0x2a,0x4a,0x5a,0x02,0xb5,0x02,0xb2,0x31,0x69,0x03,0x31,0x73,0xa9,0x0a,0x4a,0x05,0x2d,0x55,0x2d,0x09,0x5a,0x01,0xd5,0x48,0xb4,0x09,0x68,0x89,0x54,0x0b,0xa4,0x0a,0xa5,0x6a,0x95,0x04,0xad,0x08,0x6a,0x44,0xda,0x04,0x74,0x05,0xb0,0x25,0x54,0x03};
  public static final int[][] JIE_YEAR = {{13,49,85,117,149,185,201,250,250},{13,45,81,117,149,185,201,250,250},{13,48,84,112,148,184,200,201,250},{13,45,76,108,140,172,200,201,250},{13,44,72,104,132,168,200,201,250},{5,33,68,96,124,152,188,200,201},{29,57,85,120,148,176,200,201,250},{13,48,76,104,132,168,196,200,201},{25,60,88,120,148,184,200,201,250},{16,44,76,108,144,172,200,201,250},{28,60,92,124,160,192,200,201,250},{17,53,85,124,156,188,200,201,250}};
  public static final int[][] JIE_MAP = {{7,6,6,6,6,6,6,6,6,5,6,6,6,5,5,6,6,5,5,5,5,5,5,5,5,4,5,5},{5,4,5,5,5,4,4,5,5,4,4,4,4,4,4,4,4,3,4,4,4,3,3,4,4,3,3,3},{6,6,6,7,6,6,6,6,5,6,6,6,5,5,6,6,5,5,5,6,5,5,5,5,4,5,5,5,5},{5,5,6,6,5,5,5,6,5,5,5,5,4,5,5,5,4,4,5,5,4,4,4,5,4,4,4,4,5},{6,6,6,7,6,6,6,6,5,6,6,6,5,5,6,6,5,5,5,6,5,5,5,5,4,5,5,5,5},{6,6,7,7,6,6,6,7,6,6,6,6,5,6,6,6,5,5,6,6,5,5,5,6,5,5,5,5,4,5,5,5,5},{7,8,8,8,7,7,8,8,7,7,7,8,7,7,7,7,6,7,7,7,6,6,7,7,6,6,6,7,7},{8,8,8,9,8,8,8,8,7,8,8,8,7,7,8,8,7,7,7,8,7,7,7,7,6,7,7,7,6,6,7,7,7},{8,8,8,9,8,8,8,8,7,8,8,8,7,7,8,8,7,7,7,8,7,7,7,7,6,7,7,7,7},{9,9,9,9,8,9,9,9,8,8,9,9,8,8,8,9,8,8,8,8,7,8,8,8,7,7,8,8,8},{8,8,8,8,7,8,8,8,7,7,8,8,7,7,7,8,7,7,7,7,6,7,7,7,6,6,7,7,7},{7,8,8,8,7,7,8,8,7,7,7,8,7,7,7,7,6,7,7,7,6,6,7,7,6,6,6,7,7}};
  public static final int[][] QI_YEAR = {{13,45,81,113,149,185,201},{21,57,93,125,161,193,201},{21,56,88,120,152,188,200,201},{21,49,81,116,144,176,200,201},{17,49,77,112,140,168,200,201},{28,60,88,116,148,180,200,201},{25,53,84,112,144,172,200,201},{29,57,89,120,148,180,200,201},{17,45,73,108,140,168,200,201},{28,60,92,124,160,192,200,201},{16,44,80,112,148,180,200,201},{17,53,88,120,156,188,200,201}};
  public static final int[][] QI_MAP = {{21,21,21,21,21,20,21,21,21,20,20,21,21,20,20,20,20,20,20,20,20,19,20,20,20,19,19,20},{20,19,19,20,20,19,19,19,19,19,19,19,19,18,19,19,19,18,18,19,19,18,18,18,18,18,18,18},{21,21,21,22,21,21,21,21,20,21,21,21,20,20,21,21,20,20,20,21,20,20,20,20,19,20,20,20,20},{20,21,21,21,20,20,21,21,20,20,20,21,20,20,20,20,19,20,20,20,19,19,20,20,19,19,19,20,20},{21,22,22,22,21,21,22,22,21,21,21,22,21,21,21,21,20,21,21,21,20,20,21,21,20,20,20,21,21},{22,22,22,22,21,22,22,22,21,21,22,22,21,21,21,22,21,21,21,21,20,21,21,21,20,20,21,21,21},{23,23,24,24,23,23,23,24,23,23,23,23,22,23,23,23,22,22,23,23,22,22,22,23,22,22,22,22,23},{23,24,24,24,23,23,24,24,23,23,23,24,23,23,23,23,22,23,23,23,22,22,23,23,22,22,22,23,23},{23,24,24,24,23,23,24,24,23,23,23,24,23,23,23,23,22,23,23,23,22,22,23,23,22,22,22,23,23},{24,24,24,24,23,24,24,24,23,23,24,24,23,23,23,24,23,23,23,23,22,23,23,23,22,22,23,23,23},{23,23,23,23,22,23,23,23,22,22,23,23,22,22,22,23,22,22,22,22,21,22,22,22,21,21,22,22,22},{22,22,23,23,22,22,22,23,22,22,22,22,21,22,22,22,21,21,22,22,21,21,21,22,21,21,21,21,22}};
  /** 天干 */
  public static final String[] GAN = {"","甲","乙","丙","丁","戊","己","庚","辛","壬","癸"};
  /** 地支 */
  public static final String[] ZHI = {"","子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
  /** 彭祖百忌.天干 */
  public static final String[] PENGZU_GAN = {"","甲不开仓财物耗散","乙不栽植千株不长","丙不修灶必见灾殃","丁不剃头头必生疮","戊不受田田主不祥","己不破券二比并亡","庚不经络织机虚张","辛不合酱主人不尝","壬不泱水更难提防","癸不词讼理弱敌强"};
  /** 彭祖百忌.地支 */
  public static final String[] PENGZU_ZHI = {"","子不问卜自惹祸殃","丑不冠带主不还乡","寅不祭祀神鬼不尝","卯不穿井水泉不香","辰不哭泣必主重丧","巳不远行财物伏藏","午不苫盖屋主更张","未不服药毒气入肠","申不安床鬼祟入房","酉不会客醉坐颠狂","戌不吃犬作怪上床","亥不嫁娶不利新郎"};
  /** 月 */
  public static final String[] MONTH = {"","正","贰","叁","肆","伍","陆","柒","捌","玖","拾","冬","腊"};
  /** 季节 */
  public static final String[] SEASON = {"","孟春","仲春","季春","孟夏","仲夏","季夏","孟秋","仲秋","季秋","孟冬","仲冬","季冬"};
  /** 生肖 */
  public static final String[] SHENGXIAO = {"","鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"};
  /** 气 */
  public static final String[] QI = {"大寒","雨水","春分","谷雨","夏满","夏至","大暑","处暑","秋分","霜降","小雪","冬至"};
  /** 节 */
  public static final String[] JIE = {"小寒","立春","惊蛰","清明","立夏","芒种","小暑","立秋","白露","寒露","立冬","大雪"};
  /** 日 */
  public static final String[] DAY = {"","初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三","十四","十五","十六","十七","十八","十九","二十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","三十"};
  /** 农历日期对应的节日 */
  public static final Map<String,String> FESTIVAL = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("1-1","春节");
      put("1-15","元宵节");
      put("2-2","龙头节");
      put("5-5","端午节");
      put("7-7","七夕节");
      put("8-15","中秋节");
      put("9-9","重阳节");
      put("12-8","腊八节");
      put("12-30","除夕");
    }
  };
  /** 农历日期对应的非正式节日 */
  public static final Map<String,List<String>> OTHER_FESTIVAL = new HashMap<String,List<String>>(){
    private static final long serialVersionUID = -1;
    {
      put("1-1",Collections.nCopies(1,"弥勒佛圣诞"));
      put("1-8",Collections.nCopies(1,"五殿阎罗天子诞"));
      put("1-9",Collections.nCopies(1,"玉皇上帝诞"));
      put("2-1",Collections.nCopies(1,"一殿秦广王诞"));
      put("2-2",Collections.nCopies(1,"福德土地正神诞"));
      put("2-3",Collections.nCopies(1,"文昌帝君诞"));
      put("2-6",Collections.nCopies(1,"东华帝君诞"));
      put("2-8",Collections.nCopies(1,"释迦牟尼佛出家"));
      put("2-15",Collections.nCopies(1,"释迦牟尼佛般涅槃"));
      put("2-17",Collections.nCopies(1,"东方杜将军诞"));
      put("2-18",Collections.nCopies(1,"至圣先师孔子讳辰"));
      put("2-19",Collections.nCopies(1,"观音大士诞"));
      put("2-21",Collections.nCopies(1,"普贤菩萨诞"));
      put("3-1",Collections.nCopies(1,"二殿楚江王诞"));
      put("3-3",Collections.nCopies(1,"玄天上帝诞"));
      put("3-8",Collections.nCopies(1,"六殿卞城王诞"));
      put("3-15",Collections.nCopies(1,"昊天上帝诞"));
      put("3-16",Collections.nCopies(1,"准提菩萨诞"));
      put("3-19",Collections.nCopies(1,"中岳大帝诞"));
      put("3-20",Collections.nCopies(1,"子孙娘娘诞"));
      put("3-27",Collections.nCopies(1,"七殿泰山王诞"));
      put("3-28",Collections.nCopies(1,"苍颉至圣先师诞"));
      put("4-1",Collections.nCopies(1,"八殿都市王诞"));
      put("4-4",Collections.nCopies(1,"文殊菩萨诞"));
      put("4-8",Collections.nCopies(1,"释迦牟尼佛诞"));
      put("4-14",Collections.nCopies(1,"纯阳祖师诞"));
      put("4-15",Collections.nCopies(1,"钟离祖师诞"));
      put("4-17",Collections.nCopies(1,"十殿转轮王诞"));
      put("4-18",Collections.nCopies(1,"紫徽大帝诞"));
      put("4-20",Collections.nCopies(1,"眼光圣母诞"));
      put("5-1",Collections.nCopies(1,"南极长生大帝诞"));
      put("5-8",Collections.nCopies(1,"南方五道诞"));
      put("5-11",Collections.nCopies(1,"天下都城隍诞"));
      put("5-12",Collections.nCopies(1,"炳灵公诞"));
      put("5-13",Collections.nCopies(1,"关圣降"));
      put("5-16",Collections.nCopies(1,"天地元气造化万物之辰"));
      put("5-18",Collections.nCopies(1,"张天师诞"));
      put("5-22",Collections.nCopies(1,"孝娥神诞"));
      put("6-19",Collections.nCopies(1,"观世音菩萨成道日"));
      put("6-24",Collections.nCopies(1,"关帝诞"));
      put("7-7",Collections.nCopies(1,"魁星诞"));
      put("7-13",Arrays.asList("长真谭真人诞","大势至菩萨诞"));
      put("7-15",Collections.nCopies(1,"中元节"));
      put("7-18",Collections.nCopies(1,"西王母诞"));
      put("7-19",Collections.nCopies(1,"太岁诞"));
      put("7-22",Collections.nCopies(1,"增福财神诞"));
      put("7-29",Collections.nCopies(1,"杨公忌"));
      put("7-30",Collections.nCopies(1,"地藏菩萨诞"));
      put("8-1",Collections.nCopies(1,"许真君诞"));
      put("8-3",Collections.nCopies(1,"司命灶君诞"));
      put("8-5",Collections.nCopies(1,"雷声大帝诞"));
      put("8-10",Collections.nCopies(1,"北斗大帝诞"));
      put("8-12",Collections.nCopies(1,"西方五道诞"));
      put("8-16",Collections.nCopies(1,"天曹掠刷真君降"));
      put("8-18",Collections.nCopies(1,"天人兴福之辰"));
      put("8-23",Collections.nCopies(1,"汉恒候张显王诞"));
      put("8-24",Collections.nCopies(1,"灶君夫人诞"));
      put("8-29",Collections.nCopies(1,"至圣先师孔子诞"));
      put("9-1",Collections.nCopies(1,"北斗九星降世"));
      put("9-3",Collections.nCopies(1,"五瘟神诞"));
      put("9-9",Collections.nCopies(1,"酆都大帝诞"));
      put("9-13",Collections.nCopies(1,"孟婆尊神诞"));
      put("9-17",Collections.nCopies(1,"金龙四大王诞"));
      put("9-19",Collections.nCopies(1,"观世音菩萨出家"));
      put("9-30",Collections.nCopies(1,"药师琉璃光佛诞"));
      put("10-1",Collections.nCopies(1,"寒衣节"));
      put("10-3",Collections.nCopies(1,"三茅诞"));
      put("10-5",Collections.nCopies(1,"达摩祖师诞"));
      put("10-8",Collections.nCopies(1,"佛涅槃日"));
      put("10-15",Collections.nCopies(1,"下元节"));
      put("11-4",Collections.nCopies(1,"至圣先师孔子诞"));
      put("11-6",Collections.nCopies(1,"西岳大帝诞"));
      put("11-11",Collections.nCopies(1,"太乙救苦天尊诞"));
      put("11-17",Collections.nCopies(1,"阿弥陀佛诞"));
      put("11-19",Collections.nCopies(1,"太阳日宫诞"));
      put("11-23",Collections.nCopies(1,"张仙诞"));
      put("11-26",Collections.nCopies(1,"北方五道诞"));
      put("12-8",Collections.nCopies(1,"释迦如来成佛之辰"));
      put("12-16",Collections.nCopies(1,"南岳大帝诞"));
      put("12-21",Collections.nCopies(1,"天猷上帝诞"));
      put("12-23",Collections.nCopies(1,"小年"));
      put("12-24",Collections.nCopies(1,"子时灶君上天朝玉帝"));
      put("12-29",Collections.nCopies(1,"华严菩萨诞"));
    }
  };
  /** 宿 */
  public static final String[][] XIU = {
    {"室","奎","胃","毕","参","鬼","张","角","氐","心","斗","虚"},
    {"壁","娄","昴","觜","井","柳","翼","亢","房","尾","女","危"},
    {"奎","胃","毕","参","鬼","星","轸","氐","心","箕","虚","室"},
    {"娄","昴","觜","井","柳","张","角","房","尾","斗","危","壁"},
    {"胃","毕","参","鬼","星","翼","亢","心","箕","女","室","奎"},
    {"昴","觜","井","柳","张","轸","氐","尾","斗","虚","壁","娄"},
    {"毕","参","鬼","星","翼","角","房","箕","女","危","奎","胃"},
    {"觜","井","柳","张","轸","亢","心","斗","虚","室","娄","昴"},
    {"参","鬼","星","翼","角","氐","尾","女","危","壁","胃","毕"},
    {"井","柳","张","轸","亢","房","箕","虚","室","奎","昴","觜"},
    {"鬼","星","翼","角","氐","心","斗","危","壁","娄","毕","参"},
    {"柳","张","轸","亢","房","尾","女","室","奎","胃","觜","井"},
    {"星","翼","角","氐","心","箕","虚","壁","娄","昴","参","鬼"},
    {"张","轸","亢","房","尾","斗","危","奎","胃","毕","井","柳"},
    {"翼","角","氐","心","箕","女","室","娄","昴","觜","鬼","星"},
    {"轸","亢","房","尾","斗","虚","壁","胃","毕","参","柳","张"},
    {"角","氐","心","箕","女","危","奎","昴","觜","井","星","翼"},
    {"亢","房","尾","斗","虚","室","娄","毕","参","鬼","张","轸"},
    {"氐","心","箕","女","危","壁","胃","觜","井","柳","翼","角"},
    {"房","尾","斗","虚","室","奎","昴","参","鬼","星","轸","亢"},
    {"心","箕","女","危","壁","娄","毕","井","柳","张","角","氐"},
    {"尾","斗","虚","室","奎","胃","觜","鬼","星","翼","亢","房"},
    {"箕","女","危","壁","娄","昴","参","柳","张","轸","氐","心"},
    {"斗","虚","室","奎","胃","毕","井","星","翼","角","房","尾"},
    {"女","危","壁","娄","昴","觜","鬼","张","轸","亢","心","箕"},
    {"虚","室","奎","胃","毕","参","柳","翼","角","氐","尾","斗"},
    {"危","壁","娄","昴","觜","井","星","轸","亢","房","箕","女"},
    {"室","奎","胃","毕","参","鬼","张","角","氐","心","斗","虚"},
    {"壁","娄","昴","觜","井","柳","翼","亢","房","尾","女","危"},
    {"胃","鬼","氐","心","虚","星","轸","氐","心","箕","虚","室"}
  };
  /** 兽 */
  public static final Map<String,String> SHOU = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("东","青龙");
      put("南","朱雀");
      put("西","白虎");
      put("北","玄武");
    }
  };
  /** 宫 */
  public static final Map<String,String> GONG = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("角","东");
      put("井","南");
      put("奎","西");
      put("斗","北");
      put("亢","东");
      put("鬼","南");
      put("娄","西");
      put("牛","北");
      put("氐","南");
      put("柳","南");
      put("胃","西");
      put("女","北");
      put("房","东");
      put("星","南");
      put("昴","西");
      put("虚","北");
      put("心","东");
      put("张","南");
      put("毕","西");
      put("危","北");
      put("尾","东");
      put("翼","南");
      put("觜","西");
      put("室","北");
      put("箕","东");
      put("轸","南");
      put("参","西");
      put("壁","北");
    }
  };
  /** 政 */
  public static final Map<String,String> ZHENG = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("角","木");
      put("井","木");
      put("奎","木");
      put("斗","木");
      put("亢","金");
      put("鬼","金");
      put("娄","金");
      put("牛","金");
      put("氐","土");
      put("柳","土");
      put("胃","土");
      put("女","土");
      put("房","日");
      put("星","日");
      put("昴","日");
      put("虚","日");
      put("心","月");
      put("张","月");
      put("毕","月");
      put("危","月");
      put("尾","火");
      put("翼","火");
      put("觜","火");
      put("室","火");
      put("箕","水");
      put("轸","水");
      put("参","水");
      put("壁","水");
    }
  };
  /** 动物 */
  public static final Map<String,String> ANIMAL = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("角","蛟");
      put("斗","獬");
      put("奎","狼");
      put("井","犴");
      put("亢","龙");
      put("牛","牛");
      put("娄","狗");
      put("鬼","羊");
      put("女","蝠");
      put("氐","貉");
      put("胃","彘");
      put("柳","獐");
      put("房","兔");
      put("虚","鼠");
      put("昴","鸡");
      put("星","马");
      put("心","狐");
      put("危","燕");
      put("毕","乌");
      put("张","鹿");
      put("尾","虎");
      put("室","猪");
      put("觜","猴");
      put("翼","蛇");
      put("箕","豹");
      put("壁","獝");
      put("参","猿");
      put("轸","蚓");
    }
  };

  protected LunarUtil(){}

  /**
   * 计算指定日期距离基准日期的天数
   * @param year 农历年
   * @param month 农历月
   * @param day 农历日
   * @return 距离天数
   */
  public static int computeAddDays(int year,int month,int day){
    int y = BASE_YEAR;
    int m = BASE_MONTH;
    int diff = getDaysOfMonth(y,m)-BASE_DAY;
    m = nextMonth(y,m);
    while(true){
      diff += getDaysOfMonth(y,m);
      m = nextMonth(y,m);
      if(m==1){
        y++;
      }
      if(y==year&&m==month){
        diff += day;
        break;
      }
    }
    return diff;
  }

  /**
   * 获取指定年月的下一个月是第几月
   * @param y 农历年
   * @param m 农历月，闰月为负数
   * @return 1到12，闰月为负数
   */
  public static int nextMonth(int y,int m){
    int n = Math.abs(m)+1;
    if(m>0){
      int index = y-BASE_YEAR+BASE_INDEX;
      int v = LUNAR_MONTH[2*index+1];
      v = (v>>4)&0x0F;
      if(v==m){
        n = -m;
      }
    }
    if(n==13){
      n = 1;
    }
    return n;
  }

  /**
   * 获取某年某月有多少天
   *
   * @param year 农历年
   * @param month 农历月，闰月为负数
   * @return 天数
   */
  public static int getDaysOfMonth(int year,int month){
    int index = year-BASE_YEAR+BASE_INDEX;
    int v,l,d=30;
    if(1<=month&&month<=8){
      v = LUNAR_MONTH[2*index];
      l = month-1;
      if(((v>>l)&0x01)==1){
        d = 29;
      }
    }else if(9<=month&&month<=12){
      v = LUNAR_MONTH[2*index+1];
      l = month-9;
      if(((v>>l)&0x01)==1){
        d = 29;
      }
    }else{
      v = LUNAR_MONTH[2*index+1];
      v = (v>>4)&0x0F;
      if(v!=Math.abs(month)){
        d = 0;
      }else{
        d = 29;
        for(int i:LEAP_MONTH_YEAR){
          if(i==index){
            d = 30;
            break;
          }
        }
      }
    }
    return d;
  }
}
