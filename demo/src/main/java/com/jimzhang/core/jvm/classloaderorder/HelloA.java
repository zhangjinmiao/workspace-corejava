package com.jimzhang.core.jvm.classloaderorder;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 父类
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-04-29 19:36
 */
public class HelloA {
    public HelloA(){
        System.out.println("HelloA");
    }
    {
        System.out.println("I'm A class");
    }
    static {
        System.out.println("static A");
    }
}
