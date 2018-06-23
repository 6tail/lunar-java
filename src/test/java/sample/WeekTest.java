package sample;

import com.nlf.calendar.Solar;
import com.nlf.calendar.SolarWeek;
import com.nlf.calendar.util.SolarUtil;
import org.junit.Test;

/**
 * 周示例
 */
public class WeekTest {

  @Test
  public void test(){
    //一周的开始从星期一开始计
    SolarWeek week = new SolarWeek(1);
    System.out.println(week);
    System.out.println(week.toFullString());

    System.out.println("本月共几周："+ SolarUtil.getWeeksOfMonth(week.getYear(),week.getMonth(),1));
    System.out.println("本周第一天："+week.getFirstDay());
    //遍历本周的每一天
    for(Solar day:week.getDays()){
      System.out.println(day);
    }
  }

}
