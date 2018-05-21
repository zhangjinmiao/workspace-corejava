package com.design.mode.factory.common;

/**
 * 普通工厂模式，就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建
 * @author Administrator
 *
 */
public class Test {

    public static void main(String[] args) {
        Factory myFactory = new Factory();
        ISender smSender = myFactory.createSender("sms");
        smSender.send();

        ISender emailSender = myFactory.createSender("email");
        emailSender.send();
    }
}
