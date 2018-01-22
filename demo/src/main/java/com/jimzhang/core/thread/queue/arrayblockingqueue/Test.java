package com.jimzhang.core.thread.queue.arrayblockingqueue;

import com.jimzhang.core.thread.queue.Constants;
import com.jimzhang.core.thread.queue.Consumer;
import com.jimzhang.core.thread.queue.Producer;
import com.jimzhang.core.thread.queue.Task;

import java.util.concurrent.*;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * *********************************************************************************************************************
 *
 * *********************************************************************************************************************
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-12 10:52
 */
public class Test {

    public static void main(String[] args) {
        // 使用 ArrayBlockingQueue 作为 产品池
        BlockingQueue<Task> buffer = new ArrayBlockingQueue<>(Constants.MAX_BUFFER_SIZE);
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
