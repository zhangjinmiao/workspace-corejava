package com.jimzhang.java8demo.lambda.collections.obj;

import com.jimzhang.java8demo.lambda.collections.obj.one.Emp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import com.jimzhang.java8demo.lambda.collections.obj.two.Emp;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-03-18 18:37
 */
public class TestObj {

    static List<Emp> empList;

    static {
        Emp emp1 = new Emp(2, "Guan YunChang");
        Emp emp2 = new Emp(3, "Zhang Fei");
        Emp emp3 = new Emp(1, "Liu Bei");
        empList = Arrays.asList(emp1, emp2, emp3);
    }

    public static void showList(List<Emp> list) {
        for (Emp i : list) {
            System.out.println(i);
        }
    }

    /**
     * two
     */
    private static void sortEmpByDefaultMode() {
        System.out.println("before sort:");
        showList(empList);
        System.out.println("=========================");
//        Collections.sort(empList);
        System.out.println("after sort:");
        showList(empList);
    }


    public static int compareByNameThenNo(Emp o1, Emp o2) {
        if (o1.getEname().equals(o2.getEname())) {
            return o1.getEmpno() - o2.getEmpno();
        } else {
            return o1.getEname().compareTo(o2.getEname());
        }
    }

    /**
     * one
     */
    private static void sortEmpByIDefineMode() {
        System.out.println("before sort:");
        showList(empList);
        System.out.println("=========================");
        /*Collections.sort(empList, new Comparator<Emp>() {
            @Override
            public int compare(Emp o1, Emp o2) {
                *//*按员工编号正序排序*//*
//                return o1.getEmpno() - o2.getEmpno();
                *//*按员工编号逆序排序*//*
                //return o2.getEmpno()-o1.getEmpno();
                *//*按员工姓名正序排序*//*
                return o1.getEname().compareTo(o2.getEname());
                *//*按员工姓名逆序排序*//*
                //return o2.getEname().compareTo(o1.getEname());
            }
        });*/


        empList.sort(
                // 1.
//                (Emp o1, Emp o2) -> o1.getEmpno() - o2.getEmpno()
                // 2. 没有类型定义的排序-编译器自己可以进行类型判断
                (o1, o2) -> o1.getEmpno() - o2.getEmpno()
                // 3.使用静态方法的引用来排序
//                Emp::compareByNameThenNo
        );

        // 4. 提取Comparator进行排序
        Collections.sort(empList, Comparator.comparing(Emp::getEname));

        System.out.println("after sort:");
        showList(empList);
    }

    /**
     * 反转
     */
    private static void reverse(){
        System.out.println("before sort:");
        showList(empList);
        System.out.println("=========================");
        Comparator<Emp> comparator = (o1, o2) -> o1.getEmpno() - o2.getEmpno();
        System.out.println("after sort:");
        showList(empList);
        System.out.println("=========================");
        empList.sort(comparator.reversed());
        System.out.println("after reversed sort:");
        showList(empList);
    }

    /**
     * 多条件排序
     */
    private static void multiCondition(){
        List<Emp> list = Arrays.asList(
                new Emp(2, "Guan YunChang"),
                new Emp(1, "Liu Bei"),
                new Emp(3, "Zhang Fei"),
                new Emp(1, "Liu Biao"),
                new Emp(4, "Zhao Yun"),
                new Emp(6, "Zhang Fei"),
                new Emp(5, "Zhao ZiLong")
        );
        System.out.println("before sort:");
        showList(list);
        System.out.println("=========================");
        list.sort((o1,o2) ->{
            if (o1.getEname().equals(o2.getEname())) {
                return o1.getEmpno() - o2.getEmpno();
            } else {
                return o1.getEname().compareTo(o2.getEname());
            }
        });
        System.out.println("after sort:");
        showList(list);
    }


    /**
     * 多条件组合排序
     */
    public static void mutilConditionZuhe(){
        System.out.println("before sort:");
        showList(empList);
        System.out.println("=========================");
        empList.sort(Comparator.comparing(Emp::getEname).thenComparing(Emp::getEmpno));
        System.out.println("after sort:");
        showList(empList);
    }

    public static void main(String[] args) {
//        sortEmpByIDefineMode();
//        sortEmpByDefaultMode();
//        reverse();
//        multiCondition();
        mutilConditionZuhe();
    }
}
