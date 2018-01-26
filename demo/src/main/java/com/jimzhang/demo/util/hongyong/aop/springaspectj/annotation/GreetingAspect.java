package com.jimzhang.demo.util.hongyong.aop.springaspectj.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description Aspect 切面类：通过 AspectJ @annotation 表达式拦截方法，要拦截的方法使用注解 @Tag
 *
 * 注解：
 * Before           前置增强
 * After            后置增强
 * Around           环绕增强
 * AfterThrowing    抛出增强
 * DeclareParents   引入增强
 * AfterReturn      返回后增强（Finally 增强，比 After 晚）
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-26 14:48
 */
@Aspect
//@Component
public class GreetingAspect {

    /**
     *
     * @param pjp 连接点 可通过该对象获取方法的任何信息：方法名、参数
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.jimzhang.demo.util.hongyong.aop.springaspectj.annotation.Tag)")
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
