package com.jimzhang.core.string;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 字符串反转
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 9:31
 */
public class StringReverse {

    public static String reverse(String originStr) {
        if (originStr == null || originStr.length() <=1) {
            return originStr;
        }
        String s = reverse(originStr.substring(1));
        System.out.println(originStr);
        System.out.println(s);
        char c = originStr.charAt(0);
//        return reverse(originStr.substring(1)) + originStr.charAt(0);
        return s + c;
    }

    public static void main(String[] args) {
        String hello = reverse("hello");
        System.out.println(hello);
    }
}
