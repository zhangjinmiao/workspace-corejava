package com.jimzhang.core.jvm;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-02 20:34
 */
public class Test {

    public static void main(String[] args) {
        int a = -6;
        for (int i = 0;i < 32; i++){
            int t = (a & 0x80000000 >>> i) >>> (31 - i);
            System.out.print(t);
        }

    }
}
