package org.cheng.concurrent.executor;

public class TestThreadLife {
	public static void main(String[] args) {
		test();
		System.gc();
	}

	private static void test() {
		for (int i = 0; i < 10; i++) {
			new SimpleClass(i).runTask();
		}
	}
}
