package com.byshu.modifier.inner;

import java.io.File;

public class B extends A {

    public int a;

    public int getA() {
        return a;
    }

    @Override
    public void getSome() {
        super.getSome();
    }

    public static void main(String[] args) {
        File file = new File("D:/green_bg.jpg");
        System.out.println(file.getName());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        System.out.println(file.getParentFile());
        System.out.println(file.length());
    }
}
