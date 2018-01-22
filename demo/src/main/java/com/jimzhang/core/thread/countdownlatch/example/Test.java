package com.jimzhang.core.thread.countdownlatch.example;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 测试
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-18 15:53
 */
public class Test {
    public static void main(String[] args) {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: " + result);
    }
}
