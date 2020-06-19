package org.cheng.shardingsphere.service.impl;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.cheng.shardingsphere.cache.RedisUtils;
import org.cheng.shardingsphere.entity.User;
import org.cheng.shardingsphere.manager.IUserManager;
import org.cheng.shardingsphere.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
//@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserManager userManager;
	@Autowired
	private RedisUtils<User> redisUtils;
	
	private static final String USER_KEY = "crm:user:";

	@Override
	public void save(User dto) {
		userManager.add(dto);
	}

	@Override
	@HystrixCommand(fallbackMethod = "getDb")
	public User get(Long id) {
		String key = USER_KEY + id;
		User user = redisUtils.get(key);
		if(user!=null) {
			return user;
		}

		synchronized (key) {
			user = userManager.get(id);
			redisUtils.set(USER_KEY + id, user);
		}
		return user;
	}

	public User getDb(Long id) {
		log.error("熔断降级");
		return null;
	}
	
	@Override
	public User add2(User dto) {
		User u = userManager.get(1L);
		userManager.add(dto);
		User u2 = userManager.get(1L);
		return dto;
	}

	@Override
	@Transactional
	@ShardingTransactionType(TransactionType.XA) 
	public User get2add(User dto) {
		userManager.add(dto);
		User u = userManager.get(2L);
		return dto;
	}
	

}
