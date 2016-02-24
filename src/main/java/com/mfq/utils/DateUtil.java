package com.mfq.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final int INSTANCE_COUNT = 20;

    public static final SimpleDateFormat[] formatYYYYMMDD = createDateFormat(
            "yyyy-MM-dd");
    private static final SimpleDateFormat[] formatLong = createDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat[] formatShort = createDateFormat(
            "yyyyMMdd");
    private static final SimpleDateFormat[] formatTime = createDateFormat(
            "HH:mm:ss");
    private static final SimpleDateFormat[] formatYYYYMMDDHHMMSS = createDateFormat(
            "yyyyMMddHHmmss");
    
    public static final SimpleDateFormat[] formatCZYYYYMMDD = createDateFormat(
            "yyyy年MM月dd日");

    private static int currentIndex = 0; // 不需要考虑多线程问题，节省性能开销。

    private static SimpleDateFormat[] createDateFormat(String fmt) {
        SimpleDateFormat[] ret = new SimpleDateFormat[INSTANCE_COUNT];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = new SimpleDateFormat(fmt);
            ret[i].setLenient(false);
        }
        return ret;
    }

    private final static int getIndex() {
        int n = currentIndex++;
        if (n < 0) { // 处理溢出
            currentIndex = 0;
            n = 0;
        }
        return n % INSTANCE_COUNT;
    }

    public static String formatCurTimeLong() {
        SimpleDateFormat fmt = formatYYYYMMDDHHMMSS[getIndex()];
        synchronized (fmt) {
            return fmt.format(new Date());
        }
    }

    public static String formatYYYYMMDDHHMMSS(Date date) {
        SimpleDateFormat fmt = formatYYYYMMDDHHMMSS[getIndex()];
        synchronized (fmt) {
            return fmt.format(date);
        }
    }

    public static String formatCZYYYYMMDD(Date date) {
        SimpleDateFormat fmt = formatCZYYYYMMDD[getIndex()];
        synchronized (fmt) {
            return fmt.format(date);
        }
    }
    
    public static Date convertYYYYMMDDHHMMSS(String strDate) {
        if (strDate == null || strDate.indexOf("null") >= 0)
            return null;

        Date date = null;
        try {
            SimpleDateFormat fmt = formatYYYYMMDDHHMMSS[getIndex()];
            synchronized (fmt) {
                date = fmt.parse(strDate);
            }
        } catch (Exception e) {
            logger.error("convertYYYYMMDDHHMMSS error: date=" + strDate, e);
            return null;
        }

        return date;
    }

    public static Date convertYYYYMMDD(String strDate) {
        if (strDate == null || strDate.indexOf("null") >= 0)
            return null;

        Date date = null;
        try {
            SimpleDateFormat fmt = formatYYYYMMDD[getIndex()];
            synchronized (fmt) {
                date = fmt.parse(strDate);
            }
        } catch (Exception e) {
            logger.error("convertYYYYMMDD error: date=" + strDate, e);
            return null;
        }

        return date;
    }

    public static Date convertShort(String strDate) {
        if (strDate == null || strDate.indexOf("null") >= 0)
            return null;

        Date date = null;
        try {
            SimpleDateFormat fmt = formatShort[getIndex()];
            synchronized (fmt) {
                date = fmt.parse(strDate);
            }
        } catch (Exception e) {
            logger.error("convertShort error: date=" + strDate, e);
            return null;
        }

        return date;
    }

    public static Date convertLong(String strDate) {
        if (strDate == null || strDate.indexOf("null") >= 0)
            return null;

        Date date = null;
        try {
            SimpleDateFormat fmt = formatLong[getIndex()];
            synchronized (fmt) {
                date = fmt.parse(strDate);
            }
        } catch (Exception e) {
            logger.error("convertLong error: date=" + strDate, e);
            return null;
        }

        return date;
    }

    public static String formatYYYYMMDD(Date date) {
        SimpleDateFormat fmt = formatYYYYMMDD[getIndex()];
        synchronized (fmt) {
            return fmt.format(date);
        }
    }

    public static String formatTime(Date date) {
        SimpleDateFormat fmt = formatTime[getIndex()];
        synchronized (fmt) {
            return fmt.format(date);
        }
    }

    public static String formatShort(Date date) {
        SimpleDateFormat fmt = formatShort[getIndex()];
        synchronized (fmt) {
            return fmt.format(date);
        }
    }

    public static String formatLong(Date date) {
        SimpleDateFormat fmt = formatLong[getIndex()];
        synchronized (fmt) {
            return fmt.format(date);
        }
    }

    public static String formatDDMMMYY(Date date) {
        return String.format(Locale.US, "%1$td%1$tb%1$ty", date);
    }

    public static String convertYYYYMMDDToDDMMMYY(String date) {
        Date d = convertYYYYMMDD(date);
        return null == d ? "" : formatDDMMMYY(d);
    }

    public static String formatTodyDate(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date());
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date parse(String date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            logger.error("pase error: date=" + date + ", pattern=" + pattern,
                    e);
            return null;
        }
    }

    /**
     * d1 is before of d2?
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isBefore(Date d1, Date d2) {
        return getDayBetweenD(d1, d2) > 0;
    }

    /**
     * 取得两个日期的时间间隔,相差的天数
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static int getDayBetween(Date d1, Date d2) {
        Calendar before = Calendar.getInstance();
        Calendar after = Calendar.getInstance();
        if (d1.before(d2)) {
            before.setTime(d1);
            after.setTime(d2);
        } else {
            before.setTime(d2);
            after.setTime(d1);
        }
        int days = 0;

        int startDay = before.get(Calendar.DAY_OF_YEAR);
        int endDay = after.get(Calendar.DAY_OF_YEAR);

        int startYear = before.get(Calendar.YEAR);
        int endYear = after.get(Calendar.YEAR);
        before.clear();
        before.set(startYear, 0, 1);

        while (startYear != endYear) {
            before.set(startYear++, Calendar.DECEMBER, 31);
            days += before.get(Calendar.DAY_OF_YEAR);
        }
        return days + endDay - startDay;
    }

    /**
     * 取得两个日期的时间间隔,相差的天数,后面减前面，可能为负
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static int getDayBetweenD(Date d1, Date d2) {
        if (d1.before(d2)) {
            return getDayBetween(d1, d2);
        } else {
            return -getDayBetween(d1, d2);
        }
    }

    /**
     * 取得时间间隔,相差的时间，XX小时XX分钟
     * 
     * @param time1
     * @param time2
     * @return 不会超过24小时
     */
    public static String getTimeBetween(String time1, String time2) {
        String[] t1 = time1.split(":");
        String[] t2 = time2.split(":");

        int minute = Integer.parseInt(t2[1]) - Integer.parseInt(t1[1]);
        int hour = Integer.parseInt(t2[0]) - Integer.parseInt(t1[0]);

        if (minute < 0) {
            minute += 60;
            hour -= 1;
        }
        if (hour < 0) {
            hour += 24;
        }

        if (hour == 0) {
            return minute + "分钟";
        }
        if (minute == 0) {
            return hour + "小时";
        }
        return hour + "小时" + minute + "分钟";
    }

    public static Date addDay(Date myDate, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DAY_OF_MONTH, amount);
        return cal.getTime();
    }

    public static Date addMinute(Date myDate, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MINUTE, amount);
        return cal.getTime();
    }

    public static Date addYear(Date myDate, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.YEAR, amount);
        return cal.getTime();
    }

    public static Date addMonth(Date myDate, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, amount);
        return cal.getTime();
    }

    public static String dateFormatStr(String dateByyyyyMMddStr) {
        if (dateByyyyyMMddStr != null && dateByyyyyMMddStr.length() == 8) {
            String year = dateByyyyyMMddStr.substring(0, 4);
            String month = dateByyyyyMMddStr.substring(4, 6);
            String day = dateByyyyyMMddStr.substring(6, 8);
            return year + "-" + month + "-" + day;
        } else {
            return "";
        }
    }

    public static String long2DateStr(long msec, String pattern) {
        Date date = new Date();
        date.setTime(msec);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
    public static Date long2Date(long msec) {
        Date date = new Date(msec);
        return date;
    }

    public static Date getGoodSendDateTime(Date date) {
        long hour = DateUtils.getFragmentInHours(date, Calendar.DAY_OF_YEAR);

        // 8点0分到15分之间发出
        int minute = RandomUtils.nextInt(15);
        date = DateUtils.setHours(date, 8);
        date = DateUtils.setMinutes(date, minute);

        if (hour >= 0 && hour < 8) {
            return date;
        }
        return null;
    }
    
    public static Date getGoodSendDateTime() {
        return getGoodSendDateTime(new Date());
    }
    
    /**
     * 
     * @param date 毫秒数
     * @return
     */
    public static Date getDateTimeBySeconds(String date) {
    	long msgCreateTime = Long.parseLong(date) * 1000L;   
	    return new Date(msgCreateTime);
    }

    /**
     *  jdk 8 后 java.util.Date to LocalDate
     * @param date
     * @return localDate
     */
    public static LocalDate coverDateToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     *  jdk 8 后 java.util.Date to LocalTime
     * @param date
     * @return localDate
     */
    public static LocalTime coverDateToLocalTime(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    /**
     *  jdk 8 后 java.util.Date to LocalDateTime
     * @param date
     * @return localDate
     */
    public static LocalDateTime coverDateToLocalDateTime(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    public static boolean isBetween(Date thisTime, Date beginTime, Date endTime){
        if(thisTime == null){
            thisTime = new Date();
        }
        if(beginTime.getTime() > endTime.getTime()){
            return false;
        }
        if(beginTime.getTime() < thisTime.getTime() && endTime.getTime() > thisTime.getTime()){
            return true;
        }
        return false;
    }

}