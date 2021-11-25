package test;

import com.nlf.calendar.Foto;
import com.nlf.calendar.Lunar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 佛历测试
 *
 * @author 6tail
 */
public class FotoTest {

  @Test
  public void test() {
    Foto foto = Foto.fromLunar(Lunar.fromYmd(2021, 10, 14));
    Assert.assertEquals("二五六五年十月十四 (三元降) (四天王巡行)", foto.toFullString());
  }

}
