package com.jimzhang.core.thread.queue.delayqueue.test1;

import java.util.concurrent.DelayQueue;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-22 11:01
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedElement> queue = new DelayQueue<>();
        // 过期时间为3s
        DelayedElement element = new DelayedElement("cache 3 seconds", 3000);
        queue.put(element);
        System.out.println(queue.take());
    }
}
