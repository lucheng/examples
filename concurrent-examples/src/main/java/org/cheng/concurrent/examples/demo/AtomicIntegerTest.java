package org.cheng.concurrent.examples.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger();
		atomicInteger.getAndIncrement();
	}
}
