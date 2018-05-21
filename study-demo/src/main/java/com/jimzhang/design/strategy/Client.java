package com.jimzhang.design.strategy;

/**
 * @author jimzhang
 * <>使用</>
 * @version V1.0.0
 * @date 2018-04-20 9:05
 */
public class Client {

    public static void main(String[] args) {

        Context context;
        context = new Context(new ConcreteStrategy1());
        System.out.println("执行策略1");
        context.execute();

        context = new Context(new ConcreteStrategy2());
        System.out.println("执行策略2");
        context.execute();

    }
}
