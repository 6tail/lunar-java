package test;

import com.nlf.calendar.Foto;
import com.nlf.calendar.Lunar;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

  @Test
  public void test1() {
    Foto foto = Foto.fromLunar(Lunar.fromYmd(2020, 4, 13));
    Assert.assertEquals("氐", foto.getXiu());
    Assert.assertEquals("土", foto.getZheng());
    Assert.assertEquals("貉", foto.getAnimal());
    Assert.assertEquals("东", foto.getGong());
    Assert.assertEquals("青龙", foto.getShou());
  }

  @Test
  public void test2() {
    Foto foto = Foto.fromLunar(Lunar.fromYmd(2021, 3, 16));
    List<String> expected = new ArrayList<String>();
    expected.add("准提菩萨圣诞");
    Assert.assertEquals(expected, foto.getOtherFestivals());
  }

}
