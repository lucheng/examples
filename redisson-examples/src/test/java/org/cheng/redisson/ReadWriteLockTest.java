package org.cheng.redisson;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadWriteLockTest {
	@Autowired
	private Redisson redisson;

	@Test
	public void test() throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				RReadWriteLock rwlock = redisson.getReadWriteLock("tryLock");
				RLock lock = rwlock.readLock();
				try {
					System.out.println("---t1--sta-" + System.currentTimeMillis());
					lock.lock();
					Thread.sleep(3000l);
					System.out.println("---t1--end-" + System.currentTimeMillis());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
		t1.start();
		Thread.sleep(1000l);
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				RReadWriteLock rwlock = redisson.getReadWriteLock("tryLock");
				RLock lock = rwlock.readLock();
				try {
					System.out.println("---t2--sta-" + System.currentTimeMillis());
					lock.lock();
					System.out.println("---t2--end-" + System.currentTimeMillis());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
		
		t2.start();
		t1.join();
		t2.join();
	}

	@Test
	public void test2() throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				RReadWriteLock rwlock = redisson.getReadWriteLock("tryLock");
				RLock lock = rwlock.readLock();
				try {
					System.out.println("---t1--sta-" + System.currentTimeMillis());
					lock.lock();
					Thread.sleep(3000l);
					System.out.println("---t1--end-" + System.currentTimeMillis());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
		t1.start();
		Thread.sleep(1000l);
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				RReadWriteLock rwlock = redisson.getReadWriteLock("tryLock");
				RLock lock = rwlock.writeLock();
				try {
					System.out.println("---t2--sta-" + System.currentTimeMillis());
					lock.lock();
					System.out.println("---t2--end-" + System.currentTimeMillis());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
		
		t2.start();
		t1.join();
		t2.join();
	}
}
