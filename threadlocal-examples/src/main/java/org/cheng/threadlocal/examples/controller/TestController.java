package org.cheng.threadlocal.examples.controller;

import org.cheng.threadlocal.examples.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
	
	public static final ThreadLocal<User> LOCAL = new ThreadLocal<>();
	
	@RequestMapping("")
	public User index() {
		User user = LOCAL.get();
		if(user==null) {
			user = new User();
			user.setStatus("new");
			user.setName(Thread.currentThread().getName());
			LOCAL.set(user);
		}else {
			user.setStatus("old");
		}
		return user;
	}
}
