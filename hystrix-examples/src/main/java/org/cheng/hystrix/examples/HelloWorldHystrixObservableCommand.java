package org.cheng.hystrix.examples;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.Subscriber;

public class HelloWorldHystrixObservableCommand extends HystrixObservableCommand<String> {

	private final String name;

	protected HelloWorldHystrixObservableCommand(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}

	@Override
	protected Observable<String> construct() {
		System.out.println("in construct! thread:" + Thread.currentThread().getName());
		return (Observable<String>) Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
			public void call(Subscriber<? super String> observer) {
				try {
					System.out.println("in call of construct! thread:" + Thread.currentThread().getName());
					if (!observer.isUnsubscribed()) {
//                      observer.onError(getExecutionException());  // 直接抛异常退出，不会往下执行
						observer.onNext("Hello1" + " thread:" + Thread.currentThread().getName());
						observer.onNext("Hello2" + " thread:" + Thread.currentThread().getName());
						observer.onNext(name + " thread:" + Thread.currentThread().getName());
						System.out.println("complete before------" + " thread:" + Thread.currentThread().getName());
						observer.onCompleted(); // 不会往下执行observer的任何方法
						System.out.println("complete after------" + " thread:" + Thread.currentThread().getName());
						observer.onCompleted(); // 不会执行到
						observer.onNext("abc"); // 不会执行到
					}
				} catch (Exception e) {
					observer.onError(e);
				}
			}
		});
	}

	public static void main(String[] args) {
		Observable<String> observable = new HelloWorldHystrixObservableCommand("test").observe();
		observable.subscribe(new Subscriber<String>() {
			public void onCompleted() {
				System.out.println("completed");
			}

			public void onError(Throwable throwable) {
				System.out.println("error-----------" + throwable);
			}

			public void onNext(String v) {
				System.out.println("next------------" + v);
			}
		});
	}
}
