package sample;

import com.nlf.calendar.LunarMonth;
import com.nlf.calendar.Solar;
import com.nlf.calendar.SolarMonth;
import org.junit.Test;

/**
 * 月份示例
 */
public class MonthTest {

  @Test
  public void test() {
    //下个月
    SolarMonth month = new SolarMonth().next(1);
    //遍历每一天
    for (Solar d : month.getDays()) {
      //输出阳历信息
      System.out.println(d.toFullString());
      //输出阴历信息
      System.out.println(d.getLunar().toFullString());
      System.out.println();
    }
  }

  @Test
  public void test1() {
    LunarMonth month = LunarMonth.fromYm(2020, 1);
    // 顺推
    for (int i = 0; i < 60; i++) {
      System.out.println(month.next(i).toString());
    }
  }

  @Test
  public void test2() {
    LunarMonth month = LunarMonth.fromYm(2020, 1);
    // 倒推
    for (int i = 0; i < 60; i++) {
      System.out.println(month.next(-i).toString());
    }
  }

}
