#java异常的理解

异常主要处理编译期不能捕获的错误。
出现问题时能继续顺利执行下去，而不导致程序终止。确保程序的健壮性。

**处理过程：** 产生异常状态时，如果当前的context不具备处理当前异常的能力，
将在heap上new出来一个异常对象，停止当前的执行路线，把产生的异常对象抛给更高层的context。

- Throwable：异常接口；
- Error ：系统异常；不能恢复；
- Exception ：普通异常；可恢复。

利用try／catch／finally来处理异常。

###什么时候用到finally

某些事物（除内存外）在异常处理完后需要恢复到原始状态，如：开启的文件，网络连接等

###finally语句在try或catch中的return语句执行之后，还是return返回之前执行呢

**总结：**
1. try语句没有被执行，如在try语句之前就返回了，这样finally语句就不会执行；因此说明了finally语句被执行的必要而非充分条件是：相应的try语句一定被执行到。
2. 如果在try代码块中执行System.exit(0)语句;那么将终止Java虚拟机JVM，因此，finally语句也不会被执行到。
3. finally块的语句在try或catch中的return语句`执行之后返回之前执行`，且finally里的修改语句可能影响也可能不影响try或catch中return已经确定的返回值，如果返回值类型为传址类型，则影响；传值类型，则不影响。若finally里也有return语句则覆盖try或catch中的return语句直接返回。


#运行时异常和一般异常有何异同

异常分为runtime exception和checked exception。

1. runtime exception：不需要强制性处理，一旦出现异常，交由虚拟机接管。
2. checked exception：java编译器强制要求catch此类异常，如io异常、sql异常。


#遇到的异常类有哪些?产生的原因
## 常见异常

NullPointerException：空指针。

ArrayIndexOutOfBoundsException：数组越界。

IllegalArgumentException：参数非法。

BufferOverflowException：缓存溢出。

ClassNotFoundException：在编译时无法找到指定的类。

ClassCastException：类型强转。

ExceptionInInitializerError：静态初始值或静态变量初始值期间发生异常。

UnsatisfiedLinkError：JNI加载dll或者so文件时未找到。

NoClassDefFoundError：在编译时能找到合适的类，而在运行时不能找到合适的类。

## OutOfMemoryError（内存溢出） 产生的原因：

1. 内存中加载的数据量过于庞大，如一次从数据库取出过多数据。

2. 集合类中有对对象的引用，使用完后未清空，使得JVM不能回收。

3. 代码中存在死循环或循环产生过多重复的对象实体。

4. 使用的第三方软件中的BUG。

5. 启动参数内存值设定的过小。

**重点排查以下几点：**

1. 检查代码中是否有死循环或递归调用。

2. 检查是否有大循环重复产生新对象实体。

3. 检查对数据库查询中，是否有一次获得全部数据的查询。一般来说，如果一次取十万条记录到内存，就可能引起内存溢出。这个问题比较隐蔽，在上线前，数据库中数据较少，不容易出问题，上线后，数据库中数据多了，一次查询就有可能引起内存溢出。因此对于数据库查询尽量采用分页的方式查询。

4. 检查List、MAP等集合对象是否有使用完后，未清除的问题。List、MAP等集合对象会始终存有对对象的引用，使得这些对象不能被GC回收。

5. 检查对大文件的读取是否采用类nio的方式。
