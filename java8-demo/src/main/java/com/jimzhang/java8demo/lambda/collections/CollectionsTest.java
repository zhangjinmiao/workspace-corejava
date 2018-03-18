package com.jimzhang.java8demo.lambda.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 对于String或Integer这些已经实现Comparable接口的类来说，可以直接使用Collections.sort
 * 方法传入list参数来实现默认方式（正序）排序；
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-03-18 18:15
 */
public class CollectionsTest {
    static List<Integer> intList = Arrays.asList(2, 3, 1);

    public static void showList(List<Integer> list) {
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    /**
     * 默认 正序
     */
    private static void sortBaseTypeByDefaultMode() {
        System.out.println("before sort:");
        showList(intList);
        System.out.println("=========================");
        Collections.sort(intList);
        System.out.println("after sort:");
        showList(intList);
    }

    /**
     * 自定义排序规则
     */
    private static void sortBaseTypeByIDefineMode() {
        System.out.println("before sort:");
        showList(intList);
        System.out.println("=========================");
        Collections.sort(intList, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                // 返回值为int类型，大于0表示正序，小于0表示逆序
                return o2 - o1;
            }
        });
        System.out.println("after sort:");
        showList(intList);
    }

    public static void main(String[] args) {
//        sortBaseTypeByDefaultMode();
        sortBaseTypeByIDefineMode();

    }
}
