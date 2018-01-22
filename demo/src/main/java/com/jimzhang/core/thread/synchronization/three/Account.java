package com.jimzhang.core.thread.synchronization.three;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 银行账户——使用锁机制
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-10 14:07
 */
public class Account {

    private Lock accountLock = new ReentrantLock();
    private double balance; // 账户余额

    /**
     * 存款
     *
     * @param money 存入金额
     */
    public void deposit(double money) {
        accountLock.lock();
        try {
            double newBalance = balance + money;
            try {
                Thread.sleep(10); // 模拟此业务需要一段处理时间
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            balance = newBalance;
        } finally {
            accountLock.unlock();
        }
    }

    /**
     * 获得账户余额
     */
    public double getBalance() {
        return balance;
    }
}
