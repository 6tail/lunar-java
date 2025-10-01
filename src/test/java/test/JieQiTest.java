package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 节气测试
 *
 * @author 6tail
 */
public class JieQiTest {

  @Test
  public void test2022() {

    Map<String,String> jieQi = new HashMap<String, String>(){
      private static final long serialVersionUID = 1L;
      {
        put("冬至","2021-12-21 23:59:19");
        put("小寒","2022-01-05 17:14:04");
        put("大寒","2022-01-20 10:39:06");
        put("立春","2022-02-04 04:50:47");
        put("雨水","2022-02-19 00:43:01");
        put("惊蛰","2022-03-05 22:43:45");
        put("春分","2022-03-20 23:33:26");
        put("清明","2022-04-05 03:20:14");
        put("谷雨","2022-04-20 10:24:18");
        put("立夏","2022-05-05 20:25:57");
        put("小满","2022-05-21 09:22:36");
        put("芒种","2022-06-06 00:25:49");
        put("夏至","2022-06-21 17:13:51");
        put("小暑","2022-07-07 10:38:01");
        put("大暑","2022-07-23 04:07:00");
        put("立秋","2022-08-07 20:29:08");
        put("处暑","2022-08-23 11:16:11");
        put("白露","2022-09-07 23:32:18");
        put("秋分","2022-09-23 09:03:43");
        put("寒露","2022-10-08 15:22:28");
        put("霜降","2022-10-23 18:35:43");
        put("立冬","2022-11-07 18:45:30");
        put("小雪","2022-11-22 16:20:30");
        put("大雪","2022-12-07 11:46:16");
      }
    };

    Solar solar = Solar.fromYmd(2022, 7, 15);
    Map<String,Solar> result = solar.getLunar().getJieQiTable();
    for(Map.Entry<String,String> entry:jieQi.entrySet()){
      String name = entry.getKey();
      Assert.assertEquals(name, entry.getValue(), result.get(name).toYmdHms());
    }
  }

  @Test
  public void test1986() {

    Map<String,String> jieQi = new HashMap<String, String>(){
      private static final long serialVersionUID = 1L;
      {
        put("冬至","1985-12-22 06:07:40");
        put("小寒","1986-01-05 23:28:02");
        put("大寒","1986-01-20 16:46:12");
        put("立春","1986-02-04 11:07:42");
        put("雨水","1986-02-19 06:57:31");
        put("惊蛰","1986-03-06 05:12:08");
        put("春分","1986-03-21 06:02:41");
        put("清明","1986-04-05 10:06:07");
        put("谷雨","1986-04-20 17:12:08");
        put("立夏","1986-05-06 03:30:36");
        put("小满","1986-05-21 16:27:55");
        put("芒种","1986-06-06 07:44:23");
        put("夏至","1986-06-22 00:29:57");
        put("小暑","1986-07-07 18:00:45");
        put("大暑","1986-07-23 11:24:23");
        put("立秋","1986-08-08 03:45:36");
        put("处暑","1986-08-23 18:25:47");
        put("白露","1986-09-08 06:34:37");
        put("秋分","1986-09-23 15:58:52");
        put("寒露","1986-10-08 22:06:45");
        put("霜降","1986-10-24 01:14:11");
        put("立冬","1986-11-08 01:12:49");
        put("小雪","1986-11-22 22:44:20");
        put("大雪","1986-12-07 18:00:56");
      }
    };

    Solar solar = Solar.fromYmd(1986, 7, 15);
    Map<String,Solar> result = solar.getLunar().getJieQiTable();
    for(Map.Entry<String,String> entry:jieQi.entrySet()){
      String name = entry.getKey();
      Assert.assertEquals(name, entry.getValue(), result.get(name).toYmdHms());
    }
  }

