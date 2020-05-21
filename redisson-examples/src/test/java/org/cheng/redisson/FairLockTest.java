package org.cheng.redisson;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FairLockTest {
	private static final String KEY = "fairLock";
	@Autowired
	private Redisson redisson;

	@Test
	void test1() throws InterruptedException {
		RLock lock = redisson.getFairLock(KEY);
		try {
			lock.lock();
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				RLock lock = redisson.getFairLock(KEY);
				try {
					lock.lock();
					Thread.sleep(10000l);
				} catch (Exception e) {

				} finally {
					lock.unlock();
				}

			}
		});
		
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				RLock lock = redisson.getFairLock(KEY);
				try {
					lock.lock();
				} catch (Exception e) {

				} finally {
					lock.unlock();
				}

			}
		});
		
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				RLock lock = redisson.getFairLock(KEY);
				try {
					lock.lock();
				} catch (Exception e) {

				} finally {
					lock.unlock();
				}

			}
		});
		
		t2.start();
		t3.start();
		
		t2.join();
		t3.join();
	}

	@Test
	void test2() throws InterruptedException {
		RLock lock = redisson.getLock(KEY);
		try {
			lock.lock();
			lock.lock();
			lock.lock();
		} catch (Exception e) {

		} finally {
			lock.unlock();
			lock.unlock();
			lock.unlock();
		}
	}
}
