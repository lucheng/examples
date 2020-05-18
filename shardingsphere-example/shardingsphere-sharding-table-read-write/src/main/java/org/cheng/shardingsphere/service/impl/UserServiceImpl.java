package org.cheng.shardingsphere.service.impl;

import java.util.List;
import java.util.Map;

import org.cheng.shardingsphere.entity.User;
import org.cheng.shardingsphere.manager.IUserManager;
import org.cheng.shardingsphere.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserManager userManager;

	@Override
	@Transactional
	public void save(User dto) {
		userManager.add(dto);
	}

	@Override
	public User get(Long id) {
		return userManager.get(id);
	}

	@Override
	@Transactional
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

	@Override
	public List<User> list() {
		LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.orderByAsc(User::getId);
		return userManager.list(queryWrapper);
	}

	public IPage<Map<String, Object>> page() {
		LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.ge(User::getId, 10);
		queryWrapper.orderByAsc(User::getId);
		Page<User> page = new Page<>(2, 10);
		return userManager.pageMaps(page, queryWrapper);
	}

}
