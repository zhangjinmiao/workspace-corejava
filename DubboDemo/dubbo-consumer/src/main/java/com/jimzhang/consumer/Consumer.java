package com.jimzhang.consumer;

import com.jimzhang.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jimzhang
 * <>服务消费者</>
 * @version V1.0.0
 * @date 2018-03-09 12:46
 */
public class Consumer {

    public static void main(String[] args) {
        //测试常规服务
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println("consumer");
        System.out.println(demoService.getPermissions(1L));
    }
}
