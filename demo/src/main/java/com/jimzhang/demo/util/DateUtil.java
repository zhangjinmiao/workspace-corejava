package com.jimzhang.demo.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 对按指定格式对日期进行转换
 */
public class DateUtil {

  private static Log log = LogFactory.getLog(DateUtil.class);

  /**
   * 默认的日期格式
   */
  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

  /**
   * 按自定义日期格式格式化日期
   *
   * @return 格式化后的日期字符串，如果传入的日期对象为NULL，返回空字符串
   */
  public static String formatDate(Date target, String format) {
    if (target == null) {
      return "";
    }
    return new SimpleDateFormat(format).format(target);
  }

  /**
   * 按默认日期格式 格式化日期
   *
   * @return 格式化后的日期字符串，如果传入的日期对象为NULL，返回空字符串
   */
  public static String formatDate(Date target) {
    return formatDate(target, DEFAULT_DATE_FORMAT);
  }

  /**
   * 将字符串格式化为日期对象
   *
   * @return 如果date为空或格式不标准，返回NULL，否则返回对应的日期对象
   */
  public static Date formatToDate(String date, String format) {
    try {
      if (null == date || "".equalsIgnoreCase(date)) {
        return null;
      }

      SimpleDateFormat sorceFmt = new SimpleDateFormat(format);
      return new Date(sorceFmt.parse(date).getTime());
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * 将字符串格式化为日期对象
   */
  public static Timestamp formatToTimestamp(String dateStr, String format) {
    try {
      SimpleDateFormat sorceFmt = new SimpleDateFormat(format);
      return new Timestamp(sorceFmt.parse(dateStr).getTime()); // 一天的时间24*3600*1000
    } catch (ParseException e) {
      log.warn("invalid date2Get :" + dateStr);
      return null;
    }
  }

  /**
   * 将Timestamp对象格式化
   *
   * @return 格式化后的日期字符串，如果传入的Timestamp对象为NULL，返回空字符串
   */
  public static String formatTimestamp(Timestamp time, String format) {
    if (time == null) {
      return "";
    }
    return new SimpleDateFormat(format).format(time);
  }

  public static Date getYesterDay() {
    return dateAddDays(new Date(), -1);
  }

  public static Date getFirstTimeInDay(Date dateTime) {
    String simpleString = formatDate(dateTime, "yyyyMMdd");
    return formatToDate(simpleString, "yyyyMMdd");
  }

  /**
   * 获得当天unix时间戳
   */
  public static Integer getFirstUnixTimeInDay(Date dateTime) {
    String simpleString = DateUtil.format(dateTime, "yyyyMMdd");
    return (int) (DateUtil.formatToDate(simpleString, "yyyyMMdd").getTime() / 1000);
  }

  /**
   * @param dateTime
   * @return
   */
  public static Date getLastTimeInDay(Date dateTime) {
    if (null == dateTime) {
      return null;
    }

    String simpleString = formatDate(dateTime, "yyyyMMdd");
    simpleString = simpleString + " 23:59:59";

    return formatToDate(simpleString, "yyyyMMdd HH:mm:ss");
  }

  public static Date dateAddDays(Date date, int addDays) {
    Date newDate = null;
    try {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(Calendar.DATE, addDays);
      newDate = cal.getTime();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return newDate;
  }

  /**
   * 加、减天数。
   *
   * @param date 基准日期。
   * @param addedDays 如果>0，则增加天数；否则，会减天数。
   * @return 计算后的日期。
   */
  public static Date addDays(Date date, int addedDays) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, addedDays);
    return cal.getTime();
  }

  public static Date addHours(Date date, int hours) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.HOUR, hours);
    return cal.getTime();
  }

  public static Date addMinutes(Date date, int minutes) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.MINUTE, minutes);
    return cal.getTime();
  }

  public static Date addSeconds(Date date, int seconds) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.SECOND, seconds);
    return cal.getTime();
  }

  /**
   * 加、减月数。
   *
   * @param date 基准日期。
   * @param addedMonths 如果>0，则增加月数；否则，会减月数。
   * @return 计算后的日期。
   */
  public static Date addMonths(Date date, int addedMonths) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.MONTH, addedMonths);
    return c.getTime();
  }

  /**
   * 根据生日计算年龄。
   *
   * @param birth 字符串格式的生日。
   * @param format 日期格式。
   * @return 年龄。
   */
  public static int calculateAge(String birth, String format) throws ParseException {

    Date birthday = getDate(birth, format);
    return calculateAge(birthday);
  }

  /**
   * 根据生日计算年龄。
   *
   * @param birth 生日。
   * @return 年龄。
   */
  public static int calculateAge(Date birth) {

    Calendar c = Calendar.getInstance();
    Calendar b = Calendar.getInstance();
    b.setTime(birth);

    int thisYear = c.get(Calendar.YEAR);
    int thisMonth = c.get(Calendar.MONTH);
    int thisDate = c.get(Calendar.DATE);

    int birthYear = b.get(Calendar.YEAR);
    int birthMonth = b.get(Calendar.MONTH);
    int birthDate = b.get(Calendar.DAY_OF_MONTH);

    if (thisMonth < birthMonth) // 今年还没过生日
    {
      return thisYear - birthYear - 1;
    } else if (thisMonth > birthMonth) {
      return thisYear - birthYear;
    } else {
      if (thisDate < birthDate) // 在生日月份没到具体日子
      {
        return thisYear - birthYear - 1;
      } else {
        return thisYear - birthYear;
      }
    }
  }

  /**
   * 时间比较。
   *
   * @param date1 日期1
   * @param date2 日期2
   * @return 0 : 相同；<br /> <0：date1<date2；<br /> >0：date1>date2. <br />
   */
  public static int compareDate(Date date1, Date date2) {

    Calendar c1 = Calendar.getInstance();
    c1.setTime(date1);

    Calendar c2 = Calendar.getInstance();
    c2.setTime(date2);

    return c1.compareTo(c2);

  }

  /**
   * 按指定格式把Date对象格化为字符串。
   *
   * @param d 待格式化的日期。
   * @param format 日期格式。例如：“yyyy-MM-dd”。参见： <a href="http://docs.oracle.com/javase/1.4.2/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a>
   * @return 格式化后的日期字符串，如果传入的日期对象为NULL，返回null。
   */
  public static String format(Date d, String format) {
    return d == null ? null : new SimpleDateFormat(format).format(d);
  }

  /**
   * 按指定格式把Timestamp对象格化为字符串。
   *
   * @param time 带格式化的Timestamp对象。
   * @param format 日期格式。例如：“yyyy-MM-dd”。参见： <a href="http://docs.oracle.com/javase/1.4.2/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a>
   * @return 格式化后的日期字符串，如果传入的日期对象为NULL，返回空字符串。
   */
  public static String format(Timestamp time, String format) {
    return time == null ? null : new SimpleDateFormat(format).format(time);
  }

  /**
   * 判断date 是否在start 和 end之间
   *
   * @param date 待比较的日期对象。
   * @param start 日期区间的开始处。
   * @param end 日期区间的结束处。
   * @return true：date在[start,end]区间内；false：在区间外。
   */
  public static boolean isBetween(Date date, Date start, Date end) {

    if (date == null || start == null || end == null) {
      return false;
    }

    return date.after(start) && date.before(end);
  }

  /**
   * 判断是否是当月最后一天。
   *
   * @param date 待判断的日期。
   * @return true：是当月最后一天；false：不是。
   */
  public static boolean isLastDayOfMonth(Date date) {
    if (date == null) {
      return false;
    }

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    return day == last;
  }

  /**
   * 获取指定时间增加的月,日
   */
  public static Date addMonthsAndDays(Date date, int addedMonths, int days) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.MONTH, addedMonths);
    c.set(Calendar.DAY_OF_MONTH, 1);
    c.add(Calendar.DATE, days - 1);
    return c.getTime();
  }

  /**
   * 获取指定时间增加的月,设置传入date的日
   */
  public static Date addMonthsAndDateDays(Date date, int addedMonths) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.MONTH, addedMonths);
    c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
    return c.getTime();
  }

  /**
   * 判断当前日期是不是当月的第一天
   */
  public static boolean isFisrtDayOfMonth(Date date) {
    if (date == null) {
      return false;
    }

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int first = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    return day == first;
  }

  /**
   * 判断传入日期是否是今天
   *
   * @return true:是今天。< br/> false:不是今天。
   */
  public static boolean isToday(Date date) {
    if (date == null) {
      return false;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    return sdf.format(Calendar.getInstance().getTime()).equals(sdf.format(date.getTime()));

  }


  /**
   * 根据年、月、日创建日期对象。
   *
   * @param year 年
   * @param month 月
   * @param day 日
   * @return Date对象。
   */
  public static Date getDate(int year, int month, int day) {

    return getDate(year, month, day, 0, 0, 0);
  }

  /**
   * 根据年、月、日、小时、分钟、秒创建 Date对象。
   *
   * @param year 年
   * @param month 月
   * @param day 日
   * @param hour 小时
   * @param minute 分钟
   * @param second 秒
   * @return Date对象。
   */
  public static Date getDate(int year, int month, int day, int hour, int minute, int second) {

    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month - 1, day, hour, minute, second);
    return calendar.getTime();
  }

  /**
   * 将字符串格式化为日期对象
   *
   * @param format 日期格式，例如“yyyy-MM-dd”。参见： <a href="http://docs.oracle.com/javase/1.4.2/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a>
   * @return 如果date为空或格式不正确，返回NULL，否则返回对应的日期对象
   */
  public static Date getDate(String date, String format) throws ParseException {

    SimpleDateFormat sorceFmt = new SimpleDateFormat(format);
    return new Date(sorceFmt.parse(date).getTime());
  }

  /**
   * 将字符串格式化为Timestamp对象。
   *
   * @param str 待转换的字符串。
   * @param format 格式
   * @return 转换或的Timestamp对象。
   */
  public static Timestamp getTimestamp(String str, String format) throws ParseException {

    SimpleDateFormat sorceFmt = new SimpleDateFormat(format);
    return new Timestamp(sorceFmt.parse(str).getTime()); // 一天的时间24*3600*1000
  }


  /**
   * 获取当前秒级时间戳
   */
  public static Integer getCurrentTime() {
    Long time = new Date().getTime() / 1000;
    return new Integer(time.intValue());
  }

  /**
   * 获取当前秒级时间戳
   */
  public static Long getCurrentTimeLong() {
    Long time = new Date().getTime() / 1000;
    return Long.parseLong(time.intValue() + "");
  }


  /**
   * 获取秒级时间戳
   */
  public static Integer getCurrentTime(Date date) {
    Long time = date.getTime() / 1000;
    return new Integer(time.intValue());
  }


  /**
   * 获得begin-end的天数值。
   *
   * @param begin 开始的Date对象。
   * @param end 结束的Date对象。
   * @return begin-end的天数值。
   */
  public static int getDiffDays(Date begin, Date end) {

    return (int) (getDiffMinutes(begin, end) / 1440);
  }

  /**
   * 获得begin-end的分钟值。
   *
   * @param begin 开始的Date对象。
   * @param end 结束的Date对象。
   * @return begin-end的分钟值
   */
  public static long getDiffMinutes(Date begin, Date end) {

    return getDiffMsecs(begin, end) / (60 * 1000);
  }

  /**
   * 获得begin-end的毫秒值。
   *
   * @param begin 开始的Date对象。
   * @param end 结束的Date对象。
   * @return begin-end的毫秒值。
   */
  public static long getDiffMsecs(Date begin, Date end) {

    return end.getTime() - begin.getTime();
  }

  /**
   * 取Date对象的局部信息“年”。
   *
   * @param date Date对象。
   * @return 年。
   */
  public static int getYear(Date date) {

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.YEAR);
  }

  /**
   * 取Date对象的局部信息“月”。
   *
   * @param date Date对象。
   * @return 月
   */
  public static int getMonth(Date date) {

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.MONTH) + 1;
  }

  /**
   * 取Date对象的局部信息“天”。
   *
   * @param date Date对象。
   * @return 天.
   */
  public static int getDay(Date date) {

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.DAY_OF_MONTH);
  }

  /**
   * 得到Date对象的星期。
   *
   * @param date Date对象。
   * @return 1:周日、2：周一....、7:周六。
   */
  public static int getWeek(Date date) {

    Calendar current = Calendar.getInstance();
    current.setTime(date);
    return current.get(Calendar.DAY_OF_WEEK);
  }

  /**
   * 得到Date对象的小時。
   *
   * @param date Date对象。
   * @return 1:周日、2：周一....、7:周六。
   */
  public static int getHour(Date date) {

    Calendar current = Calendar.getInstance();
    current.setTime(date);
    return current.get(Calendar.HOUR_OF_DAY);
  }

  /**
   * 判断当前时间是不是一个周的第一天（周一）
   */
  public static boolean isFirstDayOfWeek(Date date) {
    if (null == date) {
      return false;
    }

    return getWeek(date) == 2 ? true : false;
  }

  /**
   * 是不是 每个周的最后一天(周日)
   */
  public static boolean isLastDayOfWeek(Date date) {
    if (null == date) {
      return false;
    }

    return getWeek(date) == 1 ? true : false;
  }

  /**
   * 是不是 每个周的最后一个工作日（周五，不考虑节假日的情况）
   */
  public static boolean isLastWorkdayOfWeek(Date date) {
    if (null == date) {
      return false;
    }

    return getWeek(date) == 6 ? true : false;
  }

  /**
   * 得到昨天。
   *
   * @return 昨天的同一时间点。
   */
  public static Date getYesterday() {
    return addDays(new Date(), -1);
  }


  /**
   * 时间戳转化为时间格式
   */
  public static String timeStamp2Date(String stamp, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(new Date(Long.valueOf(stamp + "000")));
  }


  /**
   * 日期格式字符串转换成时间戳
   */
  public static String date2TimeStamp(String date_str, String format) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      return String.valueOf(sdf.parse(date_str).getTime() / 1000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 得到当前精确到天的时间时间戳
   *
   * @param day 根据当前天需要改变的天数
   */
  public static Integer changeStampFromToday(int day) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Date date = sdf.parse(sdf.format(new Date()));
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.DATE, day); // 改变天数
      return DateUtil.getCurrentTime(calendar.getTime());

    } catch (Exception e) {
      return null;
    }
  }


  /**
   * 得到当前精确到天的时间时间戳
   *
   * @param day 根据当前天需要改变的天数
   */
  public static Long changeStampFromTodayLong(int day) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Date date = sdf.parse(sdf.format(new Date()));
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.DATE, day); // 改变天数
      return Long.parseLong(DateUtil.getCurrentTime(calendar.getTime()) + "");

    } catch (Exception e) {
      return null;
    }
  }


  /**
   * 获取改变天数之后的时间格式
   */
  public static String getTorromowDate(int day, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    Integer stamp = changeStampFromToday(day);
    return sdf.format(new Date(Long.valueOf(stamp + "000")));
  }


  public static int getUnixStamp(Date date) throws Exception {
    if (date == null) {
      throw new Exception("date is null");
    }
    return (int) (date.getTime() / 1000L);
  }

  /**
   * 根据年、月、日创建日期对象。 将之前方法中获取时间戳时的最后三位置为000
   *
   * @param year 年
   * @param month 月
   * @param day 日
   * @return Date对象。
   */
  public static Date getDateNew(int year, int month, int day) {
    Date tempDate = getDate(year, month, day, 0, 0, 0);
    return new Date(tempDate.getTime() / 1000 * 1000);
  }


  /**
   * 获取每月第一天时间戳
   */
  public static Long getFirstDayByNowMonth() {
    String day = DateUtil.timeStamp2Date(DateUtil.getCurrentTime().toString(), "yyyy/MM") + "/01";
    return Long.parseLong(DateUtil.date2TimeStamp(day, "yyyy/MM/dd"));
  }

  /**
   * 返回指定小时后的随机时间
   *
   * @param date 指定时间戳
   * @param hours 几小时后的
   */
  public static Integer getRadomTime(Integer date, int hours) {
    // (数据类型)(最小值+Math.random()*(最大值-最小值+1))
    int random = (int) (1 + Math.random() * (60)); // 1~60
    int expectTime = date + 3600 * hours + random * random;
    return expectTime;
  }

  public static void main(String[] args) {
    // D：23:59:59
    long yesterdayLastTime = DateUtil.getLastTimeInDay(DateUtil.getYesterDay()).getTime() / 1000L;
    // D+1：0:0:0
    long todayFirstTime = DateUtil.getFirstTimeInDay(new Date()).getTime() / 1000L;
    // D：0:0:0
    long yesterdayFirstTime = DateUtil.getFirstTimeInDay(DateUtil.getYesterDay()).getTime() / 1000L;

    System.out.println(yesterdayLastTime);
    System.out.println(todayFirstTime);
    System.out.println(yesterdayFirstTime);

  }
}