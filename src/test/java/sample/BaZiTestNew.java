package sample;

import com.nlf.calendar.EightChar;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.eightchar.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 八字测试
 *
 * @author 6tail
 */
public class BaZiTestNew {

  @Test
  public void test1() {
    Solar solar = new Solar(2020, 1, 1, 22, 35, 0);
    Lunar lunar = solar.getLunar();
    //己亥 丙子 癸卯 癸亥
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void test2() {
    Solar solar = new Solar(2020, 1, 6, 14, 35, 0);
    Lunar lunar = solar.getLunar();
    //己亥, 丁丑, 戊申, 己未]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void test3() {
    Solar solar = new Solar(2020, 1, 6, 3, 35, 0);
    Lunar lunar = solar.getLunar();
    //己亥, 丁丑, 戊辰, 癸亥]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void test4() {
    Solar solar = new Solar(2020, 1, 26, 21, 41, 0);
    Lunar lunar = solar.getLunar();
    //己亥, 丙子, 戊申, 甲寅]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void test5() {
    Solar solar = new Solar(2020, 2, 4, 1, 42, 0);
    Lunar lunar = solar.getLunar();
    //己亥, 丁丑, 丁丑, 辛丑]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void test6() {
    Solar solar = new Solar(2020, 2, 5, 21, 43, 0);
    Lunar lunar = solar.getLunar();
    //庚子, 戊寅, 戊寅, 癸亥]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void testBazi2Solar6() {
    List<Solar> l = Solar.fromBaZi("庚子", "戊寅", "戊寅", "癸亥");
    // [2020-02-05 22:00:00, 1960-02-20 22:00:00]
    for (Solar solar : l) {
      System.out.println(solar.toFullString());
    }
  }

  @Test
  public void test7() {
    Solar solar = new Solar(2020, 5, 26, 23, 43, 0);
    Lunar lunar = solar.getLunar();
    // [庚子, 辛巳, 庚午, 丙子]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void testBazi2Solar7() {
    List<Solar> l = Solar.fromBaZi("庚子", "辛巳", "庚午", "丙子");
    // [2020-05-26 23:00:00, 2020-05-27 00:00:00]
    for (Solar solar : l) {
      System.out.println(solar.toFullString());
    }
  }

  @Test
  public void testBazi2Solar() {
    List<Solar> l = Solar.fromBaZi("庚子", "癸未", "乙丑", "丁亥");
    // [2020-07-21 22:00:00, 1960-08-05 22:00:00]
    for (Solar solar : l) {
      System.out.println(solar.toFullString());
    }
  }

  @Test
  public void testBazi2Solar2() {
    List<Solar> l = Solar.fromBaZi("庚子", "戊子", "己卯", "庚午");
    // [1960-12-17 12:00:00, 1901-01-01 12:00:00]
    for (Solar solar : l) {
      System.out.println(solar.toFullString());
    }
  }

  @Test
  public void testBazi2Solar3() {
    List<Solar> l = Solar.fromBaZi("辛丑", "丁酉", "丙寅", "戊戌");
    // [2021-09-15 20:00:00 星期三 处女座, 1961-09-30 20:00:00 星期六 天秤座]
    for (Solar solar : l) {
      System.out.println(solar.toFullString());
    }
  }

  @Test
  public void testBaziShiShenZhi() {
    Solar solar = new Solar(2020, 1, 1, 22, 35, 0);
    Lunar lunar = solar.getLunar();
    //己亥 丙子 癸卯 癸亥
    System.out.println(lunar.getEightChar());
    //七杀
    System.out.println(lunar.getEightChar().getYearShiShenGan());
    //正财
    System.out.println(lunar.getEightChar().getMonthShiShenGan());
    //日主
    System.out.println(lunar.getEightChar().getDayShiShenGan());
    //比肩
    System.out.println(lunar.getEightChar().getTimeShiShenGan());

    //[劫财, 伤官]
    System.out.println(lunar.getEightChar().getYearShiShenZhi());
    //[比肩]
    System.out.println(lunar.getEightChar().getMonthShiShenZhi());
    //[食神]
    System.out.println(lunar.getEightChar().getDayShiShenZhi());
    //[劫财, 伤官]
    System.out.println(lunar.getEightChar().getTimeShiShenZhi());
  }

  /**
   * 起运
   */
  @Test
  public void testQiYun() {
    Solar solar = new Solar(1983, 2, 15, 20, 0, 0);
    Lunar lunar = solar.getLunar();
    EightChar bazi = lunar.getEightChar();
    Yun yun = bazi.getYun(0);
    Assert.assertEquals(6, yun.getStartYear());
    Assert.assertEquals(2, yun.getStartMonth());
    Assert.assertEquals(20, yun.getStartDay());
    Assert.assertEquals("1989-05-05", yun.getStartSolar().toYmd());

    solar = new Solar(2013, 7, 13, 16, 17, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(0);
    Assert.assertEquals(8, yun.getStartYear());
    Assert.assertEquals(4, yun.getStartMonth());
    Assert.assertEquals(0, yun.getStartDay());
    Assert.assertEquals("2021-11-13", yun.getStartSolar().toYmd());

    solar = new Solar(2020, 8, 18, 10, 0, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(0);
    Assert.assertEquals(3, yun.getStartYear());
    Assert.assertEquals(8, yun.getStartMonth());
    Assert.assertEquals(0, yun.getStartDay());
    Assert.assertEquals("2024-04-18", yun.getStartSolar().toYmd());

    solar = new Solar(1972, 6, 15, 0, 0, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(1);
    Assert.assertEquals(7, yun.getStartYear());
    Assert.assertEquals(5, yun.getStartMonth());
    Assert.assertEquals(10, yun.getStartDay());
    Assert.assertEquals("1979-11-25", yun.getStartSolar().toYmd());

    solar = new Solar(1968, 11, 22, 0, 0, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(1);
    Assert.assertEquals(5, yun.getStartYear());
    Assert.assertEquals(1, yun.getStartMonth());
    Assert.assertEquals(20, yun.getStartDay());
    Assert.assertEquals("1974-01-11", yun.getStartSolar().toYmd());

    solar = new Solar(1968, 11, 23, 0, 0, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(1);
    Assert.assertEquals(4, yun.getStartYear());
    Assert.assertEquals(9, yun.getStartMonth());
    Assert.assertEquals(20, yun.getStartDay());
    Assert.assertEquals("1973-09-12", yun.getStartSolar().toYmd());

    solar = new Solar(1992, 2, 2, 12, 0, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(1);
    Assert.assertEquals(9, yun.getStartYear());
    Assert.assertEquals(0, yun.getStartMonth());
    Assert.assertEquals(10, yun.getStartDay());
    Assert.assertEquals("2001-02-12", yun.getStartSolar().toYmd());
  }

  /**
   * 大运
   */
  @Test
  public void testDaYun() {
    int[] startYears = {1983, 1989, 1999, 2009, 2019, 2029, 2039, 2049, 2059, 2069};
    int[] endYears = {1988, 1998, 2008, 2018, 2028, 2038, 2048, 2058, 2068, 2078};
    int[] startAges = {1, 7, 17, 27, 37, 47, 57, 67, 77, 87};
    int[] endAges = {6, 16, 26, 36, 46, 56, 66, 76, 86, 96};
    String[] yearGanZhi = {"", "乙卯", "丙辰", "丁巳", "戊午", "己未", "庚申", "辛酉", "壬戌", "癸亥"};
    Solar solar = new Solar(1983, 2, 15, 20, 0, 0);
    Lunar lunar = solar.getLunar();
    EightChar bazi = lunar.getEightChar();
    Yun yun = bazi.getYun(0);
    DaYun[] l = yun.getDaYun();
    for (int i = 0, j = l.length; i < j; i++) {
      DaYun daYun = l[i];
      Assert.assertEquals(startYears[i], daYun.getStartYear());
      Assert.assertEquals(endYears[i], daYun.getEndYear());
      Assert.assertEquals(startAges[i], daYun.getStartAge());
      Assert.assertEquals(endAges[i], daYun.getEndAge());
      Assert.assertEquals(yearGanZhi[i], daYun.getGanZhi());
    }

    startYears = new int[]{1992, 2001, 2011, 2021, 2031, 2041, 2051, 2061, 2071, 2081};
    endYears = new int[]{2000, 2010, 2020, 2030, 2040, 2050, 2060, 2070, 2080, 2090};
    startAges = new int[]{1, 10, 20, 30, 40, 50, 60, 70, 80, 90};
    endAges = new int[]{9, 19, 29, 39, 49, 59, 69, 79, 89, 99};
    yearGanZhi = new String[]{"", "庚子", "己亥", "戊戌", "丁酉", "丙申", "乙未", "甲午", "癸巳", "壬辰"};
    solar = new Solar(1992, 2, 2, 12, 0, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(1);
    l = yun.getDaYun();
    for (int i = 0, j = l.length; i < j; i++) {
      DaYun daYun = l[i];
      Assert.assertEquals(startYears[i], daYun.getStartYear());
      Assert.assertEquals(endYears[i], daYun.getEndYear());
      Assert.assertEquals(startAges[i], daYun.getStartAge());
      Assert.assertEquals(endAges[i], daYun.getEndAge());
      Assert.assertEquals(yearGanZhi[i], daYun.getGanZhi());
    }
  }

  /**
   * 流年
   */
  @Test
  public void testLiuNian() {
    Solar solar = new Solar(1983, 2, 15, 20, 0, 0);
    Lunar lunar = solar.getLunar();
    EightChar bazi = lunar.getEightChar();
    Yun yun = bazi.getYun(0);
    DaYun[] daYun = yun.getDaYun();

    int[] years = {1983, 1984, 1985, 1986, 1987, 1988};
    int[] ages = {1, 2, 3, 4, 5, 6};
    String[] ganZhi = {"癸亥", "甲子", "乙丑", "丙寅", "丁卯", "戊辰"};
    LiuNian[] l = daYun[0].getLiuNian();
    for (int i = 0, j = l.length; i < j; i++) {
      LiuNian liuNian = l[i];
      Assert.assertEquals(years[i], liuNian.getYear());
      Assert.assertEquals(ages[i], liuNian.getAge());
      Assert.assertEquals(ganZhi[i], liuNian.getGanZhi());
    }

    years = new int[]{2029, 2030, 2031, 2032, 2033, 2034, 2035, 2036, 2037, 2038};
    ages = new int[]{47, 48, 49, 50, 51, 52, 53, 54, 55, 56};
    ganZhi = new String[]{"己酉", "庚戌", "辛亥", "壬子", "癸丑", "甲寅", "乙卯", "丙辰", "丁巳", "戊午"};
    l = daYun[5].getLiuNian();
    for (int i = 0, j = l.length; i < j; i++) {
      LiuNian liuNian = l[i];
      Assert.assertEquals(years[i], liuNian.getYear());
      Assert.assertEquals(ages[i], liuNian.getAge());
      Assert.assertEquals(years[i] + "年", ganZhi[i], liuNian.getGanZhi());
    }

    solar = new Solar(1981, 8, 16, 9, 30, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(1);
    daYun = yun.getDaYun();

    years = new int[]{1981, 1982, 1983};
    ages = new int[]{1, 2, 3};
    ganZhi = new String[]{"辛酉","壬戌","癸亥"};
    l = daYun[0].getLiuNian();
    for (int i = 0, j = l.length; i < j; i++) {
      LiuNian liuNian = l[i];
      Assert.assertEquals(years[i], liuNian.getYear());
      Assert.assertEquals(ages[i], liuNian.getAge());
      Assert.assertEquals(ganZhi[i], liuNian.getGanZhi());
    }

    years = new int[]{1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993};
    ages = new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    ganZhi = new String[]{"甲子","乙丑","丙寅","丁卯","戊辰","己巳","庚午","辛未","壬申","癸酉"};
    l = daYun[1].getLiuNian();
    for (int i = 0, j = l.length; i < j; i++) {
      LiuNian liuNian = l[i];
      Assert.assertEquals(years[i], liuNian.getYear());
      Assert.assertEquals(ages[i], liuNian.getAge());
      Assert.assertEquals(ganZhi[i], liuNian.getGanZhi());
    }

    solar = new Solar(1981, 8, 8, 1, 30, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(1);
    daYun = yun.getDaYun();

    l = daYun[0].getLiuNian();
    Assert.assertEquals(0, l.length);

    years = new int[]{1981,1982, 1983,1984, 1985, 1986, 1987, 1988, 1989, 1990};
    ages = new int[]{1,2,3,4,5,6,7,8,9,10};
    ganZhi = new String[]{"辛酉","壬戌","癸亥","甲子","乙丑","丙寅","丁卯","戊辰","己巳","庚午"};
    l = daYun[1].getLiuNian();
    for (int i = 0, j = l.length; i < j; i++) {
      LiuNian liuNian = l[i];
      Assert.assertEquals(years[i], liuNian.getYear());
      Assert.assertEquals(ages[i], liuNian.getAge());
      Assert.assertEquals(ganZhi[i], liuNian.getGanZhi());
    }

    solar = new Solar(1992, 2, 2, 12, 0, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(1);
    daYun = yun.getDaYun();

    years = new int[]{1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000};
    ages = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    ganZhi = new String[]{"壬申","癸酉","甲戌", "乙亥", "丙子", "丁丑", "戊寅", "己卯", "庚辰"};
    l = daYun[0].getLiuNian();
    for (int i = 0, j = l.length; i < j; i++) {
      LiuNian liuNian = l[i];
      Assert.assertEquals(years[i], liuNian.getYear());
      Assert.assertEquals(ages[i], liuNian.getAge());
      Assert.assertEquals(ganZhi[i], liuNian.getGanZhi());
    }
  }

  /**
   * 小运
   */
  @Test
  public void testXiaoYun() {
    Solar solar = new Solar(1983, 2, 15, 20, 0, 0);
    Lunar lunar = solar.getLunar();
    EightChar bazi = lunar.getEightChar();
    Yun yun = bazi.getYun(0);
    DaYun[] daYun = yun.getDaYun();

    int[] years = {1983, 1984, 1985, 1986, 1987, 1988};
    int[] ages = {1, 2, 3, 4, 5, 6};
    String[] ganZhi = {"乙亥", "丙子", "丁丑", "戊寅", "己卯", "庚辰"};
    XiaoYun[] l = daYun[0].getXiaoYun();
    for (int i = 0, j = l.length; i < j; i++) {
      XiaoYun xiaoYun = l[i];
      Assert.assertEquals(years[i], xiaoYun.getYear());
      Assert.assertEquals(ages[i], xiaoYun.getAge());
      Assert.assertEquals(ganZhi[i], xiaoYun.getGanZhi());
    }

    years = new int[]{2029, 2030, 2031, 2032, 2033, 2034, 2035, 2036, 2037, 2038};
    ages = new int[]{47, 48, 49, 50, 51, 52, 53, 54, 55, 56};
    ganZhi = new String[]{"辛酉", "壬戌", "癸亥", "甲子", "乙丑", "丙寅", "丁卯", "戊辰", "己巳", "庚午"};
    l = daYun[5].getXiaoYun();
    for (int i = 0, j = l.length; i < j; i++) {
      XiaoYun xiaoYun = l[i];
      Assert.assertEquals(years[i], xiaoYun.getYear());
      Assert.assertEquals(ages[i], xiaoYun.getAge());
      Assert.assertEquals(years[i] + "年", ganZhi[i], xiaoYun.getGanZhi());
    }

    solar = new Solar(1992, 2, 2, 12, 0, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(1);
    daYun = yun.getDaYun();

    ganZhi = new String[]{"丁巳", "丙辰", "乙卯", "甲寅", "癸丑", "壬子","辛亥","庚戌","己酉"};
    l = daYun[0].getXiaoYun();
    for (int i = 0, j = l.length; i < j; i++) {
      XiaoYun xiaoYun = l[i];
      Assert.assertEquals(ganZhi[i], xiaoYun.getGanZhi());
    }
  }

  /**
   * 流月
   */
  @Test
  public void testLiuYue() {
    Solar solar = new Solar(1983, 2, 15, 20, 0, 0);
    Lunar lunar = solar.getLunar();
    EightChar bazi = lunar.getEightChar();
    Yun yun = bazi.getYun(0);
    DaYun[] daYun = yun.getDaYun();

    String[] ganZhi = {"甲寅", "乙卯", "丙辰", "丁巳", "戊午", "己未", "庚申", "辛酉", "壬戌", "癸亥", "甲子", "乙丑"};
    LiuNian[] liuNian = daYun[0].getLiuNian();
    LiuYue[] l = liuNian[0].getLiuYue();
    for (int i = 0, j = l.length; i < j; i++) {
      LiuYue liuYue = l[i];
      Assert.assertEquals(ganZhi[i], liuYue.getGanZhi());
    }

    ganZhi = new String[]{"庚寅", "辛卯", "壬辰", "癸巳", "甲午", "乙未", "丙申", "丁酉", "戊戌", "己亥", "庚子", "辛丑"};
    liuNian = daYun[4].getLiuNian();
    l = liuNian[2].getLiuYue();
    for (int i = 0, j = l.length; i < j; i++) {
      LiuYue liuYue = l[i];
      Assert.assertEquals(ganZhi[i], liuYue.getGanZhi());
    }


    solar = new Solar(1992, 2, 2, 12, 0, 0);
    lunar = solar.getLunar();
    bazi = lunar.getEightChar();
    yun = bazi.getYun(1);
    daYun = yun.getDaYun();

    ganZhi = new String[]{"壬寅", "癸卯", "甲辰", "乙巳", "丙午", "丁未", "戊申", "己酉", "庚戌", "辛亥", "壬子", "癸丑"};
    liuNian = daYun[0].getLiuNian();
    l = liuNian[0].getLiuYue();
    for (int i = 0, j = l.length; i < j; i++) {
      LiuYue liuYue = l[i];
      Assert.assertEquals(ganZhi[i], liuYue.getGanZhi());
    }
  }

}
