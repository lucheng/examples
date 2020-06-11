package org.cheng;

import java.time.LocalDate;
import java.util.List;

import org.cheng.shardingsphere.ExampleMain;
import org.cheng.shardingsphere.entity.User;
import org.cheng.shardingsphere.manager.IUserManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExampleMain.class)
public class ArrayListTest {
	@Autowired
	private IUserManager userManager;

	@Test
	@Transactional
	@Rollback(false)
	public void save() {
		List<User> portalUserList = userManager.list();
		portalUserList.forEach(portalUser -> {
			portalUser.setCreateTime(LocalDate.now());
		});
		userManager.updateBatchById(portalUserList);
	}
}
