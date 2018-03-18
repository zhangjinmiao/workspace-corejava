package com.jimzhang.core.duotai.test2;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 测试
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-03-17 14:33
 */
public class Test {

    public static void eat(Animal a) {
        if (a instanceof Dog) {
            Dog d = (Dog) a;
            d.eat();
            d.run();//狗有一个跑的方法
        }
        if (a instanceof Cat) {
            Cat c = (Cat) a;
            c.eat();
            //猫会抱怨
            System.out.println("我也想跑，但是不会");
        }
        a.eat();//其他动物只会吃
    }

    public static void main(String[] args) {

        //向上转型
//        Animal animal = new Cat();
//        animal.eat();
//
//        animal = new Dog();
//        animal.eat();

        //
        eat(new Cat());
        eat(new Cat());
        eat(new Dog());

    }
}
