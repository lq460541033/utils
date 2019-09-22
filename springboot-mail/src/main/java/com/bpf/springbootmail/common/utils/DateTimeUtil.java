package com.bpf.springbootmail.common.utils;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    static String yyyy_MM_dd = "yyyy-MM-dd";
    static String yyyyMMdd = "yyyyMMdd";
    static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    static String yyyyMMdd2 ="yyyy年MM月dd日";


    public static Timestamp now(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp parse(String s, boolean isStandard) {
        //兼容非标字符串，左右空格
        //2019-01-01
        //2019/01/01
        //2019-01-01T16:00:00.000Z
        //2019-01-01 16:00:00
        if (null == s || "".equals(s.trim())) {
            return new Timestamp(System.currentTimeMillis());
        }
        s = s.trim();
        if (!isStandard) {
            if (s.contains("/")) {
                s = s.replaceAll("/", "-");
            }
            //2019-08-21T16:00:00.000Z
            if (s.contains("T")) {
                s = s.replaceAll("T", " ");
            }
            if (s.length() > 19) {
                s = s.substring(0, 18);
            }
            if (s.length() == 10) {
                s = s.trim() + " 00:00:00";
            }
        }
        return parse(s, yyyy_MM_dd_HH_mm_ss);
    }

    public static Timestamp parse(String s, String formater) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = new Date();
        //注意format的格式要与日期String的格式相匹配
        DateFormat sdf = new SimpleDateFormat(formater);
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ts = new Timestamp(date.getTime());
        return ts;
    }

    public static Date addDays(Date date, int days, boolean onlyDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        if (onlyDay) {
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        }
        return calendar.getTime();
    }

    public static Date addDays(int days) {
        return addDays(new Date(), days, true);
    }

    public static Timestamp addHours(int hours) {
        return addHours(new Date(), hours);
    }

    public static Timestamp addHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);
        date = calendar.getTime();
        return new Timestamp(date.getTime());
    }

    public static String formatDateString() {
        return formatDateString(new Date(), yyyy_MM_dd);
    }

    public static String formatDateString(Date date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(null==date?new Date():date);
    }

    public static String formatDateTimeString() {
        return formatDateTimeString(new Date(), yyyy_MM_dd_HH_mm_ss);
    }

    public static String formatDateTimeString(Date date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(null==date?new Date():date);
    }

    public static String formatDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd);
        return sdf.format(date);
    }
    public static String formatDateToString2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd2);
        return sdf.format(date);
    }

    public static int compareDate(Date d1, Date d2) {
        int compareResult = 0;
        //id(d1<d2)compareResult = -1
        //id(d1=d2)compareResult = 0
        //id(d1>d2)compareResult = 1
        return compareResult;
    }
}
