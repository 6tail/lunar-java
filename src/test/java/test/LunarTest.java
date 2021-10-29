package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

/**
 * 农历测试
 *
 * @author 6tail
 */
public class LunarTest {

  @Test
  public void test() {
    Lunar date = new Lunar(2019, 3, 27, 0, 0, 0);
    Assert.assertEquals("二〇一九年三月廿七", date.toString());
    Assert.assertEquals("二〇一九年三月廿七 己亥(猪)年 戊辰(龙)月 戊戌(狗)日 子(鼠)时 纳音[平地木 大林木 平地木 桑柘木] 星期三 西方白虎 星宿[参水猿](吉) 彭祖百忌[戊不受田田主不祥 戌不吃犬作怪上床] 喜神方位[巽](东南) 阳贵神方位[艮](东北) 阴贵神方位[坤](西南) 福神方位[坎](正北) 财神方位[坎](正北) 冲[(壬辰)龙] 煞[北]", date.toFullString());
    Assert.assertEquals("2019-05-01", date.getSolar().toString());
    Assert.assertEquals("2019-05-01 00:00:00 星期三 (劳动节) 金牛座", date.getSolar().toFullString());
  }

  @Test
  public void test1() {
    Solar solar = new Solar(1, 1, 1, 12, 0, 0);
    Assert.assertEquals("〇年冬月十八", solar.getLunar().toString());
  }

  @Test
  public void test2() {
    Solar solar = new Solar(9999, 12, 31, 12, 0, 0);
    Assert.assertEquals("九九九九年腊月初二", solar.getLunar().toString());
  }

  @Test
  public void test3() {
    Lunar lunar = new Lunar(0, 11, 18, 12, 0, 0);
    Assert.assertEquals("0001-01-01", lunar.getSolar().toString());
  }

  @Test
  public void test4() {
    Lunar lunar = new Lunar(9999, 12, 2, 12, 0, 0);
    Assert.assertEquals("9999-12-31", lunar.getSolar().toString());
  }

  @Test
  public void test5() {
    Lunar lunar = new Lunar(1905, 1, 1, 12, 0, 0);
    Assert.assertEquals("1905-02-04", lunar.getSolar().toString());
  }

  @Test
  public void test6() {
    Lunar lunar = new Lunar(2038, 12, 29, 12, 0, 0);
    Assert.assertEquals("2039-01-23", lunar.getSolar().toString());
  }

  @Test
  public void test7() {
    Lunar lunar = new Lunar(2020, -4, 2, 13, 0, 0);
    Assert.assertEquals("二〇二〇年闰四月初二", lunar.toString());
    Assert.assertEquals("2020-05-24", lunar.getSolar().toString());
  }

  @Test
  public void test8() {
    Lunar lunar = new Lunar(2020, 12, 10, 13, 0, 0);
    Assert.assertEquals("二〇二〇年腊月初十", lunar.toString());
    Assert.assertEquals("2021-01-22", lunar.getSolar().toString());
  }

  @Test
  public void test9() {
    Lunar lunar = new Lunar(1500, 1, 1, 12, 0, 0);
    Assert.assertEquals("1500-01-31", lunar.getSolar().toString());
  }

  @Test
  public void test10() {
    Lunar lunar = new Lunar(1500, 12, 29, 12, 0, 0);
    Assert.assertEquals("1501-01-18", lunar.getSolar().toString());
  }

  @Test
  public void test11() {
    Solar solar = new Solar(1500, 1, 1, 12, 0, 0);
    Assert.assertEquals("一四九九年腊月初一", solar.getLunar().toString());
  }

  @Test
  public void test12() {
    Solar solar = new Solar(1500, 12, 31, 12, 0, 0);
    Assert.assertEquals("一五〇〇年腊月十一", solar.getLunar().toString());
  }

  @Test
  public void test13() {
    Solar solar = new Solar(1582, 10, 4, 12, 0, 0);
    Assert.assertEquals("一五八二年九月十八", solar.getLunar().toString());
  }

  @Test
  public void test14() {
    Solar solar = new Solar(1582, 10, 15, 12, 0, 0);
    Assert.assertEquals("一五八二年九月十九", solar.getLunar().toString());
  }

  @Test
  public void test15() {
    Lunar lunar = new Lunar(1582, 9, 18, 12, 0, 0);
    Assert.assertEquals("1582-10-04", lunar.getSolar().toString());
  }

  @Test
  public void test16() {
    Lunar lunar = new Lunar(1582, 9, 19, 12, 0, 0);
    Assert.assertEquals("1582-10-15", lunar.getSolar().toString());
  }

  @Test
  public void test17() {
    Lunar lunar = new Lunar(2019, 12, 12, 11, 22, 0);
    Assert.assertEquals("2020-01-06", lunar.getSolar().toString());
  }

  @Test
  public void test18() {
    Lunar lunar = new Lunar(2021, 12, 29);
    Assert.assertEquals("除夕", lunar.getFestivals().get(0));
  }

  @Test
  public void test19() {
    Lunar lunar = new Lunar(2020, 12, 30);
    Assert.assertEquals("除夕", lunar.getFestivals().get(0));
  }

  @Test
  public void test20() {
    Lunar lunar = new Lunar(2020, 12, 29);
    Assert.assertEquals(0, lunar.getFestivals().size());
  }

  @Test
  public void test21() {
    Solar solar = Solar.fromYmd(2022, 1, 31);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("除夕", lunar.getFestivals().get(0));
  }

  @Test
  public void test22() {
    Lunar lunar = Lunar.fromYmd(2033, -11, 1);
    Assert.assertEquals("2033-12-22", lunar.getSolar().toYmd());
  }

