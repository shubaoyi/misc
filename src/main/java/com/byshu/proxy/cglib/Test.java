package com.byshu.proxy.cglib;


import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Test {

    public void hello() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D://cglib-class");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Test.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        Test test = (Test) enhancer.create();
        test.hello();
    }

}
