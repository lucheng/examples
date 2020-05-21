package org.cheng.redisson;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class LockTest {
	private static final String KEY = "anyLock";
	@Autowired
	private Redisson redisson;

	@Test
	void test1() throws InterruptedException {
		RLock lock = redisson.getLock("anyLock");
		try {
			lock.lock();
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}
	}

	@Test
	void test2() throws InterruptedException {
		RLock lock = redisson.getLock(KEY);
		try {
			lock.lock();
			println();
			lock.lock();
			println();
			lock.lock();
			println();
		} catch (Exception e) {

		} finally {
			lock.unlock();
			println();
			lock.unlock();
			println();
			lock.unlock();
			println();
		}
	}
	
	@Test
	void test3() throws InterruptedException {
		lock();
		lock();
	}
	
	private void lock() {
		RLock lock = redisson.getLock(KEY);
		try {
			lock.lock();
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}
	}

	private void println() {
		RMap<Object, Object> map = redisson.getMap(KEY);
		for (RMap.Entry<Object, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}

	@Test
	void test() throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				RLock lock = redisson.getLock("anyLock");
				try {
					lock.lock();
					Thread.sleep(5000l);
				} catch (Exception e) {

				} finally {
					lock.unlock();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				RLock lock = redisson.getLock("anyLock");
				try {
					lock.lock();
				} catch (Exception e) {

				} finally {
					lock.unlock();
				}

			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
}