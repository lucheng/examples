package org.cheng.redisson;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BloomFilterTest {
	@Autowired
	private Redisson redisson;

	@Test
	public void testRedLock(){
		RBloomFilter<Object> bloomFilter = redisson.getBloomFilter("sample");
		// 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
		bloomFilter.tryInit(55000000L, 0.03);
		bloomFilter.add("field1Value");
		bloomFilter.add("field5Value");
		System.out.println(bloomFilter.contains("field1Value"));
	}
}
