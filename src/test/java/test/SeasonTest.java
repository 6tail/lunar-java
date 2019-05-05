package test;

import com.nlf.calendar.SolarSeason;
import org.junit.Assert;
import org.junit.Test;

/**
 * 季度测试
 *
 * @author 6tail
 */
public class SeasonTest {

  @Test
  public void test(){
    SolarSeason season = new SolarSeason(2019,5);
    Assert.assertEquals("2019.2",season.toString());
    Assert.assertEquals("2019年2季度",season.toFullString());

    Assert.assertEquals("2019.3",season.next(1).toString());
    Assert.assertEquals("2019年3季度",season.next(1).toFullString());
  }

}
