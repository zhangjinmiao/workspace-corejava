package com.jimzhang.core.thread.queue.delayqueue.test4;

/**
 * 时间常量
 */
public enum Times {
    // 考试总时间
    SUBMIT_TIME(10),
    // 交卷限制时间
    SUMBMIT_LIMIT(2),
    // 模拟考生所需最大时间
    MAX_RAND_TIME(15);
    private final int value;

    private Times(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
