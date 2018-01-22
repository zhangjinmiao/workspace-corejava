## 使用 LinkedBlockingQueue 作产品池的的生产者-消费者模式：
准备：生产者（Producer）、消费者（Consumer）、产品池（BlockingQueue<Task> buffer）

1、生产者

只需考虑生产产品即可，`buffer.put(task)`。

2、消费者

只需考虑消费产品即可，`buffer.take()`。

3、产品池

使用 `LinkedBlockingQueue` 来存放产品

- LinkedBlockingQueue 采用独立的锁（ReentrantLock） [消费端 takeLock 锁 和 生产端 putLock 锁] 来控制数据同步
，高并发的情况下生产者和消费者可以并行地操作队列中的数据

