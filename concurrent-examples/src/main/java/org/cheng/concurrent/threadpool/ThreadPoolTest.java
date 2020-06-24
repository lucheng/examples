package org.cheng.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) throws InterruptedException {
		/**
		 * 固定数目线程的线程池
		 */
		ExecutorService executor = new ThreadPoolExecutor(5, 8,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(16));
		for (int i = 0; i < 40; i++) {
			Thread.sleep(1000);
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
