package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.util.LunarUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 时辰测试
 *
 * @author 6tail
 */
public class TimeTest {

  private static final Map<String,String> ZHI = new HashMap<String, String>(){
    private static final long serialVersionUID = -1L;
    {
      put("23:00","子");
      put("00:59","子");
      put("23:30","子");

      put("01:00","丑");
      put("02:59","丑");
      put("01:30","丑");

      put("03:00","寅");
      put("04:59","寅");
      put("03:30","寅");

      put("05:00","卯");
      put("06:59","卯");
      put("05:30","卯");

      put("07:00","辰");
      put("08:59","辰");
      put("07:30","辰");

      put("09:00","巳");
      put("10:59","巳");
      put("09:30","巳");

      put("11:00","午");
      put("12:59","午");
      put("11:30","午");

      put("13:00","未");
      put("14:59","未");
      put("13:30","未");

      put("15:00","申");
      put("16:59","申");
      put("15:30","申");

      put("17:00","酉");
      put("18:59","酉");
      put("17:30","酉");

      put("19:00","戌");
      put("20:59","戌");
      put("19:30","戌");

      put("21:00","亥");
      put("22:59","亥");
      put("21:30","亥");

      put(null,"子");

      put("","子");
      put("23:01:01","子");
      put("其他","子");

      put("21:01:01","亥");
    }
  };

  private static final Map<String,String> GAN = new HashMap<String, String>(){
    private static final long serialVersionUID = -1L;
    {
      put("23:00","甲");
      put("00:59","甲");
      put("23:30","甲");

      put("01:00","乙");
      put("02:59","乙");
      put("01:30","乙");

      put("03:00","丙");
      put("04:59","丙");
      put("03:30","丙");

      put("05:00","丁");
      put("06:59","丁");
      put("05:30","丁");

      put("07:00","戊");
      put("08:59","戊");
      put("07:30","戊");

      put("09:00","己");
      put("10:59","己");
      put("09:30","己");

      put("11:00","庚");
      put("12:59","庚");
      put("11:30","庚");

      put("13:00","辛");
      put("14:59","辛");
      put("13:30","辛");

      put("15:00","壬");
      put("16:59","壬");
      put("15:30","壬");

      put("17:00","癸");
      put("18:59","癸");
      put("17:30","癸");

      put("19:00","甲");
      put("20:59","甲");
      put("19:30","甲");

      put("21:00","乙");
      put("22:59","乙");
      put("21:30","乙");

      put(null,"甲");

      put("","甲");
      put("23:01:01","甲");
      put("其他","甲");
      put("80:90","甲");

      put("21:01:01","乙");
    }
  };

  private static final Map<String,String> GAN_ZHI = new HashMap<String, String>(){
    private static final long serialVersionUID = -1L;
    {
      put("23:00","甲子");
      put("00:59","甲子");
      put("23:30","甲子");

      put("01:00","乙丑");
      put("02:59","乙丑");
      put("01:30","乙丑");

      put("03:00","丙寅");
      put("04:59","丙寅");
      put("03:30","丙寅");

      put("05:00","丁卯");
      put("06:59","丁卯");
      put("05:30","丁卯");

      put("07:00","戊辰");
      put("08:59","戊辰");
      put("07:30","戊辰");

      put("09:00","己巳");
      put("10:59","己巳");
      put("09:30","己巳");

      put("11:00","庚午");
      put("12:59","庚午");
      put("11:30","庚午");

      put("13:00","辛未");
      put("14:59","辛未");
      put("13:30","辛未");

      put("15:00","壬申");
      put("16:59","壬申");
      put("15:30","壬申");

      put("17:00","癸酉");
      put("18:59","癸酉");
      put("17:30","癸酉");

      put("19:00","甲戌");
      put("20:59","甲戌");
      put("19:30","甲戌");

      put("21:00","乙亥");
      put("22:59","乙亥");
      put("21:30","乙亥");

      put(null,"甲子");

      put("","甲子");
      put("23:01:01","甲子");
      put("其他","甲子");
      put("80:90","甲子");

      put("20:21:01","甲戌");
      put("21:01:01","乙亥");
      put("01:21:01","乙丑");
    }
  };

  @Test
  public void testLunarTimeGanZhi(){
    for(Map.Entry<String,String> entry:GAN_ZHI.entrySet()){
      int hour = 0,minute = 0;
      String hm = entry.getKey();
      if(null!=hm&&hm.length()>=5){
        hour = Integer.parseInt(hm.substring(0,2),10);
        minute = Integer.parseInt(hm.substring(3,5),10);
      }
      Lunar lunar = Lunar.fromYmdHm(2020,1,1,hour,minute);
      String ganZhi = entry.getValue();
      Assert.assertEquals(hm, ganZhi, lunar.getTimeInGanZhi());
    }
  }
  @Test
  public void testLunarTimeGan(){
    for(Map.Entry<String,String> entry:GAN.entrySet()){
      int hour = 0,minute = 0;
      String hm = entry.getKey();
      if(null!=hm&&hm.length()>=5){
        hour = Integer.parseInt(hm.substring(0,2),10);
        minute = Integer.parseInt(hm.substring(3,5),10);
      }
      Lunar lunar = Lunar.fromYmdHm(2020,1,1,hour,minute);
      String gan = entry.getValue();
      Assert.assertEquals(hm, gan, lunar.getTimeGan());
    }
  }


  @Test
  public void test(){
    for(Map.Entry<String,String> entry:ZHI.entrySet()){
      String hm = entry.getKey();
      String zhi = entry.getValue();
      Assert.assertEquals(hm, zhi, LunarUtil.convertTime(hm));
    }
  }
}
