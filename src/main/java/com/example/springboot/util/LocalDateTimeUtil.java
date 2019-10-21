package com.example.springboot.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class LocalDateTimeUtil {
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String defaultDateTimePattern = "yyyy-MM-dd HH:mm:ss";

    public static final String defaultDatePattern = "yyyy-MM-dd";

    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * 校验日期格式是否正确
     */
    public static boolean validateDateTime(String dateTime, String pattern) {
        boolean result = true;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            simpleDateFormat.parse(dateTime);
        } catch (ParseException e) {
            result = false;
        }
        return result;
    }

    /**
     * SimpleDateFormat转date对象为字符串日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        String dateStr = "";
        //默认日期格式
        if (StringUtils.isBlank(pattern)) {
            pattern = defaultDatePattern;
        }
        if (null != date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            //SimpleDateFormat的format是线程不安全的。多个线程会同时调用calendar.setTime方法
            dateStr = simpleDateFormat.format(date);
        }
        return dateStr;
    }

    /**
     * DateTimeFormatter 转LocalDateTime对象为字符串日期
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        String dateStr = "";
        //默认日期格式
        if (StringUtils.isBlank(pattern)) {
            pattern = defaultDateTimePattern;
        }
        if (null != localDateTime) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            dateStr = localDateTime.format(dateTimeFormatter);
        }
        return dateStr;
    }
    /**
     * DateTimeFormatter 转LocalDate对象为字符串日期
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public static String formatLocalDate(LocalDate localDate, String pattern) {
        String dateStr = "";
        //默认日期格式
        if (StringUtils.isBlank(pattern)) {
            pattern = defaultDatePattern;
        }
        if (null != localDate) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            dateStr = localDate.format(dateTimeFormatter);
        }
        return dateStr;
    }

    /**
     * simpleDateFormat 转字符串日期为date对象
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static Date parseDate(String dateTime, String pattern) {
        Date date = null;
        //默认时间格式
        if (StringUtils.isBlank(pattern)) {
            pattern = defaultDateTimePattern;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            date = simpleDateFormat.parse(dateTime);
        } catch (ParseException e) {
            log.error("parseDate方法解析时间错误", e);
        }
        return date;
    }

    /**
     * simpleDateFormat 转字符串日期为date对象
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String dateTime, String pattern) {
        Date date = null;
        //默认时间格式
        if (StringUtils.isBlank(pattern)) {
            pattern = defaultDateTimePattern;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);

        return localDateTime;
    }

    /**
     * 获取第二天
     * @return
     */
    public static Date getSecondDayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        /**格式化***/
        LocalDateTime now= LocalDateTime.now();
        System.out.println(now);
        String localDateTimeStr=formatLocalDateTime(now,defaultDateTimePattern);
        System.out.println(localDateTimeStr);
        LocalDate localDate = now.toLocalDate();
        LocalTime localTime = now.toLocalTime();
        System.out.println(localDate);
        System.out.println(localTime);
        String localDateStr=formatLocalDate(localDate,"yyyyMMdd");
        System.out.println(localDateStr);
        /**解析**/
        LocalDateTime localDateTime = parseLocalDateTime("2019-10-20 10:10:10", defaultDateTimePattern);
        System.out.println(localDateTime);
        /*获取第二天零点日期*/
        Date secondDayDate = LocalDateTimeUtil.getSecondDayDate();
        System.out.println(formatDate(secondDayDate,""));

    }

}
