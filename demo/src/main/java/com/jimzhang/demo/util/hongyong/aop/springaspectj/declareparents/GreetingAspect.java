package com.jimzhang.demo.util.hongyong.aop.springaspectj.declareparents;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description Aspect 切面类
 * 基于 Aspect 的引入增强
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-26 14:48
 */
@Aspect
@Component
public class GreetingAspect {

    /**
     * 运行时需动态实现的接口
     * value：目标类
     * defaultImpl：引入接口的默认实现
     */
    @DeclareParents(value = "com.jimzhang.demo.util.hongyong.aop.springaspectj.declareparents.GreetingImpl2",defaultImpl =
    ApologyImpl.class)
    private Apology apology;

    public void before(){
        System.out.println("Before");
    }

    public void after(){
        System.out.println("After");
    }
}
