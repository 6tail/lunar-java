package test;

import com.nlf.calendar.Lunar;
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
    Lunar date = new Lunar(2019,3,27);
    Assert.assertEquals("己亥年叁月廿七",date.toString());
    Assert.assertEquals("己亥年叁月廿七 猪年 西方白虎 娄金狗",date.toFullString());
    Assert.assertEquals("2019-05-01",date.getSolar().toString());
    Assert.assertEquals("2019-05-01 星期三 (劳动节) 金牛座",date.getSolar().toFullString());
  }

}
