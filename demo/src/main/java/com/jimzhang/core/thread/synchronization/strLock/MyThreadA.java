package com.jimzhang.core.thread.synchronization.strLock;

/**
 * 〈一句话功能简述〉<br> 〈〉
 *
 * @author zhangjinmiao
 * @create 2018/9/14 12:41
 */
public class MyThreadA extends Thread{
    private MyLord myLord;

    public MyThreadA(MyLord myLord) {
        this.myLord = myLord;
    }

    @Override
    public void run() {
        myLord.load("sc");
    }
}
