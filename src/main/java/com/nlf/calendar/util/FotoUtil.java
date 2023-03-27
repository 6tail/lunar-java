package com.nlf.calendar.util;

import com.nlf.calendar.FotoFestival;

import java.util.*;

/**
 * 佛历工具
 *
 * @author 6tail
 */
public class FotoUtil {
  /**
   * 观音斋日期
   */
  public static final String[] DAY_ZHAI_GUAN_YIN = {"1-8", "2-7", "2-9", "2-19", "3-3", "3-6", "3-13", "4-22", "5-3", "5-17", "6-16", "6-18", "6-19", "6-23", "7-13", "8-16", "9-19", "9-23", "10-2", "11-19", "11-24", "12-25"};

  private static final String DJ = "犯者夺纪";
  private static final String JS = "犯者减寿";
  private static final String SS = "犯者损寿";
  private static final String XL = "犯者削禄夺纪";
  private static final String JW = "犯者三年内夫妇俱亡";

  private static final FotoFestival Y = new FotoFestival("杨公忌");
  private static final FotoFestival T = new FotoFestival("四天王巡行", "", true);
  private static final FotoFestival D = new FotoFestival("斗降", DJ, true);
  private static final FotoFestival S = new FotoFestival("月朔", DJ, true);
  private static final FotoFestival W = new FotoFestival("月望", DJ, true);
  private static final FotoFestival H = new FotoFestival("月晦", JS, true);
  private static final FotoFestival L = new FotoFestival("雷斋日", JS, true);
  private static final FotoFestival J = new FotoFestival("九毒日", "犯者夭亡，奇祸不测");
  private static final FotoFestival R = new FotoFestival("人神在阴", "犯者得病", true, "宜先一日即戒");
  private static final FotoFestival M = new FotoFestival("司命奏事", JS, true, "如月小，即戒廿九");
  private static final FotoFestival HH = new FotoFestival("月晦", JS, true, "如月小，即戒廿九");

