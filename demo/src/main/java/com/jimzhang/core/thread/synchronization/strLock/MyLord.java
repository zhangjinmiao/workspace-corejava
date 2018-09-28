package com.jimzhang.core.thread.synchronization.strLock;

/**
 * 〈一句话功能简述〉<br> 〈〉
 *
 * @author zhangjinmiao
 * @create 2018/9/14 12:42
 */
public class MyLord {

    public void load(String str) {
        try{
            synchronized (str) {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        MyLord myLord = new MyLord();

        MyThreadA a = new MyThreadA(myLord);
        MyThreadB b = new MyThreadB(myLord);

        a.start();
        b.start();
    }
}
