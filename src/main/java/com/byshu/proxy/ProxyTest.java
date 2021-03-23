package com.byshu.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) throws Exception {
        Moveable car = new Car();
        TimeHandler handler = new TimeHandler(car);
        Moveable proxyCar = (Moveable) Proxy.newProxyInstance(Car.class.getClassLoader(),
                Car.class.getInterfaces(), handler);
        proxyCar.move();

        // 更换被代理对象
        handler.setTarget(new Tank());
        proxyCar.move();

        // 更换InvocationHandler
        System.out.println(proxyCar.getClass().toString());
        Field field = proxyCar.getClass().getSuperclass().getDeclaredField("h");
        field.setAccessible(true);
        field.set(proxyCar, new LogHandler(car));
        proxyCar.move();


        // System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        /*byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy4", new Class[]{Moveable.class});
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D:/$Proxy4.class");
            fos.write(classFile);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

}
