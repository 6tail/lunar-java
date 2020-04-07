package test;

import com.nlf.calendar.Lunar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 农历测试
 *
 * @author 6tail
 */
public class LunarTest {

  @Test
  public void test(){
    Lunar date = new Lunar(2019,3,27);
    Assert.assertEquals("贰零壹玖年叁月廿七",date.toString());
    Assert.assertEquals("贰零壹玖年叁月廿七 己亥(猪)年 戊辰(龙)月 戊戌(狗)日 子(鼠)时 纳音[平地木 大林木 平地木 海中金] 星期三 (七殿泰山王诞) 西方白虎 星宿[参水猿](吉) 彭祖百忌[戊不受田田主不祥 戌不吃犬作怪上床] 喜神方位[巽](东南) 阳贵神方位[艮](东北) 阴贵神方位[坤](西南) 福神方位[坎](正北) 财神方位[坎](正北) 冲[(壬辰)龙] 煞[北]",date.toFullString());
    Assert.assertEquals("2019-05-01",date.getSolar().toString());
    Assert.assertEquals("2019-05-01 00:00 星期三 (劳动节) 金牛座",date.getSolar().toFullString());
  }

}
