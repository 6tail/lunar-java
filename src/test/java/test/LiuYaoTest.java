package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 六曜测试
 *
 * @author 6tail
 */
public class LiuYaoTest {

  @Test
  public void test1(){
    Solar solar = new Solar(2020,4,23);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(lunar.toString(),"佛灭",lunar.getLiuYao());
  }

  @Test
  public void test2(){
    Solar solar = new Solar(2021,1,15);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(lunar.toString(),"友引",lunar.getLiuYao());
  }

  @Test
  public void test3(){
    Solar solar = new Solar(2017,1,5);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(lunar.toString(),"先胜",lunar.getLiuYao());
  }

  @Test
  public void test4(){
    Solar solar = new Solar(2020,4,10);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(lunar.toString(),"友引",lunar.getLiuYao());
  }

  @Test
  public void test5(){
    Solar solar = new Solar(2020,6,11);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(lunar.toString(),"大安",lunar.getLiuYao());
  }

  @Test
  public void test6(){
    Solar solar = new Solar(2020,6,1);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(lunar.toString(),"先胜",lunar.getLiuYao());
  }

  @Test
  public void test7(){
    Solar solar = new Solar(2020,12,8);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(lunar.toString(),"先负",lunar.getLiuYao());
  }

  @Test
  public void test8(){
    Solar solar = new Solar(2020,12,11);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(lunar.toString(),"赤口",lunar.getLiuYao());
  }

}
