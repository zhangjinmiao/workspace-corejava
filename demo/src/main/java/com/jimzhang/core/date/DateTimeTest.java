package com.jimzhang.core.date;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 获取时间  日期
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 9:56
 */
public class DateTimeTest {
    public static void main(String[] args) {
        // jdk1.7 以前
        Calendar cl = Calendar.getInstance();
        System.out.println(cl.get(Calendar.YEAR) + " 年");
        System.out.println(cl.get(Calendar.MONTH) + " 月，比实际月份少1"); // 0-11
        System.out.println(cl.get(Calendar.DATE) + " 日");
        System.out.println(cl.get(Calendar.HOUR_OF_DAY) + " 点");
        System.out.println(cl.get(Calendar.MINUTE) + " 分");
        System.out.println(cl.get(Calendar.SECOND) + " 秒");

        System.out.println("=======================");
        // jdk 1.8
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt.getYear() + " 年");
        System.out.println("英文月份：" + dt.getMonth() + " 阿拉伯：" + dt.getMonthValue() + " 月份"); // JANUARY
        System.out.println(dt.getDayOfMonth() + " 号，英文星期：" + dt.getDayOfWeek() + " 一年中的 " + dt.getDayOfYear() + " 号");
        System.out.println(dt.getHour() + " 点");
        System.out.println(dt.getMinute() + " 分");
        System.out.println(dt.getSecond() + " 秒");



    }
}
