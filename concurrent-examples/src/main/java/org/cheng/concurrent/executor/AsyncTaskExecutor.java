package org.cheng.concurrent.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务处理器
 */
public class AsyncTaskExecutor {

    /** 线程池保持ALIVE状态线程数 */
    public static final int                 CORE_POOL_SIZE      = 10;

    /** 线程池最大线程数 */
    public static final int                 MAX_POOL_SIZE       = 40;

    /** 空闲线程回收时间 */
    public static final int                 KEEP_ALIVE_TIME     = 1000;

    /** 线程池等待队列 */
    public static final int                 BLOCKING_QUEUE_SIZE = 1000;

    /** 业务请求异步处理线程池 */
    private static final ThreadPoolExecutor processExecutor = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MICROSECONDS,
            new LinkedBlockingQueue<Runnable>(BLOCKING_QUEUE_SIZE),
            new NameableThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private AsyncTaskExecutor() {}; 

    /**
     * 异步任务处理
     *
     * @param task 任务
     */
    public void execute(Runnable task) {
        processExecutor.submit(task);
    }

}