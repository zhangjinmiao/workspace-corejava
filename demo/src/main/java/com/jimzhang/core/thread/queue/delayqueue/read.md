## DelayQueue
DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素，DelayQueue中的元素必须实现 java.util.concurrent.Delayed接口
。DelayQueue是一个没有大小限制的队列，因此往队列中插入数据的操作（生产者）永远不会被阻塞，而只有获取数据的操作（消费者）才会被阻塞。


- DelayQueue 内部实现用的是PriorityQueue和一个Lock

**使用场景：**
1. 为一个对象指定过期时间：test1
2. 学生考试，谁先做完题目释放谁：test2
3. 实现订单的定时取消 ：test3
    1. 第一种，写个定时器去每分钟扫描数据库，这样更新及时，但是如果数据库数据量大的话，会对数据库造成很大的压力。 
    2. 第二种，创建订单的时候再订单表里面创建一条记录，然后把这条记录保存到DelayQueue队列里面，并且用一个子线程不断地
    轮询这个出队的订单。然后进行订单状态修改的状态。
4. 淘宝订单业务:下单之后如果三十分钟之内没有付款就自动取消订单：test3
5. 饿了吗订餐通知:下单成功后60s之后给用户发送短信通知：test3

总之，需要延迟工作的都可以使用 DelayQueue 。



**参考：**
>- [ 7天自动收货，30分钟不支付订单自动取消](http://blog.csdn.net/goldenfish1919/article/details/50923450)
>- [基于Redis实现的延迟队列](https://github.com/ouqiang/delay-queue)

