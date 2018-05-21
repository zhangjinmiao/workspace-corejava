package com.jimzhang.design.singleton;
/*
 * 饿汉，其实都是通过定义静态的成员变量，以保证 instance 可以在类初始化的时候被实例化。
 *
 * 静态内部类，这种方式是Singleton类被装载了，instance不一定被初始化。因为SingletonHolder类没有被主动使用，只有显示通过调用
 * getInstance 方法时，才会显示装载 SingletonHolder 类，从而实例化 instance。原理和饿汉模式一样。
 *
 * 枚举，其实，如果把枚举类进行反序列化，你会发现他也是使用了static final来修饰每一个枚举项
 *
 * 以上都是利用了ClassLoader 的线程安全机制，
 * ClassLoader 的loadClass 方法在加载类的时候使用了synchronized 关键字。也正是因为这样， 除非被重写，
 * 这个方法默认在整个装载过程中都是同步的（线程安全的）。
 */