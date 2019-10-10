package com.hmlr123.recursion;

/**
 * 递归回顾
 *
 * @author liwei
 * @date 2019/10/4 20:15
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(10000);

        System.out.println(factorial(10));
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println(n);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
