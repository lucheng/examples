package org.cheng.shardingsphere.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	public void saveBatch() {
		List<User> list = new ArrayList<>(5000);
		for (long i = 1; i < 2; i++) {
			User dto = new User();
			dto.setId(i);
			dto.setName(""+i);
			dto.setCreateTime(LocalDate.now());
			list.add(dto);
		}
		userManager.updateBatchById(list);
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
