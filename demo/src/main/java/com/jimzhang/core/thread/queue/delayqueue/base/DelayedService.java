package com.jimzhang.core.thread.queue.delayqueue.base;

import com.jimzhang.core.thread.ThreadPoolUtil;

import java.util.concurrent.DelayQueue;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-23 14:33
 */
public class DelayedService {

    private boolean start;

    private DelayQueue<BaseDelayed<?>> delayQueue = new DelayQueue<BaseDelayed<?>>();

    public static interface OnDelayedListener {
        public <T extends BaseDelayed<?>> void onDelayedArrived(T delayed);
    }

    public static interface OnStartListener {
        public void onStart();
    }

    public <T> void start(final OnStartListener onStartListener, final OnDelayedListener listener) {
        if (start) {
            return;
        }
        System.out.println("DelayService start...");
        start = true;
        //启动DelayQueue
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        BaseDelayed<?> baseDelayed = delayQueue.take();
                        System.out.println("DelayService baseDelayed:" + baseDelayed);
                        if (listener != null) {
                            ThreadPoolUtil.execute(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onDelayedArrived(baseDelayed);
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //回调出去
        if (onStartListener != null) {
            ThreadPoolUtil.execute(new Runnable() {
                @Override
                public void run() {
                    onStartListener.onStart();
                }
            });
        }
    }

    public void add(BaseDelayed<?> baseDelayed) {
        delayQueue.put(baseDelayed);
    }

    @SuppressWarnings("unchecked")
    public <T, D extends BaseDelayed<?>> D remove(Class<D> clazz, T value) {
        BaseDelayed<?>[] array = delayQueue.toArray(new BaseDelayed<?>[]{});
        if (array == null || array.length <= 0) {
            return null;
        }
        D target = null;
        for (BaseDelayed<?> delayed : array) {
            if (clazz != delayed.getClass()) {
                continue;
            }
            if (delayed.getValue().equals(value)) {
                target = (D) delayed;
                break;
            }
        }
        if (target != null) {
            delayQueue.remove(target);
        }
        return target;
    }
}
