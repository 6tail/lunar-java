package test;

import com.nlf.calendar.SolarWeek;
import org.junit.Assert;
import org.junit.Test;

/**
 * 阳历周测试
 *
 * @author 6tail
 */
public class SolarWeekTest {

  @Test
  public void test() {
    SolarWeek week = SolarWeek.fromYmd(2022, 5, 1, 0);
    Assert.assertEquals(1, week.getIndex());
  }

  @Test
  public void test1() {
    SolarWeek week = SolarWeek.fromYmd(2022, 5, 7, 0);
    Assert.assertEquals(1, week.getIndex());
  }

  @Test
  public void test2() {
    SolarWeek week = SolarWeek.fromYmd(2022, 5, 8, 0);
    Assert.assertEquals(2, week.getIndex());
  }

  @Test
  public void test3() {
    SolarWeek week = SolarWeek.fromYmd(2022, 5, 1, 1);
    Assert.assertEquals(1, week.getIndex());
  }

  @Test
  public void test4() {
    SolarWeek week = SolarWeek.fromYmd(2022, 5, 2, 1);
    Assert.assertEquals(2, week.getIndex());
  }

  @Test
  public void test5() {
    SolarWeek week = SolarWeek.fromYmd(2022, 5, 8, 1);
    Assert.assertEquals(2, week.getIndex());
  }

  @Test
  public void test6() {
    SolarWeek week = SolarWeek.fromYmd(2021, 11, 1, 0);
    Assert.assertEquals(1, week.getIndex());
  }

  @Test
  public void test7() {
    SolarWeek week = SolarWeek.fromYmd(2021, 11, 1, 1);
    Assert.assertEquals(1, week.getIndex());
  }

  @Test
  public void test8() {
    SolarWeek week = SolarWeek.fromYmd(2021, 5, 2, 2);
    Assert.assertEquals(1, week.getIndex());
  }

  @Test
  public void test9() {
    SolarWeek week = SolarWeek.fromYmd(2021, 5, 4, 2);
    Assert.assertEquals(2, week.getIndex());
  }

  @Test
  public void test10() {
    SolarWeek week = SolarWeek.fromYmd(2022, 3, 6, 0);
    Assert.assertEquals(11, week.getIndexInYear());
  }

  @Test
  public void test11() {
    SolarWeek week = SolarWeek.fromYmd(2022, 3, 6, 1);
    Assert.assertEquals(10, week.getIndexInYear());
  }

}
