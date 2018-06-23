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
        StringBuilder s = new StringBuilder();
        s.append(d.getDay()<10?"0":"");
        s.append(d.getDay());
        s.append(" ");
        s.append(lunar.getMonthInChinese());
        s.append("月");
        s.append(lunar.getDayInChinese());
        s.append(" ");
        s.append("星期");
        s.append(d.getWeekInChinese());
        s.append(" ");
        s.append(d.getFestivals());
        s.append(lunar.getFestivals());
        s.append(lunar.getJie());
        s.append(lunar.getQi());
        System.out.println(s.toString());
      }
    }
  }

}
