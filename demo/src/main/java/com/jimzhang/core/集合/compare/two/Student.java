package com.jimzhang.core.集合.compare.two;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 学生类——不需实现 Comparable 接口
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-10 10:14
 */
public class Student {

    // 年龄
    private int age;
    // 姓名
    private String name;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 获取学生姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 获取学生年龄
     */
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }
}
