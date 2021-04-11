package sample;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.SolarMonth;
import com.nlf.calendar.SolarYear;
import org.junit.Test;

/**
 * 年份示例，我们来做一个日历吧
 */
public class YearTest {

  @Test
  public void test(){
    //明年
    SolarYear year = new SolarYear().next(1);
    System.out.println("==="+year.getYear()+"年===");
    //遍历明年的每个月
    for(SolarMonth m:year.getMonths()){
      System.out.println("---"+m.getMonth()+"月---");
      //遍历当月的每一天
      for(Solar d:m.getDays()){
        //获取阴历
        Lunar lunar = d.getLunar();
        String s = (d.getDay() < 10 ? "0" : "") +
            d.getDay() +
            " " +
            lunar.getMonthInChinese() +
            "月" +
            lunar.getDayInChinese() +
            " " +
            "星期" +
            d.getWeekInChinese() +
            " " +
            d.getFestivals() +
            lunar.getFestivals() +
            lunar.getJie() +
            lunar.getQi();
        System.out.println(s);
      }
    }
  }

}
