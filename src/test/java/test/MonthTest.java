package test;

import com.nlf.calendar.SolarMonth;
import org.junit.Assert;
import org.junit.Test;

/**
 * 月份测试
 *
 * @author 6tail
 */
public class MonthTest {

  @Test
  public void test(){
    SolarMonth month = new SolarMonth(2019,5);
    Assert.assertEquals("2019-5",month.toString());
    Assert.assertEquals("2019年5月",month.toFullString());

    Assert.assertEquals("2019-6",month.next(1).toString());
    Assert.assertEquals("2019年6月",month.next(1).toFullString());
  }

}
