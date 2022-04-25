package test;

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
    Assert.assertFalse(SolarUtil.isLeapYear(1500));
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

}
