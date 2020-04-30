package com.taiji.dianthus.util;


import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description
 * @Author H.M
 * @Date 2019/11/7
 */
public class DateUtil {

    /**
     * 分
     */
    public static final long MINUTE_TTL = 60 * 1000L;
    /**
     * 时
     */
    public static final long HOURS_TTL = 60 * 60 * 1000L;
    /**
     * 半天
     */
    public static final long HALF_DAY_TTL = 12 * 60 * 60 * 1000L;
    /**
     * 天
     */
    public static final long DAY_TTL = 24 * 60 * 60 * 1000L;
    /**
     * 月
     */
    public static final long MONTH_TTL = 30 * 24 * 60 * 60 * 1000L;
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static Long getSystemTimeSeconds() {
        return System.currentTimeMillis() / 1000L;
    }


    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 字符串转换成日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        Date date = new Date();
        try {
            if (StringUtils.isEmpty(strDate)) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 将时间转换为时间戳
     * 输入形如：2017-03-03   输出形如：1488510674(当天日期最大时间戳)
     * 异常格式返回-1
     */
    public static Long getSystemTimeEndDay(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!StringUtils.isEmpty(date)) {
                Date d = sdf.parse(date);
                return d.getTime() / 1000L + 24 * 60 * 60 - 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将时间戳转换为时间
     * 输入形如：1488510674 输出形如：2017.03.03 HH:mm:ss
     * 异常格式返回-1
     *
     * @param s
     * @return
     * @author: CRF
     */
    public static String getTimestampDateTimeLong(Long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        Date date = new Date(s * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将时间戳转换为时间
     * 输入形如：1488510674 输出形如：2017.03.03
     * 异常格式返回-1
     *
     * @param s
     * @return
     * @author: CRF
     */
    public static String getTimestampDateLong(Long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date(s * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取日期 当月的天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Integer getMonthOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return Calendar.DAY_OF_MONTH;
    }


    /**
     * 获取当前日期星期
     *
     * @param time
     * @return
     */
    public static String getWeek(String time) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int wek = getWeek(date);

        if (wek == 1) {
            Week += "星期日";
        }
        if (wek == 2) {
            Week += "星期一";
        }
        if (wek == 3) {
            Week += "星期二";
        }
        if (wek == 4) {
            Week += "星期三";
        }
        if (wek == 5) {
            Week += "星期四";
        }
        if (wek == 6) {
            Week += "星期五";
        }
        if (wek == 7) {
            Week += "星期六";
        }
        return Week;
    }

    /**
     * 获取当前日期星期
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week = c.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    /**
     * 获取上个月的最后一天
     *
     * @return
     */
    public static String getLastMonthLastDay() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sf.format(calendar.getTime());
    }

    /**
     * 获取本月的第一天
     * @return
     */
    public static String getCurrentMonthFirstDay() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return sf.format(calendar.getTime());
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static String getCurrentMonth() {
        Date date = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        //获取月份
        String month = monthFormat.format(date);
        return month;
    }


    /**
     * 判断当前日期是否是下旬
     *
     * @param date
     * @return
     */
    public static boolean checkLateMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day >= 20) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断月份是否比当前月份大
     *
     * @param date
     * @return
     */
    public static boolean compareMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        Calendar nowCalendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int nowMonth = calendar.get(Calendar.MONTH);
        int nowYear = calendar.get(Calendar.YEAR);
        if (nowYear == year && month > nowMonth) {
            return true;
        } else if (nowYear< year) {
            return true;
        } else {
            return false;
        }
    }

}
