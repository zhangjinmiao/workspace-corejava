package com.jimzhang.demo.util.hongyong.aop.bianchengshi;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 前置增强
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 15:00
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before");
    }
}
