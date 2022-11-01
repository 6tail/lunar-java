package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 物候测试
 *
 * @author 6tail
 */
public class WuHouTest {

  @Test
  public void test1(){
    Solar solar = new Solar(2020,4,23);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"萍始生",lunar.getWuHou());
  }

  @Test
  public void test2(){
    Solar solar = new Solar(2021,1,15);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"雉始雊",lunar.getWuHou());
  }

  @Test
  public void test3(){
    Solar solar = new Solar(2017,1,5);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"雁北乡",lunar.getWuHou());
  }

  @Test
  public void test4(){
    Solar solar = new Solar(2020,4,10);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"田鼠化为鴽",lunar.getWuHou());
  }

  @Test
  public void test5(){
    Solar solar = new Solar(2020,6,11);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"鵙始鸣",lunar.getWuHou());
  }

  @Test
  public void test6(){
    Solar solar = new Solar(2020,6,1);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"麦秋至",lunar.getWuHou());
  }

  @Test
  public void test7(){
    Solar solar = new Solar(2020,12,8);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"鹖鴠不鸣",lunar.getWuHou());
  }

  @Test
  public void test8(){
    Solar solar = new Solar(2020,12,11);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"鹖鴠不鸣",lunar.getWuHou());
  }

  @Test
  public void test9(){
    Solar solar = new Solar(1982,12,22);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"蚯蚓结",lunar.getWuHou());
  }

  @Test
  public void test10(){
    Solar solar = new Solar(2021,12,21);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"冬至 初候",lunar.getHou());
  }

  @Test
  public void test11(){
    Solar solar = new Solar(2021,12,26);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"冬至 二候",lunar.getHou());
  }

  @Test
  public void test12(){
    Solar solar = new Solar(2021,12,31);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"冬至 三候",lunar.getHou());
  }

  @Test
  public void test13(){
    Solar solar = new Solar(2022,1,5);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"小寒 初候",lunar.getHou());
  }

  @Test
  public void test15(){
    Solar solar = new Solar(2022,8,22);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"寒蝉鸣",lunar.getWuHou());
  }

  @Test
  public void test16(){
    Solar solar = new Solar(2022,8,23);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(solar.toString(),"鹰乃祭鸟",lunar.getWuHou());
  }

}
