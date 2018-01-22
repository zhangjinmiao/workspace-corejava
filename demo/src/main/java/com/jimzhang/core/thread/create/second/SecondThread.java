package com.jimzhang.core.thread.create.second;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 实现Runnable接口
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 17:51
 */
public class SecondThread implements Runnable{
    @Override
    public void run() {
        System.out.println("second thread");
    }

    public static void main(String[] args) {
        new Thread(new SecondThread()).start();
    }
}
