package com.jimzhang.demo.util.hongyong.aop.bianchengshi;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 后置增强
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 15:02
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("After");
    }
}
