package com.jimzhang.demo.util.hongyong.aop.springaspectj.declareparents;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 引入增强的默认实现类，运行时自动增强到 GreetingImpl 类中
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-26 15:50
 */
public class ApologyImpl implements Apology{
    @Override
    public void saySorry(String name) {
        System.out.println("对不起！" + name);
    }
}
