package com.jimzhang.demo.util.hongyong.aop.bianchengshi;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 客户端
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 15:03
 */
public class Client {
    public static void main(String[] args) {
        // 创建代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        // 射入目标对象
        proxyFactory.setTarget(new GreetingImpl());
        // 方式一：分别添加 前置 后置
//        // 添加前置增强：实现 MethodBeforeAdvice
//        proxyFactory.addAdvice(new GreetingBeforeAdvice());
//        // 添加后置增强：实现 AfterReturningAdvice
//        proxyFactory.addAdvice(new GreetingAfterAdvice());
        // 方式二：添加组合增强 : 实现 MethodBeforeAdvice 和 AfterReturningAdvice
//        proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());
        // 方式三：添加环绕增强：实现 MethodInterceptor
        proxyFactory.addAdvice(new GreetingAroundAdvice());
        // 从代理工厂获取代理
        Greeting greeting = (Greeting) proxyFactory.getProxy();
        // 调用代理方法
        greeting.sayHello("zhangjinmiao");
    }
}
