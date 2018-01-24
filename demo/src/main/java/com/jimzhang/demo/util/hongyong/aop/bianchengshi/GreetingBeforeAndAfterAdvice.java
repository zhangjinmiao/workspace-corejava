package com.jimzhang.demo.util.hongyong.aop.bianchengshi;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 组合增强：包含前置增强和后置增强
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 15:27
 */
public class GreetingBeforeAndAfterAdvice implements MethodBeforeAdvice,AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("After");
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before");
    }
}
