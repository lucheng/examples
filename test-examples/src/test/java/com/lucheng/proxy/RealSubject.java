package com.lucheng.proxy;

//具体角色RealSubject：
public class RealSubject implements Subject {
	public RealSubject() {
	}

	public void request() {
		System.out.println(" From real subject. ");
	}

}
