package com.jimzhang.core.thread.demo;

/**
 * 〈一句话功能简述〉<br> 〈生产者〉
 *
 * @author zhangjinmiao
 * @create 2018/12/21 14:07
 */
public class WriteTask implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<20;i++){
//			try {
//				sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
            WriterQueue.getQueue().put("for:"+i+" thread:"+Thread.currentThread().getName());
        }
    }

    private void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
