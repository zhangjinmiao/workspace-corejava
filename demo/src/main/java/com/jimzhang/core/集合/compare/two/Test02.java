package com.jimzhang.core.集合.compare.two;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-10 10:15
 */
public class Test02 {

    public static void main(String[] args) {
        // Java 7的钻石语法(构造器后面的尖括号中不需要写类型)
        List<Student> list = new ArrayList<>();
        list.add(new Student("Hao LUO", 33));
        list.add(new Student("XJ WANG", 32));
        list.add(new Student("Bruce LEE", 60));
        list.add(new Student("Bob YANG", 22));

        // 通过sort方法的第二个参数传入一个Comparator接口对象
        // 相当于是传入一个比较对象大小的算法到sort方法中
        // 由于Java中没有函数指针、仿函数、委托这样的概念
        // 因此要将一个算法传入一个方法中唯一的选择就是通过接口回调
        Collections.sort(list, new Comparator<Student>() {

            @Override
            public int compare(Student o1, Student o2) {
                // 比较学生姓名
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (Student stu : list) {
            System.out.println(stu);
        }
//        输出
//        Student[name = Bob YANG, age = 22]
//        Student[name = Bruce LEE, age = 60]
//        Student[name = Hao LUO, age = 33]
//        Student[name = XJ WANG, age = 32]
    }
}
