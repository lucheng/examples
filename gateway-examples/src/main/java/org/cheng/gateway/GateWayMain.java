package org.cheng.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Mono;

@EnableHystrix
@SpringBootApplication
public class GateWayMain {

    public static void main(final String[] args) {
        SpringApplication.run(GateWayMain.class, args);
    }

    @Bean("ipKeyResolver")
    public KeyResolver ipKeyResolver() {
        System.out.println("限流解析器");
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}