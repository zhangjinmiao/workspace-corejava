package com.jimzhang.core.thread.synchronization.one;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 银行账户
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-10 10:39
 */
public class Account {

    private double balance;     // 账户余额

    /**
     * 存款
     *
     * @param money 存入金额
     */
    public void deposit(double money) {
        double newBalance = balance + money;
        try {
            Thread.sleep(10);   // 模拟此业务需要一段处理时间
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        balance = newBalance;
    }

    /**
     * 获得账户余额
     */
    public double getBalance() {
        return balance;
    }
}
