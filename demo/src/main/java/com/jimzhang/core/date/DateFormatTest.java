package com.jimzhang.core.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 日期格式化
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 10:13
 */
public class DateFormatTest {

    public static void main(String[] args) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        System.out.println(sf.format(d));

        // java 8
        System.out.println("=====jdk 8=======");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        System.out.println(date.format(formatter));

    }
}
