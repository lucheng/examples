package org.cheng.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.cheng.shardingsphere.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
