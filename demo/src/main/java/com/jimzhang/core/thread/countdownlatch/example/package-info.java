package com.jimzhang.core.thread.countdownlatch.example;

/*
 CountDownLatch使用例子

 在这个例子中，我模拟了一个应用程序启动类，它开始时启动了n个线程类，这些线程将检查外部系统并通知闭锁，并且启动类一直
 在闭锁上等待着。一旦验证和检查了所有外部服务，那么启动类恢复执行。
 */
