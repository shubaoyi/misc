package com.byshu.interview;

import java.util.Hashtable;

public class UserTest {
    User user;

    public void set(User user) {
        //user = new User();
        //user.setName("change");
        user = null;
        //System.out.println("1:" + user.getName());
    }

    public void setB(Boolean b) {
        b = false;
        System.out.println(b);
    }

    public void get() {
        System.out.println(this.user);

    }

    public static void main(String[] args) {
        UserTest test = new UserTest();
        User user = new User();
        user.setName("hello world");
        test.set(user);
        System.out.println("2:" + user.getName());
        /*Boolean b = new Boolean(true);
        test.setB(b);
        System.err.println(b);*/
    }

}