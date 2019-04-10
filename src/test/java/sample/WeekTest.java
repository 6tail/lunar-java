package sample;

import com.nlf.calendar.Solar;
import com.nlf.calendar.SolarWeek;
import com.nlf.calendar.util.SolarUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 周示例
 */
public class WeekTest {
  private Object[][] data = new Object[][]{
          {6, 14, 2000, "2000.6.4"},
          {6, 14, 1996, "1996.6.4"},
          {6, 14, 2002, "2002.6.4"},
          {6, 29, 2000, "2000.7.2"},
          {6, 29, 1996, "1996.7.1"},
          {6, 29, 2002, "2002.7.1"},
          {6, 30, 2000, "2000.7.2"},
          {6, 30, 1996, "1996.7.1"},
          {6, 30, 2002, "2002.7.1"},
          {6, 31, 2000, "2000.7.2"},
          {6, 31, 1996, "1996.7.2"},
          {6, 31, 2002, "2002.7.2"},
          {7, 14, 2000, "2000.7.4"},
          {7, 14, 1996, "1996.7.3"},
          {7, 14, 2002, "2002.7.3"},
          {7, 29, 2000, "2000.8.1"},
          {7, 29, 1996, "1996.8.2"},
          {7, 29, 2002, "2002.8.2"},
  };
  @org.junit.Test
  public void test(){
    for (Object[] test : data) {
      SolarWeek week = new SolarWeek(Integer.parseInt(test[2].toString()), Integer.parseInt(test[0].toString()), Integer.parseInt(test[1].toString()),1);
      assertEquals(test[3].toString(), week.next(1,false).toString());
    }
  }



}
