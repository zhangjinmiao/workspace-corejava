package com.jimzhang.core.thread.threadpool;

import java.util.concurrent.Executors;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 18:24
 */
public class FourThread {

    public static void main(String[] args) {
        // 产生一个 ExecutorService 对象，这个对象带有一个大小为 poolSize 的线程池，若任务数量大于 poolSize ，任务会被放在一个 queue 里顺序执行。
        // 固定大小的线程池，每次提交任务创建一个线程，直到线程达到线程池的最大大小。线程池大小达到最大就保持不变，若有线程因异常结束，线程池会补充一个新线程。
        // 性能较好
        Executors.newFixedThreadPool(3).execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("newFixedThreadPool_" + Thread.currentThread().getName());
            }
        });

        // 创建一个单线程的线程池。只有一个线程工作，若由于异常结束会有新线程替代。
        // 该线程池保证所有任务的执行顺序按照任务的提交顺序执行。
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("newSingleThreadExecutor_" + Thread.currentThread().getName());
            }
        });

        // 可缓存的线程池。如果线程池大小超过了处理任务所需的线程，就会回收部分空闲的线程（60 秒不执行任务）
        // 线程池大小依赖于操作系统
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("newCachedThreadPool_" + Thread.currentThread().getName());
            }
        });

        // 创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
        Executors.newScheduledThreadPool(3).execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("newScheduledThreadPool_" + Thread.currentThread().getName());
            }
        });
    }
}
