package org.cheng.common.utils.concurrent;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class CompletableFutureTest {
	@Test
	public void test() throws InterruptedException, ExecutionException {
		// 创建一个带result的CompletableFuture
		CompletableFuture<String> future = CompletableFuture.completedFuture("result");
		System.out.println(future.get());
	}

	@Test
	public void test2() throws InterruptedException, ExecutionException {
		// 创建一个带result的CompletableFuture
		CompletableFuture<String> future = CompletableFuture.completedFuture("result");
		// 默认创建的CompletableFuture是没有result的，这时调用future.get()会一直阻塞下去知道有result或者出现异常
		future = new CompletableFuture<>();
		try {
			future.get(1, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			// no care
		}
		// 给future填充一个result
		future.complete("result");
		assert "result".equals(future.get());
	}

	@Test
	public void test3() throws InterruptedException, ExecutionException {
		// 创建一个带result的CompletableFuture
		CompletableFuture<String> future = new CompletableFuture<>();
		future.completeExceptionally(new RuntimeException("exception"));
		try {
			future.get();
		} catch (Exception e) {
			assert "exception".equals(e.getCause().getMessage());
		}
	}

	@Test
	public void test4() throws InterruptedException, ExecutionException {
		CompletableFuture<String> sFuture = CompletableFuture.supplyAsync(() -> {
			// callable任务
			System.out.println("hello world");
			System.out.println(1 / 0);
			return "result";
		}).thenApply(r -> {
			// 任务完成之后的动作（回调方法），类似于ThreadPoolExecutor.afterExecute方法
			System.out.println(r);
			return r;
		});

		System.out.println(sFuture.get());

	}

	@Test
	public void testMethod() {

		ExecutorService executor = Executors.newFixedThreadPool(3);

		String[] orders = { "1", "2", "3", "4", "5", "6" };
		Arrays.stream(orders)
				.forEach(id -> CompletableFuture.supplyAsync(() -> submit(id), executor).exceptionally(e -> {
					System.out.println(e);
					return false;
				}));

		executor.shutdown();
		while (!executor.isTerminated()) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static Boolean submit(String order) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("抛一个异常" + order);
	}
}
