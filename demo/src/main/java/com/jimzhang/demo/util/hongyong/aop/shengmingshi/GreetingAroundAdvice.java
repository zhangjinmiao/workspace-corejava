package com.jimzhang.demo.util.hongyong.aop.shengmingshi;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 环绕增强 需实现 org.aopalliance.intercept.MethodInterceptor AOP 联盟提供
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 15:30
 */
@Component
public class GreetingAroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        before();
        Object result = methodInvocation.proceed();
        after();
        return result;
    }

    private void before() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("After");
    }
}
