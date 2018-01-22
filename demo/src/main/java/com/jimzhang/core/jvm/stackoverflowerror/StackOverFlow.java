package com.jimzhang.core.jvm.stackoverflowerror;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 堆栈溢出
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-03 12:49
 */
public class StackOverFlow {

    public int stackSize = 0;

    public void stackIncre() {
        // 无限制递归
        stackSize++;
        stackIncre();
    }

    public static void main(String[] args) throws Throwable {
        StackOverFlow sof = new StackOverFlow();
        try {
            sof.stackIncre();
        } catch (Throwable e) {
            System.out.println(sof.stackSize);
            throw e;
        }
    }
}
