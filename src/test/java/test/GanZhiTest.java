package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 干支测试
 */
public class GanZhiTest {

  @Test
  public void test(){
    Solar solar = new Solar(2020,1,1,13,22,0);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("己亥",lunar.getYearInGanZhi());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiExact());

    Assert.assertEquals("丙子",lunar.getMonthInGanZhi());
    Assert.assertEquals("丙子",lunar.getMonthInGanZhiExact());


    //小寒
    solar = new Solar(2020,1,6,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("己亥",lunar.getYearInGanZhi());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiExact());

    Assert.assertEquals("丁丑",lunar.getMonthInGanZhi());
    Assert.assertEquals("丁丑",lunar.getMonthInGanZhiExact());


    solar = new Solar(2020,1,20,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("己亥",lunar.getYearInGanZhi());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiExact());

    Assert.assertEquals("丁丑",lunar.getMonthInGanZhi());
    Assert.assertEquals("丁丑",lunar.getMonthInGanZhiExact());


    //春节
    solar = new Solar(2020,1,25,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiExact());

    Assert.assertEquals("丁丑",lunar.getMonthInGanZhi());
    Assert.assertEquals("丁丑",lunar.getMonthInGanZhiExact());


    solar = new Solar(2020,1,30,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiExact());

    Assert.assertEquals("丁丑",lunar.getMonthInGanZhi());
    Assert.assertEquals("丁丑",lunar.getMonthInGanZhiExact());


    solar = new Solar(2020,2,1,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiExact());

    Assert.assertEquals("丁丑",lunar.getMonthInGanZhi());
    Assert.assertEquals("丁丑",lunar.getMonthInGanZhiExact());

    solar = new Solar(2020,2,4,18,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiExact());

    Assert.assertEquals("戊寅",lunar.getMonthInGanZhi());
    Assert.assertEquals("戊寅",lunar.getMonthInGanZhiExact());


    solar = new Solar(2020,2,5,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiExact());

    Assert.assertEquals("戊寅",lunar.getMonthInGanZhi());
    Assert.assertEquals("戊寅",lunar.getMonthInGanZhiExact());


    solar = new Solar(2020,5,22,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiExact());

    Assert.assertEquals("辛巳",lunar.getMonthInGanZhi());
    Assert.assertEquals("辛巳",lunar.getMonthInGanZhiExact());


    solar = new Solar(2020,5,23,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiExact());

    Assert.assertEquals("辛巳",lunar.getMonthInGanZhi());
    Assert.assertEquals("辛巳",lunar.getMonthInGanZhiExact());

    solar = new Solar(2020,5,29,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiExact());

    Assert.assertEquals("辛巳",lunar.getMonthInGanZhi());
    Assert.assertEquals("辛巳",lunar.getMonthInGanZhiExact());

    solar = new Solar(2020,6,1,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiExact());

    Assert.assertEquals("辛巳",lunar.getMonthInGanZhi());
    Assert.assertEquals("辛巳",lunar.getMonthInGanZhiExact());

    solar = new Solar(2020,6,29,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiExact());

    Assert.assertEquals("壬午",lunar.getMonthInGanZhi());
    Assert.assertEquals("壬午",lunar.getMonthInGanZhiExact());

    solar = new Solar(2019,5,1,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("己亥",lunar.getYearInGanZhi());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiExact());

    Assert.assertEquals("戊辰",lunar.getMonthInGanZhi());
    Assert.assertEquals("戊辰",lunar.getMonthInGanZhiExact());

