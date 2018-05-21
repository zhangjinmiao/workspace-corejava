package com.jimzhang.design.strategy.example1;


/**
 * @author jimzhang
 * <>客户端使用</>
 * @version V1.0.0
 * @date 2018-04-20 9:30
 */
public class Client {
    public static void main(String[] args) {
        LogContext context = new LogContext();
        context.log("记录日志");
        context.log("再次记录日志");
    }
}
