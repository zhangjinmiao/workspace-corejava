package com.jimzhang.core.jvm.classloaderorder;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 子类
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-04-29 19:37
 */
public class HelloB extends HelloA {
    public HelloB(){
        System.out.println("HelloB");
    }
    {
        System.out.println("I'm B class");
    }
    static {
        System.out.println("static B");
    }

    public static void main(String[] args) {
        new HelloB();

    }
}
