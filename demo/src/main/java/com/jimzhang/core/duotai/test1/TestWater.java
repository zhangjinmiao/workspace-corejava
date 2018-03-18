package com.jimzhang.core.duotai.test1;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 测试
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-03-17 14:27
 */
public class TestWater {

    public static void main(String[] args) {
        Water w = new WarmWater();
        w.showTem();

        w = new IceWater();
        w.showTem();

        w = new HotWater();
        w.showTem();

    }
}
