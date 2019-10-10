package com.hmlr123.algorithm.knapsack;

/**
 * 背包问题.
 *
 * @author liwei
 * @date 2019/10/9 21:35
 */
public class KnapsackProblem {


    public static void main(String[] args) {
        int[] value = {1500, 3000, 2000, 5000, 100, 30, 1000, 0, 10};
        int[] weigh = {1, 4, 3, 13, 6, 3, 6, 8, 9};
        int knapsckCapacity = 15;
        calculate(value, weigh, knapsckCapacity);
    }


    /**
     * 1. v[i][0]=v[0][j]=0; //表示 填入表 第一行和第一列是0
     * 2. 当w[i]> j 时：v[i][j]=v[i-1][j]   // 当准备加入新增的商品的容量大于 当前背包的容量时，就直接使用上一个单元格的装入策略
     * 3. 当j>=w[i]时： v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}  // 当 准备加入的新增的商品的容量小于等于当前背包的容量,
     * <p>
     * // 装入的方式:
     * <p>
     * v[i-1][j]： 就是**上一个单元格**的装入的最大值
     * <p>
     * v[i] : 表示当前商品的价值
     * <p>
     * v[i-1][j-w[i]] ： 装入i-1商品，到剩余空间j-w[i]的最大值
     * <p>
     * 当j>=w[i]时： v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}
     *
     * @param value    商品的价值
     * @param weigh    商品的重量
     * @param capacity 背包容量
     */
    public static void calculate(int[] value, int[] weigh, int capacity) {
        //背包中存放商品的最大价值
        int[][] v = new int[value.length + 1][capacity + 1];
        //记录最优存储方案
        int[][] path = new int[value.length + 1][capacity + 1];

        /**
         * 商品   价值     容量
         *  A    1500      1
         *  B    3000      4
         *  C    2000      2
         *
         *
         * 表格
         *商品\背包容量   0    1    2    3     4
         *    0
         *    A
         *    B
         *    C
         *
         * 根据表格推理，随着背包容量的增加，背包存储方案随之变化
         */


        // 商品价值（不同商品）
        for (int i = 1; i < v.length; i++) {
            // 背包容量
            for (int j = 1; j < v[0].length; j++) {
                // 如果当前格子的存储的容量小于当前商品大小 当前格子存储上一个格子的存储方式
                if (weigh[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    /**
                     *
                     * v[i - 1][j] 上一单元格存储价值
                     * value[i - 1] 当前背包容量当前商品对应下的价值
                     * v[i - 1][j - weight[i - 1]] 当前背包容量下剩余容量存储上一单元格商品的价值
                     *
                     */
                    if (value[i - 1] + v[i - 1][j - weigh[i - 1]] > v[i - 1][j]) {
                        //当前商品的价值加上上一个字的存储价值
                        v[i][j] = value[i - 1] + v[i - 1][j - weigh[i - 1]];
                        path[i][j] = 1;
                    } else {
                        // 上一格子的存储价值 相同容量下不同商品的存储价值
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        /**
         * 反向寻找最优方案
         *
         */
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d号商品添加到背包中\n", i);
                //当前包容量减去 上一次包容量 大于说明是两种方案的和，否则就是一种方案
                j -= weigh[i - 1];
            }
            i--;
        }

    }
}
