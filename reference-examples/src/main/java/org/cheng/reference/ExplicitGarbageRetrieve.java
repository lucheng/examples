package org.cheng.reference;

import java.nio.ByteBuffer;

public class ExplicitGarbageRetrieve {

	public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyDate date = new MyDate();
        date = null;
        System.out.println("before alocate:"  + Runtime.getRuntime().freeMemory());  
        System.gc();
	    System.out.println("after alocate:"  
	            + Runtime.getRuntime().freeMemory());  
	    System.gc();
	    System.out.println("after alocate:"  
	            + Runtime.getRuntime().freeMemory());  
//        System.out.println("---");
	}

}
