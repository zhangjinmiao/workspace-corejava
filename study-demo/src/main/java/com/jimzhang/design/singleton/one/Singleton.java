package com.jimzhang.design.singleton.one;

/**
 * @author jimzhang
 * <>饿汉模式</>
 * @version V1.0.0
 * @date 2018-04-08 10:58
 */
public class Singleton {
    private static Singleton instance = new Singleton();
    private Singleton() {
    }
    public static Singleton getInstance(){
        return instance;
    }
}
