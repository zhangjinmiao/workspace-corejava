package com.jimzhang.core.thread.countdownlatch;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 工人类
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-12 9:25
 */
public class Worker {
    private String name;        // 名字
    private long workDuration;  // 工作持续时间

    /**
     * 构造器
     */
    public Worker(String name, long workDuration) {
        this.name = name;
        this.workDuration = workDuration;
    }

    /**
     * 完成工作
     */
    public void doWork() {
        System.out.println(name + " begins to work...");
        try {
            Thread.sleep(workDuration); // 用休眠模拟工作执行的时间
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(name + " has finished the job...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(long workDuration) {
        this.workDuration = workDuration;
    }
}
