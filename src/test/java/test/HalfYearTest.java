package test;

import com.nlf.calendar.SolarHalfYear;
import org.junit.Assert;
import org.junit.Test;

/**
 * 半年测试
 *
 * @author 6tail
 */
public class HalfYearTest {

  @Test
  public void test(){
    SolarHalfYear halfYear = new SolarHalfYear(2019,5);
    Assert.assertEquals("2019.1",halfYear.toString());
    Assert.assertEquals("2019年上半年",halfYear.toFullString());

    Assert.assertEquals("2019.2",halfYear.next(1).toString());
    Assert.assertEquals("2019年下半年",halfYear.next(1).toFullString());
  }

}
