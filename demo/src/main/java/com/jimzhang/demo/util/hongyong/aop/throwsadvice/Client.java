package com.jimzhang.demo.util.hongyong.aop.throwsadvice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 客户端
 *
 * ********************************************
 * 抛出增强：
 * 对方法的增强
 * 程序报错，抛出异常，一般的做法是打印到控制台或日志文件中，更好的做法是使用抛出增强
 * 抛出增强类实现 org.springframework.aop.ThrowsAdvice 接口，在接口方法中可获取方法、参数、目标对象、异常对象等信息，可把
 * 这些信息统一写入日志或持久化到数据库
 *
 * ********************************************
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 15:03
 */
public class Client {
    public static void main(String[] args) {
        // 获取 Spring Context
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-throwsadvice.xml");
        // 从 Context 中根据 id 获取 Bean 对象（其实就一个代理）
        Greeting greeting = (Greeting) context.getBean("greetingProxy");
        // 调用代方法
        greeting.sayHello("张晋苗");

    }
}
