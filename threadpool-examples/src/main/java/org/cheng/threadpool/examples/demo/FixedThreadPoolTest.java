package org.cheng.threadpool.examples.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定数目线程的线程池(无界队列的线程池)
 * 
 * 线程池特点：
   核心线程数和最大线程数大小一样
   没有所谓的非空闲时间，即keepAliveTime为0
   阻塞队列为无界队列LinkedBlockingQueue
 * 
 * FixedThreadPool 
 * 适用于处理CPU密集型的任务，确保CPU在长期被工作线程使用的情况下，
 * 尽可能的少的分配线程，即适用执行长期的任务。
 */

public class FixedThreadPoolTest {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			executor.execute(() -> {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// do nothing
				}
			});
		}
	}

}
