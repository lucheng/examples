package org.cheng.threadpool.examples.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

	public static void main(String[] args) {
		/**
		 * 固定数目线程的线程池
		 */
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
