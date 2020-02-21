package com.example.springboot.util;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
    private final static String DAY_FORMAT = "yyyyMMdd";
    public final static String DAYTIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String DATE = "yyyy-MM-dd";
    public static final String DATE_FORMAT_CHINESE = "yyyy年MM月dd日";
    public static final String DATE_YYYYMMDDHHMMSS = "yyyyMMddhhmmss";
    public static final String DATE_YYYYMMDDHHMMSS_2 = "yyyyMMddHHmmss";

    /**
     * 是否时同一年月
     */
    public static boolean isSameYearAndMonth(long ams, long bms) {
        Calendar calA = Calendar.getInstance();
        calA.setTimeInMillis(ams);
        int yearA = calA.get(Calendar.YEAR);
        int monthA = calA.get(Calendar.MONTH);

        Calendar calB = Calendar.getInstance();
        calB.setTimeInMillis(bms);
        int yearB = calB.get(Calendar.YEAR);
        int monthB = calB.get(Calendar.MONTH);

        return ((yearA == yearB) && (monthA == monthB));
    }

    /**
     * 是否同一天
     */
    public static boolean isSameYearAndDay(long ams, long bms) {
        Calendar calA = Calendar.getInstance();
        calA.setTimeInMillis(ams);
        int yearA = calA.get(Calendar.YEAR);
        int dayA = calA.get(Calendar.DAY_OF_YEAR);

        Calendar calB = Calendar.getInstance();
        calB.setTimeInMillis(bms);
        int yearB = calB.get(Calendar.YEAR);
        int dayB = calB.get(Calendar.DAY_OF_YEAR);

        return ((yearA == yearB) && (dayA == dayB));
    }

    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }
    public static Date parseDate(String dateStr,String pattern) throws ParseException {
        return DateUtils.parseDate(dateStr, pattern);
    }
    public static long addYear(int year){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,year);
        Date time = calendar.getTime();
        System.out.println(time.getTime());
        return time.getTime();
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        addYear(1);
        addYear(10);
        addYear(100);
    }



}
