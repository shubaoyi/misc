package com.byshu.clone.deep;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student3 implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;// 常量对象
    int age;
    Teacher t;// 学生1和学生2的引用值都是一样的。

    public Student3(String name, int age, Teacher t) {
        this.name = name;
        this.age = age;
        this.t = t;
    }

    public Object deepClone() throws Exception {// 将对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(this);// 写入bo的bytearray中
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }

    public static void main(String[] args) throws Exception {
        Teacher t = new Teacher("tangliang", 30);
        Student3 s1 = new Student3("zhangsan", 18, t);
        Student3 s2 = (Student3) s1.deepClone();
        s2.t.name = "tony";
        s2.t.age = 40;
        // 学生1的老师不改变
        System.out.println("name=" + s1.t.name + "," + "age=" + s1.t.age);
        System.out.println("name=" + s2.t.name + "," + "age=" + s2.t.age);
    }
}
