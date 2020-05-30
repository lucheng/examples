package org.cheng.concurrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ExampleMain {

    public static void main(final String[] args) throws InterruptedException {
        SpringApplication.run(ExampleMain.class, args);
        while(true) {
        	log.info("图生log----" + System.currentTimeMillis());
        	Thread.sleep(3000l);
        }
    }
}