package org.cheng.shardingsphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

@EnableHystrix
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class ,DruidDataSourceAutoConfigure.class})
public class ExampleMain {

    public static void main(final String[] args) {
        SpringApplication.run(ExampleMain.class, args);
    }
}