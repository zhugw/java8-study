package com.zhugw.java8_study;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateDemo {

  public static void main(String[] args) {
    Clock clock = Clock.systemDefaultZone();
    long millis = clock.millis();
    System.out.println(millis);

    Instant instant = clock.instant();
    Date legacyDate = Date.from(instant); // legacy java.util.Date
    System.out.println(legacyDate);

    System.out.println(LocalTime.now());
    LocalTime time1 = LocalTime.of(22, 59, 59);
    LocalTime time2 = LocalTime.of(23, 59, 59);

    long hoursBetween = ChronoUnit.HOURS.between(time1, time2);
    System.out.println(hoursBetween);
    long minutesBetween = ChronoUnit.MINUTES.between(time1, time2);
    System.out.println(minutesBetween);

    LocalDate today = LocalDate.now();
    System.out.println(today);
    LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
    System.out.println(tomorrow);
    LocalDate yesterday = tomorrow.minusDays(2);
    System.out.println(yesterday);

    LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
    System.out.println(independenceDay);
    DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
    System.out.println(dayOfWeek);

    LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

    dayOfWeek = sylvester.getDayOfWeek();
    System.out.println(dayOfWeek); // WEDNESDAY

    Month month = sylvester.getMonth();
    System.out.println(month); // DECEMBER

    long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
    System.out.println(minuteOfDay); // 1439

    instant = sylvester.atZone(ZoneId.systemDefault()).toInstant();

    legacyDate = Date.from(instant);
    System.out.println(legacyDate); // Wed Dec 31 23:59:59 CET 2014

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    LocalDateTime parsed = LocalDateTime.parse("2015-09-15 16:43:01", formatter);
    String string = formatter.format(parsed);
    System.out.println(string); // Nov 03, 2014 - 07:13
  }

}
