package org.cheng.concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 
 * 线程池特点： 最大线程数为Integer.MAX_VALUE 阻塞队列是DelayedWorkQueue keepAliveTime为0
 * scheduleAtFixedRate() ：按某种速率周期执行 scheduleWithFixedDelay()：在某个延迟后执行
 * 
 * 周期性执行任务的场景，需要限制线程数量的场景
 */
public class ScheduledThreadPoolTest {
	public static void main(String[] args) {
		/**
		 * 创建一个给定初始延迟的间隔性的任务， 之后的下次执行时间是上一次任务从执行到结束所需要的时间+* 给定的间隔时间
		 */
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
		
		scheduledExecutorService.scheduleWithFixedDelay(() -> {
			System.out.println("current Time" + System.currentTimeMillis());
			System.out.println(Thread.currentThread().getName() + "正在执行");
		}, 1, 3, TimeUnit.SECONDS);
		
	}
}
