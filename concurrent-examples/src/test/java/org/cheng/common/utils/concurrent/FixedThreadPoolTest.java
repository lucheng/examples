package org.cheng.common.utils.concurrent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定数目线程的线程池(无界队列的线程池)
 * 
 * 线程池特点： 核心线程数和最大线程数大小一样 没有所谓的非空闲时间，即keepAliveTime为0
 * 阻塞队列为无界队列LinkedBlockingQueue
 * 
 * FixedThreadPool 适用于处理CPU密集型的任务，确保CPU在长期被工作线程使用的情况下， 尽可能的少的分配线程，即适用执行长期的任务。
 */

public class FixedThreadPoolTest {

	/**
	 * 写入TXT文件
	 */
	public static void writeFile() {
		try {
			File writeName = new File("/Users/lucheng/data/logs/output.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
			if (!writeName.exists())
				writeName.createNewFile();
			try (FileWriter writer = new FileWriter(writeName, true); BufferedWriter out = new BufferedWriter(writer)) {
				out.write("我会写入文件啦1\r\n"); // \r\n即为换行
				out.write("我会写入文件啦2\r\n"); // \r\n即为换行
				out.flush(); // 把缓存区内容压入文件
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10000; i++) {
			executor.execute(() -> {
				for (int j = 0; j < 10000; j++) {
					writeFile();
				}
			});
		}

		executor.shutdown();
	}

}
