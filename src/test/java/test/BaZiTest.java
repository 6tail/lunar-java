package test;

import com.nlf.calendar.EightChar;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

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
  public void testBaZi2Solar() {
    Solar solar = Solar.fromYmdHms(2027,1,27,12,0,0);
    System.out.println(solar.getLunar().getEightChar().toString());
    List<Solar> l = Solar.fromBaZi("丙午","辛丑","丙午","甲午");
    for(Solar s:l){
      System.out.println(s.toFullString());
    }
  }

}
