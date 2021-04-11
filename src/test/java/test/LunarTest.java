package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 农历测试
 *
 * @author 6tail
 */
public class LunarTest {

  @Test
  public void test(){
    Lunar date = new Lunar(2019,3,27,0,0,0);
    Assert.assertEquals("二〇一九年三月廿七",date.toString());
    Assert.assertEquals("二〇一九年三月廿七 己亥(猪)年 戊辰(龙)月 戊戌(狗)日 子(鼠)时 纳音[平地木 大林木 平地木 桑柘木] 星期三 (七殿泰山王诞) 西方白虎 星宿[参水猿](吉) 彭祖百忌[戊不受田田主不祥 戌不吃犬作怪上床] 喜神方位[巽](东南) 阳贵神方位[艮](东北) 阴贵神方位[坤](西南) 福神方位[坎](正北) 财神方位[坎](正北) 冲[(壬辰)龙] 煞[北]",date.toFullString());
    Assert.assertEquals("2019-05-01",date.getSolar().toString());
    Assert.assertEquals("2019-05-01 00:00:00 星期三 (劳动节) 金牛座",date.getSolar().toFullString());
  }

  @Test
  public void test1(){
    Solar solar = new Solar(1,1,1,12,0,0);
    Assert.assertEquals("〇年冬月十八",solar.getLunar().toString());
  }

  @Test
  public void test2(){
    Solar solar = new Solar(9999,12,31,12,0,0);
    Assert.assertEquals("九九九九年腊月初二",solar.getLunar().toString());
  }

  @Test
  public void test3(){
    Lunar lunar = new Lunar(0,11,18,12,0,0);
    Assert.assertEquals("0001-01-01",lunar.getSolar().toString());
  }

  @Test
  public void test4(){
    Lunar lunar = new Lunar(9999,12,2,12,0,0);
    Assert.assertEquals("9999-12-31",lunar.getSolar().toString());
  }

  @Test
  public void test5(){
    Lunar lunar = new Lunar(1905,1,1,12,0,0);
    Assert.assertEquals("1905-02-04",lunar.getSolar().toString());
  }

  @Test
  public void test6(){
    Lunar lunar = new Lunar(2038,12,29,12,0,0);
    Assert.assertEquals("2039-01-23",lunar.getSolar().toString());
  }

  @Test
  public void test7(){
    Lunar lunar = new Lunar(2020,-4,2,13,0,0);
    Assert.assertEquals("二〇二〇年闰四月初二",lunar.toString());
    Assert.assertEquals("2020-05-24",lunar.getSolar().toString());
  }

  @Test
  public void testNext(){
    Solar solar = new Solar(2020,1,10,12,0,0);
    Lunar lunar = solar.getLunar();
    for(int i=-50;i<50;i++){
      Assert.assertEquals("推移天数："+i,solar.next(i).getLunar().toFullString(),lunar.next(i).toFullString());
    }
  }

}
