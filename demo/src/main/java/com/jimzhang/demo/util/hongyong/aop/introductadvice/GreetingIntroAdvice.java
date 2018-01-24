package com.jimzhang.demo.util.hongyong.aop.introductadvice;


import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 引入增强实现 ：扩展 DelegatingIntroductionInterceptor，同时实现接口 Apology
 *
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 16:43
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology{

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        return super.invoke(mi);
    }

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! " + name);
    }
}
