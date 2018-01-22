package com.jimzhang.core.search;

import java.util.Arrays;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 二分查找
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-02 17:10
 */
public class BinarySearch {

    public static void main(String[] args) {
//        int[] num = {1, 2, 3, 4, 5, 6};//必须有序
        int[] num = {1, 2, 6, 2, 5, 4};// 无序时
        // 若数组是无序的先进行排序
        Arrays.sort(num);

        int index = Binary_Search(num, 5);
        System.out.print(index);
    }

    /* num：有序表（由小到大排列）
     * key：要查找的关键字
     * return：还回查找到关键字的下标，没有找到则还回-1
     */
    private static int Binary_Search(int[] num, int key) {
        int low, high, mid;
        low = 0;
        high = num.length - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key < num[mid])
                high = mid - 1;
            else if (key > num[mid])
                low = mid + 1;
            else// 如果等于则直接还回下标值
                return mid;
        }
        return -1;
    }
}
