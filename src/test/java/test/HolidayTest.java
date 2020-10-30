package test;

import com.nlf.calendar.util.HolidayUtil;
import org.junit.Assert;
import org.junit.Test;

public class HolidayTest {

  @Test
  public void test(){
    Assert.assertEquals("2020-01-01 元旦节 2020-01-01",HolidayUtil.getHoliday("2020-01-01")+"");

    // 将2020-01-01修改为春节
    HolidayUtil.fix("202001011120200101");
    Assert.assertEquals("2020-01-01 春节 2020-01-01",HolidayUtil.getHoliday("2020-01-01")+"");

    // 追加2099-01-01为元旦节
    HolidayUtil.fix("209901010120990101");
    Assert.assertEquals("2099-01-01 元旦节 2099-01-01",HolidayUtil.getHoliday("2099-01-01")+"");

    // 将2020-01-01修改为春节，并追加2099-01-01为元旦节
    HolidayUtil.fix("202001011120200101209901010120990101");
    Assert.assertEquals("2020-01-01 春节 2020-01-01",HolidayUtil.getHoliday("2020-01-01")+"");
    Assert.assertEquals("2099-01-01 元旦节 2099-01-01",HolidayUtil.getHoliday("2099-01-01")+"");

    // 更改节假日名称
    String[] names = HolidayUtil.NAMES;
    names[0] = "元旦";
    names[1] = "大年初一";

    HolidayUtil.fix(names, null);
    Assert.assertEquals("2020-01-01 大年初一 2020-01-01",HolidayUtil.getHoliday("2020-01-01")+"");
    Assert.assertEquals("2099-01-01 元旦 2099-01-01",HolidayUtil.getHoliday("2099-01-01")+"");

    // 追加节假日名称和数据
    names = new String[12];
    for(int i=0,j=HolidayUtil.NAMES.length;i<j;i++){
      names[i] = HolidayUtil.NAMES[i];
    }
    names[9] = "我的生日";
    names[10] = "结婚纪念日";
    names[11] = "她的生日";

    HolidayUtil.fix(names, "20210529912021052920211111:12021111120211201;120211201");
    Assert.assertEquals("2021-05-29 我的生日 2021-05-29",HolidayUtil.getHoliday("2021-05-29")+"");
    Assert.assertEquals("2021-11-11 结婚纪念日 2021-11-11",HolidayUtil.getHoliday("2021-11-11")+"");
    Assert.assertEquals("2021-12-01 她的生日 2021-12-01",HolidayUtil.getHoliday("2021-12-01")+"");
  }
}
