package com.lucheng.proxy;

//客户端：
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Client {

	static public void main(String[] args) throws Throwable {
		RealSubject rs = new RealSubject(); // 在这里指定被代理类
		InvocationHandler ds = new DynamicSubject(rs);
		Class cls = rs.getClass();
		// 以下是一次性生成代理
		Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), ds);
		subject.request();
		subject.request();
	}
}
