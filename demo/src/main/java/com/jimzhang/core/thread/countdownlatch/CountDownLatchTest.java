package com.jimzhang.core.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 测试类
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-12 9:29
 */
public class CountDownLatchTest {
    private static final int MAX_WORK_DURATION = 5000;  // 最大工作时间
    private static final int MIN_WORK_DURATION = 1000;  // 最小工作时间

    // 产生随机的工作时间
    private static long getRandomWorkDuration(long min, long max) {
        return (long) (Math.random() * (max - min) + min);
    }

    public static void main(String[] args) {
        // 创建倒计时闩并指定倒计时次数为2，只能被设置一次，且没有重置机制
        CountDownLatch latch = new CountDownLatch(2);
        Worker w1 = new Worker("骆昊", getRandomWorkDuration(MIN_WORK_DURATION, MAX_WORK_DURATION));
        Worker w2 = new Worker("王大锤", getRandomWorkDuration(MIN_WORK_DURATION, MAX_WORK_DURATION));

        new Thread(new WorkerTestThread(w1, latch)).start();
        new Thread(new WorkerTestThread(w2, latch)).start();

        try {
            System.out.println("进入等待");
            // 等待倒计时闩减到0
            // 在启动其他线程后立即调用 await() 方法，这样主线程就会在这个方法上阻塞，直到其他线程完成各自的任务。
            latch.await();
            System.out.println("All jobs have been finished!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
