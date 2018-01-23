##使用delayedQueue实现你本地的延迟队列
>参考：https://www.jianshu.com/p/87fd5b9f5ffb

**使用方法：**

1. 在容器初始化的时候调用init方法.
2. 实现一个runnable接口的类，调用TaskQueueDaemonThread的put方法传入进去.
3. 如果需要实现动态的取消任务的话，需要task任务的类重新hashcode方法，最好用业务限制hashcode的冲突发生.