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

}
