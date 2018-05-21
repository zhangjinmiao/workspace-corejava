package com.jimzhang.others.random;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author jimzhang
 * <>随机数</>
 * @version V1.0.0
 * @date 2018-04-23 9:24
 */
public class RandomTest {

    public static void test1(){
        // 生成 0.0~1.0之间的双精度浮点数
        double random = Math.random();
        System.out.println("0.0~1.0之间："+random);
        System.out.println("0.0~10.0之间：" + random *10);
        // 产生0和10之间的整数，四舍五入
        long round = Math.round(random * 10);
        System.out.println("产生0和10之间的整数："+ round);
    }

    public static void test2(){
        Random random = new Random();
        int i = random.nextInt();
        int ii = random.nextInt(10);
        System.out.println("" + i);
        System.out.println("0~10之间的整数：" + ii);

    }

    // 单一实例/静态访问  处理高并发的方法要快
    public static void test3(){
        int i = ThreadLocalRandom.current().nextInt(10);
        System.out.println("返回0[包含]~10之间的整数："+i);
    }


    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }
}
