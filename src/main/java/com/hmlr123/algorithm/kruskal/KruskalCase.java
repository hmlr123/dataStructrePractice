package com.hmlr123.algorithm.kruskal;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法
 * <p>
 * 1. 将所有路径排序
 * 2. 每次取最小权值路径，找的过程中不能构成环（构成环：起始点 == 终点）
 * <p>
 * 1. 邻接矩阵的构建
 * 顶点 边 边的权重
 * 2. 路径排序
 * 3. 判断是否成环
 *
 * @author liwei
 * @date 2019/10/10 15:28
 */
public class KruskalCase {

    // 边的个数
    private int edgeNum;
    // 顶点数组
    private char[] vertexs;
    // 邻接矩阵
    private int[][] matrix;
    // 不可达
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);

        kruskalCase.show();

        kruskalCase.kruskal();

    }


    /**
     * 克鲁斯卡尔找最小树
     * <p>
     * 取最短路径，判断是否成环
     */
    public void kruskal() {

        int index = 0;
        // 保存已有最小生成树
        int[] ends = new int[edgeNum];

        // 保存最终最小生成树结果
        EData[] rets = new EData[edgeNum];


        EData[] edges = getEdges();
        sortEdges(edges);

        for (int i = 0; i < edgeNum; i++) {
            // 获取边 i 的起点位置
            int p1 = getPosition(edges[i].start);
            // 获取边 i 的终点位置
            int p2 = getPosition(edges[i].end);

            // p1顶点再已有最小生成树中的终点
            int m = getEnd(ends, p1);
            // p2顶点再已有最小生成树中的终点
            int n = getEnd(ends, p2);

            if (m != n) {
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }

        System.out.println("最小生成树：");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }


    /**
     * 核心方法
     *
     * 获取下标为 i 的顶点的终点
     *
     * @param ends 记录各个顶点的终点
     * @param i    传入顶点的下标
     * @return 下标为 i 的顶点的终点
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }


    /**
     * 获取顶点的位置
     *
     * @param ch
     * @return
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 初始化
     *
     * @param vertexs 顶点数组
     * @param matrix  邻接矩阵
     */
    public KruskalCase(char[] vertexs, int[][] matrix) {
        // 创建数组对象
        this.vertexs = new char[vertexs.length];
        this.matrix = new int[matrix.length][matrix[0].length];
        // 深拷贝初始化
        // 源数组 起始位置 目标数组 目标数组起始位置 长度
        System.arraycopy(vertexs, 0, this.vertexs, 0, vertexs.length);
        System.arraycopy(matrix, 0, this.matrix, 0, matrix.length);

        //边的个数
        for (int i = 0; i < vertexs.length; i++) {
            // j = i + 1 避免重复统计 相当于正方形斜线取一半
            for (int j = i + 1; j < vertexs.length; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    /**
     * 输出图
     */
    public void show() {
        System.out.println("邻接矩阵为: \n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();//换行
        }
    }


    /**
     * 获取所有路径
     *
     * @return
     */
    public EData[] getEdges() {
        EData[] eDatas = new EData[edgeNum];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] != INF) {
                    eDatas[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return eDatas;
    }

    /**
     * 对边进行排序
     * 使用冒泡排序
     *
     * @param eDatas
     * @return
     */
    public void sortEdges(EData[] eDatas) {
        EData temp;
        for (int i = 0; i < eDatas.length - 1; i++) {
            for (int j = 0; j < eDatas.length - 1 - i; j++) {
                if (eDatas[j].weight > eDatas[j + 1].weight) {
                    temp = eDatas[j];
                    eDatas[j] = eDatas[j + 1];
                    eDatas[j + 1] = temp;
                }
            }
        }
    }
}

/**
 * 边
 */
class EData {
    //起点
    char start;
    //终点
    char end;
    //两点之间的权重
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData [<" + start + ", " + end + ">= " + weight + "]";
    }
}
