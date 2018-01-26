package com.jimzhang.demo.util.hongyong.threadlocal.api;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-26 16:33
 */
public class ClientThread extends Thread {

    private Sequence sequence;

    public ClientThread(Sequence sequence){
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i=0;i<3;i++){
            System.out.println(Thread.currentThread().getName() + "=>" + sequence.getNumber());
        }
    }
}
