package org.cheng.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("user")
public class User {

	@TableId(value = "id",type = IdType.ASSIGN_ID)
	private Long id;
	
	private String name;
	
	private String city;
	
	private Long dataSource;
}
