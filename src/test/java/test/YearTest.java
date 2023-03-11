package test;

import com.nlf.calendar.LunarYear;
import com.nlf.calendar.SolarYear;
import org.junit.Assert;
import org.junit.Test;

/**
 * 年份测试
 *
 * @author 6tail
 */
public class YearTest {

  @Test
  public void test(){
    SolarYear year = new SolarYear(2019);
    Assert.assertEquals("2019",year.toString());
    Assert.assertEquals("2019年",year.toFullString());

    Assert.assertEquals("2020",year.next(1).toString());
    Assert.assertEquals("2020年",year.next(1).toFullString());
  }

  @Test
  public void test1(){
    LunarYear year = new LunarYear(2017);
    Assert.assertEquals("二龙治水",year.getZhiShui());

    year = new LunarYear(2018);
    Assert.assertEquals("二龙治水",year.getZhiShui());

    year = new LunarYear(2019);
    Assert.assertEquals("八龙治水",year.getZhiShui());

    year = new LunarYear(5);
    Assert.assertEquals("三龙治水",year.getZhiShui());
  }

  @Test
  public void test2(){
    LunarYear year = new LunarYear(2017);
    Assert.assertEquals("二人分饼",year.getFenBing());

    year = new LunarYear(2018);
    Assert.assertEquals("八人分饼",year.getFenBing());

    year = new LunarYear(5);
    Assert.assertEquals("一人分饼",year.getFenBing());
  }

  @Test
  public void test3(){
    LunarYear year = new LunarYear(2021);
    Assert.assertEquals("十一牛耕田",year.getGengTian());
  }

  @Test
  public void test4(){
    LunarYear year = new LunarYear(2018);
    Assert.assertEquals("三日得金",year.getDeJin());
  }

  @Test
  public void test5(){
    LunarYear year = new LunarYear(1864);
    Assert.assertEquals("上元",year.getYuan());
  }

  @Test
  public void test6(){
    LunarYear year = new LunarYear(1923);
    Assert.assertEquals("上元",year.getYuan());
  }

  @Test
  public void test7(){
    LunarYear year = new LunarYear(1924);
    Assert.assertEquals("中元",year.getYuan());
  }

  @Test
  public void test8(){
    LunarYear year = new LunarYear(1983);
    Assert.assertEquals("中元",year.getYuan());
  }

  @Test
  public void test9(){
    LunarYear year = new LunarYear(1984);
    Assert.assertEquals("下元",year.getYuan());
  }

  @Test
  public void test10(){
    LunarYear year = new LunarYear(2043);
    Assert.assertEquals("下元",year.getYuan());
  }

  @Test
  public void test11(){
    LunarYear year = new LunarYear(1864);
    Assert.assertEquals("一运",year.getYun());
  }

  @Test
  public void test12(){
    LunarYear year = new LunarYear(1883);
    Assert.assertEquals("一运",year.getYun());
  }

  @Test
  public void test13(){
    LunarYear year = new LunarYear(1884);
    Assert.assertEquals("二运",year.getYun());
  }

  @Test
  public void test14(){
    LunarYear year = new LunarYear(1903);
    Assert.assertEquals("二运",year.getYun());
  }

  @Test
  public void test15(){
    LunarYear year = new LunarYear(1904);
    Assert.assertEquals("三运",year.getYun());
  }

  @Test
  public void test16(){
    LunarYear year = new LunarYear(1923);
    Assert.assertEquals("三运",year.getYun());
  }

  @Test
  public void test17(){
    LunarYear year = new LunarYear(2004);
    Assert.assertEquals("八运",year.getYun());
  }

  @Test
  public void test18(){
    LunarYear year = new LunarYear(2023);
    Assert.assertEquals(384, year.getDayCount());
  }

  @Test
  public void test19(){
    LunarYear year = new LunarYear(1517);
    Assert.assertEquals(384, year.getDayCount());
  }

  @Test
  public void test20(){
    LunarYear year = new LunarYear(1518);
    Assert.assertEquals(355, year.getDayCount());
  }

  @Test
  public void test21(){
    LunarYear year = new LunarYear(2022);
    Assert.assertEquals(355, year.getDayCount());
  }

  @Test
  public void test22(){
    LunarYear year = new LunarYear(2021);
    Assert.assertEquals(354, year.getDayCount());
  }

}
