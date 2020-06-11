package org.cheng.concurrent.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SimpleClass {
	private final int mIndex;
	private Executor mExecutors = Executors.newFixedThreadPool(10);

	public SimpleClass(int index) {
		mIndex = index;
	}

	public void runTask() {
		mExecutors.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("[" + mIndex + "] execute");
			}
		});
	}
}
