package org.cheng.shardingsphere.service;

import java.util.List;
import java.util.Map;

import org.cheng.shardingsphere.entity.User;

import com.baomidou.mybatisplus.core.metadata.IPage;

public interface IUserService {
	
	void save(User user);

	User get(Long id);
	
	User add2(User dto);
	
	User get2add(User dto);
	
	List<User> list();

	IPage<Map<String, Object>> page();
}
