# lunar [![License](https://img.shields.io/badge/license-MIT-4EB1BA.svg?style=flat-square)](https://github.com/6tail/lunar/blob/master/LICENSE)

lunar是一个无依赖的支持阳历和阴历的日历工具库。

> 支持java 1.5及以上版本。

[English](https://github.com/6tail/lunar-java/blob/master/README_EN.md)

### 稳定版本

```xml
<dependency>
  <groupId>cn.6tail</groupId>
  <artifactId>lunar</artifactId>
  <version>1.0.0</version>
</dependency>
```
 
### 快照版本

```xml
<repository>
  <id>sonatype</id>
  <url>https://oss.sonatype.org/content/groups/public/</url>
  <snapshots>
    <enabled>true</enabled>
    <updatePolicy>daily</updatePolicy>
    <checksumPolicy>warn</checksumPolicy>
  </snapshots>
</repository>
```

```xml
<dependency>
  <groupId>cn.6tail</groupId>
  <artifactId>lunar</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 下载jar

如果使用jar，建议下载最新的SNAPSHOT版本，bug将得到及时的修复。

[Download](https://oss.sonatype.org/content/groups/public/cn/6tail/lunar/)

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

    壹玖捌陆年肆月廿一 丙寅(虎)年 癸巳(蛇)月 癸酉(鸡)日 纳音[炉中火 长流水 剑锋金] 北方玄武 危月燕 彭祖百忌[癸不词讼理弱敌强 酉不会客醉坐颠狂] 喜神方位[巽](东南) 阳贵神方位[巽](东南) 阴贵神方位[震](正东) 福神方位[兑](正西) 财神方位[离](正南) 冲[(丁卯)兔] 刹[东]
    1986-05-29 星期四 双子座

## 文档

请移步至 [http://6tail.cn/calendar/api.html](http://6tail.cn/calendar/api.html "http://6tail.cn/calendar/api.html")

## 联系

<a target="_blank" href="https://jq.qq.com/?_wv=1027&k=5F9Pbf0"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="lunar" title="lunar"></a>

