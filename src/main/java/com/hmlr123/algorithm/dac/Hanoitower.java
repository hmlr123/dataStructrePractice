package com.hmlr123.algorithm.dac;

/**
 * 分治算法之汉诺塔问题.
 *
 * @author liwei
 * @date 2019/10/9 18:00
 */
public class Hanoitower {


    static int count = 0;

    public static void main(String[] args) {
        hanoiTower(4, 'A', 'B', 'C');
        System.out.println("挪动次数: " + count);
    }

    /**
     * 问题：怎么分解这个操作. 重复性的操作抽离
     * <p>
     * 所有操作只有两层
     * 盘子
     * a
     * a: 1 -> 3
     * <p>
     * <p>
     * a
     * b
     * 柱子号 1 2 3
     * 挪动
     * a: 1 -> 2
     * b: 1 -> 3
     * a: 2 -> 3
     * <p>
     * a
     * b
     * c
     * a: 1 -> 3
     * b: 1 -> 2
     * a: 3 -> 2
     * c: 1 -> 3
     * a: 2 -> 1
     * b: 2 -> 3
     * a: 1 -> 3
     * <p>
     * 挪动次数：2^n - 1
     * <p>
     * 1 号柱子上看成只有两个盘子 a, b
     * a: 1 -> 2
     * b: 1 -> 3
     * a: 2 -> 3
     *
     * @param no 盘子编号
     * @param a  柱子
     * @param b  柱子
     * @param c  柱子
     */
    public static void hanoiTower(int no, char a, char b, char c) {
        count++;
        if (no == 1) {
            System.out.println("第1个盘子从 " + a + " -> " + c);
        } else {
            hanoiTower(no - 1, a, c, b);
            System.out.println("第" + no + "个盘子从 " + a + " -> " + c);
            hanoiTower(no - 1, b, a, c);
        }
    }
}
