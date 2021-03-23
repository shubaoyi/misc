package com.byshu.java8;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {

    public static void main(String[] args) {
        List<String> col = new ArrayList<>();
        col.add("ddd2");
        col.add("aaa2");
        col.add("bbb1");
        col.add("aaa1");
        col.add("bbb3");
        col.add("ccc");
        col.add("bbb2");
        col.add("ddd1");

        col.stream()
            .sorted()
            .filter((s) -> s.startsWith("a"))
            .forEach(System.out::println);
        System.out.println(col);
    }

}
