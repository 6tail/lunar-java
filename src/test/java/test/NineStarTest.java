package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.LunarMonth;
import com.nlf.calendar.LunarYear;
import com.nlf.calendar.Solar;
import org.junit.Assert;
import org.junit.Test;

public class NineStarTest {
    @Test
    public void test1() {
        Lunar lunar = Solar.fromYmd(1985, 2, 19).getLunar();
        Assert.assertEquals("六", lunar.getYearNineStar().getNumber());
        Assert.assertEquals("五黄土玉衡", lunar.getDayNineStar().toString());
    }

    @Test
    public void test2() {
        Lunar lunar = Lunar.fromYmd(2022, 1, 1);
        Assert.assertEquals("六白金开阳", lunar.getYearNineStar().toString());
        Assert.assertEquals("四绿木天权", lunar.getDayNineStar().toString());
    }

    @Test
    public void test3() {
        Lunar lunar = Lunar.fromYmd(2033, 1, 1);
        Assert.assertEquals("四绿木天权", lunar.getYearNineStar().toString());
        Assert.assertEquals("一白水天枢", lunar.getDayNineStar().toString());
    }

    @Test
    public void test4() {
        LunarYear y = LunarYear.fromYear(1985);
        Assert.assertEquals("六白金开阳", y.getNineStar().toString());
    }

    @Test
    public void test5() {
        LunarYear y = LunarYear.fromYear(2022);
        Assert.assertEquals("五黄土玉衡", y.getNineStar().toString());
    }

    @Test
    public void test6() {
        LunarYear y = LunarYear.fromYear(2033);
        Assert.assertEquals("三碧木天玑", y.getNineStar().toString());
    }

    @Test
    public void test7() {
        LunarMonth m = LunarMonth.fromYm(1985, 2);
        Assert.assertEquals("四绿木天权", m.getNineStar().toString());
    }

    @Test
    public void test8() {
        LunarMonth m = LunarMonth.fromYm(2022, 1);
        Assert.assertEquals("二黑土天璇", m.getNineStar().toString());
    }

    @Test
    public void test9() {
        LunarMonth m = LunarMonth.fromYm(2033, 1);
        Assert.assertEquals("五黄土玉衡", m.getNineStar().toString());
    }

    @Test
    public void test10() {
        Lunar d = Lunar.fromYmdHms(2033, 1, 1, 12, 0,0);
        Assert.assertEquals("七赤金摇光", d.getTimeNineStar().toString());
    }

    @Test
    public void test11() {
        Lunar d = Lunar.fromYmdHms(2011, 5, 3, 23, 0,0);
        Assert.assertEquals("七赤金摇光", d.getTimeNineStar().toString());
    }

    @Test
    public void test12() {
        LunarMonth m = LunarMonth.fromYm(2024, 11);
        Assert.assertEquals("四绿木天权", m.getNineStar().toString());
        m = LunarMonth.fromYm(2024, 12);
        Assert.assertEquals("三碧木天玑", m.getNineStar().toString());
    }

}
