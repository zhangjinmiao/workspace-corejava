package com.jimzhang.core.ArrayInversion;

import java.util.ArrayList;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 数组反转
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-02 16:37
 */
public class ArrayInverse {

    public static void main(String[] args) {
//        ArrayList arrayList = new ArrayList();
//        arrayList.add("A");
//        arrayList.add("B");
//        arrayList.add("C");
//        arrayList.add("D");
//        arrayList.add("E");
//        System.out.println("反转前排序: " + arrayList);
//        // 使用 Collections.reverse(ArrayList) 将数组进行反转
//        Collections.reverse(arrayList);
//
//        System.out.println("反转后排序: " + arrayList);


        String[] Array = {"a", "b", "c", "d", "e"};
        reverseArray1(Array);// 使用集合ArrayList实现反转
        for (int j = 0; j < Array.length; j++) {
            System.out.print(Array[j] + " ");
        }

        System.out.print("\n");

        String[] temp = reverseArray2(Array);// 直接使用数组实现反转
        for (int j = 0; j < temp.length; j++) {
            System.out.print(Array[j] + " ");
        }
    }


    /*
   * 函数：reverseArray1和reverseArray2
   * 功能：实现 数组翻转
   * 例如：{'a','b','c','d'}变成{'d','c','b','a'}
   */

    /**
     * 使用 ArrayList 实现
     * @param Array 逆向读取原数组赋给ArrayList，再从array_list 中取出 数组数据，感觉多此一举，直接使用reverseArray2多好
     */
    private static void reverseArray1(String[] Array) {
        ArrayList<String> array_list = new ArrayList<String>();
        for (int i = 0; i < Array.length; i++) {
            array_list.add(Array[Array.length - i - 1]);
        }
        Array = array_list.toArray(Array); // 将 array_list 中的 数组赋给 Array
    }

    /**
     * 使用 Array 实现
     * @param Array 逆向读取原数组赋给新数组
     * @return
     */
    private static String[] reverseArray2(String[] Array) {
        String[] new_array = new String[Array.length];
        for (int i = 0; i < Array.length; i++) {
            // 反转后数组的第一个元素等于源数组的最后一个元素：
            new_array[i] = Array[Array.length - i - 1];
        }
        return new_array;
    }
}
