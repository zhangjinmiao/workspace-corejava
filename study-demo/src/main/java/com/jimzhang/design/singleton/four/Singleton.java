package com.jimzhang.design.singleton.four;

/**
 * @author jimzhang
 * <>双重校验锁</>
 * @version V1.0.0
 * @date 2018-04-08 11:23
 */
public class Singleton {
    private volatile static Singleton instance;
    private Singleton(){}
    public static Singleton getInstance(){
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
