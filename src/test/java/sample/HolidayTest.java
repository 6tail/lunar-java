package sample;

import com.nlf.calendar.Holiday;
import com.nlf.calendar.Solar;
import com.nlf.calendar.util.HolidayUtil;
import org.junit.Test;

import java.util.List;

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

  @Test
  public void test1(){
    StringBuilder s = new StringBuilder();
    for(int i = 2001; i <= 2024; i++) {
      List<Holiday> l = HolidayUtil.getHolidays(i);
      for (Holiday d : l) {
        int index = -1;
        for (int x = 0;x <HolidayUtil.NAMES.length;x++) {
          if (HolidayUtil.NAMES[x].equals(d.getName())) {
            index = x;
            break;
          }
        }
        if (index == -1) {
          throw new IllegalArgumentException(d.getName());
        }
        String day = d.getDay();
        String target = d.getTarget();
        Solar solar = Solar.fromYmd(Integer.parseInt(day.substring(0,4)), Integer.parseInt(day.substring(5,7)), Integer.parseInt(day.substring(8)));
        Solar targetSolar = Solar.fromYmd(Integer.parseInt(target.substring(0,4)), Integer.parseInt(target.substring(5,7)), Integer.parseInt(target.substring(8)));
        int diff = targetSolar.subtract(solar);
        s.append(String.format("%s%d%d%s%02d", d.getDay().replace("-",""), d.isWork() ? 0 : 1, index, diff < 0 ? "-" : "+", Math.abs(diff)));
      }
    }
    System.out.println(s);
  }
}
