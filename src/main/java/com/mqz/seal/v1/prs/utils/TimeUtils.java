package com.mqz.seal.v1.prs.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */
public class TimeUtils {

    /**
     * 获取当前时间的年
     * eg:2020
     * @return
     */
    public static String getYear() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
        return now.format(dateTimeFormatter);
    }

    /**
     * 获取当前年和月
     * eg:20200317
     * @return
     */
    public static String getYearAndMonth() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");
        return now.format(dateTimeFormatter);
    }

    /**
     * 获取当前年和月ri
     * eg:20200317
     * @return
     */
    public static String getYearToDay() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return now.format(dateTimeFormatter);
    }

    /**
     * 获取UTC时间 格式为 yyyyMMddHH  2019112909
     * @return
     */
    public static String getUTC() {
        return OffsetDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
    }


    public static String getYearToMinute() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return now.format(dateTimeFormatter);
    }

    public static String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return LocalDateTime.now().format(formatter);
    }

        public static String getYearToHou() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
        return now.format(dateTimeFormatter);
    }

    /**
     * 获取下一年开始的时间
     * @return
     */
    public static Date getNextYearBeginDate() {
        LocalDateTime nextYearDate = LocalDateTime.now().plusYears(1);
        int year = nextYearDate.getYear();
        LocalDateTime newYearDateTime = LocalDateTime.parse(year+"-01-01T00:00:00");
        return Date.from(newYearDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取下月开始的时间
     * @return
     */
    public static Date getNextMonthBeginDate() {
        LocalDateTime nextMonthDate = LocalDateTime.now().plusMonths(1);
        int year = nextMonthDate.getYear();
        String month = String.valueOf(nextMonthDate.getMonthValue());
        if (month.length() < 2) {
            month = "0"+month;
        }
        LocalDateTime newYearDateTime = LocalDateTime.parse(year+"-"+month+"-01T00:00:00");
        return Date.from(newYearDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前时间的前几天
     * @param day
     * @return
     */
    public static Date getToDayBefore(int day) {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(day);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取明天的当前时间
     * @return
     */
    public static Date getOneDayAfter() {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取三分钟以后的时间
     * @return
     */
    public static Date getThreeMinuteAfter() {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(3);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
