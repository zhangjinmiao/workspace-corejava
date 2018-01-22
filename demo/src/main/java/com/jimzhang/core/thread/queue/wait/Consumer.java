package com.jimzhang.core.thread.queue.wait;

import com.jimzhang.core.thread.queue.Task;

import java.util.List;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 消费者
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-12 10:42
 */
public class Consumer implements Runnable {
    private List<Task> buffer;

    public Consumer(List<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            // 获取对象锁
            synchronized (buffer) {
                // 产品池中没产品，则等待
                while (buffer.isEmpty()) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 有产品，则取走
                Task task = buffer.remove(0);
                // 唤醒在该对象监视器上等待的所有线程。即 告诉生产者我消费了，你们可以生产了， 或
                // 告诉其他消费者我消费了，你们看情况消费（有：消费  无：等待）
                buffer.notifyAll();
                System.out.println("Consumer[" + Thread.currentThread().getName() + "] got " + task);
            }
        }
    }
}
