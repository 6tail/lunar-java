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


    solar = new Solar(2020,2,4,13,22,0);
    lunar = solar.getLunar();
    Assert.assertEquals("庚子",lunar.getYearInGanZhi());
    Assert.assertEquals("庚子",lunar.getYearInGanZhiByLiChun());
    Assert.assertEquals("己亥",lunar.getYearInGanZhiExact());

    Assert.assertEquals("戊寅",lunar.getMonthInGanZhi());
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

}
