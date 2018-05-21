package com.design.mode.factory.common;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: The9.com</p>
 * @author Jerry Shen
 * @version 0.5
 */


public class SmsSender implements ISender {
    public void send() {
        System.out.println("This is send sms !");
    }
}