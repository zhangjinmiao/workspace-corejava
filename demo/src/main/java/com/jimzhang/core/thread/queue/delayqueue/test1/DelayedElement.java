package com.jimzhang.core.thread.queue.delayqueue.test1;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 为一个对象指定过期时间
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-22 10:55
 */
public class DelayedElement implements Delayed{
    /**
     * 对象名称
     */
    private String name;
    /**
     * 有效期
     */
    private long expired;
    /**
     * 延迟时间
     */
    private long delay;


    public DelayedElement(String name, long delay) {
        this.name = name;
        this.delay = delay;
        expired = delay + System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return (expired -System.currentTimeMillis());
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedElement cached = (DelayedElement) o;
        return cached.expired > expired ? 1 : -1;
    }

    public long getExpired() {
        return expired;
    }

    @Override
    public String toString() {
        return "DelayedElement{" +
                "name='" + name + '\'' +
                ", expired=" + expired +
                ", delay=" + delay +
                '}';
    }
}
