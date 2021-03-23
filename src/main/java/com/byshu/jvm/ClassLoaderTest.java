package com.byshu.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {

        ClassLoader myLoader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                System.out.println(name);
                InputStream is;
                try {
                    is = new FileInputStream(new File("D:/Hehe.class"));
                    byte[] b;
                    System.err.println(is.available());
                    b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

        };

        System.out.println(myLoader);
        Object obj = myLoader.loadClass("com.byshu.jvm.Hehe").newInstance();
        System.out.println(obj.getClass().getClassLoader());
        /*
         * System.err.println(System.getProperty("sun.boot.class.path"));
         * System.err.println(System.getProperty("java.ext.dirs"));
         * System.out.println(System.getProperty("java.class.path"));
         */
    }


}
