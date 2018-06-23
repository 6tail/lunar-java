# lunar [![License](https://img.shields.io/badge/license-MIT-4EB1BA.svg?style=flat-square)](https://github.com/6tail/lunar/blob/master/LICENSE)

lunar is a calendar library for Solar and Chinese Lunar.

> Support since java 1.5

[简体中文](https://github.com/6tail/lunar-java/blob/master/README_ZH.md)

### Release

```xml
<dependency>
  <groupId>cn.6tail</groupId>
  <artifactId>lunar</artifactId>
  <version>1.0.0</version>
</dependency>
```
 
### Snapshot

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

### Download

If you will use jars in your projects, I suggest you to download latest snapshot versions with less bugs.

[Download](https://oss.sonatype.org/content/groups/public/cn/6tail/lunar/)

## Example

    import com.nlf.calendar.Lunar;
     
    /**
     * 阴历示例
     *
     */
    public class LunarSample{
      public static void main(String[] args){
        //今天
        Lunar date = new Lunar();
        //输出阴历信息
        System.out.println(date.toFullString());
        //输出阳历信息
        System.out.println(date.getSolar().toFullString());
        System.out.println();
        //指定阴历的某一天
        date = new Lunar(1986,4,21);
        System.out.println(date.toFullString());
        System.out.println(date.getSolar().toFullString());
      }
    }

Output:


    丙申年捌月初八 猴年 北方玄武 斗木獬
    2016-09-08 闰年 星期四 处女座
     
    丙寅年肆月廿一 虎年 北方玄武 危月燕
    1986-05-29 星期四 双子座

## Documentation

Please visit [http://6tail.cn/calendar/api.html](http://6tail.cn/calendar/api.html "http://6tail.cn/calendar/api.html")

## Contact

<a target="_blank" href="https://jq.qq.com/?_wv=1027&k=5F9Pbf0"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="lunar" title="lunar"></a>

