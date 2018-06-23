# lunar [![License](https://img.shields.io/badge/license-MIT-4EB1BA.svg?style=flat-square)](https://github.com/6tail/nlf2/blob/master/LICENSE)

lunar是一个无依赖的支持阳历和阴历的日历工具库。

> 支持java 1.5及以上版本。

[English](https://github.com/6tail/lunar-java/blob/master/README.md)

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

输出结果：


    丙申年捌月初八 猴年 北方玄武 斗木獬
    2016-09-08 闰年 星期四 处女座
     
    丙寅年肆月廿一 虎年 北方玄武 危月燕
    1986-05-29 星期四 双子座

## 文档

请移步至 [http://6tail.cn/calendar/api.html](http://6tail.cn/calendar/api.html "http://6tail.cn/calendar/api.html")

## 联系

<a target="_blank" href="https://jq.qq.com/?_wv=1027&k=5F9Pbf0"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="lunar" title="lunar"></a>

