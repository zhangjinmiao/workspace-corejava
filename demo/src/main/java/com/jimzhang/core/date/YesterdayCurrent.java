package com.jimzhang.core.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 昨天的当前时刻
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 10:17
 */
public class YesterdayCurrent {

    public static void main(String[] args) {
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.DATE, -1);
        System.out.println(cl.getTime());

        System.out.println("====jdk 8===");
        // java 8
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1); // -1
        LocalDateTime tomorrow = today.plusDays(1); // +1
        System.out.println("昨天："+ yesterday);
        System.out.println("今天："+ today);
        System.out.println("明天："+ tomorrow);

        System.out.println("====格式化====");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("昨天：" + yesterday.format(formatter));
        System.out.println("今天：" + today.format(formatter));
        System.out.println("明天：" + tomorrow.format(formatter));
    }
}
