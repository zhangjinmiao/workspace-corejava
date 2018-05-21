package com.jimzhang.design.singleton.two;

/**
 * @author jimzhang
 * <>静态内部类</>
 * 利用了classloder的机制来保证初始化instance时只有一个线程
 * 这种方式是Singleton类被装载了，instance不一定被初始化。因为SingletonHolder类没有被主动使用，
 * 只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance。
 *
 *  如果实例化instance很消耗资源，我想让他延迟加载，另外一方面，我不希望在Singleton类加载时就实例化，
 * 因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，那么这个时候实例化instance显然是不合适的, 这种方式就比较合适了。
 * @version V1.0.0
 * @date 2018-04-08 11:01
 */
public class Singleton {
    private static class SingletonHolder{
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton() {
    }
    public static final Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
