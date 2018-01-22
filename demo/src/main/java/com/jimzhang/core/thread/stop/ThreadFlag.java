package com.jimzhang.core.thread.stop;

import com.jimzhang.demo.util.DateHelper;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 使用定义一个volatile的退出标志终止线程
 *
 * thread.Join 把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
 * 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
 *
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 18:36
 */
public class ThreadFlag extends Thread {

    private volatile boolean exit = false;

    // 终止线程
    public void shutdown() {
        exit = true;
    }

    @Override
    public void run() {
        // 判断线程是否运行
        while (!exit) {
//            System.out.println("执行中...");
        }
        System.out.println("退出运行");
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadFlag threadFlag = new ThreadFlag();
        System.out.println("开始时间：" + DateHelper.getHssCurrentDate());
        threadFlag.start();
        Thread.sleep(5 * 1000);
        threadFlag.shutdown(); // 通过自定义标识符来判断是否终止
        System.out.println("结束时间：" + DateHelper.getHssCurrentDate());
//        threadFlag.stop();// stop() 是过时方法，终止线程时，线程可能还没运行完，不安全，会破坏原子逻辑。
        threadFlag.join(); // 将线程 threadFlag 加入 main 主线程，执行完后执行main 线程
    }
}
