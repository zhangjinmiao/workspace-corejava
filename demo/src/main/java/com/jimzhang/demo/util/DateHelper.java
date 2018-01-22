package com.jimzhang.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: Prodigy</p>
 * <p>
 * <p>Description:时间帮助类</p>
 * <p>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p>
 * <p>Company: cmbc</p>
 *
 * @author lintenghui@cmbc.com
 * @version 0.1
 */
public final class DateHelper {

    /**
     * 默认的日期格式.
     */
    public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String DEFAULT_DATE_FORMAT_INFORMIX = "%Y-%m-%d %H:%M:%S";

    /**
     * 用于文件命名的日期格式.
     */
    public static String NAME_FILE_DATE_FORMAT = "yyyyMMdd_HHmmss";

    /**
     * HSS的日期格式.
     */
    public static String HSS_DATE_FORMAT = "yyyyMMddHHmmss";

    public static String HSS_DATE_FORMAT_INFORMIX = "%Y%m%d%H%M%S";

    /**
     * 端日期格式.
     */
    public static String SHORT_DATE_FORMAT = "yyyyMMdd";

    private static final Logger logger = LoggerFactory.getLogger(DateHelper.class);

    private DateHelper() {
    }

    /**
     * 将Date转换成字符串
     *
     * @param date   Date 要转换的Date实例
     * @param format String 日期格式字符串
     * @return String
     */
    public static String date2String(Date date, String format) {
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将Date类转换成字符串形式,使用默认的格式做转换.
     * yyyy年MM月DD日 HH:MM:SS
     *
     * @param date Date
     * @return String
     */
    public static String date2String(Date date) {
        return date2String(date, DEFAULT_DATE_FORMAT);
    }


    /**
     * String 转date
     * @param date
     * @param format
     * @return
     */
    public static Date string2Date(String date, String format) {
        Date reDate = null;
        if (StringUtils.isEmpty(date)) {
            reDate = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            reDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reDate;
    }

    /**
     * 判断日期是否在今天之前
     * @param dateStr
     * @return
     */
    public static Boolean isBeforeToday(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateHelper.SHORT_DATE_FORMAT, Locale.CHINA);
        Date date = null;
        Date now = null;
        try {
            date = sdf.parse(dateStr);
            now = sdf.parse(date2String(new Date(), DateHelper.SHORT_DATE_FORMAT));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean flag = false;
        if (date.before(now)) {
            System.out.print("早于今天");
            flag = true;
        } else {
            System.out.print("晚于今天");
        }
        return flag;
    }

    /**
     * 得到字符串形式的当前时间,日期格式采用默认的格式.
     *
     * @return String
     */
    public static String getCurrentDate() {
        Date date = new Date();
        return date2String(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 得到字符串形式的当前时间,日期格式采用默认的格式.
     *
     * @return String
     */
    public static String getHssCurrentDate() {
        Date date = new Date();
        return date2String(date, HSS_DATE_FORMAT);
    }

    /**
     * 得到字符串形式昨天时间,日期格式采用yyyyMMddHHmmss.
     *
     * @return String
     * add by zhou.linglong 20121214
     */
    public static String getHssYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        return yesterday;
    }

    /**
     * 得到字符串形式的当前时间,日期格式采用用于文件命名的格式.
     *
     * @return String
     */
    public static String getNameFileCurrentDate() {
        Date date = new Date();
        return date2String(date, NAME_FILE_DATE_FORMAT);
    }


    /**
     * 将字符串格式的日期转换成SQL日期,格式yyyy-MM-DD
     *
     * @param date String
     * @return Date
     */
    public static java.sql.Date string2SQLDate(String date) {
        return java.sql.Date.valueOf(date);
    }

    public static java.sql.Timestamp string2Timestamp(String date, String dateFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            long time = sdf.parse(date).getTime();
            return new java.sql.Timestamp(time);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public static java.sql.Date string2SQLDate(String date, String dateFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            long time = sdf.parse(date).getTime();
            return new java.sql.Date(time);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }


    /**
     * 得到当前标准时间 yyyy-MM-dd HH:mm:ss
     *
     * @return String 得到当前标准时间字符串
     */
    public static String getStandardNowTime() {
        return DateHelper.getStandardTimeFormat().format(new Date());
    }


    /**
     * 得到当前 yyyy-MM-dd HH:mm:ss 格式化器
     *
     * @return SimpleDateFormat 格式化器
     */
    public static SimpleDateFormat getStandardTimeFormat() {
        String format = DEFAULT_DATE_FORMAT;
        return new SimpleDateFormat(format);
    }

    /**
     * 得到当前日期
     *
     * @return java.sql.Date 当前服务器时间
     */
    public static java.sql.Date getNowDate() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    /**
     * 偏移时间
     *
     * @param date   Date 初始时间
     * @param seconds long 偏移秒数
     * @return Date
     */
    public static java.sql.Date offsetSecond(java.sql.Date date, long seconds) {
        long time = date.getTime();
        time = time + (seconds * 1000);
        return new java.sql.Date(time);
    }

    /**
     * 偏移时间
     *
     * @param date   Date 初始时间
     * @param minutes long 偏移分钟数
     * @return Date
     */
    public static java.sql.Date offsetMinute(java.sql.Date date, long minutes) {
        return offsetSecond(date, 60 * minutes);
    }

    /**
     * 偏移时间
     *
     * @param date Date 初始时间
     * @param hours long 偏移小时数
     * @return Date
     */
    public static java.sql.Date offsetHour(java.sql.Date date, long hours) {
        return offsetMinute(date, 60 * hours);
    }

    /**
     * 偏移时间
     *
     * @param date Date 初始时间
     * @param days  long 偏移天数
     * @return Date
     */
    public static java.sql.Date offsetDay(java.sql.Date date, int days) {
        return offsetHour(date, 24 * days);
    }

    /**
     * 偏移时间
     *
     * @param date Date 初始时间
     * @param weeks long 偏移周数
     * @return Date
     */
    public static java.sql.Date offsetWeek(java.sql.Date date, int weeks) {
        return offsetDay(date, 7 * weeks);
    }

    /**
     * 得到本月的最后时间
     *
     * @param date Date 要偏移的时间
     * @return Date
     */
    public static java.sql.Date getLastday(java.sql.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxDay);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        date.setTime(calendar.getTimeInMillis());
        return date;
    }

    /**
     * 得到本月的开始时间
     *
     * @param date Date 要偏移的时间
     * @return Date
     */
    public static java.sql.Date getBeginday(java.sql.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        date.setTime(calendar.getTimeInMillis());
        return date;
    }

    /**
     * 偏移时间(按月)
     * 规则:
     * 1. 如果要偏移的时间是月末, 偏移后也是月末
     * <p>
     * 2. 要偏移的时间的当前天大于偏移后的月份的最大天数也调整为月末, 比如12月30号(非月末)偏移两个月
     * <p>
     * 应变为2月28(29)号
     *
     * @param date   Date 要偏移的时间
     * @param months int 要偏移的月份
     * @return Date
     */
    public static java.sql.Date offersetMonth(java.sql.Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        //将当前天设置为1号, 然后增加月份数 (先加月份, 再加天)
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, months);

        //加过月份以后的日期当月的最大天数
        int newMaxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        //如果当前天为月底, 加过以后也调整为月底
        if (curDay == maxDay) {
            calendar.set(Calendar.DAY_OF_MONTH, newMaxDay);

        } else {
            //如果要加的初始日期的天大于新的月份的最大天数, 则调整为月底, 比如12月30号加两个月

            //不是2 * 30天 到 3月2号, 而是到2月底
            if (curDay > newMaxDay) {
                calendar.set(Calendar.DAY_OF_MONTH, newMaxDay);
            } else {
                calendar.set(Calendar.DAY_OF_MONTH, curDay);
            }
        }

        date.setTime(calendar.getTimeInMillis());
        return date;
    }


    /**
     * 检查指定时间是否在某个时间范围内(闭区间)
     *
     * @param date      Date 指定时间
     * @param beginDate Date 范围开始时间
     * @param endDate   Date 范围结束时间
     * @return boolean true-在范围内, false-不在范围内
     */
    public static boolean isInRange(Date date, Date beginDate, Date endDate) {
        long time = date.getTime();
        long beginTime = beginDate.getTime();
        long endTime = endDate.getTime();

        if (time >= beginTime && time <= endTime) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查指定时间比较大小
     * <p>
     * * @param beginDate Date 范围开始时间
     *
     * @param endDate Date 范围结束时间
     * @return boolean 0-小于, 1-等于，2-大于
     */
    public static int isCompare(Date beginDate, Date endDate) {
        int ret = 1;
        long beginTime = beginDate.getTime();
        long endTime = endDate.getTime();

        if (beginTime > endTime) {
            ret = 2;
        }
        if (beginTime == endTime) {
            ret = 1;
        }
        if (beginTime < endTime) {
            ret = 0;
        }
        return ret;
    }


    /**
     * 判断字符串是否升序的, 第一个字符串小于第二个字符串
     *
     * @param firstStr  String
     * @param secondStr String
     * @return boolean true-升序, false-降序
     */
    private static boolean isAsc(String firstStr, String secondStr) {
        return (firstStr.compareTo(secondStr) < 0);
    }

    /**
     * Informix 取数据库sql
     */
    public static String INFORMIX_GETTIME = " SELECT CURRENT FROM DUAL ";

    /**
     * Oracle 取数据库sql
     */
    public static String ORACLE_GETTIME = " SELECT SYSDATE FROM DUAL ";

    /**
     * 得到两个时间之间的月份（含润年）
     *
     * @param date1
     * @return
     * @throws
     */
    public static int getDifferMonth(Date date1, Date date2) throws Exception {

        int iMonth = 0;
        int flag = 0;
        try {
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(date1);

            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(date2);

            if (objCalendarDate2.equals(objCalendarDate1)) {
                return 0;
            }

            if (objCalendarDate1.after(objCalendarDate2)) {
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }

            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1
                    .get(Calendar.DAY_OF_MONTH)) {
                flag = 1;
            }


            if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR)) {
                iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1
                        .get(Calendar.YEAR))
                        * 12 + objCalendarDate2.get(Calendar.MONTH) - flag)
                        - objCalendarDate1.get(Calendar.MONTH);
            } else {
                iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return iMonth;
    }

    /**
     * 日期合法性校验
     * <p>
     * <p>
     * <p>
     * add by wang.minxin 2013-07-20 23:19 场景六校验规则联调
     * <p>
     * </p>
     *
     * @param dateStr <code>String</code>
     *                需要校验的日期字符串，格式可以是yyyy-MM-dd、yyyy/MM/dd、yyyyMMdd
     * @return isValid <code>boolean</code> true-合法；false-不合法
     */
    public static boolean isValidDate(String dateStr) {
        String matchPattern = "^((\\d{2}(([02468][048])|([13579][26]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern pattern = Pattern.compile(matchPattern);
        Matcher match = pattern.matcher(dateStr);
        return match.matches();
    }

    public static void main(String[] args) throws ParseException {
        java.sql.Date nowDate = DateHelper.getNowDate();
        java.sql.Date beforeDate = DateHelper.offersetMonth(DateHelper.getNowDate(), -3);
        System.out.println(nowDate);
        System.out.println(new java.sql.Date(System.currentTimeMillis()));
        System.out.println(beforeDate);

        Boolean beforeToday = isBeforeToday("20171024");
        System.out.println(beforeToday);
    }

}
