package com.tao.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
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

    LocalDate ofDate = LocalDate.of(2018, 11, 1);
    LocalDate firstDay = localDate.with(TemporalAdjusters.firstDayOfMonth());
    LocalDate lastDay = localDate.withDayOfMonth(1);
    LocalDate plusDay = localDate.plusDays(-1);
    boolean leapYear = localDate.isLeapYear();

    System.out.println(localDate);
    System.out.println(ofDate);
    System.out.println(firstDay);
    System.out.println(lastDay);
    System.out.println(plusDay);
    System.out.println(leapYear);
    System.out.println("**************************");

    LocalTime nowTime = LocalTime.now();
    LocalTime parseTime = LocalTime.parse("12:00:23");

    System.out.println(nowTime);
    System.out.println(nowTime.withNano(0));
    System.out.println(parseTime);
    System.out.println(parseTime.plus(-2, ChronoUnit.HOURS));

    System.out.println("**********************");

    ZoneId defaultZone = ZoneId.systemDefault();
    LocalDate today = LocalDate.now();
    LocalDate specifyDate = LocalDate.of(1996, 5, 13);
    Period between = Period.between(specifyDate, today);

    System.out.println(defaultZone);
    System.out.println();
    System.out.println(between.getDays());
    System.out.println(between.getMonths());
    System.out.println(specifyDate.until(today, ChronoUnit.DAYS));

    System.out.println("********************");

    String date = "20180710";
    DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("YYYY MM dd");
    LocalDate formatDate = LocalDate.parse(date, formatter);
    System.out.println(formatDate);
    System.out.println(formatter2.format(LocalDate.now()));
    System.out.println(formatter.format(LocalDate.now()));

    System.out.println("*******************************");

    Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

    Date date1 = Date.from(instant);
    System.out.println(instant);
    System.out.println(date1);
    Date date2 = new Date();
    System.out.println(date2.toInstant().atZone(ZoneId.systemDefault()));

    System.out.println("*******************************");

    DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime localDateTime = LocalDateTime.parse("2018-09-06 13:12:03",formatter3);
    System.out.println(localDateTime);
    System.out.println(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

  }

}
