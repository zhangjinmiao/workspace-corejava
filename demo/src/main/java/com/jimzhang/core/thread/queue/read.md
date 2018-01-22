>参考：http://blog.csdn.net/suifeng3051/article/details/48807423

## BlockingQueue 接口：
阻塞队列 线程安全

阻塞情况：
1. 当队列满了的时候进行入队列操作
2. 当队列空了的时候进行出队列操作

## 用法

**主要用在生产者/消费者的场景：**

负责生产的线程不断的制造新对象并插入到阻塞队列中，直到达到这个队列的上限值。队列达到上限值之后生产线程将会被阻塞，
直到消费的线程对这个队列进行消费。

同理，负责消费的线程不断的从队列中消费对象，直到这个队列为空，当队列为空时，消费线程将会被阻塞，除非队列中有新的对象被插入。


![](http://img.blog.csdn.net/20150929153140497)


## 接口中的方法：
|        | Throws Exception | Special Value | Blocks | Times Out |
| ------ | ---------------- | ------------- | -------| ----------|
| Insert | add(o) | offer(o) | put(o)| offer(o, timeout, timeunit) |
| Remove | remove(o) | poll() | take()| poll(timeout, timeunit) |
| Examine | element() | peek() |      |                         |

**特点：**
```
1. ThrowsException：如果操作不能马上进行，则抛出异常
2. SpecialValue：如果操作不能马上进行，将会返回一个特殊的值，一般是true或者false
3. Blocks:如果操作不能马上进行，操作会被阻塞
4. TimesOut:如果操作不能马上进行，操作会被阻塞指定的时间，如果指定时间没执行，则返回一个特殊值，一般是true或者false
```
**详细介绍：**

放入数据：
1. offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则返回false。（本方法不阻塞当前执行方法的线程）
2. offer(E o, long timeout, TimeUnit unit),可以设定等待的时间，如果在指定的时间内，还不能往队列中加入BlockingQueue，则返回失败。
3. put(anObject):把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续。

获取数据：
1. poll(time):取走BlockingQueue里排在`首位`的对象,若不能立即取出,则可以等time参数规定的时间,取不到时返回null。
2. poll(long timeout, TimeUnit unit)：从BlockingQueue取出一个队首的对象，如果在指定时间内，队列一旦有数据可取，则立即返回队列中的数据。否则直到时间超时还没有数据可取，返回失败。
3. take():取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入。
4. drainTo():一次性从BlockingQueue获取所有可用的数据对象（还可以指定获取数据的个数），通过该方法，可以提升获取数据效率；不需要多次分批加锁或释放锁。

***不能向BlockingQueue中插入null，否则会报NullPointerException。***
## 实现类

### 1、LinkedBlockingQueue

基于链表的阻塞队列，以`先进先出`的方式存储数据，最新插入的对象是尾部，最新移出的对象是头部

内部维持着一个数据缓冲队列（该队列由一个链表构成），当生产者往队列中放入一个数据时，队列会从生产者手中获取数据，
并缓存在队列内部，而生产者立即返回；
只有当队列缓冲区达到最大值缓存容量时（LinkedBlockingQueue可以通过构造函数指定该值），才会阻塞生产者队列，直到消费者
从队列中消费掉一份数据，生产者线程会被唤醒，反之对于消费者这端的处理也基于同样的原理。

而LinkedBlockingQueue之所以能够高效的处理并发数据，还因为其对于生产者端和消费者端分别采用了**独立的锁（ReentrantLock）**
[消费端 takeLock 锁 和 生产端 putLock 锁] 来控制数据同步，这也意味着在高并发的情况下生产者和消费者可以并行地操作队列中的数据，以此来提高整个队列的并发性能。

使用方法：
```java
BlockingQueue<String> unbounded = new LinkedBlockingQueue<String>(); // 默认无限大小的容量（Integer.MAX_VALUE），最好指定
BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);
bounded.put("Value");
String value = bounded.take();

```

### 2、ArrayBlockingQueue

是一个有边界的阻塞队列，它的内部实现是一个数组。有边界的意思是它的容量是有限的，我们必须在其初始化的时候指定它的容量大小，容量大小一旦指定就不可改变。
除了一个定长数组外，ArrayBlockingQueue内部还保存着两个整形变量，分别标识着队列的头部和尾部在数组中的位置。

1. ArrayBlockingQueue在生产者放入数据和消费者获取数据，都是共用同一个锁对象，由此也意味着两者无法真正并行运行，这点尤其不同于LinkedBlockingQueue；
2. ArrayBlockingQueue在插入或删除元素时不会产生或销毁任何额外的对象实例，而LinkedBlockingQueue
则会生成一个额外的Node对象。这在长时间内需要高效并发地处理大批量数据的系统中，其对于GC的影响还是存在一定的区别。
3. 在创建ArrayBlockingQueue时，我们还可以控制对象的内部锁是否采用公平锁，默认采用非公平锁。

### 3、DelayQueue

DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。DelayQueue是一个没有大小限制的队列，因此往队列中插入数据的操作（生产者）永远不会被阻塞，而只有获取数据的操作（消费者）才会被阻塞。

**应用场景：**
定时关闭连接、缓存对象，超时处理
