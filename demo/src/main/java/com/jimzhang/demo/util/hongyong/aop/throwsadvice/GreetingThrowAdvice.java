package com.jimzhang.demo.util.hongyong.aop.throwsadvice;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 抛出增强
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 16:17
 */
@Component
public class GreetingThrowAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method,Object[] args, Object target, Exception e){
        System.out.println("------- Throw Exception -----------");
        System.out.println("Target Class:" + target.getClass().getName());
        System.out.println("Method Name:" + method.getName());
        System.out.println("Exception Message:" + e.getMessage());
        System.out.println("------------------------------------");
    }
}
