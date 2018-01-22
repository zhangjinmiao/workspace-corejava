package com.jimzhang.core.thread.queue;

import java.util.UUID;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 工作任务
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-12 10:42
 */
public class Task {

    private String id;  // 任务的编号

    public Task() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Task[" + id + "]";
    }
}
