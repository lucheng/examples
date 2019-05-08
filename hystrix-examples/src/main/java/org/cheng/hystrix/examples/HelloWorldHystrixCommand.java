package org.cheng.hystrix.examples;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloWorldHystrixCommand extends HystrixCommand<String> {
	private final String name;

	public HelloWorldHystrixCommand(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		 Thread.sleep(1000);
		System.out.println("2-----" +Thread.currentThread().getName());
		return "hello" + name;
	}
	
	@Override
	protected String getFallback() {
		return super.getFallback();
	}
	
	public static void main(String[] args) {
		System.out.println(" 1 ----- " + Thread.currentThread().getName());
		String result = new HelloWorldHystrixCommand("test").execute();
		System.out.println(result);
	}
}
