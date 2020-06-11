package org.cheng.common.utils.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
}
