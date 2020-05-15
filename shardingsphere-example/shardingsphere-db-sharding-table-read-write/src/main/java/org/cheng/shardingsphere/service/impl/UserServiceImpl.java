package org.cheng.shardingsphere.service.impl;

import org.cheng.shardingsphere.entity.User;
import org.cheng.shardingsphere.manager.IUserManager;
import org.cheng.shardingsphere.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserManager userManager;

	@Override
	public void save(User dto) {
		userManager.add(dto);
	}

	@Override
	public User get(Long id) {
		return userManager.get(id);
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
	public User get2add(User dto) {
		userManager.add(dto);
		User u = userManager.get(2L);
		return dto;
	}
	

}
