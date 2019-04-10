package sample;

import com.nlf.calendar.SolarHalfYear;
import com.nlf.calendar.SolarMonth;
import com.nlf.calendar.SolarSeason;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 季度示例
 */
public class SeasonTest {
  private Object[][] data = new Object[][]{
          //强一般等价类 P73
          {6, 2000, "2000.3"},
          {6, 1996, "1996.3"},
          {6, 2002, "2002.3"},
          {6, 2000, "2000.3"},
          {6, 1996, "1996.3"},
          {6, 2002, "2002.3"},
          {6, 2000, "2000.3"},
          {6, 1996, "1996.3"},
          {6, 2002, "2002.3"},
          {6, 2000, "2000.3"},
          {6, 1996, "1996.3"},
          {6, 2002, "2002.3"},
          {7, 2000, "2000.4"},
          {7, 1996, "1996.4"},
          {7, 2002, "2002.4"},
          {7, 2000, "2000.4"},
          {7, 1996, "1996.4"},
          {7, 2002, "2002.4"},
          {7, 2000, "2000.4"},
          {7, 1996, "1996.4"},
          {7, 2002, "2002.4"},
          {7, 2000, "2000.4"},
          {7, 1996, "1996.4"},
          {7, 2002, "2002.4"},
          {2, 2000, "2000.2"},
          {2, 1996, "1996.2"},
          {2, 2002, "2002.2"},
          {2, 2000, "2000.2"},
          {2, 1996, "1996.2"},
          {2, 2002, "2002.2"},
          {2, 2000, "2000.2"},
          {2, 1996, "1996.2"},
          {2, 2002, "2002.2"},
          {2, 2000, "2000.2"},
          {2, 1996, "1996.2"},
          {2, 2002, "2002.2"},
  };
  @org.junit.Test
  public void test(){
    for (Object[] test : data) {
      SolarSeason season = new SolarSeason(Integer.parseInt(test[1].toString()), Integer.parseInt(test[0].toString()));
      assertEquals(test[2].toString(), season.next(1).toString());
    }
  }

}
