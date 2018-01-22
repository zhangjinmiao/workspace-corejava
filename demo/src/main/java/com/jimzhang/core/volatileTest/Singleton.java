package com.jimzhang.core.volatileTest;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description volatile 使用
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 16:07
 */
public class Singleton {

    private volatile static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }


}
