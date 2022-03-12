package test;

import com.nlf.calendar.EightChar;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.eightchar.Yun;
import org.junit.Assert;
import org.junit.Test;

/**
 * 起运测试
 */
public class YunTest {

  @Test
  public void test1() {
    Solar solar = new Solar(1981, 1, 29, 23, 37, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Yun yun = eightChar.getYun(0);
    Assert.assertEquals("起运年数", 8, yun.getStartYear());
    Assert.assertEquals("起运月数", 0, yun.getStartMonth());
    Assert.assertEquals("起运天数", 20, yun.getStartDay());
    Assert.assertEquals("起运阳历", "1989-02-18", yun.getStartSolar().toYmd());
  }

  @Test
  public void test2() {
    Lunar lunar = new Lunar(2019, 12, 12, 11, 22, 0);
    System.out.println(lunar.getMonth());
    System.out.println(lunar.toFullString());
    System.out.println(lunar.getSolar().toFullString());
    EightChar eightChar = lunar.getEightChar();
    Yun yun = eightChar.getYun(1);
    Assert.assertEquals("起运年数", 0, yun.getStartYear());
    Assert.assertEquals("起运月数", 1, yun.getStartMonth());
    Assert.assertEquals("起运天数", 0, yun.getStartDay());
    Assert.assertEquals("起运阳历", "2020-02-06", yun.getStartSolar().toYmd());
  }

  @Test
  public void test3() {
    Solar solar = new Solar(2020, 1, 6, 11, 22, 0);
    Lunar lunar = solar.getLunar();
    System.out.println(lunar.getMonth());
    System.out.println(lunar.toFullString());
    System.out.println(lunar.getSolar().toFullString());
    EightChar eightChar = lunar.getEightChar();
    Yun yun = eightChar.getYun(1);
    Assert.assertEquals("起运年数", 0, yun.getStartYear());
    Assert.assertEquals("起运月数", 1, yun.getStartMonth());
    Assert.assertEquals("起运天数", 0, yun.getStartDay());
    Assert.assertEquals("起运阳历", "2020-02-06", yun.getStartSolar().toYmd());
  }

  @Test
  public void test4() {
    Solar solar = new Solar(2022, 3, 9, 20, 51, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Yun yun = eightChar.getYun(1);
    Assert.assertEquals("起运阳历", "2030-12-19", yun.getStartSolar().toYmd());
  }

  @Test
  public void test5() {
    Solar solar = new Solar(2022, 3, 9, 20, 51, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Yun yun = eightChar.getYun(1, 2);
    Assert.assertEquals("起运年数", 8, yun.getStartYear());
    Assert.assertEquals("起运月数", 9, yun.getStartMonth());
    Assert.assertEquals("起运天数", 2, yun.getStartDay());
    Assert.assertEquals("起运阳历", "2030-12-12", yun.getStartSolar().toYmd());
  }

  @Test
  public void test6() {
    Solar solar = new Solar(2018, 6, 11, 9, 30, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Yun yun = eightChar.getYun(0, 2);
    Assert.assertEquals("起运阳历", "2020-03-21", yun.getStartSolar().toYmd());
  }

}
