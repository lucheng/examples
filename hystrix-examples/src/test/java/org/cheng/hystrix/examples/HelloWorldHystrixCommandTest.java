package org.cheng.hystrix.examples;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import junit.framework.TestCase;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class HelloWorldHystrixCommandTest extends TestCase {

	@Test
	public void testQueue() throws Exception {
		// queue()是异步非堵塞性执行：直接返回，同时创建一个线程运行HelloWorldHystrixCommand.run()
		// 一个对象只能queue()一次
		// queue()事实上是toObservable().toBlocking().toFuture()
		HelloWorldHystrixCommand command = new HelloWorldHystrixCommand("Hlx");

		System.out.println(" 1 ----- " + Thread.currentThread().getName());
		String result = command.execute();
		System.out.println(result);

		Future<String> future = command.queue();
		// 使用future时会堵塞，必须等待HelloWorldHystrixCommand.run()执行完返回
		String queueResult = future.get(10000, TimeUnit.MILLISECONDS);
		// String queueResult = future.get();
		System.out.println("queue异步结果：" + queueResult);
		assertEquals("hello", queueResult.substring(0, 5));
	}

	@Test
	public void testObservable() throws Exception {

		// observe()是异步非堵塞性执行，同queue
		Observable<String> hotObservable = new HelloWorldHystrixCommand("Hlx").observe();

		// single()是堵塞的
		// System.out.println("hotObservable single结果：" +
		// hotObservable.toBlocking().single());
		// System.out.println("------------------single");
		// 注册观察者事件
		// subscribe()是非堵塞的
		hotObservable.subscribe(new Observer<String>() {

			// 先执行onNext再执行onCompleted
			// @Override
			public void onCompleted() {
				System.out.println("hotObservable completed");
			}

			// @Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			// @Override
			public void onNext(String v) {
				System.out.println("hotObservable onNext: " + v);
			}
		});
		// 非堵塞
		// - also verbose anonymous inner-class
		// - ignore errors and onCompleted signal
		hotObservable.subscribe(new Action1<String>() {

			// 相当于上面的onNext()
			// @Override
			public void call(String v) {
				System.out.println("hotObservable call: " + v);
			}
		});
		// 主线程不直接退出，在此一直等待其他线程执行
		System.in.read();
	}
}