    solar = new Solar(1986,5,29,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("丙寅",lunar.getYearInGanZhi());
    Assert.assertEquals("丙寅",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("丙寅",lunar.getYearInGanZhiExact());

    Assert.assertEquals("癸巳",lunar.getMonthInGanZhi());
    Assert.assertEquals("癸巳",lunar.getMonthInGanZhiExact());

    solar = new Solar(1986,5,1,1,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("丙寅",lunar.getYearInGanZhi());
    Assert.assertEquals("丙寅",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("丙寅",lunar.getYearInGanZhiExact());

    Assert.assertEquals("壬辰",lunar.getMonthInGanZhi());
    Assert.assertEquals("壬辰",lunar.getMonthInGanZhiExact());

    solar = new Solar(1986,5,6,1,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("丙寅",lunar.getYearInGanZhi());
    Assert.assertEquals("丙寅",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("丙寅",lunar.getYearInGanZhiExact());

    Assert.assertEquals("癸巳",lunar.getMonthInGanZhi());
    Assert.assertEquals("壬辰",lunar.getMonthInGanZhiExact());

    solar = new Solar(1986,5,6,23,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("丙寅",lunar.getYearInGanZhi());
    Assert.assertEquals("丙寅",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("丙寅",lunar.getYearInGanZhiExact());

    Assert.assertEquals("癸巳",lunar.getMonthInGanZhi());
    Assert.assertEquals("癸巳",lunar.getMonthInGanZhiExact());
  }

  @Test
  public void test1() {
    Solar solar = new Solar(1996, 1, 16);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("壬子",lunar.getDayInGanZhi());
  }

  @Test
  public void test2() {
    Solar solar = new Solar(1997, 2, 16);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("己丑",lunar.getDayInGanZhi());
  }

  @Test
  public void test3() {
    Solar solar = new Solar(1998, 3, 16);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("壬戌",lunar.getDayInGanZhi());
  }

  @Test
  public void test4() {
    Solar solar = new Solar(1999, 4, 16);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("戊戌",lunar.getDayInGanZhi());
  }

  @Test
  public void test5() {
    Solar solar = new Solar(2000, 7, 16);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("乙亥",lunar.getDayInGanZhi());
  }

  @Test
  public void test6() {
    Solar solar = new Solar(2000, 1, 6);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("癸亥",lunar.getDayInGanZhi());
  }

  @Test
  public void test7() {
    Solar solar = new Solar(2000, 1, 9);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("丙寅",lunar.getDayInGanZhi());
  }

  @Test
  public void test8() {
    Solar solar = new Solar(2000, 2, 2);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("庚寅",lunar.getDayInGanZhi());
  }

  @Test
  public void test9() {
    Solar solar = new Solar(2012, 8, 5);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("戊戌",lunar.getDayInGanZhi());
  }

  @Test
  public void test10() {
    Solar solar = new Solar(2012, 9, 20);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("壬辰",lunar.getYearInGanZhi());
    Assert.assertEquals("己酉",lunar.getMonthInGanZhi());
    Assert.assertEquals("甲申",lunar.getDayInGanZhi());
  }

  @Test
  public void test11() {
    Solar solar = new Solar(2012, 10, 20);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("壬辰",lunar.getYearInGanZhi());
    Assert.assertEquals("庚戌",lunar.getMonthInGanZhi());
    Assert.assertEquals("甲寅",lunar.getDayInGanZhi());
  }

  @Test
  public void test12() {
    Solar solar = new Solar(2012, 11, 20);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("壬辰",lunar.getYearInGanZhi());
    Assert.assertEquals("辛亥",lunar.getMonthInGanZhi());
    Assert.assertEquals("乙酉",lunar.getDayInGanZhi());
  }

  @Test
  public void test13() {
    Solar solar = new Solar(2012, 12, 20);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("壬辰",lunar.getYearInGanZhi());
    Assert.assertEquals("壬子",lunar.getMonthInGanZhi());
    Assert.assertEquals("乙卯",lunar.getDayInGanZhi());
  }

  @Test
  public void test14() {
    Solar solar = new Solar(2012, 12, 27);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("壬辰",lunar.getYearInGanZhi());
    Assert.assertEquals("壬子",lunar.getMonthInGanZhi());
    Assert.assertEquals("壬戌",lunar.getDayInGanZhi());
  }

  @Test
  public void test15() {
    Solar solar = new Solar(1988, 2, 15);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("丁卯",lunar.getYearInGanZhi());
  }

  @Test
  public void test16() {
    Solar solar = new Solar(1988, 2, 15, 23, 30,0);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("丁卯",lunar.getYearInGanZhi());
    Assert.assertEquals("戊辰",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("戊辰",lunar.getYearInGanZhiExact());
  }

  @Test
  public void test17() {
    Solar solar = new Solar(2019, 2, 8, 13, 22, 0);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("己亥", lunar.getYearInGanZhi());
    Assert.assertEquals("己亥", lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("己亥", lunar.getYearInGanZhiExact());

    Assert.assertEquals("丙寅", lunar.getMonthInGanZhi());
    Assert.assertEquals("丙寅", lunar.getMonthInGanZhiExact());
  }

  @Test
  public void test18() {
    Solar solar = new Solar(2020,2,4,13,22,0);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiExact());

    Assert.assertEquals("戊寅",lunar.getMonthInGanZhi());
    Assert.assertEquals("丁丑",lunar.getMonthInGanZhiExact());
  }

  @Test
  public void test19() {
    Solar solar = new Solar(2022,4,6,20,18,0);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("壬寅",lunar.getYearInGanZhi());
    Assert.assertEquals("壬寅",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("壬寅",lunar.getYearInGanZhiExact());

    Assert.assertEquals("甲辰",lunar.getMonthInGanZhi());
    Assert.assertEquals("甲辰",lunar.getMonthInGanZhiExact());

    Assert.assertEquals("己丑",lunar.getDayInGanZhi());
    Assert.assertEquals("己丑",lunar.getDayInGanZhiExact());
    Assert.assertEquals("己丑",lunar.getDayInGanZhiExact2());

    Assert.assertEquals("甲戌",lunar.getTimeInGanZhi());
  }

}
