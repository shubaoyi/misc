package com.byshu.clone.deep;

import com.byshu.clone.shallow.Professor;

public class Student2 implements Cloneable {
    String name;// 常量对象。
    int age;
    Professor p;// 学生1和学生2的引用值都是一样的。

    Student2(String name, int age, Professor p) {
        this.name = name;
        this.age = age;
        this.p = p;
    }

    public Object clone() {
        try {
            Student2 stu = (Student2) super.clone();
            stu.p = (Professor) p.clone();
            return stu;
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        Professor p = new Professor("wangwu", 50);
        Student2 s1 = new Student2("zhangsan", 18, p);
        Student2 s2 = (Student2) s1.clone();
        s2.p.name = "lisi";
        s2.p.age = 30;
        System.out.println("name=" + s1.p.name + "," + "age=" + s1.p.age);
        System.out.println("name=" + s2.p.name + "," + "age=" + s2.p.age);
        // 输出结果学生1和2的教授成为lisi,age为30。
    }
}
