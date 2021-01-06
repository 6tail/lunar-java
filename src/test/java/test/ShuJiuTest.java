package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.ShuJiu;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 数九测试
 *
 * @author 6tail
 */
public class ShuJiuTest {

  @Test
  public void test1(){
    Solar solar = new Solar(2020,12,21);
    Lunar lunar = solar.getLunar();
    ShuJiu shuJiu = lunar.getShuJiu();
    Assert.assertEquals(solar.toYmd(),"一九",shuJiu.toString());
    Assert.assertEquals(solar.toYmd(),"一九第1天",shuJiu.toFullString());
  }

  @Test
  public void test2(){
    Solar solar = new Solar(2020,12,22);
    Lunar lunar = solar.getLunar();
    ShuJiu shuJiu = lunar.getShuJiu();
    Assert.assertEquals(solar.toYmd(),"一九",shuJiu.toString());
    Assert.assertEquals(solar.toYmd(),"一九第2天",shuJiu.toFullString());
  }

  @Test
  public void test3(){
    Solar solar = new Solar(2020,1,7);
    Lunar lunar = solar.getLunar();
    ShuJiu shuJiu = lunar.getShuJiu();
    Assert.assertEquals(solar.toYmd(),"二九",shuJiu.toString());
    Assert.assertEquals(solar.toYmd(),"二九第8天",shuJiu.toFullString());
  }

  @Test
  public void test4(){
    Solar solar = new Solar(2021,1,6);
    Lunar lunar = solar.getLunar();
    ShuJiu shuJiu = lunar.getShuJiu();
    Assert.assertEquals(solar.toYmd(),"二九",shuJiu.toString());
    Assert.assertEquals(solar.toYmd(),"二九第8天",shuJiu.toFullString());
  }

  @Test
  public void test5(){
    Solar solar = new Solar(2021,1,8);
    Lunar lunar = solar.getLunar();
    ShuJiu shuJiu = lunar.getShuJiu();
    Assert.assertEquals(solar.toYmd(),"三九",shuJiu.toString());
    Assert.assertEquals(solar.toYmd(),"三九第1天",shuJiu.toFullString());
  }

  @Test
  public void test6(){
    Solar solar = new Solar(2021,3,5);
    Lunar lunar = solar.getLunar();
    ShuJiu shuJiu = lunar.getShuJiu();
    Assert.assertEquals(solar.toYmd(),"九九",shuJiu.toString());
    Assert.assertEquals(solar.toYmd(),"九九第3天",shuJiu.toFullString());
  }

  @Test
  public void test7(){
    Solar solar = new Solar(2021,7,5);
    Lunar lunar = solar.getLunar();
    ShuJiu shuJiu = lunar.getShuJiu();
    Assert.assertNull(solar.toYmd(),shuJiu);
  }

}
