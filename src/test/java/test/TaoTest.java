package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Tao;
import org.junit.Assert;
import org.junit.Test;

/**
 * 道历测试
 *
 * @author 6tail
 */
public class TaoTest {

  @Test
  public void test() {
    Tao tao = Tao.fromLunar(Lunar.fromYmdHms(2021, 10, 17, 18, 0, 0));
    Assert.assertEquals("四七一八年十月十七", tao.toString());
    Assert.assertEquals("道歷四七一八年，天運辛丑年，己亥月，癸酉日。十月十七日，酉時。", tao.toFullString());
  }

  @Test
  public void test1() {
    Tao tao = Tao.fromYmd(4718, 10, 18);
    Assert.assertEquals("[地母娘娘圣诞, 四时会]", tao.getFestivals().toString());

    tao = Lunar.fromYmd(2021, 10, 18).getTao();
    Assert.assertEquals("[地母娘娘圣诞, 四时会]", tao.getFestivals().toString());
  }

}
