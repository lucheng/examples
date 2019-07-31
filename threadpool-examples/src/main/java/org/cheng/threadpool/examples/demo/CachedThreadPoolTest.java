package org.cheng.threadpool.examples.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可缓存线程的线程池
 * 
 * 线程池特点： 
 * 核心线程数为0 
 * 最大线程数为Integer.MAX_VALUE 
 * 阻塞队列是SynchronousQueue 
 * 非核心线程空闲存活时间为60秒
 * 
 * 
 * 使用场景
	用于并发执行大量短期的小任务。
 */
public class CachedThreadPoolTest {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executor.execute(() -> {
				System.out.println(Thread.currentThread().getName() + "正在执行");
			});
		}
		System.out.println("---");
	}

}
