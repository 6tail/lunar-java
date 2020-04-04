package sample;

import com.nlf.calendar.Solar;
import org.junit.Test;

/**
 * 阳历示例
 */
public class SolarTest {

  @Test
  public void test(){
    //阳历今天
    Solar date = new Solar();
    //输出阳历信息
    System.out.println(date.toFullString());
    //输出阴历信息
    System.out.println(date.getLunar().toFullString());
    System.out.println();
    //指定某个阳历日期
    date = new Solar(2016,9,8);
    System.out.println(date.toFullString());
    System.out.println(date.getLunar().toFullString());
  }

}
