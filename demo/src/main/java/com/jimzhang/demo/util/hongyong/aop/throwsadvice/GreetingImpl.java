package com.jimzhang.demo.util.hongyong.aop.throwsadvice;

import org.springframework.stereotype.Service;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 15:07
 */
@Service
public class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello!" + name);

        throw new RuntimeException("Error");
    }

}
