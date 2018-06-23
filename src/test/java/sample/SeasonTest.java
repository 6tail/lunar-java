package sample;

import com.nlf.calendar.SolarMonth;
import com.nlf.calendar.SolarSeason;
import org.junit.Test;

/**
 * 季度示例
 */
public class SeasonTest {

  @Test
  public void test(){
    SolarSeason season = new SolarSeason();
    System.out.println(season);
    System.out.println(season.toFullString());

    for(SolarMonth month:season.getMonths()){
      System.out.println(month.toFullString());
    }
  }

}
