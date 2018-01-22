##普通的生产者-消费者模式：
准备：生产者（Producer）、消费者（Consumer）、产品池（List<Task> buffer）

1、生产者

生产者生产时需要先获取产品池对象`synchronized (buffer)`，然后判断池中产品是否已达上限，未达上限则
进行生产，并将产品放入池中`buffer.add(task)`，同时唤醒等待的消费者`buffer.notifyAll()`；
已达产品池上限则等待消费者消费之后再继续`buffer.wait()`。

2、消费者

消费者消费时需要先获取产品池对象`synchronized (buffer)`，然后判断池中是否有产品，若有则
取走`buffer.remove(0)`；若产品池为空，则继续等待`buffer.wait()`。

3、产品池

使用 List 来存放产品，需要生产者和消费者各自手动获取产品池对象，同时使用 synchronized 
避免互斥。


