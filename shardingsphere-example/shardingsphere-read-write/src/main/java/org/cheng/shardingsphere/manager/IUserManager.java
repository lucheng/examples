package org.cheng.shardingsphere.manager;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cheng.shardingsphere.entity.User;

/**
 * 通用业务
 * @author lucheng
 *
 */
public interface IUserManager extends IService<User>{
	
	boolean add(User dto);

	User get(Long id);
	
}
