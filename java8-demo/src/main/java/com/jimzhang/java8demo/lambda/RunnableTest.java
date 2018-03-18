package com.jimzhang.java8demo.lambda;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description Runnable 使用
 *
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-03-18 17:58
 */
public class RunnableTest {

    /**
     *  (params) -> expression
     *  (params) -> statement
     *  (params) -> { statements }
     *  只输出：() -> System.out.println("Hello Lambda Expressions");
     *  有参数 (int x, int y) -> x + y
     * @param args
     */
    public static void main(String[] args) {
        // before 8
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        // java 8
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();

    }
}