  @Test
  public void test() {
    Solar solar = Solar.fromYmd(1986,1,5);
    Lunar lunar = solar.getLunar();
    Assert.assertEquals("小寒", lunar.getJie());
    Assert.assertEquals("小寒", lunar.getJieQi());
    Assert.assertEquals("小寒", lunar.getCurrentJieQi().getName());
    Assert.assertEquals("小寒", lunar.getCurrentJie().getName());
    Assert.assertNull(lunar.getCurrentQi());
    Assert.assertEquals("", lunar.getQi());
    Assert.assertEquals("大雪", lunar.getPrevJie().getName());
    Assert.assertEquals("冬至", lunar.getPrevQi().getName());
    Assert.assertEquals("冬至", lunar.getPrevJieQi().getName());

    solar = Solar.fromYmdHms(1986,1,20,17,0,0);
    lunar = solar.getLunar();
    Assert.assertEquals("大寒", lunar.getQi());
    Assert.assertEquals("大寒", lunar.getJieQi());
    Assert.assertEquals("大寒", lunar.getCurrentJieQi().getName());
    Assert.assertEquals("大寒", lunar.getCurrentQi().getName());
    Assert.assertNull(lunar.getCurrentJie());
    Assert.assertEquals("", lunar.getJie());
    Assert.assertEquals("立春", lunar.getNextJie().getName());
    Assert.assertEquals("雨水", lunar.getNextQi().getName());
    Assert.assertEquals("立春", lunar.getNextJieQi().getName());
    solar = Solar.fromYmdHms(1986,1,20,14,0,0);
    lunar = solar.getLunar();
    Assert.assertEquals("小寒", lunar.getPrevJie().getName());
    Assert.assertEquals("冬至", lunar.getPrevQi().getName());
    Assert.assertEquals("小寒", lunar.getPrevJieQi().getName());

    solar = Solar.fromYmd(1986,12,7);
    lunar = solar.getLunar();
    Assert.assertEquals("大雪", lunar.getJie());
    Assert.assertEquals("大雪", lunar.getJieQi());
    Assert.assertEquals("大雪", lunar.getCurrentJieQi().getName());
    Assert.assertEquals("大雪", lunar.getCurrentJie().getName());
    Assert.assertNull(lunar.getCurrentQi());
    Assert.assertEquals("", lunar.getQi());
    Assert.assertEquals("大雪", lunar.getNextJie().getName());
    Assert.assertEquals("冬至", lunar.getNextQi().getName());
    Assert.assertEquals("大雪", lunar.getNextJieQi().getName());

    solar = Solar.fromYmd(1986,1,1);
    lunar = solar.getLunar();
    Assert.assertEquals("", lunar.getJie());
    Assert.assertEquals("", lunar.getQi());
    Assert.assertEquals("", lunar.getJieQi());
    Assert.assertNull(lunar.getCurrentJieQi());
    Assert.assertNull(lunar.getCurrentJie());
    Assert.assertNull(lunar.getCurrentQi());
    Assert.assertEquals("大雪", lunar.getPrevJie().getName());
    Assert.assertEquals("冬至", lunar.getPrevQi().getName());
    Assert.assertEquals("冬至", lunar.getPrevJieQi().getName());
    Assert.assertEquals("小寒", lunar.getNextJie().getName());
    Assert.assertEquals("大寒", lunar.getNextQi().getName());
    Assert.assertEquals("小寒", lunar.getNextJieQi().getName());


    solar = Solar.fromYmd(2012,12,25);
    lunar = solar.getLunar();
    Assert.assertEquals("", lunar.getJie());
    Assert.assertEquals("", lunar.getQi());
    Assert.assertEquals("", lunar.getJieQi());
    Assert.assertNull(lunar.getCurrentJie());
    Assert.assertNull(lunar.getCurrentQi());
    Assert.assertNull(lunar.getCurrentJieQi());

    Assert.assertEquals("小寒", lunar.getNextJie().getName());
    Assert.assertEquals("大寒", lunar.getNextQi().getName());
    Assert.assertEquals("小寒", lunar.getNextJieQi().getName());

    Assert.assertEquals("大雪", lunar.getPrevJie().getName());
    Assert.assertEquals("冬至", lunar.getPrevQi().getName());
    Assert.assertEquals("冬至", lunar.getPrevJieQi().getName());
  }

  @Test
  public void test7() {
    Lunar lunar = Lunar.fromYmd(2012, 9, 1);
    Assert.assertEquals("2012-09-07 13:29:01", lunar.getJieQiTable().get("白露").toYmdHms());
  }

  @Test
  public void test8() {
    Lunar lunar = Lunar.fromYmd(2050, 12, 1);
    Assert.assertEquals("2050-12-07 06:41:54", lunar.getJieQiTable().get("DA_XUE").toYmdHms());
  }

  @Test
  public void test9() {
    Solar solar = Solar.fromYmd(2021, 12, 21);
    Assert.assertEquals("冬至", solar.getLunar().getJieQi());
    Assert.assertEquals("", solar.getLunar().getJie());
    Assert.assertEquals("冬至", solar.getLunar().getQi());
  }

  @Test
  public void test10() {
    Lunar lunar = Solar.fromYmd(2023, 6, 1).getLunar();
    Assert.assertEquals("2022-12-22 05:48:12", lunar.getJieQiTable().get("冬至").toYmdHms());
  }

  @Test
  public void test11() {
    Lunar lunar = Solar.fromYmd(2025, 3, 5).getLunar();
    Assert.assertEquals("2025-03-05 16:07:18", lunar.getJieQiTable().get("惊蛰").toYmdHms());
  }

}