  /**
   * 因果犯忌
   */
  public static final Map<String, List<FotoFestival>> FESTIVAL = new HashMap<String, List<FotoFestival>>() {
    private static final long serialVersionUID = 1L;

    {
      put("1-1", Arrays.asList(new FotoFestival("天腊，玉帝校世人神气禄命", XL), S));
      put("1-3", Arrays.asList(new FotoFestival("万神都会", DJ), D));
      put("1-5", Collections.nCopies(1, new FotoFestival("五虚忌")));
      put("1-6", Arrays.asList(new FotoFestival("六耗忌"), L));
      put("1-7", Collections.nCopies(1, new FotoFestival("上会日", SS)));
      put("1-8", Arrays.asList(new FotoFestival("五殿阎罗天子诞", DJ), T));
      put("1-9", Collections.nCopies(1, new FotoFestival("玉皇上帝诞", DJ)));
      put("1-13", Collections.nCopies(1, Y));
      put("1-14", Arrays.asList(new FotoFestival("三元降", JS), T));
      put("1-15", Arrays.asList(new FotoFestival("三元降", JS), new FotoFestival("上元神会", DJ), W, T));
      put("1-16", Collections.nCopies(1, new FotoFestival("三元降", JS)));
      put("1-19", Collections.nCopies(1, new FotoFestival("长春真人诞")));
      put("1-23", Arrays.asList(new FotoFestival("三尸神奏事"), T));
      put("1-25", Arrays.asList(H, new FotoFestival("天地仓开日", "犯者损寿，子带疾")));
      put("1-27", Collections.nCopies(1, D));
      put("1-28", Collections.nCopies(1, R));
      put("1-29", Collections.nCopies(1, T));
      put("1-30", Arrays.asList(HH, M, T));
      put("2-1", Arrays.asList(new FotoFestival("一殿秦广王诞", DJ), S));
      put("2-2", Arrays.asList(new FotoFestival("万神都会", DJ), new FotoFestival("福德土地正神诞", "犯者得祸")));
      put("2-3", Arrays.asList(new FotoFestival("文昌帝君诞", XL), D));
      put("2-6", Arrays.asList(new FotoFestival("东华帝君诞"), L));
      put("2-8", Arrays.asList(new FotoFestival("释迦牟尼佛出家", DJ), new FotoFestival("三殿宋帝王诞", DJ), new FotoFestival("张大帝诞", DJ), T));
      put("2-11", Collections.nCopies(1, Y));
      put("2-14", Collections.nCopies(1, T));
      put("2-15", Arrays.asList(new FotoFestival("释迦牟尼佛涅槃", XL), new FotoFestival("太上老君诞", XL), new FotoFestival("月望", XL, true), T));
      put("2-17", Collections.nCopies(1, new FotoFestival("东方杜将军诞")));
      put("2-18", Arrays.asList(new FotoFestival("四殿五官王诞", XL), new FotoFestival("至圣先师孔子讳辰", XL)));
      put("2-19", Collections.nCopies(1, new FotoFestival("观音大士诞", DJ)));
      put("2-21", Collections.nCopies(1, new FotoFestival("普贤菩萨诞")));
      put("2-23", Collections.nCopies(1, T));
      put("2-25", Collections.nCopies(1, H));
      put("2-27", Collections.nCopies(1, D));
      put("2-28", Collections.nCopies(1, R));
      put("2-29", Collections.nCopies(1, T));
      put("2-30", Arrays.asList(HH, M, T));
      put("3-1", Arrays.asList(new FotoFestival("二殿楚江王诞", DJ), S));
      put("3-3", Arrays.asList(new FotoFestival("玄天上帝诞", DJ), D));
      put("3-6", Collections.nCopies(1, L));
      put("3-8", Arrays.asList(new FotoFestival("六殿卞城王诞", DJ), T));
      put("3-9", Arrays.asList(new FotoFestival("牛鬼神出", "犯者产恶胎"), Y));
      put("3-12", Collections.nCopies(1, new FotoFestival("中央五道诞")));
      put("3-14", Collections.nCopies(1, T));
      put("3-15", Arrays.asList(new FotoFestival("昊天上帝诞", DJ), new FotoFestival("玄坛诞", DJ), W, T));
      put("3-16", Collections.nCopies(1, new FotoFestival("准提菩萨诞", DJ)));
      put("3-19", Arrays.asList(new FotoFestival("中岳大帝诞"), new FotoFestival("后土娘娘诞"), new FotoFestival("三茅降")));
      put("3-20", Arrays.asList(new FotoFestival("天地仓开日", SS), new FotoFestival("子孙娘娘诞")));
      put("3-23", Collections.nCopies(1, T));
      put("3-25", Collections.nCopies(1, H));
      put("3-27", Arrays.asList(new FotoFestival("七殿泰山王诞"), D));
      put("3-28", Arrays.asList(R, new FotoFestival("苍颉至圣先师诞", XL), new FotoFestival("东岳大帝诞")));
      put("3-29", Collections.nCopies(1, T));
      put("3-30", Arrays.asList(HH, M, T));
      put("4-1", Arrays.asList(new FotoFestival("八殿都市王诞", DJ), S));
      put("4-3", Collections.nCopies(1, D));
      put("4-4", Arrays.asList(new FotoFestival("万神善会", "犯者失瘼夭胎"), new FotoFestival("文殊菩萨诞")));
      put("4-6", Collections.nCopies(1, L));
      put("4-7", Arrays.asList(new FotoFestival("南斗、北斗、西斗同降", JS), Y));
      put("4-8", Arrays.asList(new FotoFestival("释迦牟尼佛诞", DJ), new FotoFestival("万神善会", "犯者失瘼夭胎"), new FotoFestival("善恶童子降", "犯者血死"), new FotoFestival("九殿平等王诞"), T));
      put("4-14", Arrays.asList(new FotoFestival("纯阳祖师诞", JS), T));
      put("4-15", Arrays.asList(W, new FotoFestival("钟离祖师诞"), T));
      put("4-16", Collections.nCopies(1, new FotoFestival("天地仓开日", SS)));
      put("4-17", Collections.nCopies(1, new FotoFestival("十殿转轮王诞", DJ)));
      put("4-18", Arrays.asList(new FotoFestival("天地仓开日", SS), new FotoFestival("紫徽大帝诞", SS)));
      put("4-20", Collections.nCopies(1, new FotoFestival("眼光圣母诞")));
      put("4-23", Collections.nCopies(1, T));
      put("4-25", Collections.nCopies(1, H));
      put("4-27", Collections.nCopies(1, D));
      put("4-28", Collections.nCopies(1, R));
      put("4-29", Collections.nCopies(1, T));
      put("4-30", Arrays.asList(HH, M, T));
      put("5-1", Arrays.asList(new FotoFestival("南极长生大帝诞", DJ), S));
      put("5-3", Collections.nCopies(1, D));
      put("5-5", Arrays.asList(new FotoFestival("地腊", XL), new FotoFestival("五帝校定生人官爵", XL), J, Y));
      put("5-6", Arrays.asList(J, L));
      put("5-7", Collections.nCopies(1, J));
      put("5-8", Arrays.asList(new FotoFestival("南方五道诞"), T));
      put("5-11", Arrays.asList(new FotoFestival("天地仓开日", SS), new FotoFestival("天下都城隍诞")));
      put("5-12", Collections.nCopies(1, new FotoFestival("炳灵公诞")));
      put("5-13", Collections.nCopies(1, new FotoFestival("关圣降", XL)));
      put("5-14", Arrays.asList(new FotoFestival("夜子时为天地交泰", JW), T));
      put("5-15", Arrays.asList(W, J, T));
      put("5-16", Arrays.asList(new FotoFestival("九毒日", JW), new FotoFestival("天地元气造化万物之辰", JW)));
      put("5-17", Collections.nCopies(1, J));
      put("5-18", Collections.nCopies(1, new FotoFestival("张天师诞")));
      put("5-22", Collections.nCopies(1, new FotoFestival("孝娥神诞", DJ)));
      put("5-23", Collections.nCopies(1, T));
      put("5-25", Arrays.asList(J, H));
      put("5-26", Collections.nCopies(1, J));
      put("5-27", Arrays.asList(J, D));
      put("5-28", Collections.nCopies(1, R));
      put("5-29", Collections.nCopies(1, T));
      put("5-30", Arrays.asList(HH, M, T));
      put("6-1", Collections.nCopies(1, S));
      put("6-3", Arrays.asList(new FotoFestival("韦驮菩萨圣诞"), D, Y));
      put("6-5", Collections.nCopies(1, new FotoFestival("南赡部洲转大轮", SS)));
      put("6-6", Arrays.asList(new FotoFestival("天地仓开日", SS), L));
      put("6-8", Collections.nCopies(1, T));
      put("6-10", Collections.nCopies(1, new FotoFestival("金粟如来诞")));
      put("6-14", Collections.nCopies(1, T));
      put("6-15", Arrays.asList(W, T));
      put("6-19", Collections.nCopies(1, new FotoFestival("观世音菩萨成道", DJ)));
      put("6-23", Arrays.asList(new FotoFestival("南方火神诞", "犯者遭回禄"), T));
      put("6-24", Arrays.asList(new FotoFestival("雷祖诞", XL), new FotoFestival("关帝诞", XL)));
      put("6-25", Collections.nCopies(1, H));
      put("6-27", Collections.nCopies(1, D));
      put("6-28", Collections.nCopies(1, R));
      put("6-29", Collections.nCopies(1, T));
      put("6-30", Arrays.asList(HH, M, T));
      put("7-1", Arrays.asList(S, Y));
      put("7-3", Collections.nCopies(1, D));
      put("7-5", Collections.nCopies(1, new FotoFestival("中会日", SS, false, "一作初七")));
      put("7-6", Collections.nCopies(1, L));
      put("7-7", Arrays.asList(new FotoFestival("道德腊", XL), new FotoFestival("五帝校生人善恶", XL), new FotoFestival("魁星诞", XL)));
      put("7-8", Collections.nCopies(1, T));
      put("7-10", Collections.nCopies(1, new FotoFestival("阴毒日", "", false, "大忌")));
      put("7-12", Collections.nCopies(1, new FotoFestival("长真谭真人诞")));
      put("7-13", Collections.nCopies(1, new FotoFestival("大势至菩萨诞", JS)));
      put("7-14", Arrays.asList(new FotoFestival("三元降", JS), T));
      put("7-15", Arrays.asList(W, new FotoFestival("三元降", DJ), new FotoFestival("地官校籍", DJ), T));
      put("7-16", Collections.nCopies(1, new FotoFestival("三元降", JS)));
      put("7-18", Collections.nCopies(1, new FotoFestival("西王母诞", DJ)));
      put("7-19", Collections.nCopies(1, new FotoFestival("太岁诞", DJ)));
      put("7-22", Collections.nCopies(1, new FotoFestival("增福财神诞", XL)));
      put("7-23", Collections.nCopies(1, T));
      put("7-25", Collections.nCopies(1, H));
      put("7-27", Collections.nCopies(1, D));
      put("7-28", Collections.nCopies(1, R));
      put("7-29", Arrays.asList(Y, T));
      put("7-30", Arrays.asList(new FotoFestival("地藏菩萨诞", DJ), HH, M, T));
      put("8-1", Arrays.asList(S, new FotoFestival("许真君诞")));
      put("8-3", Arrays.asList(D, new FotoFestival("北斗诞", XL), new FotoFestival("司命灶君诞", "犯者遭回禄")));
      put("8-5", Collections.nCopies(1, new FotoFestival("雷声大帝诞", DJ)));
      put("8-6", Collections.nCopies(1, L));
      put("8-8", Collections.nCopies(1, T));
      put("8-10", Collections.nCopies(1, new FotoFestival("北斗大帝诞")));
      put("8-12", Collections.nCopies(1, new FotoFestival("西方五道诞")));
      put("8-14", Collections.nCopies(1, T));
      put("8-15", Arrays.asList(W, new FotoFestival("太明朝元", "犯者暴亡", false, "宜焚香守夜"), T));
      put("8-16", Collections.nCopies(1, new FotoFestival("天曹掠刷真君降", "犯者贫夭")));
      put("8-18", Collections.nCopies(1, new FotoFestival("天人兴福之辰", "", false, "宜斋戒，存想吉事")));
      put("8-23", Arrays.asList(new FotoFestival("汉恒候张显王诞"), T));
      put("8-24", Collections.nCopies(1, new FotoFestival("灶君夫人诞")));
      put("8-25", Collections.nCopies(1, H));
      put("8-27", Arrays.asList(D, new FotoFestival("至圣先师孔子诞", XL), Y));
      put("8-28", Arrays.asList(R, new FotoFestival("四天会事")));
      put("8-29", Collections.nCopies(1, T));
      put("8-30", Arrays.asList(new FotoFestival("诸神考校", "犯者夺算"), HH, M, T));
      put("9-1", Arrays.asList(S, new FotoFestival("南斗诞", XL), new FotoFestival("北斗九星降世", DJ, false, "此九日俱宜斋戒")));
      put("9-3", Arrays.asList(D, new FotoFestival("五瘟神诞")));
      put("9-6", Collections.nCopies(1, L));
      put("9-8", Collections.nCopies(1, T));
      put("9-9", Arrays.asList(new FotoFestival("斗母诞", XL), new FotoFestival("酆都大帝诞"), new FotoFestival("玄天上帝飞升")));
      put("9-10", Collections.nCopies(1, new FotoFestival("斗母降", DJ)));
      put("9-11", Collections.nCopies(1, new FotoFestival("宜戒")));
      put("9-13", Collections.nCopies(1, new FotoFestival("孟婆尊神诞")));
      put("9-14", Collections.nCopies(1, T));
      put("9-15", Arrays.asList(W, T));
      put("9-17", Collections.nCopies(1, new FotoFestival("金龙四大王诞", "犯者遭水厄")));
      put("9-19", Arrays.asList(new FotoFestival("日宫月宫会合", JS), new FotoFestival("观世音菩萨诞", JS)));
      put("9-23", Collections.nCopies(1, T));
      put("9-25", Arrays.asList(H, Y));
      put("9-27", Collections.nCopies(1, D));
      put("9-28", Collections.nCopies(1, R));
      put("9-29", Collections.nCopies(1, T));
      put("9-30", Arrays.asList(new FotoFestival("药师琉璃光佛诞", "犯者危疾"), HH, M, T));
      put("10-1", Arrays.asList(S, new FotoFestival("民岁腊", DJ), new FotoFestival("四天王降", "犯者一年内死")));
      put("10-3", Arrays.asList(D, new FotoFestival("三茅诞")));
      put("10-5", Arrays.asList(new FotoFestival("下会日", JS), new FotoFestival("达摩祖师诞", JS)));
      put("10-6", Arrays.asList(L, new FotoFestival("天曹考察", DJ)));
      put("10-8", Arrays.asList(new FotoFestival("佛涅槃日", "", false, "大忌色欲"), T));
      put("10-10", Collections.nCopies(1, new FotoFestival("四天王降", "犯者一年内死")));
      put("10-11", Collections.nCopies(1, new FotoFestival("宜戒")));
      put("10-14", Arrays.asList(new FotoFestival("三元降", JS), T));
      put("10-15", Arrays.asList(W, new FotoFestival("三元降", DJ), new FotoFestival("下元水府校籍", DJ), T));
      put("10-16", Arrays.asList(new FotoFestival("三元降", JS), T));
      put("10-23", Arrays.asList(Y, T));
      put("10-25", Collections.nCopies(1, H));
      put("10-27", Arrays.asList(D, new FotoFestival("北极紫徽大帝降")));
      put("10-28", Collections.nCopies(1, R));
      put("10-29", Collections.nCopies(1, T));
      put("10-30", Arrays.asList(HH, M, T));
      put("11-1", Collections.nCopies(1, S));
      put("11-3", Collections.nCopies(1, D));
      put("11-4", Collections.nCopies(1, new FotoFestival("至圣先师孔子诞", XL)));
      put("11-6", Collections.nCopies(1, new FotoFestival("西岳大帝诞")));
      put("11-8", Collections.nCopies(1, T));
      put("11-11", Arrays.asList(new FotoFestival("天地仓开日", DJ), new FotoFestival("太乙救苦天尊诞", DJ)));
      put("11-14", Collections.nCopies(1, T));
      put("11-15", Arrays.asList(new FotoFestival("月望", "上半夜犯男死 下半夜犯女死"), new FotoFestival("四天王巡行", "上半夜犯男死 下半夜犯女死")));
      put("11-17", Collections.nCopies(1, new FotoFestival("阿弥陀佛诞")));
      put("11-19", Collections.nCopies(1, new FotoFestival("太阳日宫诞", "犯者得奇祸")));
      put("11-21", Collections.nCopies(1, Y));
      put("11-23", Arrays.asList(new FotoFestival("张仙诞", "犯者绝嗣"), T));
      put("11-25", Arrays.asList(new FotoFestival("掠刷大夫降", "犯者遭大凶"), H));
      put("11-26", Collections.nCopies(1, new FotoFestival("北方五道诞")));
      put("11-27", Collections.nCopies(1, D));
      put("11-28", Collections.nCopies(1, R));
      put("11-29", Collections.nCopies(1, T));
      put("11-30", Arrays.asList(HH, M, T));
      put("12-1", Collections.nCopies(1, S));
      put("12-3", Collections.nCopies(1, D));
      put("12-6", Arrays.asList(new FotoFestival("天地仓开日", JS), L));
      put("12-7", Collections.nCopies(1, new FotoFestival("掠刷大夫降", "犯者得恶疾")));
      put("12-8", Arrays.asList(new FotoFestival("王侯腊", DJ), new FotoFestival("释迦如来成佛之辰"), T, new FotoFestival("初旬内戊日，亦名王侯腊", DJ)));
      put("12-12", Collections.nCopies(1, new FotoFestival("太素三元君朝真")));
      put("12-14", Collections.nCopies(1, T));
      put("12-15", Arrays.asList(W, T));
      put("12-16", Collections.nCopies(1, new FotoFestival("南岳大帝诞")));
      put("12-19", Collections.nCopies(1, Y));
      put("12-20", Collections.nCopies(1, new FotoFestival("天地交道", "犯者促寿")));
      put("12-21", Collections.nCopies(1, new FotoFestival("天猷上帝诞")));
      put("12-23", Arrays.asList(new FotoFestival("五岳诞降"), T));
      put("12-24", Collections.nCopies(1, new FotoFestival("司今朝天奏人善恶", "犯者得大祸")));
      put("12-25", Arrays.asList(new FotoFestival("三清玉帝同降，考察善恶", "犯者得奇祸"), H));
      put("12-27", Collections.nCopies(1, D));
      put("12-28", Collections.nCopies(1, R));
      put("12-29", Arrays.asList(new FotoFestival("华严菩萨诞"), T));
      put("12-30", Collections.nCopies(1, new FotoFestival("诸神下降，察访善恶", "犯者男女俱亡")));
    }
  };

