package com.jimzhang.design.strategy.example2;

/**
 * @author jimzhang
 * <>把日志记录到文件</>
 * @version V1.0.0
 * @date 2018-04-20 9:29
 */
public class FileLog extends LogStrategyTemplate {
    @Override
    protected void doLog(String msg) {
        System.out.println("现在把 '" + msg + "' 记录到文件中");
    }
}
