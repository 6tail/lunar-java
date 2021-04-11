package test;

import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 星座测试
 *
 * @author 6tail
 */
public class XingZuoTest {

  @Test
  public void test1(){
    Solar solar = new Solar(2020,3,21);
    Assert.assertEquals(solar.toYmd(),"白羊",solar.getXingZuo());
    solar = new Solar(2020,4,19);
    Assert.assertEquals(solar.toYmd(),"白羊",solar.getXingZuo());
  }

  @Test
  public void test2(){
    Solar solar = new Solar(2020,4,20);
    Assert.assertEquals(solar.toYmd(),"金牛",solar.getXingZuo());
    solar = new Solar(2020,5,20);
    Assert.assertEquals(solar.toYmd(),"金牛",solar.getXingZuo());
  }

  @Test
  public void test3(){
    Solar solar = new Solar(2020,5,21);
    Assert.assertEquals(solar.toYmd(),"双子",solar.getXingZuo());
    solar = new Solar(2020,6,21);
    Assert.assertEquals(solar.toYmd(),"双子",solar.getXingZuo());
  }

  @Test
  public void test4(){
    Solar solar = new Solar(2020,6,22);
    Assert.assertEquals(solar.toYmd(),"巨蟹",solar.getXingZuo());
    solar = new Solar(2020,7,22);
    Assert.assertEquals(solar.toYmd(),"巨蟹",solar.getXingZuo());
  }

  @Test
  public void test5(){
    Solar solar = new Solar(2020,7,23);
    Assert.assertEquals(solar.toYmd(),"狮子",solar.getXingZuo());
    solar = new Solar(2020,8,22);
    Assert.assertEquals(solar.toYmd(),"狮子",solar.getXingZuo());
  }

  @Test
  public void test6(){
    Solar solar = new Solar(2020,8,23);
    Assert.assertEquals(solar.toYmd(),"处女",solar.getXingZuo());
    solar = new Solar(2020,9,22);
    Assert.assertEquals(solar.toYmd(),"处女",solar.getXingZuo());
  }

  @Test
  public void test7(){
    Solar solar = new Solar(2020,9,23);
    Assert.assertEquals(solar.toYmd(),"天秤",solar.getXingZuo());
    solar = new Solar(2020,10,23);
    Assert.assertEquals(solar.toYmd(),"天秤",solar.getXingZuo());
  }

  @Test
  public void test8(){
    Solar solar = new Solar(2020,10,24);
    Assert.assertEquals(solar.toYmd(),"天蝎",solar.getXingZuo());
    solar = new Solar(2020,11,22);
    Assert.assertEquals(solar.toYmd(),"天蝎",solar.getXingZuo());
  }

  @Test
  public void test9(){
    Solar solar = new Solar(2020,11,23);
    Assert.assertEquals(solar.toYmd(),"射手",solar.getXingZuo());
    solar = new Solar(2020,12,21);
    Assert.assertEquals(solar.toYmd(),"射手",solar.getXingZuo());
  }

  @Test
  public void test10(){
    Solar solar = new Solar(2020,12,22);
    Assert.assertEquals(solar.toYmd(),"摩羯",solar.getXingZuo());
    solar = new Solar(2021,1,19);
    Assert.assertEquals(solar.toYmd(),"摩羯",solar.getXingZuo());
  }

  @Test
  public void test11(){
    Solar solar = new Solar(2021,1,20);
    Assert.assertEquals(solar.toYmd(),"水瓶",solar.getXingZuo());
    solar = new Solar(2021,2,18);
    Assert.assertEquals(solar.toYmd(),"水瓶",solar.getXingZuo());
  }

  @Test
  public void test12(){
    Solar solar = new Solar(2021,2,19);
    Assert.assertEquals(solar.toYmd(),"双鱼",solar.getXingZuo());
    solar = new Solar(2021,3,20);
    Assert.assertEquals(solar.toYmd(),"双鱼",solar.getXingZuo());
  }

}
