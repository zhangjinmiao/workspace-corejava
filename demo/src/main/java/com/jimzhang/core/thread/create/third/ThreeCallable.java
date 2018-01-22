package com.jimzhang.core.thread.create.third;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 实现Callable接口来创建
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 17:52
 */
public class ThreeCallable implements java.util.concurrent.Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (;i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreeCallable threeCallable = new ThreeCallable();
        FutureTask<Integer> ft = new FutureTask<Integer>(threeCallable);
        new Thread(ft).start();
        System.out.println("ft" + ft.get());
    }
}
