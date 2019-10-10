package com.hmlr123.algorithm.prim;

/**
 * 普利姆算法.
 * <p>
 * 1. 构建无向图，无向图元素，顶点、边、边权值、顶点值
 *
 * @author liwei
 * @date 2019/10/10 11:52
 */
public class PrimDemo {

    public static void main(String[] args) {
        //测试看看图是否创建ok
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通 不可达
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        MinTree minTree = new MinTree();
        Graph graph = new Graph(verxs);
        minTree.createGraph(graph, verxs, data, weight);
        minTree.showGraph(graph);

        minTree.prim(graph, 3);
    }

}

// 创建最小生成树
class MinTree {

    /**
     * 初始化图
     *
     * @param graph 图（已经创建过的）
     * @param verxs 顶点个数
     * @param data  顶点元素
     * @param weigh 边的权重
     */
    public void createGraph(Graph graph, int verxs, char[] data, int[][] weigh) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            // 初始化顶点数据
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                // 初始化边的数据
                graph.edegs[i][j] = weigh[i][j];
            }
        }
    }

    /**
     * 打印图.
     *
     * @param graph
     */
    public void showGraph(Graph graph) {
        for (int i = 0; i < graph.verxs; i++) {
            for (int j = 0; j < graph.verxs; j++) {
                System.out.print(graph.edegs[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 普利姆.
     * 寻找最小权值和的所有顶点的可达路径
     * <p>
     * 1. 寻找起始点的可达点
     * 2. 找出最小可达点，标记为已到达
     * 3. 可达点不能重复
     * 4. 最终边数为 n - 1 (n 顶点数量)
     *
     * @param graph 图
     * @param start 起始点
     */
    public void prim(Graph graph, int start) {
        // 是否被访问过
        int[] isVisited = new int[graph.verxs];
        isVisited[start] = 1;

        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        //遍历每条边 vertex - 1
        for (int k = 1; k < graph.verxs; k++) {
            //寻找每个节点的可达权值最小节点
            /**
             * 当前节点一定是已经访问过，下一个节点一定是没有被访问过
             *
             */
            //当前节点
            for (int i = 0; i < graph.verxs; i++) {
                //寻找可达节点
                for (int j = 0; j < graph.verxs; j++) {
                    //可达节点未被访问且 路径权值更小
                    if (isVisited[i] == 1 && isVisited[j] == 0 && graph.edegs[i][j] < minWeight) {
                        minWeight = graph.edegs[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //当前节点的路径权值最小可达节点标记为已访问
            isVisited[h2] = 1;
            //下一个节点重新开始
            minWeight = 10000;
        }
    }
}

class Graph {
    // 顶点个数
    int verxs;
    // 顶点数据
    char[] data;
    // 边 边的可达用二位数组表示
    int[][] edegs;

    /**
     * 初始化图
     *
     * @param verxs
     */
    public Graph(int verxs) {
        this.verxs = verxs;
        this.data = new char[verxs];
        this.edegs = new int[verxs][verxs];
    }
}