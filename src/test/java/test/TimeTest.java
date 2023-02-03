package test;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.util.LunarUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 时辰测试
 *
 * @author 6tail
 */
public class TimeTest {

  private static final Map<String,String> ZHI = new LinkedHashMap<String, String>(){
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

  private static final Map<String,String> GAN = new LinkedHashMap<String, String>(){
    private static final long serialVersionUID = -1L;
    {
      //晚子时
      put("2020-4,5,23:00","戊");
      //早子时
      put("2020-4,5,00:59","丙");
      //晚子时
      put("2020-4,5,23:30","戊");

      put("2020-4,5,01:00","丁");
      put("2020-4,5,02:59","丁");
      put("2020-4,5,01:30","丁");

      put("2020-4,5,03:00","戊");
      put("2020-4,5,04:59","戊");
      put("2020-4,5,03:30","戊");

      put("2020-4,5,05:00","己");
      put("2020-4,5,06:59","己");
      put("2020-4,5,05:30","己");

      put("2020-4,5,07:00","庚");
      put("2020-4,5,08:59","庚");
      put("2020-4,5,07:30","庚");

      put("2020-4,5,09:00","辛");
      put("2020-4,5,10:59","辛");
      put("2020-4,5,09:30","辛");

      put("2020-4,5,11:00","壬");
      put("2020-4,5,12:59","壬");
      put("2020-4,5,11:30","壬");

      put("2020-4,5,13:00","癸");
      put("2020-4,5,14:59","癸");
      put("2020-4,5,13:30","癸");

      put("2020-4,5,15:00","甲");
      put("2020-4,5,16:59","甲");
      put("2020-4,5,15:30","甲");

      put("2020-4,5,17:00","乙");
      put("2020-4,5,18:59","乙");
      put("2020-4,5,17:30","乙");

      put("2020-4,5,19:00","丙");
      put("2020-4,5,20:59","丙");
      put("2020-4,5,19:30","丙");

      put("2020-4,5,21:00","丁");
      put("2020-4,5,22:59","丁");
      put("2020-4,5,21:30","丁");

      put("2020-4,5,null","丙");

      put("2020-4,5,","丙");
      put("2020-4,5,23:01:01","戊");
      put("2020-4,5,其他","丙");
      put("2020-4,5,0:90","丙");

      put("2020-4,5,21:01:01","丁");

      put("2020-4,2,23:00","壬");
      put("2020-4,2,11:20","丙");
    }
  };

  private static final Map<String,String> GAN_ZHI = new HashMap<String, String>(){
    private static final long serialVersionUID = -1L;
    {
      //晚子时
      put("2020-4,5,23:00","戊子");
      //早子时
      put("2020-4,5,00:59","丙子");
      //晚子时
      put("2020-4,5,23:30","戊子");

      put("2020-4,5,01:00","丁丑");
      put("2020-4,5,02:59","丁丑");
      put("2020-4,5,01:30","丁丑");

      put("2020-4,5,03:00","戊寅");
      put("2020-4,5,04:59","戊寅");
      put("2020-4,5,03:30","戊寅");

      put("2020-4,5,05:00","己卯");
      put("2020-4,5,06:59","己卯");
      put("2020-4,5,05:30","己卯");

      put("2020-4,5,07:00","庚辰");
      put("2020-4,5,08:59","庚辰");
      put("2020-4,5,07:30","庚辰");

      put("2020-4,5,09:00","辛巳");
      put("2020-4,5,10:59","辛巳");
      put("2020-4,5,09:30","辛巳");

      put("2020-4,5,11:00","壬午");
      put("2020-4,5,12:59","壬午");
      put("2020-4,5,11:30","壬午");

      put("2020-4,5,13:00","癸未");
      put("2020-4,5,14:59","癸未");
      put("2020-4,5,13:30","癸未");

      put("2020-4,5,15:00","甲申");
      put("2020-4,5,16:59","甲申");
      put("2020-4,5,15:30","甲申");

      put("2020-4,5,17:00","乙酉");
      put("2020-4,5,18:59","乙酉");
      put("2020-4,5,17:30","乙酉");

      put("2020-4,5,19:00","丙戌");
      put("2020-4,5,20:59","丙戌");
      put("2020-4,5,19:30","丙戌");

      put("2020-4,5,21:00","丁亥");
      put("2020-4,5,22:59","丁亥");
      put("2020-4,5,21:30","丁亥");

      put("2020-4,5,null","丙子");

      put("2020-4,5,","丙子");
      put("2020-4,5,23:01:01","戊子");
      put("2020-4,5,其他","丙子");
      put("2020-4,5,0:90","丙子");

      put("2020-4,5,20:21:01","丙戌");
      put("2020-4,5,21:01:01","丁亥");
      put("2020-4,5,01:21:01","丁丑");

      put("2020-4,2,23:00","壬子");
      put("2020-4,2,11:20","丙午");
      put("20204,28,23:20","甲子");
      put("20204,29,00:20","甲子");
    }
  };

  @Test
  public void testLunarTimeGanZhi(){
    for(Map.Entry<String,String> entry:GAN_ZHI.entrySet()){
      int hour = 0,minute = 0;
      String time = entry.getKey();
      int year = Integer.parseInt(time.substring(0,4));
      int month = Integer.parseInt(time.substring(4,time.indexOf(",")));
      int day = Integer.parseInt(time.substring(time.indexOf(",")+1,time.lastIndexOf(",")));
      String hm = time.substring(time.lastIndexOf(",")+1);
      if(hm.length()>=5){
        hour = Integer.parseInt(hm.substring(0,2),10);
        minute = Integer.parseInt(hm.substring(3,5),10);
      }
      Lunar lunar = Lunar.fromYmdHms(year,month,day,hour,minute,0);
      String ganZhi = entry.getValue();
      Assert.assertEquals(lunar.getYear()+"年"+lunar.getMonthInChinese()+"月"+lunar.getDayInChinese()+" "+hm, ganZhi, lunar.getTimeInGanZhi());
    }
  }
  @Test
  public void testLunarTimeGan(){
    for(Map.Entry<String,String> entry:GAN.entrySet()){
      int hour = 0,minute = 0;
      String time = entry.getKey();
      int year = Integer.parseInt(time.substring(0,4));
      int month = Integer.parseInt(time.substring(4,time.indexOf(",")));
      int day = Integer.parseInt(time.substring(time.indexOf(",")+1,time.lastIndexOf(",")));
      String hm = time.substring(time.lastIndexOf(",")+1);
      if(hm.length()>=5){
        hour = Integer.parseInt(hm.substring(0,2),10);
        minute = Integer.parseInt(hm.substring(3,5),10);
      }
      Lunar lunar = Lunar.fromYmdHms(year,month,day,hour,minute,0);
      String gan = entry.getValue();
      Assert.assertEquals(lunar.getYear()+"年"+lunar.getMonthInChinese()+"月"+lunar.getDayInChinese()+" "+hm, gan, lunar.getTimeGan());
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
