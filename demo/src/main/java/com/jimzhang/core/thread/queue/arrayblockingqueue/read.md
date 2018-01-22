## 使用 ArrayBlockingQueue 作产品池的的生产者-消费者模式：
准备：生产者（Producer）、消费者（Consumer）、产品池（BlockingQueue<Task> buffer）

1、生产者

只需考虑生产产品即可，`buffer.put(task)`。

2、消费者

只需考虑消费产品即可，`buffer.take()`。

3、产品池

使用 `ArrayBlockingQueue` 来存放产品

- 内部使用数组，生产者和消费者共用同一个锁对象