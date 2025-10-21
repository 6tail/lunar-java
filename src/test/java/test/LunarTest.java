package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    Assert.assertEquals("二〇一九年三月廿七 己亥(猪)年 戊辰(龙)月 戊戌(狗)日 子(鼠)时 纳音[平地木 大林木 平地木 桑柘木] 星期三 西方白虎 星宿[参水猿](吉) 彭祖百忌[戊不受田田主不祥 戌不吃犬作怪上床] 喜神方位[巽](东南) 阳贵神方位[艮](东北) 阴贵神方位[坤](西南) 福神方位[艮](东北) 财神方位[坎](正北) 冲[(壬辰)龙] 煞[北]", date.toFullString());
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

  @Test
  public void test48() {
    Solar solar = Solar.fromYmd(2021, 11, 13);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("碓磨厕 外东南", lunar.getDayPositionTai());
  }

  @Test
  public void test49() {
    Solar solar = Solar.fromYmd(2021, 11, 12);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("占门碓 外东南", lunar.getDayPositionTai());
  }

  @Test
  public void test50() {
    Solar solar = Solar.fromYmd(2021, 11, 13);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("西南", lunar.getDayPositionFuDesc());
  }

  @Test
  public void test51() {
    Solar solar = Solar.fromYmd(2021, 11, 12);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("正北", lunar.getDayPositionFuDesc());
  }

  @Test
  public void test52() {
    Solar solar = Solar.fromYmd(2011, 11, 12);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("厨灶厕 外西南", lunar.getDayPositionTai());
  }

  @Test
  public void test53() {
    Solar solar = Solar.fromYmd(2021, 11, 27);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("[嫁娶, 祭祀, 祈福, 求嗣, 开光, 出行, 解除, 出火, 拆卸, 进人口, 入宅, 移徙, 安床, 栽种, 动土, 修造, 纳畜, 入殓, 安葬, 立碑, 除服, 成服]", lunar.getDayYi().toString());
  }

  @Test
  public void test54() throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    Date date = format.parse("1986-08-14");
    Lunar lunar = new Lunar(date);
    Assert.assertEquals("一九八六年七月初九", lunar.toString());
  }

  @Test
  public void test55() {
    Solar solar = new Solar(1986, 8, 14);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("一九八六年七月初九", lunar.toString());
  }

  @Test
  public void test56() {
    Solar solar = new Solar(2022, 4, 5);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("甲辰", lunar.getMonthInGanZhi());
    Assert.assertEquals("癸卯", lunar.getMonthInGanZhiExact());
    Assert.assertEquals("[出火, 入宅, 安葬, 伐木]", lunar.getDayJi().toString());
  }

  @Test
  public void test57() {
    Solar solar = new Solar(1991, 2, 5);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("庚寅", lunar.getMonthInGanZhi());
  }

  @Test
  public void test58() {
    Solar solar = new Solar(2021, 3, 21);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("春社", lunar.getOtherFestivals().get(0));
  }

  @Test
  public void test59() {
    Solar solar = new Solar(2022, 3, 16);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("春社", lunar.getOtherFestivals().get(0));
  }

  @Test
  public void test60() {
    Solar solar = new Solar(1722, 9, 25);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("秋社", lunar.getOtherFestivals().get(0));
  }

  @Test
  public void test61() {
    Solar solar = new Solar(840, 9, 14);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("秋社", lunar.getOtherFestivals().get(0));
  }

  @Test
  public void test62() {
    Lunar lunar = Lunar.fromYmd(1582, 9, 18);
    Assert.assertEquals("1582-10-04", lunar.getSolar().toString());
  }

  @Test
  public void test63() {
    Lunar lunar = Lunar.fromYmd(1582, 9, 19);
    Assert.assertEquals("1582-10-15", lunar.getSolar().toString());
  }

  @Test
  public void test64() {
    Lunar lunar = Lunar.fromYmd(1518, 1, 1);
    Assert.assertEquals("1518-02-10", lunar.getSolar().toString());
  }

  @Test
  public void test65() {
    Lunar lunar = Lunar.fromYmd(793, 1, 1);
    Assert.assertEquals("0793-02-15", lunar.getSolar().toString());
  }

  @Test
  public void test66() {
    Lunar lunar = Lunar.fromYmd(2025, -6, 1);
    Assert.assertEquals("2025-07-25", lunar.getSolar().toString());
  }

  @Test
  public void test67() {
    Lunar lunar = Lunar.fromYmd(2025, 6, 1);
    Assert.assertEquals("2025-06-25", lunar.getSolar().toString());
  }

  @Test
  public void test68() {
    Lunar lunar = Lunar.fromYmd(193, 1, 1);
    Assert.assertEquals("0193-02-19", lunar.getSolar().toString());
  }

  @Test
  public void test69() {
    Lunar lunar = Lunar.fromYmd(288, 1, 1);
    Assert.assertEquals("0288-02-19", lunar.getSolar().toString());
  }

  @Test
  public void test70() {
    Lunar lunar = Lunar.fromYmd(755, 1, 1);
    Assert.assertEquals("0755-02-16", lunar.getSolar().toString());
  }

  @Test
  public void test71() {
    Lunar lunar = Lunar.fromYmd(41, 1, 1);
    Assert.assertEquals("0041-02-20", lunar.getSolar().toString());
  }

  @Test
  public void test72() {
    Lunar lunar = Lunar.fromYmd(57, 1, 1);
    Assert.assertEquals("0057-02-23", lunar.getSolar().toString());
  }

  @Test
  public void test73() {
    Lunar lunar = Lunar.fromYmd(345, 1, 1);
    Assert.assertEquals("0345-02-18", lunar.getSolar().toString());
  }

  @Test
  public void test74() {
    Lunar lunar = Lunar.fromYmd(459, 1, 1);
    Assert.assertEquals("0459-02-18", lunar.getSolar().toString());
  }

  @Test
  public void test75() {
    Lunar lunar = Lunar.fromYmd(497, 1, 1);
    Assert.assertEquals("0497-02-18", lunar.getSolar().toString());
  }

  @Test
  public void test76() {
    Lunar lunar = Lunar.fromYmd(516, 1, 1);
    Assert.assertEquals("0516-02-18", lunar.getSolar().toString());
  }

  @Test
  public void test77() {
    Lunar lunar = Lunar.fromYmd(554, 1, 1);
    Assert.assertEquals("0554-02-18", lunar.getSolar().toString());
  }

  @Test
  public void test78() {
    Lunar lunar = Lunar.fromYmd(698, 1, 1);
    Assert.assertEquals("0698-02-16", lunar.getSolar().toString());
  }

  @Test
  public void test79() {
    Lunar lunar = Lunar.fromYmd(793, 1, 1);
    Assert.assertEquals("0793-02-15", lunar.getSolar().toString());
  }

  @Test
  public void test80() {
    Lunar lunar = Lunar.fromYmd(918, 1, 1);
    Assert.assertEquals("0918-02-14", lunar.getSolar().toString());
  }

  @Test
  public void test81() {
    Lunar lunar = Lunar.fromYmd(1013, 1, 1);
    Assert.assertEquals("1013-02-13", lunar.getSolar().toString());
  }

  @Test
  public void test82() {
    Lunar lunar = Lunar.fromYmd(1051, 1, 1);
    Assert.assertEquals("1051-02-14", lunar.getSolar().toString());
  }

  @Test
  public void test83() {
    Lunar lunar = Lunar.fromYmd(1070, 1, 1);
    Assert.assertEquals("1070-02-14", lunar.getSolar().toString());
  }

  @Test
  public void test84() {
    Lunar lunar = Lunar.fromYmd(1127, 1, 1);
    Assert.assertEquals("1127-02-13", lunar.getSolar().toString());
  }

  @Test
  public void test85() {
    Lunar lunar = Lunar.fromYmd(1146, 1, 1);
    Assert.assertEquals("1146-02-13", lunar.getSolar().toString());
  }

  @Test
  public void test86() {
    Lunar lunar = Lunar.fromYmd(1165, 1, 1);
    Assert.assertEquals("1165-02-13", lunar.getSolar().toString());
  }

  @Test
  public void test87() {
    Lunar lunar = Lunar.fromYmd(1423, 1, 1);
    Assert.assertEquals("1423-02-11", lunar.getSolar().toString());
  }

  @Test
  public void test88() {
    Lunar lunar = Lunar.fromYmd(1442, 1, 1);
    Assert.assertEquals("1442-02-11", lunar.getSolar().toString());
  }

  @Test
  public void test89() {
    Lunar lunar = Lunar.fromYmd(1461, 1, 1);
    Assert.assertEquals("1461-02-10", lunar.getSolar().toString());
  }

  @Test
  public void test90() {
    Lunar lunar = Lunar.fromYmd(1556, 1, 1);
    Assert.assertEquals("1556-02-11", lunar.getSolar().toString());
  }

  @Test
  public void test91() {
    Lunar lunar = Lunar.fromYmd(1537, 1, 1);
    Assert.assertEquals("1537-02-10", lunar.getSolar().toString());
  }

  @Test
  public void test92() {
    Lunar lunar = Solar.fromYmd(917, 12, 1).getLunar();
    Assert.assertEquals("九一七年闰十月十四", lunar.toString());
  }

  @Test
  public void test93() {
    Lunar lunar = Solar.fromYmd(917, 12, 31).getLunar();
    Assert.assertEquals("九一七年冬月十五", lunar.toString());
  }

  @Test
  public void test94() {
    Lunar lunar = Solar.fromYmd(918, 1, 1).getLunar();
    Assert.assertEquals("九一七年冬月十六", lunar.toString());
  }

  @Test
  public void test95() {
    Lunar lunar = Solar.fromYmd(2023, 10, 30).getLunar();
    Assert.assertEquals("闭", lunar.getZhiXing());
  }

  @Test
  public void test96() {
    Lunar lunar = Solar.fromYmd(2025, 2, 7).getLunar();
    Assert.assertEquals("[嫁娶, 祭祀, 祈福, 出行, 解除, 出火, 拆卸, 动土, 入宅, 移徙, 安床, 上梁, 栽种, 纳畜, 破土, 启钻, 安葬]", lunar.getDayYi().toString());
    Assert.assertEquals("[开市, 立券, 理发, 作灶]", lunar.getDayJi().toString());
  }

  @Test
  public void test97() {
    Lunar lunar = Solar.fromYmd(2025, 3, 5).getLunar();
    Assert.assertEquals("[破屋, 坏垣, 求医, 治病]", lunar.getDayYi().toString());
    Assert.assertEquals("[诸事不宜]", lunar.getDayJi().toString());
  }

  @Test
  public void test98() {
    Lunar lunar = Solar.fromYmd(2025, 5, 3).getLunar();
    Assert.assertEquals("[祭祀, 祈福, 求嗣, 斋醮, 沐浴, 纳畜, 入殓, 破土, 安葬]", lunar.getDayYi().toString());
    Assert.assertEquals("[移徙, 入宅, 嫁娶, 出行, 安床]", lunar.getDayJi().toString());
  }

  @Test
  public void test99() {
    Lunar lunar = Solar.fromYmd(2025, 5, 4).getLunar();
    Assert.assertEquals("[纳采, 祭祀, 祈福, 求嗣, 斋醮, 出行, 起基, 盖屋, 定磉, 安门, 入殓, 安葬]", lunar.getDayYi().toString());
    Assert.assertEquals("[嫁娶, 开市, 纳财, 出火]", lunar.getDayJi().toString());
  }

  @Test
  public void test100() {
    Solar solar = new Solar(2025, 9, 16);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("[时德, 阳德, 民日, 玉宇, 司命]", lunar.getDayJiShen().toString());
    Assert.assertEquals("[河魁, 死神, 天吏, 致死, 往亡]", lunar.getDayXiongSha().toString());
  }

  @Test
  public void test101() {
    Solar solar = new Solar(2025, 12, 15);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("[阳德, 六仪, 续世, 解神, 司命]", lunar.getDayJiShen().toString());
    Assert.assertEquals("[月破, 大耗, 灾煞, 天火, 厌对, 招摇, 五虚, 血忌]", lunar.getDayXiongSha().toString());
  }
}
