package sample;

import com.nlf.calendar.Solar;
import com.nlf.calendar.SolarHalfYear;
import com.nlf.calendar.SolarMonth;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 月份示例
 */
public class MonthTest {
  private Object[][] data = new Object[][]{
          //强一般等价类 P73
          {6, 2000, "2000-7"},
          {6, 1996, "1996-7"},
          {6, 2002, "2002-7"},
          {6, 2000, "2000-7"},
          {6, 1996, "1996-7"},
          {6, 2002, "2002-7"},
          {6, 2000, "2000-7"},
          {6, 1996, "1996-7"},
          {6, 2002, "2002-7"},
          {6, 2000, "2000-7"},
          {6, 1996, "1996-7"},
          {6, 2002, "2002-7"},
          {7, 2000, "2000-8"},
          {7, 1996, "1996-8"},
          {7, 2002, "2002-8"},
          {7, 2000, "2000-8"},
          {7, 1996, "1996-8"},
          {7, 2002, "2002-8"},
          {7, 2000, "2000-8"},
          {7, 1996, "1996-8"},
          {7, 2002, "2002-8"},
          {7, 2000, "2000-8"},
          {7, 1996, "1996-8"},
          {7, 2002, "2002-8"},
          {2, 2000, "2000-3"},
          {2, 1996, "1996-3"},
          {2, 2002, "2002-3"},
          {2, 2000, "2000-3"},
          {2, 1996, "1996-3"},
          {2, 2002, "2002-3"},
          {2, 2000, "2000-3"},
          {2, 1996, "1996-3"},
          {2, 2002, "2002-3"},
          {2, 2000, "2000-3"},
          {2, 1996, "1996-3"},
          {2, 2002, "2002-3"},
  };
  @org.junit.Test
  public void test() {
    for (Object[] test : data) {
      SolarMonth month = new SolarMonth(Integer.parseInt(test[1].toString()), Integer.parseInt(test[0].toString()));
      assertEquals(test[2].toString(), month.next(1).toString());
    }
  }


}
