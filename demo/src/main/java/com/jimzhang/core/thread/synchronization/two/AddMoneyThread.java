package com.jimzhang.core.thread.synchronization.two;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 存钱线程
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-10 14:02
 */
public class AddMoneyThread implements Runnable{

    private Account account;    // 存入账户
    private double money;       // 存入金额

    public AddMoneyThread(Account account, double money) {
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        synchronized (account) {
            account.deposit(money);
        }
    }

}
