package com.zstao.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理
 * @author 赵胜涛
 */
public class DateTime {
    //字符串转换为时间 yyyy-MM-dd
    public static Date toDate(String dateStr){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    //字符串转换为时间 yyyy年MM月dd日
    public static Date toDateCHN(String dateStr){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    //字符串转换为具体时间 yyyy-MM-dd HH:mm:ss
    public static Date toDateCHNTime(String dateStr){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    //字符串转换为具体时间 yyyy-MM-dd HH:mm:ss
    public static Date toDateTime(String dateStr){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    //时间转换为字符串  年月日时分秒
    public static String  dateToStringCHNTime(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(date);
    }
    //时间转换为字符串  年月日时分秒
    public static String  dateToStringTime(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    //时间转换为字符串  年月日时分秒
    public static String  dateToStringCHN(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(date);
    }
    //时间转换为字符串  年月日时分秒
    public static String  dateToString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    //时间转换为时间戳long  直接转化
    public static long dateToTimestamp(Date date){
        return date.getTime();
    }
    //时间转换为时间戳 long 字符串转化
    public static long StringToTimestampCHN(String dateStr) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date=sdf.parse(dateStr);
        return date.getTime();
    }
    //时间转换为时间戳 long 字符串转化
    public static long StringToTimestamp(String dateStr) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=sdf.parse(dateStr);
        return date.getTime();
    }
    //时间戳转化为时间字符串
    public static String TimestampToString(Timestamp date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr=sdf.format(date);
        return dateStr;
    }
    //时间戳转化为字符串
    public static String TimestampToStringCHN(Timestamp date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String dateStr=sdf.format(date);
        return dateStr;
    }
// 字符串转换为timetamp类型
public static Timestamp StringTotamp(String dateStr) {
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    try {
        ts = Timestamp.valueOf(dateStr);
        System.out.println(ts);
        return ts;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    public static void main(String[] args) {
       String date="2019年12月23日 12:23:21";
//        System.out.println(TimestampToString(1585485939000));
//        System.out.println(toDateCHNTime(date));

    }


}
