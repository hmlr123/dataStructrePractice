package com.hmlr123.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 图.
 * 顶点 边
 *
 * @author liwei
 * @date 2019/10/9 14:06
 */
public class GraphDemo {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertexs = {"A", "B", "C", "D", "E"};
        for (int i = 0; i < vertexs.length; i++) {
            graph.addVertex(vertexs[i]);

        }

        graph.addEdge(0, 2, 1);     //a c
        graph.addEdge(0, 1, 1);     //a b
        graph.addEdge(1, 2, 1);     //b c
        graph.addEdge(1, 3, 1);     //b d
        graph.addEdge(1, 4, 1);     //b e

        graph.showEdge();
        System.out.println(graph.getNumOfEdge());
        System.out.println(graph.getNumOfVertex());

        System.out.println();
//        graph.dfs();

        graph.bfs();
    }


}

class Graph {
    // 顶点
    private List<String> vertex;
    // 边
    private int[][] edge;
    // 边数量
    private int edgeNum;
    // 是否被访问
    private boolean[] isVisited;

    /**
     * 初始化
     *
     * @param vertexNum 顶点数量
     */
    public Graph(int vertexNum) {
        vertex = new ArrayList<>(vertexNum);
        edge = new int[vertexNum][vertexNum];
        isVisited = new boolean[vertexNum];
        edgeNum = 0;
    }

    public void dfs() {
        dfs(isVisited, 0);
    }


    /**
     * 深度遍历顶点.
     * 对某个顶点进行深度优先搜索
     * depth first search
     *
     * @param isVisited 是否被访问过
     * @param i         访问的顶点
     */
    private void dfs(boolean[] isVisited, int i) {
        //输出访问的顶点
        System.out.print(vertex.get(i) + " => ");
        //标识当前访问的顶点已经访问过
        isVisited[i] = true;
        //获取当前顶点的邻接顶点
        int neighborVertex = getNeighborVertex(i);
        //如果存在，就判断是否被访问过，如果没有访问过就开始递归往下找，就从下一个邻顶点开始找
        //如果不存在 说明当前顶点已经没有邻接顶点
        while (neighborVertex != -1) {
            if (!isVisited[neighborVertex]) {
                dfs(isVisited, neighborVertex);
            }
            neighborVertex = getNextNeighborVertex(i, neighborVertex);
        }
    }

    public void bfs() {
        for (int i = 0; i < vertex.size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    /**
     * 广度优先遍历.
     * 对某个顶点进行广度优先搜索
     * broad first search
     * <p>
     * 有点类似跳跃式的搜索
     * 假设从初始顶点 A 开始搜索，先保存所有的邻接顶点，当邻接顶点为 -1 的时候，再从保存的邻接顶点中取出一个往下找
     *
     * @param isVisited 是否访问过
     * @param i         访问的顶点
     */
    public void bfs(boolean[] isVisited, int i) {
        // 队列头节点下标
        int u;
        // 邻接顶点下标
        int w;
        //队列 保存 顶点i所有的邻接顶点
        LinkedList<Integer> queue = new LinkedList<>();

        System.out.print(vertex.get(i) + " => ");
        isVisited[i] = true;
        queue.add(i);

        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getNeighborVertex(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(vertex.get(w) + " => ");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighborVertex(u, w);
            }
        }
    }


    /**
     * 获取当前顶点的邻接顶点.
     * 从行index 开始匹配
     *
     * @param index 当前顶点的位置
     * @return 如果存在，返回当前顶点的邻接顶点位置, 不存在就返回-1
     */
    public int getNeighborVertex(int index) {
        for (int i = 0; i < vertex.size(); i++) {
            if (edge[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取某个顶点的下一个邻接顶点
     *
     * @param vertex1 当前顶点 行
     * @param vertex2 当前顶点的第一个邻接顶点 列
     * @return
     */
    public int getNextNeighborVertex(int vertex1, int vertex2) {
        for (int i = vertex2 + 1; i < vertex.size(); i++) {
            if (edge[vertex1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 添加顶点
     *
     * @param vertexValue
     */
    public void addVertex(String vertexValue) {
        vertex.add(vertexValue);
    }

    /**
     * 添加边.
     * 注意是无向图
     *
     * @param vertex1 顶点1
     * @param vertex2 顶点2
     * @param weigh   权值
     */
    public void addEdge(int vertex1, int vertex2, int weigh) {
        edge[vertex1][vertex2] = weigh;
        edge[vertex2][vertex1] = weigh;
        edgeNum++;
    }

    /**
     * 获取顶点的个数
     *
     * @return
     */
    public int getNumOfVertex() {
        return vertex.size();
    }

    /**
     * 获取边的个数
     *
     * @return
     */
    public int getNumOfEdge() {
        return edgeNum;
    }

    /**
     * 输出图
     */
    public void showEdge() {
        for (int i = 0; i < edge.length; i++) {
            System.out.println(Arrays.toString(edge[i]));
        }
    }
}



