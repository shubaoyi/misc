package com.byshu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {
	
	Object target;

	public TimeHandler(Object target) {
		this.target = target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("start time : " + System.currentTimeMillis());
		method.invoke(target, args);
		System.out.println("end time : " + System.currentTimeMillis());
		return null;
	}

}
