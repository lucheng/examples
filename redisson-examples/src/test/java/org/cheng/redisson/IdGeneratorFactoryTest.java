package org.cheng.redisson;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class IdGeneratorFactoryTest {

	@Autowired
	private IdGeneratorFactory idGeneratorFactory;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Test
	void test() {
		
		String key = "key";

		CompletableFuture<Void> f1 = CompletableFuture.runAsync(()->{
			for(int i=0;i<1;i++) {
				Long id = idGeneratorFactory.generate(key);
				System.out.println(Thread.currentThread().getName() + " "+  id);
			}
		});

		CompletableFuture<Void> f2 = CompletableFuture.runAsync(()->{
			for(int i=0;i<1;i++) {
				Long id = idGeneratorFactory.generate(key);
				System.out.println(Thread.currentThread().getName() + " "+ id);
			}
		});
		
		f1.join();
		f2.join();
		
	}
	
	@Test
	void test2() {
		
		String key = "key";

		CompletableFuture<Void> f1 = CompletableFuture.runAsync(()->{
			long sta = System.currentTimeMillis();
			for(int i=0;i<1000;i++) {
				Long id = idGeneratorFactory.generate(key);
				//System.out.println(Thread.currentThread().getName() + " "+  id);
			}
			System.out.println(System.currentTimeMillis()-sta);
		});
		f1.join();
	}
	
	@Test
	void test3() {
		String key = "key";
		RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		long sta = System.currentTimeMillis();
		CompletableFuture<Void> f1 = CompletableFuture.runAsync(()->{
			for(int i=0;i<10000;i++) {
				counter.incrementAndGet();
			}
		});
		
		CompletableFuture<Void> f2 = CompletableFuture.runAsync(()->{
			for(int i=0;i<10000;i++) {
				counter.incrementAndGet();
			}
		});
		
		CompletableFuture<Void> f3 = CompletableFuture.runAsync(()->{
			for(int i=0;i<10000;i++) {
				counter.incrementAndGet();
			}
		});
		
		CompletableFuture<Void> f4 = CompletableFuture.runAsync(()->{
			for(int i=0;i<10000;i++) {
				counter.incrementAndGet();
			}
		});
		f1.join();
		f2.join();
		f3.join();
		f4.join();
		System.out.println(System.currentTimeMillis()-sta);
	}
}
