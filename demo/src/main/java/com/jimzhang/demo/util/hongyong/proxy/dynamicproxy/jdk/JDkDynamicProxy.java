package com.jimzhang.demo.util.hongyong.proxy.dynamicproxy.jdk;

import com.jimzhang.demo.util.hongyong.proxy.Hello;
import com.jimzhang.demo.util.hongyong.proxy.HelloImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 只能代理有接口的类，不能代理没有接口的类
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-23 16:57
 */
public class JDkDynamicProxy implements InvocationHandler {

    /**
     * 被代理的目标对象
     */
    private Object target;

    public JDkDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        // 参数说明：ClassLoader、该实现类的所有接口、动态代理对象
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    private void before() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("After");
    }

    public static void main(String[] args) {
        JDkDynamicProxy dynamicProxy = new JDkDynamicProxy(new HelloImpl());
        // 需要强转
        // 使用 JDK 提供的 Proxy 类的工厂方法 newProxyInstance 动态地创建 Hello 接口的代理类
        Hello helloProxy = dynamicProxy.getProxy();
        helloProxy.say("zjm");
    }
}
