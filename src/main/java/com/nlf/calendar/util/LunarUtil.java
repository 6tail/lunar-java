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
  /** 星期偏移量 */
  public static final int BASE_WEEK_INDEX = 2;
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
  /** 喜神方位，《喜神方位歌》：甲己在艮乙庚乾，丙辛坤位喜神安.  丁壬只在离宫坐，戊癸原在在巽间。 */
  public static final String[] POSITION_XI = {"","艮","乾","坤","离","巽","艮","乾","坤","离","巽"};
  /** 阳贵方位，《阳贵神歌》：甲戊坤艮位，乙己是坤坎，庚辛居离艮，丙丁兑与乾，震巽属何日，壬癸贵神安。 */
  public static final String[] POSITION_YANG_GUI = {"","坤","坤","兑","乾","艮","坎","离","艮","震","巽"};
  /** 阴贵方位，《阴贵神歌》：甲戊见牛羊，乙己鼠猴乡，丙丁猪鸡位，壬癸蛇兔藏，庚辛逢虎马，此是贵神方。 */
  public static final String[] POSITION_YIN_GUI = {"","艮","坎","乾","兑","坤","坤","艮","离","巽","震"};
  /** 福神方位，参考多个黄历而决定采用的《福神方位歌》：甲乙东南是福神，丙丁正东是堪宜，戊北己南庚辛坤，壬在乾方癸在西。 */
  public static final String[] POSITION_FU = {"","巽","巽","震","震","坎","离","坤","坤","乾","兑"};
  //未采用的《福神方位歌》：甲己正北是福神，丙辛西北乾宫存，乙庚坤位戊癸艮，丁壬巽上好追寻。
  //public static final String[] POSITION_FU = {"","坎","坤","乾","巽","艮","坎","坤","乾","巽","艮"};
  /** 财神方位，《财神方位歌》：甲乙东北是财神，丙丁向在西南寻，戊己正北坐方位， 庚辛正东去安身，壬癸原来正南坐，便是财神方位真。 */
  public static final String[] POSITION_CAI = {"","艮","艮","坤","坤","坎","坎","震","震","离","离"};
  /** 地支 */
  public static final String[] ZHI = {"","子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
  /** 十二值星 */
  public static final String[] ZHI_XING = {"","建","除","满","平","定","执","破","危","成","收","开","闭"};
  /** 十二天神 */
  public static final String[] TIAN_SHEN = {"","青龙","明堂","天刑","朱雀","金匮","天德","白虎","玉堂","天牢","玄武","司命","勾陈"};
  /** 月份地支对应天神偏移下标 */
  public static final Map<String,Integer> MONTH_ZHI_TIAN_SHEN_OFFSET = new HashMap<String, Integer>(){
    private static final long serialVersionUID = -1L;
    {
      put("子",4);
      put("丑",2);
      put("寅",0);
      put("卯",10);
      put("辰",8);
      put("巳",6);
      put("午",4);
      put("未",2);
      put("申",0);
      put("酉",10);
      put("戌",8);
      put("亥",6);
    }
  };
  /** 天神类型：黄道，黑道 */
  public static final Map<String,String> TIAN_SHEN_TYPE = new HashMap<String, String>(){
    private static final long serialVersionUID = -1L;
    {
      put("青龙","黄道");
      put("明堂","黄道");
      put("金贵","黄道");
      put("天德","黄道");
      put("玉堂","黄道");
      put("司命","黄道");

      put("天刑","黑道");
      put("朱雀","黄道");
      put("白虎","黄道");
      put("天牢","黄道");
      put("玄武","黄道");
      put("勾陈","黄道");
    }
  };
  /** 天神类型吉凶 */
  public static final Map<String,String> TIAN_SHEN_TYPE_LUCK = new HashMap<String, String>(){
    private static final long serialVersionUID = -1L;
    {
      put("黄道","吉");
      put("黑道","凶");
    }
  };
  /** 彭祖百忌.天干 */
  public static final String[] PENGZU_GAN = {"","甲不开仓财物耗散","乙不栽植千株不长","丙不修灶必见灾殃","丁不剃头头必生疮","戊不受田田主不祥","己不破券二比并亡","庚不经络织机虚张","辛不合酱主人不尝","壬不泱水更难提防","癸不词讼理弱敌强"};
  /** 彭祖百忌.地支 */
  public static final String[] PENGZU_ZHI = {"","子不问卜自惹祸殃","丑不冠带主不还乡","寅不祭祀神鬼不尝","卯不穿井水泉不香","辰不哭泣必主重丧","巳不远行财物伏藏","午不苫盖屋主更张","未不服药毒气入肠","申不安床鬼祟入房","酉不会客醉坐颠狂","戌不吃犬作怪上床","亥不嫁娶不利新郎"};
  /** 数字 */
  public static final String[] NUMBER = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
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
  /** 28星宿对照表，地支+星期 */
  public static final Map<String,String> XIU = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("申1","毕");
      put("申2","翼");
      put("申3","箕");
      put("申4","奎");
      put("申5","鬼");
      put("申6","氐");
      put("申0","虚");

      put("子1","毕");
      put("子2","翼");
      put("子3","箕");
      put("子4","奎");
      put("子5","鬼");
      put("子6","氐");
      put("子0","虚");

      put("辰1","毕");
      put("辰2","翼");
      put("辰3","箕");
      put("辰4","奎");
      put("辰5","鬼");
      put("辰6","氐");
      put("辰0","虚");

      put("巳1","危");
      put("巳2","觜");
      put("巳3","轸");
      put("巳4","斗");
      put("巳5","娄");
      put("巳6","柳");
      put("巳0","房");

      put("酉1","危");
      put("酉2","觜");
      put("酉3","轸");
      put("酉4","斗");
      put("酉5","娄");
      put("酉6","柳");
      put("酉0","房");

      put("丑1","危");
      put("丑2","觜");
      put("丑3","轸");
      put("丑4","斗");
      put("丑5","娄");
      put("丑6","柳");
      put("丑0","房");

      put("寅1","心");
      put("寅2","室");
      put("寅3","参");
      put("寅4","角");
      put("寅5","牛");
      put("寅6","胃");
      put("寅0","星");

      put("午1","心");
      put("午2","室");
      put("午3","参");
      put("午4","角");
      put("午5","牛");
      put("午6","胃");
      put("午0","星");

      put("戌1","心");
      put("戌2","室");
      put("戌3","参");
      put("戌4","角");
      put("戌5","牛");
      put("戌6","胃");
      put("戌0","星");

      put("亥1","张");
      put("亥2","尾");
      put("亥3","壁");
      put("亥4","井");
      put("亥5","亢");
      put("亥6","女");
      put("亥0","昴");

      put("卯1","张");
      put("卯2","尾");
      put("卯3","壁");
      put("卯4","井");
      put("卯5","亢");
      put("卯6","女");
      put("卯0","昴");

      put("未1","张");
      put("未2","尾");
      put("未3","壁");
      put("未4","井");
      put("未5","亢");
      put("未6","女");
      put("未0","昴");
    }
  };
  /** 星宿对应吉凶 */
  public static final Map<String,String> XIU_LUCK = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("角","吉");
      put("亢","凶");
      put("氐","凶");
      put("房","吉");
      put("心","凶");
      put("尾","吉");
      put("箕","吉");
      put("斗","吉");
      put("牛","凶");
      put("女","凶");
      put("虚","凶");
      put("危","凶");
      put("室","吉");
      put("壁","吉");
      put("奎","凶");
      put("娄","吉");
      put("胃","吉");
      put("昴","凶");
      put("毕","吉");
      put("觜","凶");
      put("参","吉");
      put("井","吉");
      put("鬼","凶");
      put("柳","凶");
      put("星","凶");
      put("张","吉");
      put("翼","凶");
      put("轸","吉");
    }
  };
  /** 星宿对应吉凶 */
  public static final Map<String,String> XIU_SONG = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("角","角星造作主荣昌，外进田财及女郎，嫁娶婚姻出贵子，文人及第见君王，惟有埋葬不可用，三年之后主瘟疫，起工修筑坟基地，堂前立见主人凶。");
      put("亢","亢星造作长房当，十日之中主有殃，田地消磨官失职，接运定是虎狼伤，嫁娶婚姻用此日，儿孙新妇守空房，埋葬若还用此日，当时害祸主重伤。");
      put("氐","氐星造作主灾凶，费尽田园仓库空，埋葬不可用此日，悬绳吊颈祸重重，若是婚姻离别散，夜招浪子入房中，行船必定遭沉没，更生聋哑子孙穷。");
      put("房","房星造作田园进，钱财牛马遍山岗，更招外处田庄宅，荣华富贵福禄康，埋葬若然用此日，高官进职拜君王，嫁娶嫦娥至月殿，三年抱子至朝堂。");
      put("心","心星造作大为凶，更遭刑讼狱囚中，忤逆官非宅产退，埋葬卒暴死相从，婚姻若是用此日，子死儿亡泪满胸，三年之内连遭祸，事事教君没始终。");
      put("尾","尾星造作主天恩，富贵荣华福禄增，招财进宝兴家宅，和合婚姻贵子孙，埋葬若能依此日，男清女正子孙兴，开门放水招田宅，代代公侯远播名。");
      put("箕","箕星造作主高强，岁岁年年大吉昌，埋葬修坟大吉利，田蚕牛马遍山岗，开门放水招田宅，箧满金银谷满仓，福荫高官加禄位，六亲丰禄乐安康。");
      put("斗","斗星造作主招财，文武官员位鼎台，田宅家财千万进，坟堂修筑贵富来，开门放水招牛马，旺蚕男女主和谐，遇此吉宿来照护，时支福庆永无灾。");
      put("牛","牛星造作主灾危，九横三灾不可推，家宅不安人口退，田蚕不利主人衰，嫁娶婚姻皆自损，金银财谷渐无之，若是开门并放水，牛猪羊马亦伤悲。");
      put("女","女星造作损婆娘，兄弟相嫌似虎狼，埋葬生灾逢鬼怪，颠邪疾病主瘟惶，为事遭官财失散，泻利留连不可当，开门放水用此日，全家财散主离乡。");
      put("虚","虚星造作主灾殃，男女孤眠不一双，内乱风声无礼节，儿孙媳妇伴人床，开门放水遭灾祸，虎咬蛇伤又卒亡，三三五五连年病，家破人亡不可当。");
      put("危","危星不可造高楼，自遭刑吊见血光，三年孩子遭水厄，后生出外永不还，埋葬若还逢此日，周年百日取高堂，三年两载一悲伤，开门放水到官堂。");
      put("室","室星修造进田牛，儿孙代代近王侯，家贵荣华天上至，寿如彭祖八千秋，开门放水招财帛，和合婚姻生贵儿，埋葬若能依此日，门庭兴旺福无休。");
      put("壁","壁星造作主增财，丝蚕大熟福滔天，奴婢自来人口进，开门放水出英贤，埋葬招财官品进，家中诸事乐陶然，婚姻吉利主贵子，早播名誉著祖鞭。");
      put("奎","奎星造作得祯祥，家内荣和大吉昌，若是埋葬阴卒死，当年定主两三伤，看看军令刑伤到，重重官事主瘟惶，开门放水遭灾祸，三年两次损儿郎。");
      put("娄","娄星修造起门庭，财旺家和事事兴，外进钱财百日进，一家兄弟播高名，婚姻进益生贵子，玉帛金银箱满盈，放水开门皆吉利，男荣女贵寿康宁。");
      put("胃","胃星造作事如何，家贵荣华喜气多，埋葬贵临官禄位，夫妇齐眉永保康，婚姻遇此家富贵，三灾九祸不逢他，从此门前多吉庆，儿孙代代拜金阶。");
      put("昴","昴星造作进田牛，埋葬官灾不得休，重丧二日三人死，尽卖田园不记增，开门放水招灾祸，三岁孩儿白了头，婚姻不可逢此日，死别生离是可愁。");
      put("毕","毕星造作主光前，买得田园有余钱，埋葬此日添官职，田蚕大熟永丰年，开门放水多吉庆，合家人口得安然，婚姻若得逢此日，生得孩儿福寿全。");
      put("觜","觜星造作有徒刑，三年必定主伶丁，埋葬卒死多因此，取定寅年使杀人，三丧不止皆由此，一人药毒二人身，家门田地皆退败，仓库金银化作尘。");
      put("参","参星造作旺人家，文星照耀大光华，只因造作田财旺，埋葬招疾哭黄沙，开门放水加官职，房房子孙见田加，婚姻许遁遭刑克，男女朝开幕落花。");
      put("井","井星造作旺蚕田，金榜题名第一光，埋葬须防惊卒死，狂颠风疾入黄泉，开门放水招财帛，牛马猪羊旺莫言，贵人田塘来入宅，儿孙兴旺有余钱。");
      put("鬼","鬼星起造卒人亡，堂前不见主人郎，埋葬此日官禄至，儿孙代代近君王，开门放水须伤死，嫁娶夫妻不久长，修土筑墙伤产女，手扶双女泪汪汪。");
      put("柳","柳星造作主遭官，昼夜偷闭不暂安，埋葬瘟惶多疾病，田园退尽守冬寒，开门放水遭聋瞎，腰驼背曲似弓弯，更有棒刑宜谨慎，妇人随客走盘桓。");
      put("星","星宿日好造新房，进职加官近帝王，不可埋葬并放水，凶星临位女人亡，生离死别无心恋，要自归休别嫁郎，孔子九曲殊难度，放水开门天命伤。");
      put("张","张星日好造龙轩，年年并见进庄田，埋葬不久升官职，代代为官近帝前，开门放水招财帛，婚姻和合福绵绵，田蚕人满仓库满，百般顺意自安然。");
      put("翼","翼星不利架高堂，三年二载见瘟惶，埋葬若还逢此日，子孙必定走他乡，婚姻此日不宜利，归家定是不相当，开门放水家须破，少女恋花贪外郎。");
      put("轸","轸星临水造龙宫，代代为官受皇封，富贵荣华增寿禄，库满仓盈自昌隆，埋葬文昌来照助，宅舍安宁不见凶，更有为官沾帝宠，婚姻龙子入龙宫。");
    }
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
  /** 地支相冲（子午相冲，丑未相冲，寅申相冲，辰戌相冲，卯酉相冲，巳亥相冲），由于地支对应十二生肖，也就对应了生肖相冲 */
  public static final Map<String,String> CHONG = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("子","午");
      put("丑","未");
      put("寅","申");
      put("卯","酉");
      put("辰","戌");
      put("巳","亥");
      put("午","子");
      put("未","丑");
      put("申","寅");
      put("酉","卯");
      put("戌","辰");
      put("亥","巳");
    }
  };
  /** 天干相冲之无情之克（阳克阳，阴克阴） */
  public static final Map<String,String> CHONG_GAN = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("甲","戊");
      put("乙","己");
      put("丙","庚");
      put("丁","辛");
      put("戊","壬");
      put("己","癸");
      put("庚","甲");
      put("辛","乙");
      put("壬","丙");
      put("癸","丁");
    }
  };
  /** 天干四冲（无情之克中克得最严重的4个） */
  public static final Map<String,String> CHONG_GAN_BAD = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("庚","甲");
      put("辛","乙");
      put("壬","丙");
      put("癸","丁");
    }
  };
  /** 天干相冲之有情之克（阳克阴，阴克阳） */
  public static final Map<String,String> CHONG_GAN_TIE = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("甲","己");
      put("乙","戊");
      put("丙","辛");
      put("丁","庚");
      put("戊","癸");
      put("己","壬");
      put("庚","乙");
      put("辛","甲");
      put("壬","丁");
      put("癸","丙");
    }
  };
  /** 天干五合（有情之克中最有情的5个） */
  public static final Map<String,String> CHONG_GAN_TIE_GOOD = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("甲","己");
      put("丙","辛");
      put("戊","癸");
      put("庚","乙");
      put("壬","丁");
    }
  };
  /** 煞（逢巳日、酉日、丑日必煞东；亥日、卯日、未日必煞西；申日、子日、辰日必煞南；寅日、午日、戌日必煞北） */
  public static final Map<String,String> SHA = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("子","南");
      put("丑","东");
      put("寅","北");
      put("卯","西");
      put("辰","南");
      put("巳","东");
      put("午","北");
      put("未","西");
      put("申","南");
      put("酉","东");
      put("戌","北");
      put("亥","西");
    }
  };
  /** 方位 */
  public static final Map<String,String> POSITION_DESC = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("坎","正北");
      put("艮","东北");
      put("震","正东");
      put("巽","东南");
      put("离","正南");
      put("坤","西南");
      put("兑","正西");
      put("乾","西北");
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
  /** 天干五行 */
  public static final Map<String,String> WU_XING_GAN = new HashMap<String, String>(){
    private static final long serialVersionUID = -1;
    {
      put("甲","木");
      put("乙","木");
      put("丙","火");
      put("丁","火");
      put("戊","土");
      put("己","土");
      put("庚","金");
      put("辛","金");
      put("壬","水");
      put("癸","水");
    }
  };
  /** 地支五行 */
  public static final Map<String,String> WU_XING_ZHI = new HashMap<String, String>(){
    private static final long serialVersionUID = -1;
    {
      put("寅","木");
      put("卯","木");
      put("巳","火");
      put("午","火");
      put("辰","土");
      put("丑","土");
      put("戌","土");
      put("未","土");
      put("申","金");
      put("酉","金");
      put("亥","水");
      put("子","水");
    }
  };
  /** 纳音 */
  public static final Map<String,String> NAYIN = new HashMap<String,String>(){
    private static final long serialVersionUID = -1;
    {
      put("甲子","海中金");
      put("甲午","沙中金");
      put("丙寅","炉中火");
      put("丙申","山下火");
      put("戊辰","大林木");
      put("戊戌","平地木");
      put("庚午","路旁土");
      put("庚子","壁上土");
      put("壬申","剑锋金");
      put("壬寅","金箔金");
      put("甲戌","山头火");
      put("甲辰","覆灯火");
      put("丙子","涧下水");
      put("丙午","天河水");
      put("戊寅","城头土");
      put("戊申","大驿土");
      put("庚辰","白蜡金");
      put("庚戌","钗钏金");
      put("壬午","杨柳木");
      put("壬子","桑柘木");
      put("甲申","泉中水");
      put("甲寅","大溪水");
      put("丙戌","屋上土");
      put("丙辰","沙中土");
      put("戊子","霹雳火");
      put("戊午","天上火");
      put("庚寅","松柏木");
      put("庚申","石榴木");
      put("壬辰","长流水");
      put("壬戌","大海水");
      put("乙丑","海中金");
      put("乙未","沙中金");
      put("丁卯","炉中火");
      put("丁酉","山下火");
      put("己巳","大林木");
      put("己亥","平地木");
      put("辛未","路旁土");
      put("辛丑","壁上土");
      put("癸酉","剑锋金");
      put("癸卯","金箔金");
      put("乙亥","山头火");
      put("乙巳","覆灯火");
      put("丁丑","涧下水");
      put("丁未","天河水");
      put("己卯","城头土");
      put("己酉","大驿土");
      put("辛巳","白蜡金");
      put("辛亥","钗钏金");
      put("癸未","杨柳木");
      put("癸丑","桑柘木");
      put("乙酉","泉中水");
      put("乙卯","大溪水");
      put("丁亥","屋上土");
      put("丁巳","沙中土");
      put("己丑","霹雳火");
      put("己未","天上火");
      put("辛卯","松柏木");
      put("辛酉","石榴木");
      put("癸巳","长流水");
      put("癸亥","大海水");
    }
  };

  /** 天干十神，日主+天干为键 */
  public static final Map<String,String> SHI_SHEN_GAN = new HashMap<String,String>() {
    private static final long serialVersionUID = -1;
    {
      put("甲甲","比肩");
      put("甲乙","劫财");
      put("甲丙","食神");
      put("甲丁","伤官");
      put("甲戊","偏财");
      put("甲己","正财");
      put("甲庚","七杀");
      put("甲辛","正官");
      put("甲壬","偏印");
      put("甲癸","正印");
      put("乙乙","比肩");
      put("乙甲","劫财");
      put("乙丁","食神");
      put("乙丙","伤官");
      put("乙己","偏财");
      put("乙戊","正财");
      put("乙辛","七杀");
      put("乙庚","正官");
      put("乙癸","偏印");
      put("乙壬","正印");
      put("丙丙","比肩");
      put("丙丁","劫财");
      put("丙戊","食神");
      put("丙己","伤官");
      put("丙庚","偏财");
      put("丙辛","正财");
      put("丙壬","七杀");
      put("丙癸","正官");
      put("丙甲","偏印");
      put("丙乙","正印");
      put("丁丁","比肩");
      put("丁丙","劫财");
      put("丁己","食神");
      put("丁戊","伤官");
      put("丁辛","偏财");
      put("丁庚","正财");
      put("丁癸","七杀");
      put("丁壬","正官");
      put("丁乙","偏印");
      put("丁甲","正印");
      put("戊戊","比肩");
      put("戊己","劫财");
      put("戊庚","食神");
      put("戊辛","伤官");
      put("戊壬","偏财");
      put("戊癸","正财");
      put("戊甲","七杀");
      put("戊乙","正官");
      put("戊丙","偏印");
      put("戊丁","正印");
      put("己己","比肩");
      put("己戊","劫财");
      put("己辛","食神");
      put("己庚","伤官");
      put("己癸","偏财");
      put("己壬","正财");
      put("己乙","七杀");
      put("己甲","正官");
      put("己丁","偏印");
      put("己丙","正印");
      put("庚庚","比肩");
      put("庚辛","劫财");
      put("庚壬","食神");
      put("庚癸","伤官");
      put("庚甲","偏财");
      put("庚乙","正财");
      put("庚丙","七杀");
      put("庚丁","正官");
      put("庚戊","偏印");
      put("庚己","正印");
      put("辛辛","比肩");
      put("辛庚","劫财");
      put("辛癸","食神");
      put("辛壬","伤官");
      put("辛乙","偏财");
      put("辛甲","正财");
      put("辛丁","七杀");
      put("辛丙","正官");
      put("辛己","偏印");
      put("辛戊","正印");
      put("壬壬","比肩");
      put("壬癸","劫财");
      put("壬甲","食神");
      put("壬乙","伤官");
      put("壬丙","偏财");
      put("壬丁","正财");
      put("壬戊","七杀");
      put("壬己","正官");
      put("壬庚","偏印");
      put("壬辛","正印");
      put("癸癸","比肩");
      put("癸壬","劫财");
      put("癸乙","食神");
      put("癸甲","伤官");
      put("癸丁","偏财");
      put("癸丙","正财");
      put("癸己","七杀");
      put("癸戊","正官");
      put("癸辛","偏印");
      put("癸庚","正印");
    }
  };

  /** 地支十神，日主+地支藏干主气为键 */
  public static final Map<String,String> SHI_SHEN_ZHI = new HashMap<String,String>() {
    private static final long serialVersionUID = -1;
    {
      put("甲子癸","正印");
      put("甲丑癸","正印");
      put("甲丑己","正财");
      put("甲丑辛","正官");
      put("甲寅丙","食神");
      put("甲寅甲","比肩");
      put("甲寅戊","偏财");
      put("甲卯乙","劫财");
      put("甲辰乙","劫财");
      put("甲辰戊","偏财");
      put("甲辰癸","正印");
      put("甲巳戊","偏财");
      put("甲巳丙","食神");
      put("甲巳庚","七杀");
      put("甲午丁","伤官");
      put("甲午己","正财");
      put("甲未乙","劫财");
      put("甲未己","正财");
      put("甲未丁","伤官");
      put("甲申戊","偏财");
      put("甲申庚","七杀");
      put("甲申壬","偏印");
      put("甲酉辛","正官");
      put("甲戌辛","正官");
      put("甲戌戊","偏财");
      put("甲戌丁","伤官");
      put("甲亥壬","偏印");
      put("甲亥甲","比肩");
      put("乙子癸","偏印");
      put("乙丑癸","偏印");
      put("乙丑己","偏财");
      put("乙丑辛","七杀");
      put("乙寅丙","伤官");
      put("乙寅甲","劫财");
      put("乙寅戊","正财");
      put("乙卯乙","比肩");
      put("乙辰乙","比肩");
      put("乙辰戊","正财");
      put("乙辰癸","偏印");
      put("乙巳戊","正财");
      put("乙巳丙","伤官");
      put("乙巳庚","正官");
      put("乙午丁","食神");
      put("乙午己","偏财");
      put("乙未乙","比肩");
      put("乙未己","偏财");
      put("乙未丁","食神");
      put("乙申戊","正财");
      put("乙申庚","正官");
      put("乙申壬","正印");
      put("乙酉辛","七杀");
      put("乙戌辛","七杀");
      put("乙戌戊","正财");
      put("乙戌丁","食神");
      put("乙亥壬","正印");
      put("乙亥甲","劫财");
      put("丙子癸","正官");
      put("丙丑癸","正官");
      put("丙丑己","伤官");
      put("丙丑辛","正财");
      put("丙寅丙","比肩");
      put("丙寅甲","偏印");
      put("丙寅戊","食神");
      put("丙卯乙","正印");
      put("丙辰乙","正印");
      put("丙辰戊","食神");
      put("丙辰癸","正官");
      put("丙巳戊","食神");
      put("丙巳丙","比肩");
      put("丙巳庚","偏财");
      put("丙午丁","劫财");
      put("丙午己","伤官");
      put("丙未乙","正印");
      put("丙未己","伤官");
      put("丙未丁","劫财");
      put("丙申戊","食神");
      put("丙申庚","偏财");
      put("丙申壬","七杀");
      put("丙酉辛","正财");
      put("丙戌辛","正财");
      put("丙戌戊","食神");
      put("丙戌丁","劫财");
      put("丙亥壬","七杀");
      put("丙亥甲","偏印");
      put("丁子癸","七杀");
      put("丁丑癸","七杀");
      put("丁丑己","食神");
      put("丁丑辛","偏财");
      put("丁寅丙","劫财");
      put("丁寅甲","正印");
      put("丁寅戊","伤官");
      put("丁卯乙","偏印");
      put("丁辰乙","偏印");
      put("丁辰戊","伤官");
      put("丁辰癸","七杀");
      put("丁巳戊","伤官");
      put("丁巳丙","劫财");
      put("丁巳庚","正财");
      put("丁午丁","比肩");
      put("丁午己","食神");
      put("丁未乙","偏印");
      put("丁未己","食神");
      put("丁未丁","比肩");
      put("丁申戊","伤官");
      put("丁申庚","正财");
      put("丁申壬","正官");
      put("丁酉辛","偏财");
      put("丁戌辛","偏财");
      put("丁戌戊","伤官");
      put("丁戌丁","比肩");
      put("丁亥壬","正官");
      put("丁亥甲","正印");
      put("戊子癸","正财");
      put("戊丑癸","正财");
      put("戊丑己","劫财");
      put("戊丑辛","伤官");
      put("戊寅丙","偏印");
      put("戊寅甲","七杀");
      put("戊寅戊","比肩");
      put("戊卯乙","正官");
      put("戊辰乙","正官");
      put("戊辰戊","比肩");
      put("戊辰癸","正财");
      put("戊巳戊","比肩");
      put("戊巳丙","偏印");
      put("戊巳庚","食神");
      put("戊午丁","正印");
      put("戊午己","劫财");
      put("戊未乙","正官");
      put("戊未己","劫财");
      put("戊未丁","正印");
      put("戊申戊","比肩");
      put("戊申庚","食神");
      put("戊申壬","偏财");
      put("戊酉辛","伤官");
      put("戊戌辛","伤官");
      put("戊戌戊","比肩");
      put("戊戌丁","正印");
      put("戊亥壬","偏财");
      put("戊亥甲","七杀");
      put("己子癸","偏财");
      put("己丑癸","偏财");
      put("己丑己","比肩");
      put("己丑辛","食神");
      put("己寅丙","正印");
      put("己寅甲","正官");
      put("己寅戊","劫财");
      put("己卯乙","七杀");
      put("己辰乙","七杀");
      put("己辰戊","劫财");
      put("己辰癸","偏财");
      put("己巳戊","劫财");
      put("己巳丙","正印");
      put("己巳庚","伤官");
      put("己午丁","偏印");
      put("己午己","比肩");
      put("己未乙","七杀");
      put("己未己","比肩");
      put("己未丁","偏印");
      put("己申戊","劫财");
      put("己申庚","伤官");
      put("己申壬","正财");
      put("己酉辛","食神");
      put("己戌辛","食神");
      put("己戌戊","劫财");
      put("己戌丁","偏印");
      put("己亥壬","正财");
      put("己亥甲","正官");
      put("庚子癸","伤官");
      put("庚丑癸","伤官");
      put("庚丑己","正印");
      put("庚丑辛","劫财");
      put("庚寅丙","七杀");
      put("庚寅甲","偏财");
      put("庚寅戊","偏印");
      put("庚卯乙","正财");
      put("庚辰乙","正财");
      put("庚辰戊","偏印");
      put("庚辰癸","伤官");
      put("庚巳戊","偏印");
      put("庚巳丙","七杀");
      put("庚巳庚","比肩");
      put("庚午丁","正官");
      put("庚午己","正印");
      put("庚未乙","正财");
      put("庚未己","正印");
      put("庚未丁","正官");
      put("庚申戊","偏印");
      put("庚申庚","比肩");
      put("庚申壬","食神");
      put("庚酉辛","劫财");
      put("庚戌辛","劫财");
      put("庚戌戊","偏印");
      put("庚戌丁","正官");
      put("庚亥壬","食神");
      put("庚亥甲","偏财");
      put("辛子癸","食神");
      put("辛丑癸","食神");
      put("辛丑己","偏印");
      put("辛丑辛","比肩");
      put("辛寅丙","正官");
      put("辛寅甲","正财");
      put("辛寅戊","正印");
      put("辛卯乙","偏财");
      put("辛辰乙","偏财");
      put("辛辰戊","正印");
      put("辛辰癸","食神");
      put("辛巳戊","正印");
      put("辛巳丙","正官");
      put("辛巳庚","劫财");
      put("辛午丁","七杀");
      put("辛午己","偏印");
      put("辛未乙","偏财");
      put("辛未己","偏印");
      put("辛未丁","七杀");
      put("辛申戊","正印");
      put("辛申庚","劫财");
      put("辛申壬","伤官");
      put("辛酉辛","比肩");
      put("辛戌辛","比肩");
      put("辛戌戊","正印");
      put("辛戌丁","七杀");
      put("辛亥壬","伤官");
      put("辛亥甲","正财");
      put("壬子癸","劫财");
      put("壬丑癸","劫财");
      put("壬丑己","正官");
      put("壬丑辛","正印");
      put("壬寅丙","偏财");
      put("壬寅甲","食神");
      put("壬寅戊","七杀");
      put("壬卯乙","伤官");
      put("壬辰乙","伤官");
      put("壬辰戊","七杀");
      put("壬辰癸","劫财");
      put("壬巳戊","七杀");
      put("壬巳丙","偏财");
      put("壬巳庚","偏印");
      put("壬午丁","正财");
      put("壬午己","正官");
      put("壬未乙","伤官");
      put("壬未己","正官");
      put("壬未丁","正财");
      put("壬申戊","七杀");
      put("壬申庚","偏印");
      put("壬申壬","比肩");
      put("壬酉辛","正印");
      put("壬戌辛","正印");
      put("壬戌戊","七杀");
      put("壬戌丁","正财");
      put("壬亥壬","比肩");
      put("壬亥甲","食神");
      put("癸子癸","比肩");
      put("癸丑癸","比肩");
      put("癸丑己","七杀");
      put("癸丑辛","偏印");
      put("癸寅丙","正财");
      put("癸寅甲","伤官");
      put("癸寅戊","正官");
      put("癸卯乙","食神");
      put("癸辰乙","食神");
      put("癸辰戊","正官");
      put("癸辰癸","比肩");
      put("癸巳戊","正官");
      put("癸巳丙","正财");
      put("癸巳庚","正印");
      put("癸午丁","偏财");
      put("癸午己","七杀");
      put("癸未乙","食神");
      put("癸未己","七杀");
      put("癸未丁","偏财");
      put("癸申戊","正官");
      put("癸申庚","正印");
      put("癸申壬","劫财");
      put("癸酉辛","偏印");
      put("癸戌辛","偏印");
      put("癸戌戊","正官");
      put("癸戌丁","偏财");
      put("癸亥壬","劫财");
      put("癸亥甲","伤官");
    }
  };

  /** 地支藏干表，分别为主气、余气、杂气 */
  public static final Map<String,List<String>> ZHI_HIDE_GAN = new HashMap<String,List<String>>() {
    private static final long serialVersionUID = -1;
    {
      put("子",Collections.nCopies(1,"癸"));
      put("丑",Arrays.asList("己","癸","辛"));
      put("寅",Arrays.asList("甲","丙","戊"));
      put("卯",Collections.nCopies(1,"乙"));
      put("辰",Arrays.asList("戊","乙","癸"));
      put("巳",Arrays.asList("丙","庚","戊"));
      put("午",Arrays.asList("丁","己"));
      put("未",Arrays.asList("己","丁","乙"));
      put("申",Arrays.asList("庚","壬","戊"));
      put("酉",Collections.nCopies(1,"辛"));
      put("戌",Arrays.asList("戊","辛","丁"));
      put("亥",Arrays.asList("壬","甲"));
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
   * 获取指定年份的闰月
   * @param year 年份
   * @return 闰月数字，1代表闰1月，0代表无闰月
   */
  public static int getLeapMonth(int year){
    int index = year-BASE_YEAR+BASE_INDEX;
    int v = LUNAR_MONTH[2*index+1];
    v = (v>>4)&0x0F;
    return v;
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

  /**
   * 将HH:mm时刻转换为时辰（地支），非法的时刻返回子，null返回null
   * @param hm HH:mm时刻
   * @return 时辰(地支)，如子
   */
  public static String convertTime(String hm){
    if(null==hm){
      return null;
    }
    if(hm.length()>5){
      hm = hm.substring(0,5);
    }
    int x = 2;
    for(int i=1;i<22;i+=2){
      if(hm.compareTo((i<10?"0":"")+i+":00")>=0&&hm.compareTo((i+1<10?"0":"")+(i+1)+":59")<=0){
        return ZHI[x];
      }
      x++;
    }
    return ZHI[1];
  }
}
