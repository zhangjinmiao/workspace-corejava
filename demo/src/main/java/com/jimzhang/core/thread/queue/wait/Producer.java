package com.jimzhang.core.thread.queue.wait;

import com.jimzhang.core.thread.queue.Constants;
import com.jimzhang.core.thread.queue.Task;

import java.util.List;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 生产者
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-12 10:46
 */
public class Producer implements Runnable {
    private List<Task> buffer;

    public Producer(List<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            // 获取对象锁
            synchronized (buffer) {
                // 池中生产的个数 > 最大可存放数，先停止生产
                while (buffer.size() >= Constants.MAX_BUFFER_SIZE) {
                    try {
                        buffer.wait(); // wait()  notify() notifyAll() 必须先获取对象锁才可以使用该方法，否则抛异常 java.lang.IllegalMonitorStateException
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 继续生产
                Task task = new Task();
                buffer.add(task);
                // 唤醒在该对象监视器上等待的所有线程。即 告诉消费者我又生产了产品，你们可以消费了 或
                // 告诉其他生产者 我生产了，你们看情况生产（满：等待 不满：生产）
                buffer.notifyAll();
                System.out.println("Producer[" + Thread.currentThread().getName() + "] put " + task);
            }
        }
    }
}
