package com.jimzhang.core.thread.create.first;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 继承Thread类
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 17:49
 */
public class FirstThread extends Thread {
    @Override
    public void run() {
        System.out.println("first thread");
    }

    public static void main(String[] args) {
        new FirstThread().start();
    }
}
