package com.jimzhang.test;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-04-29 17:44
 */
public class Test {

    /**
     * pong
     * ping
     * @param args
     */
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                pong();
            }
        };
        t.run();
        System.out.println("ping");

        System.out.println("5" + 2);
    }

    static void pong(){
        System.out.println("pong");
    }

}
