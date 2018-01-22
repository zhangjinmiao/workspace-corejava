package com.jimzhang.core.集合.compare.one;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 测试1
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-10 10:12
 */
public class Test01 {
    /**
     * TreeSet要求存放的对象所属的类必须实现Comparable接口，该接口提供了比较元素的compareTo()方法，当插入元素时会回调该方法比较元素的大小。
     * @param args
     */
    public static void main(String[] args) {
        // Java 7的钻石语法(构造器后面的尖括号中不需要写类型)
        Set<Student> set = new TreeSet<>();
        set.add(new Student("Hao LUO", 33));
        set.add(new Student("XJ WANG", 32));
        set.add(new Student("Bruce LEE", 60));
        set.add(new Student("Bob YANG", 22));

        for (Student stu : set) {
            System.out.println(stu);
        }

//      输出结果:
//      Student [name=Bob YANG, age=22]
//      Student [name=XJ WANG, age=32]
//      Student [name=Hao LUO, age=33]
//      Student [name=Bruce LEE, age=60]

    }
}
