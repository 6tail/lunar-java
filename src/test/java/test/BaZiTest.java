package test;

import com.nlf.calendar.EightChar;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

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
    Assert.assertEquals("年柱地势", "临官", eightChar.getYearDiShi());
    Assert.assertEquals("月柱地势", "长生", eightChar.getMonthDiShi());
    Assert.assertEquals("日柱地势", "死", eightChar.getDayDiShi());
    Assert.assertEquals("时柱地势", "墓", eightChar.getTimeDiShi());
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

}
