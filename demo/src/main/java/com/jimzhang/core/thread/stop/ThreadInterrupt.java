package com.jimzhang.core.thread.stop;

import java.io.IOException;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 使用interrupt方法中断线程
 *
 * ********************************************************************************************************************
 * thread.interrupt() 方法：中断线程，将线程中断标示位设置为true，不会立刻清除中断标示位，即不会将中断标设置为false
 * thread.interrupted() 方法：该方法调用后会将中断标示位清除，即重新设置为false，不推荐使用
 * 通过 Thread.currentThread().isInterrupted() 方法来判断是否已中断
 * ********************************************************************************************************************
 *
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 18:42
 */
public class ThreadInterrupt extends Thread {

    @Override
    public void run() {
        try {
            sleep(50000);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ThreadInterrupt threadInterrupt = new ThreadInterrupt();
        threadInterrupt.start();
        System.in.read();
        threadInterrupt.interrupt();  // 不会中断正在运行的线程
        threadInterrupt.join(); // 将线程 threadFlag 加入 main 主线程，执行完后执行main 线程
    }
}
