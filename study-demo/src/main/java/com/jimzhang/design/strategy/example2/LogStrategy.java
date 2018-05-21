package com.jimzhang.design.strategy.example2;

/**
 * @author jimzhang
 * <>日志记录策略的接口</>
 * @version V1.0.0
 * @date 2018-04-20 9:22
 */
public interface LogStrategy {
    /**
     * 记录日志
     *
     * @param msg 需记录的日志信息
     */
    public void log(String msg);
}
