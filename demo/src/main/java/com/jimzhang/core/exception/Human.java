package com.jimzhang.core.exception;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 里氏替换原则：能使用父类型的地方，一定能使用子类型
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-09 10:27
 */
public class Human {

    public static void main(String[] args) {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance"); // 1
                throw a;
            }
        } catch (Sneeze sneeze) {
//            sneeze.printStackTrace();
            System.out.println("Caught Sneeze");    // 2
            return;
        }finally {
            System.out.println("Hello World!");     // 3
        }
    }
}
