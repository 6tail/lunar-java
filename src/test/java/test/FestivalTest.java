package test;

import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 节日测试
 *
 * @author 6tail
 */
public class FestivalTest {
  @Test
  public void test() {
    Solar solar = Solar.fromYmd(2020, 11, 26);
    Assert.assertEquals("[感恩节]", solar.getFestivals() + "");

    solar = Solar.fromYmd(2020, 6, 21);
    Assert.assertEquals("[父亲节]", solar.getFestivals() + "");

    solar = Solar.fromYmd(2021, 5, 9);
    Assert.assertEquals("[母亲节]", solar.getFestivals() + "");

    solar = Solar.fromYmd(1986, 11, 27);
    Assert.assertEquals("[感恩节]", solar.getFestivals() + "");

    solar = Solar.fromYmd(1985, 6, 16);
    Assert.assertEquals("[父亲节]", solar.getFestivals() + "");

    solar = Solar.fromYmd(1984, 5, 13);
    Assert.assertEquals("[母亲节]", solar.getFestivals() + "");
  }
}
