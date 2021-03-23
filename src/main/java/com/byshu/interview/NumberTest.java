package com.byshu.interview;

import java.util.Scanner;

public class NumberTest {

    /**
     * 质数
     */
    public static boolean isPrime(int num) {
        if (num <= 3) {
            return true;
        }
        // 到达num的一半后无需再判断
        int k = num % 2 == 0 ? num / 2 : num / 2 + 1;
        for (int i = 2; i <= k; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @desc 水仙花数
     * 水仙花数是指一个 3 位数，它的每个位上的数字的 3次幂之和等于它本身
     * （例如：1^3 + 5^3+ 3^3 = 153）
     */
    public static boolean isNarcissistic(int num) {
        if (num < 100 || num > 999) {
            return false;
        }
        int raw = num, sum = 0, remainder;
        while (num > 0) {
            remainder = num % 10;
            sum += (remainder * remainder * remainder);
            num /= 10;
        }
        return sum == raw;
    }

    public static void fetchNum(int num) {
        while (num > 0) {
            System.out.println(num % 10);
            num /= 10;
        }
    }


    /**
     * 完数
     */
    public static boolean isPerfect(int num) {
        int sum = 0, remainder;
        for (int i = 1; i < num; i++) {
            if ((remainder = num % i) == 0) {
                sum += i;
            }
        }
        return sum == num;
    }

    /**
     * 输入字符行，统计输入字符行中数字符、英文字母个数
     */
    public static void scanner() {
        Scanner scan = new Scanner(System.in);
        // 从键盘接收数据
        // next方式接收字符串
        System.out.println("next方式接收：");
        // 判断是否还有输入
        if (scan.hasNextLine()) {
            String str1 = scan.next();
            System.out.println("输入的数据为：" + str1);
            char[] arr = str1.toCharArray();
            int numCnt = 0, letterCnt = 0, temp;
            for (int i = 0; i < arr.length; i++) {
                temp = arr[i];
                if ((temp >= 65 && temp <= 90) || (temp >= 97 && temp <= 122)) {
                    letterCnt++;
                } else if (temp >= 48 && temp <= 57) {
                    numCnt++;
                }
            }
            System.out.println("letterCnt : " + letterCnt);
            System.out.println("numCnt : " + numCnt);
        }
        scan.close();
    }

    public static void main(String[] args) {
        System.out.println(isPrime(13));
        System.out.println(isNarcissistic(153));
        System.out.println(isPerfect(28));
        // scanner();
        fetchNum(153);
    }
}
