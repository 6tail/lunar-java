package sample;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Test;

import java.util.List;

/**
 * 八字测试
 * @author 6tail
 */
public class BaZiTestNew {

  @Test
  public void test1(){
    Solar solar = new Solar(2020,1,1,22,35,0);
    Lunar lunar = solar.getLunar();
    //己亥 丙子 癸卯 癸亥
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void test2(){
    Solar solar = new Solar(2020,1,6,14,35,0);
    Lunar lunar = solar.getLunar();
    //己亥, 丁丑, 戊申, 己未]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void test3(){
    Solar solar = new Solar(2020,1,6,3,35,0);
    Lunar lunar = solar.getLunar();
    //己亥, 丁丑, 戊辰, 癸亥]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void test4(){
    Solar solar = new Solar(2020,1,26,21,41,0);
    Lunar lunar = solar.getLunar();
    //己亥, 丙子, 戊申, 甲寅]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void test5(){
    Solar solar = new Solar(2020,2,4,1,42,0);
    Lunar lunar = solar.getLunar();
    //己亥, 丁丑, 丁丑, 辛丑]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void test6(){
    Solar solar = new Solar(2020,2,5,21,43,0);
    Lunar lunar = solar.getLunar();
    //庚子, 戊寅, 戊寅, 癸亥]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void testBazi2Solar6() {
    List<Solar> l = Solar.fromBaZi("庚子", "戊寅", "戊寅", "癸亥");
    // [2020-02-05 22:00:00, 1960-02-20 22:00:00]
    for (Solar solar : l) {
      System.out.println(solar.toFullString());
    }
  }

  @Test
  public void test7(){
    Solar solar = new Solar(2020,5,26,23,43,0);
    Lunar lunar = solar.getLunar();
    //庚子, 辛巳, 庚午, 丙子]
    System.out.println(lunar.getEightChar());
  }

  @Test
  public void testBazi2Solar7() {
    List<Solar> l = Solar.fromBaZi("庚子", "辛巳", "庚午", "丙子");
    // [2020-05-26 23:00:00, 2020-05-27 00:00:00]
    for (Solar solar : l) {
      System.out.println(solar.toFullString());
    }
  }

  @Test
  public void testBazi2Solar() {
    List<Solar> l = Solar.fromBaZi("庚子", "癸未", "乙丑", "丁亥");
    // [2020-07-21 22:00:00, 1960-08-05 22:00:00]
    for (Solar solar : l) {
      System.out.println(solar.toFullString());
    }
  }

  @Test
  public void testBazi2Solar2() {
    List<Solar> l = Solar.fromBaZi("庚子", "戊子", "己卯", "庚午");
    // [1960-12-17 12:00:00, 1901-01-01 12:00:00]
    for (Solar solar : l) {
      System.out.println(solar.toFullString());
    }
  }

  @Test
  public void testBaziShiShenZhi() {
    Solar solar = new Solar(2020,1,1,22,35,0);
    Lunar lunar = solar.getLunar();
    //己亥 丙子 癸卯 癸亥
    System.out.println(lunar.getEightChar());
    //七杀
    System.out.println(lunar.getEightChar().getYearShiShenGan());
    //正财
    System.out.println(lunar.getEightChar().getMonthShiShenGan());
    //日主
    System.out.println(lunar.getEightChar().getDayShiShenGan());
    //比肩
    System.out.println(lunar.getEightChar().getTimeShiShenGan());

    //[劫财, 伤官]
    System.out.println(lunar.getEightChar().getYearShiShenZhi());
    //[比肩]
    System.out.println(lunar.getEightChar().getMonthShiShenZhi());
    //[食神]
    System.out.println(lunar.getEightChar().getDayShiShenZhi());
    //[劫财, 伤官]
    System.out.println(lunar.getEightChar().getTimeShiShenZhi());
  }

}
