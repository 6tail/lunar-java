package test;

import com.nlf.calendar.Solar;
import com.nlf.calendar.SolarWeek;
import com.nlf.calendar.util.SolarUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * 周测试
 *
 * @author 6tail
 */
public class WeekTest {

  @Test
  public void testFromMonday(){
    //一周的开始从星期一开始计
    int start = 1;
    SolarWeek week = new SolarWeek(2019,5,1,start);
    Assert.assertEquals("2019.5.1",week.toString());
    Assert.assertEquals("2019年5月第1周",week.toFullString());
    //当月共几周
    Assert.assertEquals(5,SolarUtil.getWeeksOfMonth(week.getYear(),week.getMonth(),start));
    //当周第一天
    Assert.assertEquals("2019-04-29",week.getFirstDay().toString());
    //当周第一天（本月）
    Assert.assertEquals("2019-05-01",week.getFirstDayInMonth().toString());
  }

  @Test
  public void testFromSunday(){
    //一周的开始从星期日开始计
    int start = 0;
    SolarWeek week = new SolarWeek(2019,5,1,start);
    Assert.assertEquals("2019.5.1",week.toString());
    Assert.assertEquals("2019年5月第1周",week.toFullString());
    //当月共几周
    Assert.assertEquals(5,SolarUtil.getWeeksOfMonth(week.getYear(),week.getMonth(),start));
    //当周第一天
    Assert.assertEquals("2019-04-28",week.getFirstDay().toString());
    //当周第一天（本月）
    Assert.assertEquals("2019-05-01",week.getFirstDayInMonth().toString());
  }

  @Test
  public void test1(){
    Solar solar = Solar.fromYmd(1582, 10, 1);
    Assert.assertEquals(1, solar.getWeek());
  }

  @Test
  public void test2(){
    Solar solar = Solar.fromYmd(1582, 10, 15);
    Assert.assertEquals(5, solar.getWeek());
  }

  @Test
  public void test3(){
    Solar solar = Solar.fromYmd(1129, 11, 17);
    Assert.assertEquals(0, solar.getWeek());
  }

  @Test
  public void test4(){
    Solar solar = Solar.fromYmd(1129, 11, 1);
    Assert.assertEquals(5, solar.getWeek());
  }

  @Test
  public void test5(){
    Solar solar = Solar.fromYmd(8, 11, 1);
    Assert.assertEquals(4, solar.getWeek());
  }

  @Test
  public void test6(){
    Solar solar = Solar.fromYmd(1582, 9, 30);
    Assert.assertEquals(0, solar.getWeek());
  }

  @Test
  public void test7(){
    Solar solar = Solar.fromYmd(1582, 1, 1);
    Assert.assertEquals(1, solar.getWeek());
  }

  @Test
  public void test8(){
    Solar solar = Solar.fromYmd(1500, 2, 29);
    Assert.assertEquals(6, solar.getWeek());
  }

  @Test
  public void test9(){
    Solar solar = Solar.fromYmd(9865, 7, 26);
    Assert.assertEquals(3, solar.getWeek());
  }

  @Test
  public void test10(){
    Assert.assertEquals(6, Solar.fromYmd(1961, 9, 30).getWeek());
    Assert.assertEquals(6, Solar.fromYmdHms(1961, 9, 30, 23, 59, 59).getWeek());
    Assert.assertEquals(6, Solar.fromYmdHms(1961, 9, 30, 20, 0, 0).getWeek());
  }

  @Test
  public void test11(){
    Assert.assertEquals(3, Solar.fromYmdHms(2021, 9, 15, 20, 0, 0).getWeek());
  }

}
