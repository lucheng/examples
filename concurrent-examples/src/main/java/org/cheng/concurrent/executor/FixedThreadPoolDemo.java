package org.cheng.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolDemo {

	public static void main(String[] args) {
		int corePoolSize = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = new ThreadPoolExecutor(corePoolSize*2, corePoolSize*2, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(5000),new NameableThreadFactory("system"),new ThreadPoolExecutor.CallerRunsPolicy());
		try {
			for (int i = 0; i < 5000; i++) {
				executor.execute(() -> {
					try {
						Thread.sleep(150);
						System.out.println(Thread.currentThread().getName());
					} catch (Exception e) {
						// do nothing
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
