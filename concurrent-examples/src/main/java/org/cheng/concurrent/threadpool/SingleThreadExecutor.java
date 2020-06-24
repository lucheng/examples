package org.cheng.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 
 * 线程池特点： 
 * 	核心线程数为1
	最大线程数也为1
	阻塞队列是LinkedBlockingQueue

 	keepAliveTime为0
 * 
 * 适用于串行执行任务的场景，一个任务一个任务地执行。
 */
public class SingleThreadExecutor {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			executor.execute(() -> {
				try {
					Thread.sleep(2000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "正在执行");
			});
		}
	}
}
