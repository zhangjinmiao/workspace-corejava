## PriorityBlockingQueue 
PriorityBlockingQueue是一个基于优先级堆的无界的并发安全的优先级队列（FIFO），队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 进行排序，具体取决于所使用的构造方法。

###实现原理

PriorityBlockingQueue通过使用堆这种数据结构实现将队列中的元素按照某种排序规则进行
排序，从而改变先进先出的队列顺序，提供开发者改变队列中元素的顺序的能力。队列中的元素必须是可比较的，即实现Comparable接口，或者在构建函数时提供可对队列元素进行比较的Comparator对象。

###结构介绍

PriorityBlockingQueue通过内部组合PriorityQueue的方式实现优先级队列（private final PriorityQueue q;），另外在外层通过ReentrantLock实现线程安全，同时通过Condition实现阻塞唤醒。

**使用场景：**




**参考：**
>- [ 7天自动收货，30分钟不支付订单自动取消](http://blog.csdn.net/goldenfish1919/article/details/50923450)
>- [基于Redis实现的延迟队列](https://github.com/ouqiang/delay-queue)

