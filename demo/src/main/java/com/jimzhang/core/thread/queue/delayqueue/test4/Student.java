package com.jimzhang.core.thread.queue.delayqueue.test4;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 学生类
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-23 10:29
 */
public class Student implements Delayed{
    private String name;
    /**
     * 考试花费时间，单位为毫秒
     */
    private long delay;
    /**
     * 交卷时间，单位为毫秒
     */
    private long expire;

    /**
     * 此构造可随机生成考试花费时间
     * @param name
     */
    public Student(String name) {
        this.name = name;
        // 随机生成考试花费时间
        this.delay = TimeUnit.MILLISECONDS.convert(getRandomSeconds(), TimeUnit.SECONDS);
        this.expire = System.currentTimeMillis() + this.delay;
    }

    /**
     * 此构造可指定考试花费时间
     */
    public Student(String name, long delay, TimeUnit unit) {
        this.name = name;
        this.delay = TimeUnit.MILLISECONDS.convert(delay, unit);
        this.expire = System.currentTimeMillis() + this.delay;
    }

    /**
     * 获取随机花费时间
     * @return
     */
    public int getRandomSeconds() {
        return new Random().nextInt(Times.MAX_RAND_TIME.getValue() - Times.SUMBMIT_LIMIT.getValue())
                + Times.SUMBMIT_LIMIT.getValue();
    }

    /**
     * 设置花费时间和交卷时间，考试时间结束强制交卷时调用此方法
     * @return
     */
    public Student submit() {
        setDelay(Times.SUBMIT_TIME.getValue(), TimeUnit.SECONDS);
        setExpire(System.currentTimeMillis());
        return this;
    }

    public String getName() {
        return name;
    }

    public long getExpire() {
        return expire;
    }

    public void setDelay(long delay, TimeUnit unit) {
        this.delay = TimeUnit.MILLISECONDS.convert(delay, TimeUnit.SECONDS);
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    /**
     * 此方法的实现用于定义优先级
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        long td = this.getDelay(TimeUnit.MILLISECONDS);
        long od = o.getDelay(TimeUnit.MILLISECONDS);
        return td > od ? 1 : td == od ? 0 : -1;
    }

    /**
     * 这里返回的是剩余延时，当延时为0 时，此元素延时期满，可从take()取出
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "学生姓名：" + this.name + ",考试用时：" + TimeUnit.SECONDS.convert(delay, TimeUnit.MILLISECONDS) + ",交卷时间："
                + DateFormat.getDateTimeInstance().format(new Date(this.expire));
    }
}
