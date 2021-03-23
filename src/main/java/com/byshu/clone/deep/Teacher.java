package com.byshu.clone.deep;

import java.io.Serializable;

public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    int age;

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
