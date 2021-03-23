package com.byshu.interview;


public class StringTest {

    public String str = new String("good");
    public char[] ch = new char[]{'a', 'b', 'c'};

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'd';
    }


    public static void main(String[] args) {
        StringTest ex = new StringTest();
        ex.change(ex.str, ex.ch);

        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);
    }

}
