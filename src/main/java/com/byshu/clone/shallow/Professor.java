package com.byshu.clone.shallow;

public class Professor implements Cloneable {
    public String name;
    public int age;

    public Professor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
