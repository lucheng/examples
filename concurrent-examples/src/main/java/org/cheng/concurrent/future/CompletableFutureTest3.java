package org.cheng.concurrent.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest3 {

	public static void main(String[] args) throws InterruptedException {
		CompletableFuture.runAsync(()->{  //在此处打断点
	        System.out.println("111");
	    });
	    Thread.sleep(400000);
	}
}
