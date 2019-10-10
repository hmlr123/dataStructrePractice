package com.hmlr123.tree;

/**
 * 顺序存储二叉树.
 * 数组装转成树
 *
 * @author liwei
 * @date 2019/10/7 8:36
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinary arrayBinary = new ArrayBinary(arr);

//        arrayBinary.infixOrder();
        arrayBinary.postOrder();
    }
}

class ArrayBinary {
    private int[] arr;

    public ArrayBinary(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }

    /**
     * 前序遍历.
     *
     * @param index 数组下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("数组为空！");
        }
        System.out.println(arr[index]);
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }

        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void infixOrder() {
        infixOrder(0);
    }

    /**
     * 中序遍历.
     *
     * @param index 数组下标
     */
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("数组为空！");
        }
        if (2 * index + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }

        System.out.println(arr[index]);

        if (2 * index + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    public void postOrder() {
        postOrder(0);
    }

    /**
     * 中序遍历.
     *
     * @param index 数组下标
     */
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("数组为空！");
        }

        if (2 * index + 1 < arr.length) {
            postOrder(2 * index + 1);
        }

        if (2 * index + 2 < arr.length) {
            postOrder(2 * index + 2);
        }

        System.out.println(arr[index]);
    }
}
