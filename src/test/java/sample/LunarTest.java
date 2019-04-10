package sample;

import com.nlf.calendar.Lunar;

import static org.junit.Assert.assertEquals;

/**
 * 农历示例
 */
public class LunarTest {
    private Object[][] data = new Object[][]{
            {1986, 4, 21, "1986-05-30"}
    };

    @org.junit.Test
    public void test() {
        Lunar date = new Lunar(Integer.parseInt(data[0][0].toString()), Integer.parseInt(data[0][1].toString()), Integer.parseInt(data[0][2].toString()));
        assertEquals(data[0][3].toString(), date.getSolar().next(1).toString());
    }

}
