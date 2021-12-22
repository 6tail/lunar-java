# lunar [![License](https://img.shields.io/badge/license-MIT-4EB1BA.svg?style=flat-square)](https://github.com/6tail/lunar-java/blob/master/LICENSE)

lunar是一款无第三方依赖的公历(阳历)、农历(阴历、老黄历)、道历、佛历工具，支持星座、儒略日、干支、生肖、节气、节日、彭祖百忌、吉神(喜神/福神/财神/阳贵神/阴贵神)方位、胎神方位、冲煞、纳音、星宿、八字、五行、十神、建除十二值星、青龙名堂等十二神、黄道日及吉凶、法定节假日及调休等。

> 支持java 1.5及以上版本。

> 干支纪年：支持以正月初一计，支持以立春当日计，支持以立春交接时刻计。

> 干支纪月：支持以节当日计，支持以节交接时刻计。

[English](https://github.com/6tail/lunar-java/blob/master/README_EN.md)

### Maven

```xml
<dependency>
  <groupId>cn.6tail</groupId>
  <artifactId>lunar</artifactId>
  <version>1.2.15</version>
</dependency>
```

### 下载jar

如果使用jar，建议下载最新的版本，bug将得到及时的修复。

[Download](https://github.com/6tail/lunar-java/releases)

## 示例

    import com.nlf.calendar.Lunar;
     
    /**
     * 阴历示例
     *
     */
    public class LunarSample{
      public static void main(String[] args){
        //今天
        //Lunar date = new Lunar();
         
        //指定阴历的某一天
        Lunar date = new Lunar(1986,4,21);
        System.out.println(date.toFullString());
        System.out.println(date.getSolar().toFullString());
      }
    }

输出结果：

    一九八六年四月廿一 丙寅(虎)年 癸巳(蛇)月 癸酉(鸡)日 子(鼠)时 纳音[炉中火 长流水 剑锋金 桑柘木] 星期四 北方玄武 星宿[斗木獬](吉) 彭祖百忌[癸不词讼理弱敌强 酉不会客醉坐颠狂] 喜神方位[巽](东南) 阳贵神方位[巽](东南) 阴贵神方位[震](正东) 福神方位[兑](正西) 财神方位[离](正南) 冲[(丁卯)兔] 煞[东]
    1986-05-29 00:00:00 星期四 双子座

## 文档

请移步至 [http://6tail.cn/calendar/api.html](http://6tail.cn/calendar/api.html "http://6tail.cn/calendar/api.html")

## 更新日志

v1.2.15 南京大XX纪念日更改为国家公祭日；新增初候、二候、三候；新增三元九运；新增道历戊日。

v1.2.14 佛历新增27宿；修复宜忌重复的问题；修复获取气时缺冬至的问题。

v1.2.13 新增道历Tao。

v1.2.12 修复星宿方位错误。

v1.2.11 修正胎神数据；增加福神流派。

v1.2.9 新增2022年法定假日。

v1.2.8 新增佛历Foto；更改Lunar中的getOtherFestivals方法为传统节日。

v1.2.7 修复儒略日转阳历秒数为60的错误。

v1.2.6 新增治水、分饼、耕田、得金、日禄；新增时辰LunarTime；新增获取当天的所有时辰。

v1.2.5 修复公元0至4年转阴历错误的问题。

v1.2.4 修复闰冬月、闰腊月的问题；修复日历不准的问题；修复物候错误；大运、小运、流年支持自定义轮数。

v1.2.3 修复除夕错误；代码优化。

v1.2.2 修正2016年国庆节数据；删除5月23日世界读书日；修复v1.2.0和v1.2.1的重大bug。

v1.2.0 支持0001到9999年。
