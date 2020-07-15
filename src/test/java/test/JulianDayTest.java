package test;

import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 儒略日测试
 *
 * @author 6tail
 */
public class JulianDayTest {

  @Test
  public void testSolar2JD() {
    Solar solar = Solar.fromYmd(2020, 7, 15);
    Assert.assertEquals(2459045.5, solar.getJulianDay(), 0);
  }

  @Test
  public void testJD2Solar() {
    Solar solar = Solar.fromJulianDay(2459045.5);
    Assert.assertEquals("2020-07-15 00:00:00", solar.toYmdHms());
  }
}
