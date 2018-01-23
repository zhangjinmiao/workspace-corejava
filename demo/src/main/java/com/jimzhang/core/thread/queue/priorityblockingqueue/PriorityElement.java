package com.jimzhang.core.thread.queue.priorityblockingqueue;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 对象类型
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-23 11:09
 */
public class PriorityElement implements Comparable<PriorityElement> {
    /**
     * 定义优先级
     */
    private int priority;

    PriorityElement(int priority) {
        //初始化优先级
        this.priority = priority;
    }

    @Override
    public int compareTo(PriorityElement o) {
        //按照优先级大小进行排序
        return priority >= o.getPriority() ? 1 : -1;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "PriorityElement [priority=" + priority + "]";
    }
}
