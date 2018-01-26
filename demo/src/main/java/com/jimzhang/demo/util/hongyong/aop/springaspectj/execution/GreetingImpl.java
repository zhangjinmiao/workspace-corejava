package com.jimzhang.demo.util.hongyong.aop.springaspectj.execution;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 16:54
 */
//@Service
public class GreetingImpl implements Greeting {

    @Override
    public void sayHello(String name) {
        System.out.println("你好：" + name);
    }

    public void goodMorning(String name){
        System.out.println("Good Morning! " + name);
    }

    public void goodNight(String name) {
        System.out.println("Good Night! " + name);
    }
}
