package com.jimzhang.demo.util.hongyong.aop.introductadvice;

import org.springframework.stereotype.Service;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 无须直接实现 Apology 接口,即可使用 saySorry 方法
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 16:54
 */
@Service
public class GreetingImpl implements Greeting{

    @Override
    public void sayHello(String name) {
        System.out.println("你好：" + name);
    }
}
