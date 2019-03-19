package com.tao.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author DongTao
 * @since 2018-08-27
 */
public class Time {

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("localData is " + localDate);
        System.out.println("localTime is " + localTime);
        System.out.println("localDataTime is " + localDateTime);

        System.out.println(localTime.withNano(0));  // 去掉纳秒
        System.out.println(localDate.withYear(1996).withMonth(5).withDayOfMonth(13)); // 调整时间
        System.out.println(localDate.with(TemporalAdjusters.firstDayOfNextMonth()));  // 下月第一天
        System.out.println(localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY))); // 下个星期一，不包括今天
        System.out
                .println(localDate
                        .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))); // 下个星期一，包括今天

        LocalDate ofData = LocalDate.of(2018, 8, 15);
        System.out.println(localDate.isLeapYear());  //闰年
        System.out.println(localDate.isBefore(ofData));
        System.out.println(localDate.isAfter(ofData));
        System.out.println(localDate.plusDays(1)); // +1 天
        System.out.println(localDate.plusDays(-1)); //  -1 天
        System.out.println(localDate.minusDays(1)); //  -1 天
        System.out.println(localDate.plus(2, ChronoUnit.DAYS)); // // +2 天
        System.out.println(localTime.withNano(0));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        System.out.println(LocalDate.parse("2018 03 12", formatter));
        System.out.println(formatter.format(LocalDate.now()));

        //获得所有可用的时区  size=595
        ZoneId.getAvailableZoneIds();
        //获取默认ZoneId对象
        ZoneId.systemDefault();
        //获取指定时区的ZoneId对象
        ZoneId.of("Asia/Shanghai");
        //ZoneId.SHORT_IDS返回一个Map<String, String> 是时区的简称与全称的映射。下面可以得到字符串 Asia/Shanghai
        ZoneId.SHORT_IDS.get("CTT");

        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Instant instant1 = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(instant.toEpochMilli());
        System.out.println(instant1.toEpochMilli());

        LocalDate specifyDate = LocalDate.of(2018, 9, 13);
        LocalTime specifyTime = LocalTime.of(14, 2, 58);
        Period period = Period.between(specifyDate, localDate); // 天
        Duration duration = Duration.between(specifyTime, localTime);  // 时分秒
        System.out.println(period.getDays());
        System.out.println(period.getMonths());
        System.out.println(duration.getSeconds());
        System.out.println(specifyDate.until(localDate, ChronoUnit.DAYS)); // 获取相距天数，前 - 后

        Date date1 = Date.from(instant);
        Instant instant2 = new Date().toInstant();
        System.out.println(date1);
        System.out.println(instant2);

        final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate parse = LocalDate.parse("2019-01-13", formatter1);

        System.out.println(parse);

        final Date from = Date.from(LocalDate.parse("2019-01-13", formatter1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        System.out.println(from);
        System.out.println(new Date());
    }

}
