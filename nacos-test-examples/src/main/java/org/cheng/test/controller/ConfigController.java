package org.cheng.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }
    
    @RequestMapping("/get2")
    public boolean get2() throws InterruptedException {
    	List<MatTest> list = new ArrayList<>();
    	for(int i=0;i<1000;i++) {
    		list.add(new MatTest());
    		Thread.sleep(200);
    	}
        return useLocalCache;
    }
}
