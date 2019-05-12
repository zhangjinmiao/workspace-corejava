package com.jimzhang.test;

/**
 * 〈一句话功能简述〉<br> 〈〉
 *
 * @author zhangjinmiao
 * @create 2019/1/10 16:36
 */
public class TsetDemo {

  public static void main(String[] args) {
    Integer a = 1547136000; // 2019/1/11 0:0:0
    Integer b = 1547049600; // 2019/1/10 0:0:0

    System.out.println("<<使用 compareTo>>");
    boolean b1 = b.compareTo(a) == -1;
    System.out.println("b小于a:" + b1);

    boolean b2 = a.compareTo(b) == 1;
    System.out.println("a大于b:" + b2);

    System.out.println("<<使用 compare>>");
    boolean c1 = Integer.compare(b, a) == -1;
    System.out.println("b小于a:" + c1);

    boolean c2 = Integer.compare(a, b) == 1;
    System.out.println("a大于b:" + c2);

    b = a;
    boolean b3 = a.compareTo(b) == 0;
    System.out.println("a等于b:" + b3);

    boolean c3 = Integer.compare(a, b) == 0;
    System.out.println("a等于b:" + c3);

  }
}
