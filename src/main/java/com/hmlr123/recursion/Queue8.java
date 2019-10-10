package com.hmlr123.recursion;

/**
 * 8皇后问题
 * 回溯算法
 *
 * @author liwei
 * @date 2019/10/4 23:54
 */
public class Queue8 {

    //总共的方案数量
    private static int count = 0;
    //检测冲突次数
    private static int judgeCount = 0;
    // 皇后数量
    private static int maxSize;
    private static int[] arr;

    public Queue8() {
        this(8);
    }

    public Queue8(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8(14);
        check(0);
        System.out.println("总共方案数量：\t" + count);
        System.out.println("总共检测冲突数量：\t" + judgeCount);
    }

    /**
     * 放置皇后
     */
    public static void check(int n) {
        //n从0 开始 当 n == maxSize的时候 说明最后一个皇后已经被安排，打印当前方案
        if (n == maxSize) {
            show();
            //此处输出之后需要结束当前递归
            return;
        }
        //循环放置
        for (int i = 0; i < maxSize; i++) {
            arr[n] = i;
            //判断是否冲突
            if (judge(n)) {
                // 没有冲突，安排下一个皇后
                check(n + 1);
            }
            //冲突了，接着安排当前皇后，此处有回溯
        }
    }


    /**
     * 判断是否冲突.
     * arr[n] = i
     * n表示第几行 i表示第几列
     * 和n前面的皇后判断是否冲突
     *
     * @param n 第n个皇后
     * @return
     */
    public static boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //判断是否在同一列 以及是否在同一斜线
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    public static void show() {
        count++;
        for (int i = 0; i < maxSize; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
