package com.jimzhang.design.strategy.example2;

/**
 * @author jimzhang
 * <>把日志记录到数据库</>
 * @version V1.0.0
 * @date 2018-04-20 9:29
 */
public class DbLog extends LogStrategyTemplate {
    @Override
    protected void doLog(String msg) {
        //制造错误
        if (msg != null && msg.trim().length() > 5) {
            int a = 5 / 0;
        }
        System.out.println("现在把 '" + msg + "' 记录到数据库中");
    }
}
