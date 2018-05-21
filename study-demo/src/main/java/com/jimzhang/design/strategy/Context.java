package com.jimzhang.design.strategy;

/**
 * @author jimzhang
 * <>封装类：也叫上下文，对策略进行二次封装，目的是避免高层模块对策略的直接调用。</>
 * @version V1.0.0
 * @date 2018-04-20 9:04
 */
public class Context {

    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        strategy.doSomething();
    }
}
