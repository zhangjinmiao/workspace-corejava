package com.jimzhang.core.jvm.heapoverflow;

import java.util.ArrayList;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 堆内存溢出
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-03 12:54
 */
public class HeapOverFlow {
    public static void main(String[] args) {
        ArrayList<HeapOverFlow> list = new ArrayList<HeapOverFlow>();

        while (true) {
            // 无限制地往list里面塞对象
            list.add(new HeapOverFlow());
        }
    }
}
