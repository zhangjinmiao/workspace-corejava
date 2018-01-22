package com.jimzhang.core.thread.queue;


import java.util.concurrent.BlockingQueue;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 消费者
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-12 10:51
 */
public class Consumer implements Runnable {
    private BlockingQueue<Task> buffer;

    public Consumer(BlockingQueue<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = buffer.take();
                System.out.println("Consumer[" + Thread.currentThread().getName() + "] got " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
