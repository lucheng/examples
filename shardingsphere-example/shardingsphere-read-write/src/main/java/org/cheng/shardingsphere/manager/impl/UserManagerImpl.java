package org.cheng.shardingsphere.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cheng.shardingsphere.entity.User;
import org.cheng.shardingsphere.manager.IUserManager;
import org.cheng.shardingsphere.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl extends ServiceImpl<UserMapper, User> implements IUserManager {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean add(User dto) {
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		userMapper.insert(user);
		return true;
	}

	@Override
	public User get(Long id) {
		return getById(id);
	}
}
