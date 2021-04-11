package test;

import com.nlf.calendar.EightChar;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.eightchar.Yun;
import org.junit.Assert;
import org.junit.Test;

/**
 * 起运测试
 */
public class YunTest {

  @Test
  public void test1() {
    Solar solar = new Solar(1981, 1, 29, 23, 37, 0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Yun yun = eightChar.getYun(0);
    Assert.assertEquals("起运年数", 8, yun.getStartYear());
    Assert.assertEquals("起运月数", 0, yun.getStartMonth());
    Assert.assertEquals("起运天数", 20, yun.getStartDay());
    Assert.assertEquals("起运阳历", "1989-02-18", yun.getStartSolar().toYmd());
  }

}
