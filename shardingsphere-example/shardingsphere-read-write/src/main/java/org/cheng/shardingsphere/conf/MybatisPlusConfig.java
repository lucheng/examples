package org.cheng.shardingsphere.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({ "org.cheng.*.mapper" })
public class MybatisPlusConfig {
}
