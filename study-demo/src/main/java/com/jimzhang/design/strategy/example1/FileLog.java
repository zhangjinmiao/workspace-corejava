package com.jimzhang.design.strategy.example1;

/**
 * @author jimzhang
 * <>把日志记录到文件</>
 * @version V1.0.0
 * @date 2018-04-20 9:41
 */
public class FileLog implements LogStrategy {
    @Override
    public void log(String msg) {
        System.out.println("现在把 '" + msg + "' 记录到文件中");
    }
}
