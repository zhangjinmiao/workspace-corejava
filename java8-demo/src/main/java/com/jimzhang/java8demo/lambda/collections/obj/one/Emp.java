package com.jimzhang.java8demo.lambda.collections.obj.one;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 员工类
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-03-18 18:34
 */
public class Emp {
    private int empno;
    private String ename;

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Emp(int empno, String ename) {
        super();
        this.empno = empno;
        this.ename = ename;
    }
    @Override
    public String toString() {
        return "empno:\t" + empno + "\tename:\t" + ename;
    }
}
