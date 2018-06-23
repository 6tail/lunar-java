package sample;

import com.nlf.calendar.*;
import org.junit.Test;

/**
 * 半年示例
 */
public class HalfYearTest {

  @Test
  public void test(){
    SolarHalfYear halfYear = new SolarHalfYear();
    System.out.println(halfYear);
    System.out.println(halfYear.toFullString());

    for(SolarMonth month:halfYear.getMonths()){
      System.out.println(month.toFullString());
    }
  }

}
