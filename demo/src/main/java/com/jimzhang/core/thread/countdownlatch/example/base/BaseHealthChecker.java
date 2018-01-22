package com.jimzhang.core.thread.countdownlatch.example.base;

import java.util.concurrent.CountDownLatch;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 负责所有特定的外部服务健康的检测。它删除了重复的代码和闭锁的中心控制代码。
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-18 15:19
 */
public abstract class BaseHealthChecker implements Runnable{

    private CountDownLatch latch;
    private String serviceName;
    private boolean serviceUp;

    /**
     * Get latch object in constructor so that after completing the task, thread can countDown() the latch
     * @param serviceName   服务名
     * @param latch         latch对象
     */
    public BaseHealthChecker(String serviceName, CountDownLatch latch) {
        super();
        this.latch = latch;
        this.serviceName = serviceName;
        this.serviceUp = false;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            serviceUp = false;
        } finally {
            if (latch != null) {
                latch.countDown();
            }
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }

    /**d
     * This method needs to be implemented by all specific service checker
     */
    public abstract void verifyService();
}

