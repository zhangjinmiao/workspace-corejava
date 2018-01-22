package com.jimzhang.core.jvm.weixiaoge;


/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-04 16:57
 */
public class LoadTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = HelloWorld.class.getClassLoader();
        System.out.println(loader);
        //使用ClassLoader.loadClass()来加载类，不会执行初始化块
//        loader.loadClass("com.jimzhang.core.jvm.weixiaoge.Test2");
        //使用Class.forName()来加载类，默认会执行初始化块
        Class.forName("com.jimzhang.core.jvm.weixiaoge.Test2");
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
        //Class.forName("Test2", false, loader);
    }

}
