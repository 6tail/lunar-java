package test;

import com.nlf.calendar.SolarYear;
import org.junit.Assert;
import org.junit.Test;

/**
 * 年份测试
 *
 * @author 6tail
 */
public class YearTest {

  @Test
  public void test(){
    SolarYear year = new SolarYear(2019);
    Assert.assertEquals("2019",year.toString());
    Assert.assertEquals("2019年",year.toFullString());

    Assert.assertEquals("2020",year.next(1).toString());
    Assert.assertEquals("2020年",year.next(1).toFullString());
  }

}
