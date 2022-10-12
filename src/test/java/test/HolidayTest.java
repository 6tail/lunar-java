package test;

import com.nlf.calendar.Holiday;
import com.nlf.calendar.Solar;
import com.nlf.calendar.util.HolidayUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HolidayTest {

  @Test
  public void test2021() {
    Map<String, String> holidays = new HashMap<String, String>() {
      private static final long serialVersionUID = 1L;

      {
        put("2021-01-01", "2021-01-01 元旦节 2021-01-01");
        put("2021-01-02", "2021-01-02 元旦节 2021-01-01");
        put("2021-01-03", "2021-01-03 元旦节 2021-01-01");

        put("2021-02-11", "2021-02-11 春节 2021-02-12");
        put("2021-02-12", "2021-02-12 春节 2021-02-12");
        put("2021-02-13", "2021-02-13 春节 2021-02-12");
        put("2021-02-14", "2021-02-14 春节 2021-02-12");
        put("2021-02-15", "2021-02-15 春节 2021-02-12");
        put("2021-02-16", "2021-02-16 春节 2021-02-12");
        put("2021-02-17", "2021-02-17 春节 2021-02-12");

        put("2021-02-07", "2021-02-07 春节调休 2021-02-12");
        put("2021-02-20", "2021-02-20 春节调休 2021-02-12");

        put("2021-04-03", "2021-04-03 清明节 2021-04-04");
        put("2021-04-04", "2021-04-04 清明节 2021-04-04");
        put("2021-04-05", "2021-04-05 清明节 2021-04-04");

        put("2021-05-01", "2021-05-01 劳动节 2021-05-01");
        put("2021-05-02", "2021-05-02 劳动节 2021-05-01");
        put("2021-05-03", "2021-05-03 劳动节 2021-05-01");
        put("2021-05-04", "2021-05-04 劳动节 2021-05-01");
        put("2021-05-05", "2021-05-05 劳动节 2021-05-01");

        put("2021-04-25", "2021-04-25 劳动节调休 2021-05-01");
        put("2021-05-08", "2021-05-08 劳动节调休 2021-05-01");

        put("2021-06-12", "2021-06-12 端午节 2021-06-14");
        put("2021-06-13", "2021-06-13 端午节 2021-06-14");
        put("2021-06-14", "2021-06-14 端午节 2021-06-14");

        put("2021-09-19", "2021-09-19 中秋节 2021-09-21");
        put("2021-09-20", "2021-09-20 中秋节 2021-09-21");
        put("2021-09-21", "2021-09-21 中秋节 2021-09-21");

        put("2021-09-18", "2021-09-18 中秋节调休 2021-09-21");

        put("2021-10-01", "2021-10-01 国庆节 2021-10-01");
        put("2021-10-02", "2021-10-02 国庆节 2021-10-01");
        put("2021-10-03", "2021-10-03 国庆节 2021-10-01");
        put("2021-10-04", "2021-10-04 国庆节 2021-10-01");
        put("2021-10-05", "2021-10-05 国庆节 2021-10-01");
        put("2021-10-06", "2021-10-06 国庆节 2021-10-01");
        put("2021-10-07", "2021-10-07 国庆节 2021-10-01");

        put("2021-09-26", "2021-09-26 国庆节调休 2021-10-01");
        put("2021-10-09", "2021-10-09 国庆节调休 2021-10-01");
      }
    };
    Solar solar = Solar.fromYmd(2021, 1, 1);
    while (solar.toYmd().compareTo("2022-01-01") < 0) {
      String ymd = solar.toYmd();
      String holiday = holidays.get(ymd);
      Assert.assertEquals(ymd, holiday + "", HolidayUtil.getHoliday(ymd) + "");

      solar = solar.next(1);
    }
  }

  @Test
  public void test() {
    Assert.assertEquals("2020-01-01 元旦节 2020-01-01", HolidayUtil.getHoliday("2020-01-01") + "");

    // 将2020-01-01修改为春节
    HolidayUtil.fix("202001011120200101");
    Assert.assertEquals("2020-01-01 春节 2020-01-01", HolidayUtil.getHoliday("2020-01-01") + "");

    // 追加2099-01-01为元旦节
    HolidayUtil.fix("209901010120990101");
    Assert.assertEquals("2099-01-01 元旦节 2099-01-01", HolidayUtil.getHoliday("2099-01-01") + "");

    // 将2020-01-01修改为春节，并追加2099-01-01为元旦节
    HolidayUtil.fix("202001011120200101209901010120990101");
    Assert.assertEquals("2020-01-01 春节 2020-01-01", HolidayUtil.getHoliday("2020-01-01") + "");
    Assert.assertEquals("2099-01-01 元旦节 2099-01-01", HolidayUtil.getHoliday("2099-01-01") + "");

    // 自定义节假日名称
    String[] names = new String[HolidayUtil.NAMES.length];
    for (int i = 0, j = HolidayUtil.NAMES.length; i < j; i++) {
      names[i] = HolidayUtil.NAMES[i];
    }
    names[0] = "元旦";
    names[1] = "大年初一";

    HolidayUtil.fix(names, null);
    Assert.assertEquals("2020-01-01 大年初一 2020-01-01", HolidayUtil.getHoliday("2020-01-01") + "");
    Assert.assertEquals("2099-01-01 元旦 2099-01-01", HolidayUtil.getHoliday("2099-01-01") + "");

    // 追加节假日名称和数据
    names = new String[12];
    for (int i = 0, j = HolidayUtil.NAMES.length; i < j; i++) {
      names[i] = HolidayUtil.NAMES[i];
    }
    names[9] = "我的生日";
    names[10] = "结婚纪念日";
    names[11] = "她的生日";

    HolidayUtil.fix(names, "20210529912021052920211111:12021111120211201;120211201");
    Assert.assertEquals("2021-05-29 我的生日 2021-05-29", HolidayUtil.getHoliday("2021-05-29") + "");
    Assert.assertEquals("2021-11-11 结婚纪念日 2021-11-11", HolidayUtil.getHoliday("2021-11-11") + "");
    Assert.assertEquals("2021-12-01 她的生日 2021-12-01", HolidayUtil.getHoliday("2021-12-01") + "");
  }

  @Test
  public void test1() {
    Holiday holiday = HolidayUtil.getHoliday(2016,10,4);
    Assert.assertNotNull(holiday);
    Assert.assertNotNull(holiday.getTarget());
    Assert.assertEquals("2016-10-01",holiday.getTarget());
  }

  @Test
  public void testRemove() {
    // 设置默认的节假日名称
    HolidayUtil.fix(HolidayUtil.NAMES, null);
    Holiday holiday = HolidayUtil.getHoliday(2010,1,1);
    Assert.assertNotNull(holiday);
    Assert.assertEquals("元旦节",holiday.getName());

    HolidayUtil.fix("20100101~000000000000000000000000000");
    holiday = HolidayUtil.getHoliday(2010,1,1);
    Assert.assertNull(holiday);
  }
}
