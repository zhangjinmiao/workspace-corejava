package com.jimzhang.demo.util.hongyong.threadlocal.mythreadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 自定义ThreadLocal
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-26 16:51
 */
public class MyThreadLocal<T> {

    private Map<Thread,T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T value) {
        container.put(Thread.currentThread(),value);
    }

    public T get(){
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if ( value == null && !container.containsKey(thread)){
            value = initialValue();
            container.put(thread,value);
        }
        return value;
    }

    public void remove(){
        container.remove(Thread.currentThread());
    }

    protected T initialValue() {
        return null;
    }
}
