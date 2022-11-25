package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

public class NineStarTest {
    @Test
    public void test1() {
        Lunar lunar = Solar.fromYmd(1985, 2, 19).getLunar();
        Assert.assertEquals("六", lunar.getYearNineStar().getNumber());
    }

    @Test
    public void test2() {
        Lunar lunar = Lunar.fromYmd(2022, 1, 1);
        Assert.assertEquals("六白金开阳", lunar.getYearNineStar().toString());
    }

    @Test
    public void test3() {
        Lunar lunar = Lunar.fromYmd(2033, 1, 1);
        Assert.assertEquals("四绿木天权", lunar.getYearNineStar().toString());
    }
}
