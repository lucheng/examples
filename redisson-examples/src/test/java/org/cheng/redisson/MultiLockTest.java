package org.cheng.redisson;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiLockTest {
	@Autowired
	private Redisson redisson;

	@Test
	public void testMultiLock(){
		RLock lock1 = redisson.getLock("lock1");
		RLock lock2 = redisson.getLock("lock2");
		RLock lock3 = redisson.getLock("lock3");
		RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);
		try {
			// 同时加锁：lock1 lock2 lock3, 所有的锁都上锁成功才算成功。
//			lock.lock();
			// 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
			boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	@Test
	public void testMultiLock2(){
		RLock lock1 = redisson.getLock("lock1");
		RLock lock2 = redisson.getLock("lock2");
		RLock lock3 = redisson.getLock("lock3");
		RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);
		try {
			// 同时加锁：lock1 lock2 lock3, 所有的锁都上锁成功才算成功。
			lock.lock();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
