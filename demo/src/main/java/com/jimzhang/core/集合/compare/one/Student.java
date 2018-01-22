package com.jimzhang.core.集合.compare.one;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 学生类 实现 Comparable 接口
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-10 10:05
 */
public class Student implements Comparable<Student> {


    private String name;
    private int age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        // 年龄升序
        return this.age - o.age;
    }
}
