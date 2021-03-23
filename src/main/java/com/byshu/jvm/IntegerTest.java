package com.byshu.jvm;

public class IntegerTest {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 128;
        Integer f = 128;
        Long g = 3l;
        System.out.println(Integer.toBinaryString(-128));

        // 包装类间的==不会拆箱，进行内存地址比较 但-128-127(含边界)范围内的数装箱时会缓存
        System.out.println(c == d);
        System.out.println(e == f);
        // a + b进行拆箱后运算，然后c会进行拆箱
        System.out.println(c == (a + b));
        System.out.println();
        // 包装类的equals方法会首先检查参数的类型是否与该包装类，然后才进行数值比较
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }

}
