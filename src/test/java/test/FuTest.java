package test;

import com.nlf.calendar.Fu;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 三伏测试
 *
 * @author 6tail
 */
public class FuTest {

  @Test
  public void test1(){
    Solar solar = new Solar(2011,7,14);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"初伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"初伏第1天",fu.toFullString());
  }

  @Test
  public void test2(){
    Solar solar = new Solar(2011,7,23);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"初伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"初伏第10天",fu.toFullString());
  }

  @Test
  public void test3(){
    Solar solar = new Solar(2011,7,24);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"中伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"中伏第1天",fu.toFullString());
  }

  @Test
  public void test4(){
    Solar solar = new Solar(2011,8,12);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"中伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"中伏第20天",fu.toFullString());
  }

  @Test
  public void test5(){
    Solar solar = new Solar(2011,8,13);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"末伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"末伏第1天",fu.toFullString());
  }

  @Test
  public void test6(){
    Solar solar = new Solar(2011,8,22);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"末伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"末伏第10天",fu.toFullString());
  }

  @Test
  public void test7(){
    Solar solar = new Solar(2011,7,13);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertNull(solar.toYmd(),fu);
  }

  @Test
  public void test8(){
    Solar solar = new Solar(2011,8,23);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertNull(solar.toYmd(),fu);
  }

  @Test
  public void test9(){
    Solar solar = new Solar(2012,7,18);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"初伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"初伏第1天",fu.toFullString());
  }

  @Test
  public void test10(){
    Solar solar = new Solar(2012,8,5);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"中伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"中伏第9天",fu.toFullString());
  }

  @Test
  public void test11(){
    Solar solar = new Solar(2012,8,8);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"末伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"末伏第2天",fu.toFullString());
  }

  @Test
  public void test12(){
    Solar solar = new Solar(2020,7,17);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"初伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"初伏第2天",fu.toFullString());
  }

  @Test
  public void test13(){
    Solar solar = new Solar(2020,7,26);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"中伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"中伏第1天",fu.toFullString());
  }

  @Test
  public void test14(){
    Solar solar = new Solar(2020,8,24);
    Lunar lunar = solar.getLunar();
    Fu fu = lunar.getFu();
    Assert.assertEquals(solar.toYmd(),"末伏",fu.toString());
    Assert.assertEquals(solar.toYmd(),"末伏第10天",fu.toFullString());
  }

}
