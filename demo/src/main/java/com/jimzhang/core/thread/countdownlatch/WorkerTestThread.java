package com.jimzhang.core.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 测试线程
 * *********************************************************************************************************************
 * CountDownLatch Java1.5 引入的，是一种简单的同步模式，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行。
 * （例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。）
 * 避免了多线程环境下对临界资源并发访问所引发的各种问题。
 *
 * CountDownLatch 伪代码：
 *  //Main thread start
    //Create CountDownLatch for N threads
    //Create and start N threads
    //Main thread wait on latch
    //N threads completes there tasks are returns
    //Main thread resume execution
 *
 * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。
 * 当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
 *
 * 其他N 个线程必须引用闭锁对象，因为他们需要通知CountDownLatch对象，他们已经完成了各自的任务。这种通知机制是通过
 * CountDownLatch.countDown()方法来完成的；每调用一次这个方法，在构造函数中初始化的count值就减1。所以当N个线程都调用了这
 * 个方法，count的值等于0，然后主线程就能通过await()方法，恢复执行自己的任务。
 * *********************************************************************************************************************
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-12 9:25
 */
public class WorkerTestThread implements Runnable {
    private Worker worker;
    private CountDownLatch cdLatch;

    /**
     * 构造方法
     * @param worker
     * @param cdLatch
     */
    public WorkerTestThread(Worker worker, CountDownLatch cdLatch) {
        this.worker = worker;
        this.cdLatch = cdLatch;
    }

    @Override
    public void run() {
        worker.doWork();        // 让工人开始工作
        System.out.println(worker.getName() + "工作结束,一共工作时长： " + worker.getWorkDuration());
        cdLatch.countDown();    // 工作完成后倒计时次数减1
        System.out.println("倒计时：" + cdLatch.getCount());
    }
}
