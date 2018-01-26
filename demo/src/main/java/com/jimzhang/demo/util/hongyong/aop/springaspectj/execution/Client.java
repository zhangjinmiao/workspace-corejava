package com.jimzhang.demo.util.hongyong.aop.springaspectj.execution;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 客户端
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 15:03
 */
public class Client {
    public static void main(String[] args) {
        // 获取 Spring Context
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aspect.xml");
        // 从 Context 中根据 id 获取 Bean 对象（其实就一个代理）
        GreetingImpl greetingImpl = (GreetingImpl) context.getBean("greetingImpl");
        // 调用代方法
        greetingImpl.sayHello("张晋苗");
        greetingImpl.goodMorning("张晋苗");
        greetingImpl.goodNight("张晋苗");

    }
}
