package test;

import com.nlf.calendar.LunarMonth;
import org.junit.Assert;
import org.junit.Test;

/**
 * 农历月测试
 *
 * @author 6tail
 */
public class LunarMonthTest {

  @Test
  public void test(){
    LunarMonth month = LunarMonth.fromYm(2023, 1);
    Assert.assertEquals(1, month.getIndex());
    Assert.assertEquals("甲寅", month.getGanZhi());
  }

  @Test
  public void test1(){
    LunarMonth month = LunarMonth.fromYm(2023, -2);
    Assert.assertEquals(3, month.getIndex());
    Assert.assertEquals("乙卯", month.getGanZhi());
  }

  @Test
  public void test2(){
    LunarMonth month = LunarMonth.fromYm(2023, 3);
    Assert.assertEquals(4, month.getIndex());
    Assert.assertEquals("丙辰", month.getGanZhi());
  }

  @Test
  public void test3(){
    LunarMonth month = LunarMonth.fromYm(2024, 1);
    Assert.assertEquals(1, month.getIndex());
    Assert.assertEquals("丙寅", month.getGanZhi());
  }

  @Test
  public void test4(){
    LunarMonth month = LunarMonth.fromYm(2023, 12);
    Assert.assertEquals(13, month.getIndex());
    Assert.assertEquals("乙丑", month.getGanZhi());
  }

  @Test
  public void test5(){
    LunarMonth month = LunarMonth.fromYm(2022, 1);
    Assert.assertEquals(1, month.getIndex());
    Assert.assertEquals("壬寅", month.getGanZhi());
  }

  @Test
  public void test6(){
    LunarMonth month = LunarMonth.fromYm(2023, 9);
    Assert.assertEquals("壬戌", month.getGanZhi());
  }

}
