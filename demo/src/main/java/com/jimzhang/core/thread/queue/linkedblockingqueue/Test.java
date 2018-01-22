package com.jimzhang.core.thread.queue.linkedblockingqueue;

import com.jimzhang.core.thread.queue.Constants;
import com.jimzhang.core.thread.queue.Consumer;
import com.jimzhang.core.thread.queue.Producer;
import com.jimzhang.core.thread.queue.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * *********************************************************************************************************************
 *  实现类：LinkedBlockingQueue
 *  内部是链表，以先进先出的方式存储数据，最新插入的对象是尾部，最新移出的对象是头部
 *  使用 ReentrantLock 重入锁保证同步
 *
 *  内部维持着一个数据缓冲队列（该队列由一个链表构成），当生产者往队列中放入一个数据时，队列会从生产者手中获取数据，
 *  并缓存在队列内部，而生产者立即返回；
 *  只有当队列缓冲区达到最大值缓存容量时（LinkedBlockingQueue可以通过构造函数指定该值），才会阻塞生产者队列，直到消费者
 *  从队列中消费掉一份数据，生产者线程会被唤醒，反之对于消费者这端的处理也基于同样的原理。
 *
 *  而LinkedBlockingQueue之所以能够高效的处理并发数据，还因为其对于生产者端和消费者端分别采用了独立的锁（ReentrantLock）
 *  来控制数据同步，这也意味着在高并发的情况下生产者和消费者可以并行地操作队列中的数据，以此来提高整个队列的并发性能。
 * *********************************************************************************************************************
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-12 10:52
 */
public class Test {

    public static void main(String[] args) {
        // 使用 LinkedBlockingQueue 作为 产品池
        BlockingQueue<Task> buffer = new LinkedBlockingQueue<>(Constants.MAX_BUFFER_SIZE);
        ExecutorService es = Executors.newFixedThreadPool(Constants.NUM_OF_CONSUMER + Constants.NUM_OF_PRODUCER);
        for (int i = 1; i <= Constants.NUM_OF_PRODUCER; ++i) {
            // 通过构造方法将产品池传给生产者
            es.execute(new Producer(buffer));
        }
        for (int i = 1; i <= Constants.NUM_OF_CONSUMER; ++i) {
            // 通过构造方法将产品池传给消费者
            es.execute(new Consumer(buffer));
        }
    }
}
