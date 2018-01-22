package com.jimzhang.core.thread.countdownlatch.example;

import com.jimzhang.core.thread.countdownlatch.example.base.BaseHealthChecker;
import com.jimzhang.core.thread.countdownlatch.example.service.CacheHealthChecker;
import com.jimzhang.core.thread.countdownlatch.example.service.DatabaseHealthChecker;
import com.jimzhang.core.thread.countdownlatch.example.service.NetworkHealthChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 主启动类，它负责初始化闭锁，然后等待，直到所有服务都被检测完。
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-18 15:29
 */
public class ApplicationStartupUtil {
    /**
     * List of service checkers
     */
    private static List<BaseHealthChecker> services;

    /**
     * This latch will be used to wait on
     */
    private static CountDownLatch latch;

    private ApplicationStartupUtil() {
    }

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance() {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception {
        //Initialize the latch with number of service checkers
        latch = new CountDownLatch(3);

        //All add checker in lists
        services = new ArrayList<BaseHealthChecker>();
        services.add(new NetworkHealthChecker(latch));
        services.add(new CacheHealthChecker(latch));
        services.add(new DatabaseHealthChecker(latch));

        //Start service checkers using executor framework
        Executor executor = Executors.newFixedThreadPool(services.size());

        for (final BaseHealthChecker v : services) {
            executor.execute(v);
        }

        //Now wait till all services are checked
        latch.await();

        //Services are file and now proceed startup
        for (final BaseHealthChecker v : services) {
            if (!v.isServiceUp()) {
                return false;
            }
        }
        return true;
    }
}
