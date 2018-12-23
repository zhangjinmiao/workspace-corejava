package com.jimzhang.core.thread.demo;

/**
 * 〈一句话功能简述〉<br> 〈测试类〉
 *
 * @author zhangjinmiao
 * @create 2018/12/21 14:09
 */
public class TestWrite {

    public static void main(String[] args) {

        test02();
    }

    private static void test02() {
        WriteTask write = new WriteTask();
        for(int i=0;i<4;i++){
            new Thread(write).start();
        }

        OutputTask output = new OutputTask("abc.txt");
        new Thread(output).start();
    }

}
