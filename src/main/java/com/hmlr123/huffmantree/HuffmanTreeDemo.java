package com.hmlr123.huffmantree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 哈夫曼树.
 *
 * @author liwei
 * @date 2019/10/7 20:29
 */
public class HuffmanTreeDemo {

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node node = huffmanTree.huffmanTree(arr);
        huffmanTree.preOrder(node);

    }

}

class HuffmanTree {

    public Node huffmanTree(int[] arr) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new Node(arr[i]));
        }
        Collections.sort(list);

        while (list.size() > 1) {
            Node left = list.get(0);
            Node right = list.get(1);
            Node root = new Node(left.value + right.value);
            root.left = left;
            root.right = right;
            list.remove(left);
            list.remove(right);
            list.add(root);
            Collections.sort(list);
        }
        return list.get(0);
    }

    public void preOrder (Node node) {
        node.preOrder();
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(@NotNull Node o) {
        return this.value - o.value;
    }

    /**
     * 前序遍历
     *
     */
    public void preOrder() {
        if (this == null) {
            return;
        }

        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }
}