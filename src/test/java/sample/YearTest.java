package sample;

import com.nlf.calendar.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 年份示例，我们来做一个日历吧
 */
public class YearTest {
    private Object[][] data = new Object[][]{
            {2000, "2001"},
            {1996, "1997"},
            {2002, "2003"},
    };

    @org.junit.Test
    public void test() {
        for (Object[] test : data) {
            SolarYear year = new SolarYear(Integer.parseInt(test[0].toString()));
            assertEquals(test[1].toString(), year.next(1).toString());
        }
    }

}
