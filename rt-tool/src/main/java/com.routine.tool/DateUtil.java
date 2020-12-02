package com.routine.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtil 时间常用操作类
 * @DESCRIPTION TODO
 * @Author zf
 * @Date 2020/12/2 13:02
 */
public class DateUtil {
    private DateUtil() { }
    /** 日期格式 **/
    public interface DATE_PATTERN {
        String HHMMSS = "HHmmss";
        String HH_MM_SS = "HH:mm:ss";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        String MMDD = "MM月dd日";
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static final String format(Date date) {
        return format(date, DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
    }
    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static final String format(Date date, String pattern) {
        if (date == null) { return null; }
        if (pattern == null) { return format(date); }
        return new SimpleDateFormat(pattern).format(date);
    }
    /**
     * 获取日期时间
     *
     * @return
     */
    public static final String getDateTime() {
        return format(new Date());
    }

    /**@Description   计算与当前时间差值
     * @param ltime 传入时间戳
     * @return long 返回与当前时间戳差值
     * @Author zf
     * @Date 2020-12-02 13:35
     * @Update
     **/
    public static long computerDiff(long ltime){
        return new Date().getTime() - ltime;
    }
}
