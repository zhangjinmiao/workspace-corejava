package com.jimzhang.demo.util.hongyong.aop.springaspectj.execution;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description Aspect 切面类：通过 AspectJ execution 表达式拦截方法
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-26 14:48
 */
@Aspect
//@Component
public class GreetingAspect {

    /**
     *
     * @param pjp 连接点 可通过该对象获取方法的任何信息：方法名、参数
     *            execution：表示拦截方法，括号中定义匹配规则
     *            第一个“*”表示方法的返回值是任意的
     *            第二个“*”表示匹配该类中所有的方法
     *            (..) 表示方法参数是任意的
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.jimzhang.demo.util.hongyong.aop.springaspectj.execution.GreetingImpl.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        before();
        Object result = pjp.proceed();
        after();
        return result;

    }

    public void before(){
        System.out.println("Before");
    }

    public void after(){
        System.out.println("After");
    }
}
