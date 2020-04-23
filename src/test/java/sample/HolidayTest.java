package sample;

import com.nlf.calendar.util.HolidayUtil;
import org.junit.Test;

public class HolidayTest {

  @Test
  public void test(){
    System.out.println(HolidayUtil.getHolidays("2017-10"));
    System.out.println(HolidayUtil.getHoliday("2020-01-01"));
    System.out.println(HolidayUtil.getHoliday("20200102"));
    System.out.println(HolidayUtil.getHolidays("202002"));
    System.out.println(HolidayUtil.getHolidays("20200201"));
    System.out.println(HolidayUtil.getHoliday(1999,1,5));
    System.out.println(HolidayUtil.getHoliday(2019,1,1));
    System.out.println(HolidayUtil.getHolidays(2020,5));
    System.out.println(HolidayUtil.getHolidays(2020));

    System.out.println(HolidayUtil.getHolidaysByTarget("20200501"));
    System.out.println(HolidayUtil.getHolidaysByTarget(2011,1,1));
  }
}
