package com.jimzhang.core.thread.countdownlatch.example.service;

import com.jimzhang.core.thread.countdownlatch.example.base.BaseHealthChecker;

import java.util.concurrent.CountDownLatch;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 缓存健康监测
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-18 15:32
 */
public class CacheHealthChecker extends BaseHealthChecker {

    public CacheHealthChecker(CountDownLatch latch) {
        super("Cache Service", latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
