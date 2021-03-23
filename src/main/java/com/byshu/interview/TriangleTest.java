package com.byshu.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author byshu
 * @desc 杨辉三角
 */
public class TriangleTest {

    static {
        System.out.println("TriangleTest static executed..");
    }

    public static void main(String[] args) {
        generate(6);
    }

    public static void generate(int n) {
        List<List<Integer>> lines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> currLine = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    currLine.add(1);
                } else {
                    List<Integer> prevLine = lines.get(i - 1);
                    currLine.add(prevLine.get(j - 1) + prevLine.get(j));
                }
            }
            lines.add(currLine);

            for (Integer e : currLine) {
                System.out.print(e);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
