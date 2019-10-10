package com.hmlr123.recursion;

import java.util.Random;

/**
 * 迷宫问题.
 *
 * @author liwei
 * @date 2019/10/4 20:50
 */
public class MiGong {

    public static void main(String[] args) {

        //创建迷宫
        int[][] map = initMiGong(15, 15, 50);
        show(map);
        System.out.println();
        setWay(map, 1, 1);
        show(map);


    }

    /**
     * 约定：
     * 0 表示没有走过
     * 1 表示墙
     * 2 表示走通
     * 3 表示走死了
     * <p>
     * 行走约定：
     * 下 右 上 左
     *
     * @param map 地图
     * @param i   起始位置
     * @param j   起始位置
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[map.length - 2][map[0].length - 2] == 2) {
            return true;
        } else {
            // 当前地点还没有走过
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    return true;
                }
                if (setWay(map, i, j + 1)) {
                    return true;
                }
                if (setWay(map, i - 1, j)) {
                    return true;
                }
                if (setWay(map, i, j - 1)) {
                    return true;
                }
                //走不通
                map[i][j] = 3;
                return false;
            } else {
                //当前点不可行 1，2，3
                return false;
            }
        }
    }

    /**
     * 地图初始化
     *
     * @param x 行数
     * @param y 列数
     * @param o 障碍数量
     * @return
     */
    public static int[][] initMiGong(int x, int y, int o) {
        if (x < 1 || y < 1 || x * y <= o) {
            throw new RuntimeException("就这种地图，玩啥呢？？？");
        }
        int[][] map = new int[x][y];

        /**
         * 行边界
         */
        for (int i = 0; i < x; i++) {
            map[0][i] = 1;
            map[y - 1][i] = 1;
        }

        /**
         * 列边界
         */
        for (int j = 0; j < y; j++) {
            map[j][0] = 1;
            map[j][x - 1] = 1;
        }

        /**
         * 生成随机障碍物
         */
        Random random = new Random();
        for (int i = 0; i < o; i++) {
            int a = random.nextInt(x);
            int b = random.nextInt(y);
            map[a][b] = 1;
        }
        map[x - 2][y - 2] = 0;

        return map;
    }

    public static void show(int[][] map) {
        int x = map.length;
        int y = map[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
