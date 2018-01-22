package com.jimzhang.core.thread.queue.delayqueue.test2;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 把所有考试的学生看做是一个DelayQueue，谁先做完题目释放谁
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-22 11:04
 */
public class Student implements Delayed,Runnable {

    private String name;  //姓名
    private long costTime;//做试题的时间
    private long finishedTime;//完成时间

    public Student(String name, long costTime) {
        this.name = name;
        this.costTime = costTime;
        finishedTime = costTime + System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return (finishedTime - System.currentTimeMillis());
    }

    @Override
    public int compareTo(Delayed o) {
        Student s = (Student) o;
        return costTime >= ((Student) o).costTime ? 1 :-1;
    }

    @Override
    public void run() {
        System.out.println(name + " 交卷,用时" + costTime / 1000);
    }
}
