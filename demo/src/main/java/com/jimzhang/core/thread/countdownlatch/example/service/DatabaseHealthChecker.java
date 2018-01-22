package com.jimzhang.core.thread.countdownlatch.example.service;

import com.jimzhang.core.thread.countdownlatch.example.base.BaseHealthChecker;

import java.util.concurrent.CountDownLatch;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 数据库健康监测——继承了BaseHealthChecker，实现了verifyService()方法
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-18 15:27
 */
public class DatabaseHealthChecker extends BaseHealthChecker {

    public DatabaseHealthChecker(CountDownLatch latch) {
        super("Database Service", latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
