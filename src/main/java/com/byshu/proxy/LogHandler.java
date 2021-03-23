package com.byshu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandler implements InvocationHandler {

	Object target;

	public LogHandler(Object target) {
		this.target = target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("log start");
		method.invoke(target, args);
		System.out.println("log end");
		return null;
	}

}
