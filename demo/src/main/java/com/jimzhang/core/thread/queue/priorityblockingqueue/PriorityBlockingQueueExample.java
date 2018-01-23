package com.jimzhang.core.thread.queue.priorityblockingqueue;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 测试
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-23 11:10
 */
public class PriorityBlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<PriorityElement> queue = new PriorityBlockingQueue<>();
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            PriorityElement ele = new PriorityElement(random.nextInt(10));
            queue.put(ele);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.take());
        }
    }
}
