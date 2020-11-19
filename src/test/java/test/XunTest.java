package test;

import com.nlf.calendar.EightChar;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.util.LunarUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 旬测试
 *
 * @author 6tail
 */
public class XunTest {

  @Test
  public void testXun(){
    Map<String,String> xun = new HashMap<String, String>();
    xun.put("甲子","甲子");
    xun.put("乙丑","甲子");
    xun.put("丙寅","甲子");
    xun.put("丁卯","甲子");
    xun.put("戊辰","甲子");
    xun.put("己巳","甲子");
    xun.put("庚午","甲子");
    xun.put("辛未","甲子");
    xun.put("壬申","甲子");
    xun.put("癸酉","甲子");
    xun.put("甲戌","甲戌");
    xun.put("乙亥","甲戌");
    xun.put("丙子","甲戌");
    xun.put("丁丑","甲戌");
    xun.put("戊寅","甲戌");
    xun.put("己卯","甲戌");
    xun.put("庚辰","甲戌");
    xun.put("辛巳","甲戌");
    xun.put("壬午","甲戌");
    xun.put("癸未","甲戌");
    xun.put("甲申","甲申");
    xun.put("乙酉","甲申");
    xun.put("丙戌","甲申");
    xun.put("丁亥","甲申");
    xun.put("戊子","甲申");
    xun.put("己丑","甲申");
    xun.put("庚寅","甲申");
    xun.put("辛卯","甲申");
    xun.put("壬辰","甲申");
    xun.put("癸巳","甲申");
    xun.put("甲午","甲午");
    xun.put("乙未","甲午");
    xun.put("丙申","甲午");
    xun.put("丁酉","甲午");
    xun.put("戊戌","甲午");
    xun.put("己亥","甲午");
    xun.put("庚子","甲午");
    xun.put("辛丑","甲午");
    xun.put("壬寅","甲午");
    xun.put("癸卯","甲午");
    xun.put("甲辰","甲辰");
    xun.put("乙巳","甲辰");
    xun.put("丙午","甲辰");
    xun.put("丁未","甲辰");
    xun.put("戊申","甲辰");
    xun.put("己酉","甲辰");
    xun.put("庚戌","甲辰");
    xun.put("辛亥","甲辰");
    xun.put("壬子","甲辰");
    xun.put("癸丑","甲辰");
    xun.put("甲寅","甲寅");
    xun.put("乙卯","甲寅");
    xun.put("丙辰","甲寅");
    xun.put("丁巳","甲寅");
    xun.put("戊午","甲寅");
    xun.put("己未","甲寅");
    xun.put("庚申","甲寅");
    xun.put("辛酉","甲寅");
    xun.put("壬戌","甲寅");
    xun.put("癸亥","甲寅");

    for(Map.Entry<String,String> entry:xun.entrySet()){
      Assert.assertEquals(entry.getValue(), LunarUtil.getXun(entry.getKey()));
    }
  }

  @Test
  public void testXunKong(){
    Map<String,String> kong = new HashMap<String, String>();
    kong.put("甲子","戌亥");
    kong.put("乙丑","戌亥");
    kong.put("丙寅","戌亥");
    kong.put("丁卯","戌亥");
    kong.put("戊辰","戌亥");
    kong.put("己巳","戌亥");
    kong.put("庚午","戌亥");
    kong.put("辛未","戌亥");
    kong.put("壬申","戌亥");
    kong.put("癸酉","戌亥");
    kong.put("甲戌","申酉");
    kong.put("乙亥","申酉");
    kong.put("丙子","申酉");
    kong.put("丁丑","申酉");
    kong.put("戊寅","申酉");
    kong.put("己卯","申酉");
    kong.put("庚辰","申酉");
    kong.put("辛巳","申酉");
    kong.put("壬午","申酉");
    kong.put("癸未","申酉");
    kong.put("甲申","午未");
    kong.put("乙酉","午未");
    kong.put("丙戌","午未");
    kong.put("丁亥","午未");
    kong.put("戊子","午未");
    kong.put("己丑","午未");
    kong.put("庚寅","午未");
    kong.put("辛卯","午未");
    kong.put("壬辰","午未");
    kong.put("癸巳","午未");
    kong.put("甲午","辰巳");
    kong.put("乙未","辰巳");
    kong.put("丙申","辰巳");
    kong.put("丁酉","辰巳");
    kong.put("戊戌","辰巳");
    kong.put("己亥","辰巳");
    kong.put("庚子","辰巳");
    kong.put("辛丑","辰巳");
    kong.put("壬寅","辰巳");
    kong.put("癸卯","辰巳");
    kong.put("甲辰","寅卯");
    kong.put("乙巳","寅卯");
    kong.put("丙午","寅卯");
    kong.put("丁未","寅卯");
    kong.put("戊申","寅卯");
    kong.put("己酉","寅卯");
    kong.put("庚戌","寅卯");
    kong.put("辛亥","寅卯");
    kong.put("壬子","寅卯");
    kong.put("癸丑","寅卯");
    kong.put("甲寅","子丑");
    kong.put("乙卯","子丑");
    kong.put("丙辰","子丑");
    kong.put("丁巳","子丑");
    kong.put("戊午","子丑");
    kong.put("己未","子丑");
    kong.put("庚申","子丑");
    kong.put("辛酉","子丑");
    kong.put("壬戌","子丑");
    kong.put("癸亥","子丑");

    for(Map.Entry<String,String> entry:kong.entrySet()){
      Assert.assertEquals(entry.getValue(), LunarUtil.getXunKong(entry.getKey()));
    }
  }

  @Test
  public void testXun1(){
    Solar solar = new Solar(2020,11,19,0,0,0);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("甲午",lunar.getYearXun());
  }

  @Test
  public void testXunKong1(){
    Solar solar = new Solar(2020,11,19,0,0,0);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("辰巳",lunar.getYearXunKong());
    Assert.assertEquals("午未",lunar.getMonthXunKong());
    Assert.assertEquals("戌亥",lunar.getDayXunKong());
  }

  @Test
  public void testBaZiDayXunKong(){
    Solar solar = new Solar(1990,12,23,8,37,0);
    Lunar lunar = solar.getLunar();
    EightChar eightChar = lunar.getEightChar();
    Assert.assertEquals("子丑",eightChar.getDayXunKong());
  }

}
