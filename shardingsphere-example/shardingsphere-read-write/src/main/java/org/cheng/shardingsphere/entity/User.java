package org.cheng.shardingsphere.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("user")
public class User {

	@TableId(value = "id")
	private Long id;
	
	@TableField("name")
	private String name;
	
	@TableField("city")
	private String city;

	private LocalDate createTime;
}
