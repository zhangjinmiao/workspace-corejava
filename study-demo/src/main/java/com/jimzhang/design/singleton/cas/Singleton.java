package com.jimzhang.design.singleton.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author jimzhang
 * <>使用 CAS 实现单例</>
 * 非阻塞：
 * 用CAS的好处在于不需要使用传统的锁机制来保证线程安全,CAS是一种基于忙等待的算法,依赖底层硬件的实现,相对于锁它没有线程切换
 * 和阻塞的额外消耗,可以支持较大的并行度。
 * CAS 的一个重要缺点在于如果忙等待一直执行不成功(一直在死循环中),会对CPU造成较大的执行开销。
 * @version V1.0.0
 * @date 2018-04-08 11:29
 */
public class Singleton {
    private static final AtomicReference<Singleton> INSTANCE = new AtomicReference<>();
    private Singleton(){}
    public static Singleton getInstance(){
        for (;;) {
            Singleton singleton = INSTANCE.get();
            if (null != singleton) {
                return singleton;
            }
            singleton = new Singleton();
            if (INSTANCE.compareAndSet(null,singleton)) {
                return singleton;
            }
        }
    }
}
