package test;

import com.nlf.calendar.Solar;
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
    Assert.assertEquals("2019-05-01 星期三 (劳动节) 金牛座",date.toFullString());
    Assert.assertEquals("己亥年叁月廿七",date.getLunar().toString());
    Assert.assertEquals("己亥年叁月廿七 猪年 西方白虎 娄金狗",date.getLunar().toFullString());
  }

}
