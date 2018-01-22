package com.jimzhang.core.thread.queue;

import java.util.concurrent.BlockingQueue;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 生产者
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-12 10:51
 */
public class Producer implements Runnable {
    private BlockingQueue<Task> buffer;

    public Producer(BlockingQueue<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = new Task();
                buffer.put(task);
                System.out.println("Producer[" + Thread.currentThread().getName() + "] put " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
