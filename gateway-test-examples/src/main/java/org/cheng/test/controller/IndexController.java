package org.cheng.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/hello")
    public String hello(String name){
    	try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "hi " + name;
    }

    @RequestMapping("/system/timeout")
    public String timeout(){
        try{
            //睡5秒，网关Hystrix3秒超时，会触发熔断降级操作
            Thread.sleep(6000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "timeout";
    }
}