  /**
   * 纪念日
   */
  public static final Map<String, List<String>> OTHER_FESTIVAL = new HashMap<String, List<String>>() {
    private static final long serialVersionUID = -1;

    {
      put("1-1", Collections.nCopies(1, "弥勒菩萨圣诞"));
      put("1-6", Collections.nCopies(1, "定光佛圣诞"));
      put("2-8", Collections.nCopies(1, "释迦牟尼佛出家"));
      put("2-15", Collections.nCopies(1, "释迦牟尼佛涅槃"));
      put("2-19", Collections.nCopies(1, "观世音菩萨圣诞"));
      put("2-21", Collections.nCopies(1, "普贤菩萨圣诞"));
      put("3-16", Collections.nCopies(1, "准提菩萨圣诞"));
      put("4-4", Collections.nCopies(1, "文殊菩萨圣诞"));
      put("4-8", Collections.nCopies(1, "释迦牟尼佛圣诞"));
      put("4-15", Collections.nCopies(1, "佛吉祥日"));
      put("4-28", Collections.nCopies(1, "药王菩萨圣诞"));
      put("5-13", Collections.nCopies(1, "伽蓝菩萨圣诞"));
      put("6-3", Collections.nCopies(1, "韦驮菩萨圣诞"));
      put("6-19", Collections.nCopies(1, "观音菩萨成道"));
      put("7-13", Collections.nCopies(1, "大势至菩萨圣诞"));
      put("7-15", Collections.nCopies(1, "佛欢喜日"));
      put("7-24", Collections.nCopies(1, "龙树菩萨圣诞"));
      put("7-30", Collections.nCopies(1, "地藏菩萨圣诞"));
      put("8-15", Collections.nCopies(1, "月光菩萨圣诞"));
      put("8-22", Collections.nCopies(1, "燃灯佛圣诞"));
      put("9-9", Collections.nCopies(1, "摩利支天菩萨圣诞"));
      put("9-19", Collections.nCopies(1, "观世音菩萨出家"));
      put("9-30", Collections.nCopies(1, "药师琉璃光佛圣诞"));
      put("10-5", Collections.nCopies(1, "达摩祖师圣诞"));
      put("10-20", Collections.nCopies(1, "文殊菩萨出家"));
      put("11-17", Collections.nCopies(1, "阿弥陀佛圣诞"));
      put("11-19", Collections.nCopies(1, "日光菩萨圣诞"));
      put("12-8", Collections.nCopies(1, "释迦牟尼佛成道"));
      put("12-23", Collections.nCopies(1, "监斋菩萨圣诞"));
      put("12-29", Collections.nCopies(1, "华严菩萨圣诞"));
    }
  };

  /**
   * 27星宿，佛教从印度传入中国，印度把28星宿改为27星宿，把牛宿(牛金牛)纳入了女宿(女土蝠)。
   */
  public static final String[] XIU_27 = {"角", "亢", "氐", "房", "心", "尾", "箕", "斗", "女", "虚", "危", "室", "壁", "奎", "娄", "胃", "昴", "毕", "觜", "参", "井", "鬼", "柳", "星", "张", "翼", "轸"};

  /**
   * 每月初一的27星宿偏移
   */
  private static final int[] XIU_OFFSET = {11, 13, 15, 17, 19, 21, 24, 0, 2, 4, 7, 9};

  /**
   * 获取27星宿
   *
   * @param month 佛历月
   * @param day   佛历日
   * @return 星宿
   */
  public static String getXiu(int month, int day) {
    return XIU_27[(XIU_OFFSET[Math.abs(month)-1] + day - 1) % XIU_27.length];
  }
}