  @Test
  public void test23() {
    Lunar lunar = Lunar.fromYmd(2022, 1, 1);
    Assert.assertEquals("五黄土玉衡", lunar.getYearNineStar().toString());
  }

  @Test
  public void test24() {
    Lunar lunar = Lunar.fromYmd(2033, 1, 1);
    Assert.assertEquals("三碧木天玑", lunar.getYearNineStar().toString());
  }

  @Test
  public void test25() {
    Solar solar = new Solar(2021, 6, 7, 21, 18, 0);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("二〇二一年四月廿七", lunar.toString());
  }

  @Test
  public void test26() {
    Lunar lunar = Lunar.fromYmdHms(2021, 6, 7, 21, 18, 0);
    Solar solar = lunar.getSolar();
    Assert.assertEquals("2021-07-16", solar.toString());
  }

  @Test
  public void testNext() {
    Solar solar = new Solar(2020, 1, 10, 12, 0, 0);
    Lunar lunar = solar.getLunar();
    for (int i = -50; i < 50; i++) {
      Assert.assertEquals("推移天数：" + i, solar.next(i).getLunar().toFullString(), lunar.next(i).toFullString());
    }
  }

  @Test
  public void test27() {
    Solar solar = Solar.fromYmd(1989, 4, 28);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals(23, lunar.getDay());
  }

  @Test
  public void test28() {
    Solar solar = Solar.fromYmd(1990, 10, 8);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("乙酉", lunar.getMonthInGanZhiExact());
  }

  @Test
  public void test29() {
    Solar solar = Solar.fromYmd(1990, 10, 9);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("丙戌", lunar.getMonthInGanZhiExact());
  }

  @Test
  public void test30() {
    Solar solar = Solar.fromYmd(1990, 10, 8);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("丙戌", lunar.getMonthInGanZhi());
  }

  @Test
  public void test31() {
    Solar solar = Solar.fromYmdHms(1987, 4, 17, 9, 0, 0);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("一九八七年三月二十", lunar.toString());
  }

  @Test
  public void test32() {
    Lunar lunar = Lunar.fromYmd(2034, 1, 1);
    Assert.assertEquals("2034-02-19", lunar.getSolar().toYmd());
  }

  @Test
  public void test33() {
    Lunar lunar = Lunar.fromYmd(2033, 12, 1);
    Assert.assertEquals("2034-01-20", lunar.getSolar().toYmd());
  }

  @Test
  public void test34() {
    Lunar lunar = Lunar.fromYmd(37, -12, 1);
    Assert.assertEquals("闰腊", lunar.getMonthInChinese());
  }

  @Test
  public void test35() {
    Lunar lunar = Lunar.fromYmd(56, -12, 1);
    Assert.assertEquals("闰腊", lunar.getMonthInChinese());

    lunar = Lunar.fromYmd(75, -11, 1);
    Assert.assertEquals("闰冬", lunar.getMonthInChinese());

    lunar = Lunar.fromYmd(94, -11, 1);
    Assert.assertEquals("闰冬", lunar.getMonthInChinese());

    lunar = Lunar.fromYmd(94, 12, 1);
    Assert.assertEquals("腊", lunar.getMonthInChinese());

    lunar = Lunar.fromYmd(113, 12, 1);
    Assert.assertEquals("腊", lunar.getMonthInChinese());

    lunar = Lunar.fromYmd(113, -12, 1);
    Assert.assertEquals("闰腊", lunar.getMonthInChinese());

    lunar = Lunar.fromYmd(5552, -12, 1);
    Assert.assertEquals("闰腊", lunar.getMonthInChinese());
  }

  @Test
  public void test36() {
    Solar solar = Solar.fromYmd(5553, 1, 22);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("五五五二年闰腊月初二", lunar.toString());
  }

  @Test
  public void test37() {
    Solar solar = Solar.fromYmd(7013, 12, 24);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("七〇一三年闰冬月初四", lunar.toString());
  }

  @Test
  public void test38() {
    Lunar lunar = Lunar.fromYmd(7013, -11, 4);
    Solar solar = lunar.getSolar();
    Assert.assertEquals("7013-12-24", solar.toString());
  }

  @Test
  public void test39() {
    Solar solar = Solar.fromYmd(1987, 4, 12);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("一九八七年三月十五", lunar.toString());
  }

  @Test
  public void test40() {
    Solar solar = Solar.fromYmd(1987, 4, 13);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("一九八七年三月十六", lunar.toString());
  }

  @Test
  public void test41() {
    Solar solar = Solar.fromYmd(4, 2, 10);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("鼠", lunar.getYearShengXiao());
  }

  @Test
  public void test42() {
    Solar solar = Solar.fromYmd(4, 2, 9);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("猪", lunar.getYearShengXiao());
  }

  @Test
  public void test43() {
    Solar solar = Solar.fromYmd(1, 2, 12);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("鸡", lunar.getYearShengXiao());
  }

  @Test
  public void test44() {
    Solar solar = Solar.fromYmd(1, 1, 1);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("猴", lunar.getYearShengXiao());
  }

  @Test
  public void test45() {
    Solar solar = Solar.fromYmd(1946, 9, 30);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("一九四六年九月初六", lunar.toString());
  }

  @Test
  public void test46() {
    Solar solar = Solar.fromYmd(1946, 9, 29);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("一九四六年九月初五", lunar.toString());
  }

  @Test
  public void test47() {
    Solar solar = Solar.fromYmd(2017, 2, 15);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("子命互禄 辛命进禄", lunar.getDayLu());
  }

}
