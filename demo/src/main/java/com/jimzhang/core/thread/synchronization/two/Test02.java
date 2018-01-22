package com.jimzhang.core.thread.synchronization.two;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-10 14:04
 */
public class Test02 {

    public static void main(String[] args) {

        Account account = new Account();

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(100, 100, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 1; i <= 100; i++) {
            pool.execute(new AddMoneyThread(account, 1));
        }

        pool.shutdown();

        while (!pool.isTerminated()) {
        }

        System.out.println("账户余额: " + account.getBalance());
    }
}
