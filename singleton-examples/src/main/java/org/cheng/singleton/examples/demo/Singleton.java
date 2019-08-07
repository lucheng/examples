package org.cheng.singleton.examples.demo;

import java.util.concurrent.atomic.AtomicReference;

public class Singleton {
    
	private static final AtomicReference<Singleton> priveInstance = new AtomicReference<>();

    private Singleton() {
    }

    Singleton getInstance() {
        for(;;){
            Singleton instance = priveInstance.get();
            if(instance != null ) {
                return instance;
            }
            instance = new Singleton();
            if(priveInstance.compareAndSet(null, instance)) {
                return instance;
            }
        }
    }
}