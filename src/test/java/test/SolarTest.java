package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.util.SolarUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * 阳历测试
 *
 * @author 6tail
 */
public class SolarTest {

  @Test
  public void test(){
    Solar date = new Solar(2019,5,1);
    Assert.assertEquals("2019-05-01",date.toString());
    Assert.assertEquals("2019-05-01 00:00:00 星期三 (劳动节) 金牛座",date.toFullString());
    Assert.assertEquals("二〇一九年三月廿七",date.getLunar().toString());
    Assert.assertEquals("二〇一九年三月廿七 己亥(猪)年 戊辰(龙)月 戊戌(狗)日 子(鼠)时 纳音[平地木 大林木 平地木 桑柘木] 星期三 西方白虎 星宿[参水猿](吉) 彭祖百忌[戊不受田田主不祥 戌不吃犬作怪上床] 喜神方位[巽](东南) 阳贵神方位[艮](东北) 阴贵神方位[坤](西南) 福神方位[艮](东北) 财神方位[坎](正北) 冲[(壬辰)龙] 煞[北]",date.getLunar().toFullString());
  }

  @Test
  public void test1(){
    Solar solar = new Solar(2020,5,24,13,0,0);
    Assert.assertEquals("二〇二〇年闰四月初二",solar.getLunar().toString());
  }

  @Test
  public void test6(){
    Solar solar = new Solar(11,1,1);
    Assert.assertEquals("一〇年腊月初八",solar.getLunar().toString());
  }

  @Test
  public void test7(){
    Solar solar = new Solar(11,3,1);
    Assert.assertEquals("一一年二月初八",solar.getLunar().toString());
  }

  @Test
  public void test9(){
    Solar solar = new Solar(26,4,13);
    Assert.assertEquals("二六年三月初八",solar.getLunar().toString());
  }

  @Test
  public void test10(){
    Assert.assertTrue(SolarUtil.isLeapYear(1500));
  }

  @Test
  public void testNext(){
    Solar date = new Solar(2020,1,23);
    Assert.assertEquals("2020-01-24",date.next(1).toString());
    // 仅工作日，跨越春节假期
    Assert.assertEquals("2020-02-03",date.next(1,true).toString());

    date = new Solar(2020,2,3);
    Assert.assertEquals("2020-01-31",date.next(-3).toString());
    // 仅工作日，跨越春节假期
    Assert.assertEquals("2020-01-21",date.next(-3,true).toString());

    date = new Solar(2020,2,9);
    Assert.assertEquals("2020-02-15",date.next(6).toString());
    // 仅工作日，跨越周末
    Assert.assertEquals("2020-02-17",date.next(6,true).toString());

    date = new Solar(2020,1,17);
    Assert.assertEquals("2020-01-18",date.next(1).toString());
    // 仅工作日，周日调休按上班算
    Assert.assertEquals("2020-01-19",date.next(1,true).toString());
  }

  @Test
  public void test11(){
    Solar solar = new Solar(2022, 3, 28);
    Assert.assertEquals("全国中小学生安全教育日",solar.getFestivals().get(0));
  }

  @Test
  public void test12(){
    Solar solar = new Solar(2022, 1, 1);
    Assert.assertEquals("2022-01-02", solar.next(1).toYmd());
  }

  @Test
  public void test13(){
    Solar solar = new Solar(2022, 1, 31);
    Assert.assertEquals("2022-02-01", solar.next(1).toYmd());
  }

  @Test
  public void test14(){
    Solar solar = new Solar(2022, 1, 1);
    Assert.assertEquals("2023-01-01", solar.next(365).toYmd());
  }

  @Test
  public void test15(){
    Solar solar = new Solar(2023, 1, 1);
    Assert.assertEquals("2022-01-01", solar.next(-365).toYmd());
  }

  @Test
  public void test16(){
    Solar solar = new Solar(1582, 10, 4);
    Assert.assertEquals("1582-10-15", solar.next(1).toYmd());
  }

  @Test
  public void test17(){
    Solar solar = new Solar(1582, 10, 4);
    Assert.assertEquals("1582-11-01", solar.next(18).toYmd());
  }

  @Test
  public void test18(){
    Solar solar = new Solar(1582, 11, 1);
    Assert.assertEquals("1582-10-04", solar.next(-18).toYmd());
  }

  @Test
  public void test19(){
    Solar solar = new Solar(1582, 11, 1);
    Assert.assertEquals("1582-10-15", solar.next(-17).toYmd());
  }

  @Test
  public void test20(){
    int days = SolarUtil.getDaysBetween(1582, 10, 4, 1582, 10, 15);
    Assert.assertEquals(1, days);
  }

  @Test
  public void test21(){
    int days = SolarUtil.getDaysBetween(1582, 10, 4, 1582, 11, 1);
    Assert.assertEquals(18, days);
  }

  @Test
  public void test22(){
    int days = SolarUtil.getDaysBetween(1582, 1, 1, 1583, 1, 1);
    Assert.assertEquals(355, days);
  }

  @Test
  public void test23(){
    Solar solar = Solar.fromYmd(1991, 5, 12);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("壬午", lunar.getDayInGanZhi());
  }

  @Test
  public void test24(){
    Solar solar = new Solar(1582, 10, 15);
    Assert.assertEquals("1582-09-30", solar.next(-5).toYmd());
  }

  @Test
  public void test25(){
    Solar solar = new Solar(1582, 10, 15);
    Assert.assertEquals("1582-10-04", solar.next(-1).toYmd());
  }

  @Test
  public void test26(){
    Solar solar = new Solar(1582, 10, 15);
    Assert.assertEquals("1582-09-29", solar.next(-6).toYmd());
  }

  @Test
  public void test27(){
    Solar solar = Solar.fromYmd(2023, 8, 31);
    Assert.assertEquals("2023-09-30", solar.nextMonth(1).toYmd());
  }

  @Test
  public void test28(){
    Solar solar = Solar.fromYmd(2023, 8, 31);
    Assert.assertEquals("2023-10-31", solar.nextMonth(2).toYmd());
  }

  @Test
  public void test29(){
    Solar solar = Solar.fromYmd(2023, 8, 31);
    Assert.assertEquals("2025-08-31", solar.nextYear(2).toYmd());
  }

  @Test
  public void test30(){
    Solar solar = Solar.fromYmd(2023, 8, 31);
    Assert.assertEquals("2024-02-29", solar.nextMonth(6).toYmd());
  }

}
