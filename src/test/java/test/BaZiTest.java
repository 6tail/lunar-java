package test;

import com.nlf.calendar.EightChar;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.eightchar.Yun;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 八字测试
 */
public class BaZiTest {

  @Test
  public void testGanZhi() {
    Solar solar = new Solar(2005, 12, 23, 8, 37, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("年柱", "乙酉", eightChar.getYear());
    Assert.assertEquals("月柱", "戊子", eightChar.getMonth());
    Assert.assertEquals("日柱", "辛巳", eightChar.getDay());
    Assert.assertEquals("时柱", "壬辰", eightChar.getTime());
  }

  @Test
  public void testGanZhi2() {
    Solar solar = new Solar(1988, 2, 15, 23, 30, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("年柱", "戊辰", eightChar.getYear());
    Assert.assertEquals("月柱", "甲寅", eightChar.getMonth());
    Assert.assertEquals("日柱", "庚子", eightChar.getDay());
    Assert.assertEquals("时柱", "戊子", eightChar.getTime());

    eightChar.setSect(1);
    Assert.assertEquals("年柱", "戊辰", eightChar.getYear());
    Assert.assertEquals("月柱", "甲寅", eightChar.getMonth());
    Assert.assertEquals("日柱", "辛丑", eightChar.getDay());
    Assert.assertEquals("时柱", "戊子", eightChar.getTime());

    solar = new Solar(1988, 2, 15, 22, 30, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals("年柱", "戊辰", eightChar.getYear());
    Assert.assertEquals("月柱", "甲寅", eightChar.getMonth());
    Assert.assertEquals("日柱", "庚子", eightChar.getDay());
    Assert.assertEquals("时柱", "丁亥", eightChar.getTime());

    solar = new Solar(1988, 2, 2, 22, 30, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals("年柱", "丁卯", eightChar.getYear());
    Assert.assertEquals("月柱", "癸丑", eightChar.getMonth());
    Assert.assertEquals("日柱", "丁亥", eightChar.getDay());
    Assert.assertEquals("时柱", "辛亥", eightChar.getTime());
  }

  @Test
  public void testGanZhi3(){
    Lunar lunar = new Lunar(2019,12,12,11,22,0);
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("年柱", "己亥", eightChar.getYear());
    Assert.assertEquals("月柱", "丁丑", eightChar.getMonth());
    Assert.assertEquals("日柱", "戊申", eightChar.getDay());
    Assert.assertEquals("时柱", "戊午", eightChar.getTime());
  }

  @Test
  public void testHideGan() {
    Solar solar = new Solar(2005, 12, 23, 8, 37, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("年支藏干", "[辛]", eightChar.getYearHideGan().toString());
    Assert.assertEquals("月支藏干", "[癸]", eightChar.getMonthHideGan().toString());
    Assert.assertEquals("日支藏干", "[丙, 庚, 戊]", eightChar.getDayHideGan().toString());
    Assert.assertEquals("时支藏干", "[戊, 乙, 癸]", eightChar.getTimeHideGan().toString());
  }

  @Test
  public void testShiShenGan() {
    Solar solar = new Solar(2005, 12, 23, 8, 37, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("年干十神", "偏财", eightChar.getYearShiShenGan());
    Assert.assertEquals("月干十神", "正印", eightChar.getMonthShiShenGan());
    Assert.assertEquals("日干十神", "日主", eightChar.getDayShiShenGan());
    Assert.assertEquals("时干十神", "伤官", eightChar.getTimeShiShenGan());
  }

  @Test
  public void testShiShenZhi() {
    Solar solar = new Solar(2005, 12, 23, 8, 37, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("年支十神", "[比肩]", eightChar.getYearShiShenZhi().toString());
    Assert.assertEquals("月支十神", "[食神]", eightChar.getMonthShiShenZhi().toString());
    Assert.assertEquals("日支十神", "[正官, 劫财, 正印]", eightChar.getDayShiShenZhi().toString());
    Assert.assertEquals("时支十神", "[正印, 偏财, 食神]", eightChar.getTimeShiShenZhi().toString());
  }

  @Test
  public void testDiShi() {
    Solar solar = new Solar(2005, 12, 23, 8, 37, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals(String.format("年柱地势(%s%s)",eightChar.getDayGan(),eightChar.getYearZhi()), "临官", eightChar.getYearDiShi());
    Assert.assertEquals(String.format("月柱地势(%s%s)",eightChar.getDayGan(),eightChar.getMonthZhi()), "长生", eightChar.getMonthDiShi());
    Assert.assertEquals(String.format("日柱地势(%s%s)",eightChar.getDayGan(),eightChar.getDayZhi()), "死", eightChar.getDayDiShi());
    Assert.assertEquals(String.format("时柱地势(%s%s)",eightChar.getDayGan(),eightChar.getTimeZhi()), "墓", eightChar.getTimeDiShi());

    solar = new Solar(2020, 11, 18, 17, 37, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals(String.format("年柱地势(%s%s)",eightChar.getDayGan(),eightChar.getYearZhi()), "病", eightChar.getYearDiShi());
    Assert.assertEquals(String.format("月柱地势(%s%s)",eightChar.getDayGan(),eightChar.getMonthZhi()), "死", eightChar.getMonthDiShi());
    Assert.assertEquals(String.format("日柱地势(%s%s)",eightChar.getDayGan(),eightChar.getDayZhi()), "衰", eightChar.getDayDiShi());
    Assert.assertEquals(String.format("时柱地势(%s%s)",eightChar.getDayGan(),eightChar.getTimeZhi()), "绝", eightChar.getTimeDiShi());

    solar = new Solar(2020, 11, 19, 17, 37, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals(String.format("年柱地势(%s%s)",eightChar.getDayGan(),eightChar.getYearZhi()), "胎", eightChar.getYearDiShi());
    Assert.assertEquals(String.format("月柱地势(%s%s)",eightChar.getDayGan(),eightChar.getMonthZhi()), "绝", eightChar.getMonthDiShi());
    Assert.assertEquals(String.format("日柱地势(%s%s)",eightChar.getDayGan(),eightChar.getDayZhi()), "长生", eightChar.getDayDiShi());
    Assert.assertEquals(String.format("时柱地势(%s%s)",eightChar.getDayGan(),eightChar.getTimeZhi()), "死", eightChar.getTimeDiShi());

    solar = new Solar(2020, 11, 20, 17, 37, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals(String.format("年柱地势(%s%s)",eightChar.getDayGan(),eightChar.getYearZhi()), "绝", eightChar.getYearDiShi());
    Assert.assertEquals(String.format("月柱地势(%s%s)",eightChar.getDayGan(),eightChar.getMonthZhi()), "胎", eightChar.getMonthDiShi());
    Assert.assertEquals(String.format("日柱地势(%s%s)",eightChar.getDayGan(),eightChar.getDayZhi()), "病", eightChar.getDayDiShi());
    Assert.assertEquals(String.format("时柱地势(%s%s)",eightChar.getDayGan(),eightChar.getTimeZhi()), "长生", eightChar.getTimeDiShi());

    solar = new Solar(2020, 11, 21, 17, 37, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals(String.format("年柱地势(%s%s)",eightChar.getDayGan(),eightChar.getYearZhi()), "胎", eightChar.getYearDiShi());
    Assert.assertEquals(String.format("月柱地势(%s%s)",eightChar.getDayGan(),eightChar.getMonthZhi()), "绝", eightChar.getMonthDiShi());
    Assert.assertEquals(String.format("日柱地势(%s%s)",eightChar.getDayGan(),eightChar.getDayZhi()), "冠带", eightChar.getDayDiShi());
    Assert.assertEquals(String.format("时柱地势(%s%s)",eightChar.getDayGan(),eightChar.getTimeZhi()), "死", eightChar.getTimeDiShi());

    solar = new Solar(2020, 11, 22, 17, 37, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals(String.format("年柱地势(%s%s)",eightChar.getDayGan(),eightChar.getYearZhi()), "绝", eightChar.getYearDiShi());
    Assert.assertEquals(String.format("月柱地势(%s%s)",eightChar.getDayGan(),eightChar.getMonthZhi()), "胎", eightChar.getMonthDiShi());
    Assert.assertEquals(String.format("日柱地势(%s%s)",eightChar.getDayGan(),eightChar.getDayZhi()), "帝旺", eightChar.getDayDiShi());
    Assert.assertEquals(String.format("时柱地势(%s%s)",eightChar.getDayGan(),eightChar.getTimeZhi()), "长生", eightChar.getTimeDiShi());

    solar = new Solar(2020, 11, 23, 17, 37, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals(String.format("年柱地势(%s%s)",eightChar.getDayGan(),eightChar.getYearZhi()), "死", eightChar.getYearDiShi());
    Assert.assertEquals(String.format("月柱地势(%s%s)",eightChar.getDayGan(),eightChar.getMonthZhi()), "病", eightChar.getMonthDiShi());
    Assert.assertEquals(String.format("日柱地势(%s%s)",eightChar.getDayGan(),eightChar.getDayZhi()), "沐浴", eightChar.getDayDiShi());
    Assert.assertEquals(String.format("时柱地势(%s%s)",eightChar.getDayGan(),eightChar.getTimeZhi()), "帝旺", eightChar.getTimeDiShi());

    solar = new Solar(2020, 11, 24, 17, 37, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals(String.format("年柱地势(%s%s)",eightChar.getDayGan(),eightChar.getYearZhi()), "长生", eightChar.getYearDiShi());
    Assert.assertEquals(String.format("月柱地势(%s%s)",eightChar.getDayGan(),eightChar.getMonthZhi()), "沐浴", eightChar.getMonthDiShi());
    Assert.assertEquals(String.format("日柱地势(%s%s)",eightChar.getDayGan(),eightChar.getDayZhi()), "衰", eightChar.getDayDiShi());
    Assert.assertEquals(String.format("时柱地势(%s%s)",eightChar.getDayGan(),eightChar.getTimeZhi()), "临官", eightChar.getTimeDiShi());

    solar = new Solar(2020, 11, 25, 17, 37, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals(String.format("年柱地势(%s%s)",eightChar.getDayGan(),eightChar.getYearZhi()), "帝旺", eightChar.getYearDiShi());
    Assert.assertEquals(String.format("月柱地势(%s%s)",eightChar.getDayGan(),eightChar.getMonthZhi()), "临官", eightChar.getMonthDiShi());
    Assert.assertEquals(String.format("日柱地势(%s%s)",eightChar.getDayGan(),eightChar.getDayZhi()), "长生", eightChar.getDayDiShi());
    Assert.assertEquals(String.format("时柱地势(%s%s)",eightChar.getDayGan(),eightChar.getTimeZhi()), "沐浴", eightChar.getTimeDiShi());

    solar = new Solar(2020, 11, 26, 17, 37, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals(String.format("年柱地势(%s%s)",eightChar.getDayGan(),eightChar.getYearZhi()), "临官", eightChar.getYearDiShi());
    Assert.assertEquals(String.format("月柱地势(%s%s)",eightChar.getDayGan(),eightChar.getMonthZhi()), "帝旺", eightChar.getMonthDiShi());
    Assert.assertEquals(String.format("日柱地势(%s%s)",eightChar.getDayGan(),eightChar.getDayZhi()), "病", eightChar.getDayDiShi());
    Assert.assertEquals(String.format("时柱地势(%s%s)",eightChar.getDayGan(),eightChar.getTimeZhi()), "病", eightChar.getTimeDiShi());

    solar = new Solar(2020, 11, 27, 17, 37, 0);
    lunar = solar.getLunar();
    eightChar = lunar.getEightChar();
    Assert.assertEquals(String.format("年柱地势(%s%s)",eightChar.getDayGan(),eightChar.getYearZhi()), "沐浴", eightChar.getYearDiShi());
    Assert.assertEquals(String.format("月柱地势(%s%s)",eightChar.getDayGan(),eightChar.getMonthZhi()), "长生", eightChar.getMonthDiShi());
    Assert.assertEquals(String.format("日柱地势(%s%s)",eightChar.getDayGan(),eightChar.getDayZhi()), "养", eightChar.getDayDiShi());
    Assert.assertEquals(String.format("时柱地势(%s%s)",eightChar.getDayGan(),eightChar.getTimeZhi()), "胎", eightChar.getTimeDiShi());
  }

  @Test
  public void testNaYin() {
    Solar solar = new Solar(2005, 12, 23, 8, 37, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("年柱纳音", "泉中水", eightChar.getYearNaYin());
    Assert.assertEquals("月柱纳音", "霹雳火", eightChar.getMonthNaYin());
    Assert.assertEquals("日柱纳音", "白蜡金", eightChar.getDayNaYin());
    Assert.assertEquals("时柱纳音", "长流水", eightChar.getTimeNaYin());
  }

  @Test
  public void testTaiYuan() {
    Solar solar = new Solar(2005, 12, 23, 8, 37, 0);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("胎元", "己卯", lunar.getEightChar().getTaiYuan());

    lunar = new Solar(1995, 12, 18, 10, 28, 0).getLunar();
    Assert.assertEquals("胎元", "己卯", lunar.getEightChar().getTaiYuan());
  }

  @Test
  public void testMingGong() {
    Lunar lunar = new Solar(2005, 12, 23, 8, 37, 0).getLunar();
    Assert.assertEquals("命宫", "己丑", lunar.getEightChar().getMingGong());

    lunar = new Solar(1998, 6, 11, 4, 28, 0).getLunar();
    Assert.assertEquals("命宫", "辛酉", lunar.getEightChar().getMingGong());

    lunar = new Solar(1995, 12, 18, 10, 28, 0).getLunar();
    Assert.assertEquals("命宫", "戊子", lunar.getEightChar().getMingGong());
  }

  @Test
  public void testShenGong() {
    Lunar lunar = new Solar(1995, 12, 18, 10, 28, 0).getLunar();
    Assert.assertEquals("身宫", "壬午", lunar.getEightChar().getShenGong());
  }

  @Test
  public void testShenGong1() {
    Lunar lunar = new Solar(1994, 12, 6, 2, 0, 0).getLunar();
    Assert.assertEquals("身宫", "丁丑", lunar.getEightChar().getShenGong());
  }

  @Test
  public void testShenGong2() {
    Lunar lunar = new Solar(1990, 12, 11, 6, 0, 0).getLunar();
    Assert.assertEquals("身宫", "庚辰", lunar.getEightChar().getShenGong());
  }

  @Test
  public void testShenGong3() {
    Lunar lunar = new Solar(1993, 5, 23, 4, 0, 0).getLunar();
    Assert.assertEquals("身宫", "庚申", lunar.getEightChar().getShenGong());
  }

  @Test
  public void testBaZi2Solar() {
    List<Solar> l = Solar.fromBaZi("丙辰","丁酉","丙子","甲午");
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("1916-10-06 12:00:00");
    expected.add("1976-09-21 12:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testBaZi2Solar1() {
    List<Solar> l = Solar.fromBaZi("壬寅","庚戌","己未","乙亥");
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("2022-11-02 22:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testBaZi2Solar2() {
    List<Solar> l = Solar.fromBaZi("己卯","辛未","甲戌","壬申");
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("1939-08-05 16:00:00");
    expected.add("1999-07-21 16:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testBaZi2Solar3() {
    List<Solar> l = Solar.fromBaZi("庚子","戊子","己卯","庚午");
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("1901-01-01 12:00:00");
    expected.add("1960-12-17 12:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testBaZi2Solar4() {
    List<Solar> l = Solar.fromBaZi("庚子","癸未","乙丑","丁亥");
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("1960-08-05 22:00:00");
    expected.add("2020-07-21 22:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void test4() {
    Lunar lunar = Lunar.fromYmd(1985, 12, 27);
    Assert.assertEquals("1995-11-05", lunar.getEightChar().getYun(1).getStartSolar().toYmd());
  }

  @Test
  public void test5() {
    Lunar lunar = Lunar.fromYmd(1985, 1, 27);
    Assert.assertEquals("1989-03-28", lunar.getEightChar().getYun(1).getStartSolar().toYmd());
  }

  @Test
  public void test6() {
    Lunar lunar = Lunar.fromYmd(1986, 12, 27);
    Assert.assertEquals("1990-04-15", lunar.getEightChar().getYun(1).getStartSolar().toYmd());
  }

  @Test
  public void test10(){
    Lunar lunar = Solar.fromYmdHms(1988, 2, 15, 23, 30, 0).getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("年柱", "戊辰", eightChar.getYear());
    Assert.assertEquals("月柱", "甲寅", eightChar.getMonth());
    Assert.assertEquals("日柱", "庚子", eightChar.getDay());
    Assert.assertEquals("时柱", "戊子", eightChar.getTime());
  }

  @Test
  public void test11(){
    Lunar lunar = Lunar.fromYmdHms(1987, 12, 28, 23, 30, 0);
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("年柱", "戊辰", eightChar.getYear());
    Assert.assertEquals("月柱", "甲寅", eightChar.getMonth());
    Assert.assertEquals("日柱", "庚子", eightChar.getDay());
    Assert.assertEquals("时柱", "戊子", eightChar.getTime());
  }

  @Test
  public void test13(){
    Lunar lunar = Lunar.fromYmdHms(1991, 4, 5, 3, 37, 0);
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("年柱", "辛未", eightChar.getYear());
    Assert.assertEquals("月柱", "癸巳", eightChar.getMonth());
    Assert.assertEquals("日柱", "戊子", eightChar.getDay());
    Assert.assertEquals("时柱", "甲寅", eightChar.getTime());
  }

  @Test
  public void test14() {
    List<Solar> l = Solar.fromBaZi("癸卯","甲寅","甲寅","甲子", 2, 1843);
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("1843-02-09 00:00:00");
    expected.add("2023-02-25 00:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void test15() {
    List<Solar> l = Solar.fromBaZi("己亥","丁丑","壬寅","戊申");
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("1900-01-29 16:00:00");
    expected.add("1960-01-15 16:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void test16() {
    List<Solar> l = Solar.fromBaZi("己亥","丙子","癸酉","庚申");
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("1959-12-17 16:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void test17() {
    Solar solar = new Solar(1986, 5, 29, 13, 37, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("辛丑", eightChar.getShenGong());
  }

  @Test
  public void test18() {
    Solar solar = new Solar(2023, 10, 8, 18, 37, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("己未", eightChar.getShenGong());
  }

  @Test
  public void test19() {
    List<Solar> l = Solar.fromBaZi("丁丑","癸卯","癸丑","辛酉");
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("1937-03-27 18:00:00");
    expected.add("1997-03-12 18:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void test20() {
    List<Solar> l = Solar.fromBaZi("乙未","己卯","丁丑","甲辰");
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("1955-03-17 08:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void test21() {
    Lunar lunar = Solar.fromYmdHms(2024, 1, 29, 9, 30, 0).getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("命宫", "癸亥", eightChar.getMingGong());
    Assert.assertEquals("身宫", "己未", eightChar.getShenGong());
  }

  @Test
  public void test22() {
    Assert.assertEquals("身宫", "丙寅", Solar.fromYmdHms(1990, 1, 27, 0, 0, 0).getLunar().getEightChar().getShenGong());
  }

  @Test
  public void test23() {
    Assert.assertEquals("甲戌", Solar.fromYmdHms(2019, 3, 7, 8, 0, 0).getLunar().getEightChar().getMingGong());
  }

  @Test
  public void test24() {
    Assert.assertEquals("丁丑", Solar.fromYmdHms(2019, 3, 27, 2, 0, 0).getLunar().getEightChar().getMingGong());
  }

  @Test
  public void test25() {
    Assert.assertEquals("丙寅", Lunar.fromYmdHms(1994, 5, 20, 18, 0 ,0).getEightChar().getMingGong());
  }

  @Test
  public void test26() {
    Lunar lunar = Solar.fromYmdHms(1986, 2, 16, 8, 0, 0).getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("命宫", "己亥", eightChar.getMingGong());
    Assert.assertEquals("身宫", "乙未", eightChar.getShenGong());
  }

  @Test
  public void test27() {
    Lunar lunar = Solar.fromYmdHms(1972, 11, 27, 10, 0, 0).getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("身宫", "乙巳", eightChar.getShenGong());
  }

  @Test
  public void test28() {
    Lunar lunar = Solar.fromYmdHms(1986, 5, 29, 13, 37, 0).getLunar();
    EightChar eightChar = lunar.getEightChar();
    Yun yun = eightChar.getYun(1, 2);
    Assert.assertEquals(2, yun.getStartYear());
    Assert.assertEquals(7, yun.getStartMonth());
    Assert.assertEquals(0, yun.getStartDay());
  }

  @Test
  public void test29() {
    List<Solar> l = Solar.fromBaZi("甲辰","丙寅","壬戌","壬子", 2);
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("2024-02-28 23:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void test30() {
    List<Solar> l = Solar.fromBaZi("甲辰","丙寅","癸亥","壬子", 1);
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("2024-02-29 00:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void test31() {
    List<Solar> l = Solar.fromBaZi("丁卯","丁未","甲申","乙丑", 1, 1900);
    List<String> actual = new ArrayList<String>();
    for (Solar solar : l) {
      actual.add(solar.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("1987-08-03 02:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void test32() {
    Lunar lunar = Solar.fromYmdHms(1980, 6, 15, 12, 30, 30).getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("身宫", "己丑", eightChar.getShenGong());
  }

  @Test
  public void test50() {
    List<Solar> solarTimes = Solar.fromBaZi("壬申", "壬寅", "庚辰", "甲申", 1, 1801);
    List<String> actual = new ArrayList<String>();
    for (Solar solarTime : solarTimes) {
      actual.add(solarTime.toYmdHms());
    }

    List<String> expected = new ArrayList<String>();
    expected.add("1812-02-18 16:00:00");
    expected.add("1992-03-05 15:00:00");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void test51() {
    Solar solar = new Solar(2013, 8, 8, 9, 30, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("时柱", "癸巳", eightChar.getTime());
  }
}
