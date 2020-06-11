package org.cheng.shardingsphere.service;

import org.cheng.shardingsphere.entity.User;

public interface IUserService {
	
	void save(User user);
	
	void saveBatch();
	
	User get(Long id);
	
	User add2(User dto);
	
	User get2add(User dto);
}
