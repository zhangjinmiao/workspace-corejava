package com.jimzhang.demo.util.hongyong.aop.introductadvice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 客户端
 *
 * ********************************************
 * 引入增强：
 * 对类的增强（引入），
 * 接口 Greeting 有方法 sayHello，接口 Apology 有方法 saySorry
 * 实现类 GreetingImpl 实现接口Greeting，所以有方法 sayHello，但没实现 Apology，所以没有方法 saySorry
 * （若实现 Apology ，还需改写类 GreetingImpl，真实场景可能类有1 万行代码）
 * 现在 使用引入增强，类 GreetingImpl 无须实现接口 Apology，即可使用方法 saySorry
 *
 * ********************************************
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-24 15:03
 */
public class Client {
    public static void main(String[] args) {
        // 获取 Spring Context
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-introductadvice.xml");
        // 注意：转型为目标类，而并非 Greeting 接口
        GreetingImpl greetingImpl = (GreetingImpl) context.getBean("greetingProxy");
        // 调用代方法
        greetingImpl.sayHello("张晋苗");

        // 将目标类强制向上转型为 Apology 接口（这是引入增强给我们带来的特性，也就是 “接口动态实现”功能）
        Apology apology = (Apology) greetingImpl;
        apology.saySorry("二磊");
    }
}
