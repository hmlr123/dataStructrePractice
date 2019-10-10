package com.hmlr123.binarySort;

/**
 * 二叉排序树.
 *
 * @author liwei
 * @date 2019/10/8 14:50
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println(binarySortTree.getRoot());
        binarySortTree.infixOrder();
        System.out.println();
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(5);
        binarySortTree.delNode(1);
        binarySortTree.delNode(9);


        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private Node root;

    /**
     * 创建二叉树.
     *
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public Node getRoot() {
        return root;
    }

    /**
     * 删除节点.
     *
     * @param value
     */
    public void delNode(int value) {
        if (root == null) {
            throw new RuntimeException("玩个棒棒锤！");
        } else {
            Node targetNode = searchNode(value);
            if (null == targetNode) {
                System.err.println("当前节点不存在！");
            } else {
                // 特殊情况，要删除的节点为父节点
                if (root.left == null && root.right == null) {
                    root = null;
                    return;
                }

                /**
                 * 三种可能
                 * 1. 删除叶子节点
                 * 2. 删除含有一个子树的节点的情况
                 * 3. 删除含有两颗子树的节点的情况
                 *    - 方案一：从当前要删除节点的右子树找最小节点，将该节点替代当前节点
                 *    - 方案二：从当前要删除节点的左子树找最大节点，将该节点替代当前节点
                 *
                 */
                Node parentNode = searchParentNode(value);

                // 第一种可能 删除叶子节点
                if (targetNode.left == null && targetNode.right == null) {
                    if (parentNode.left == targetNode) {
                        parentNode.left = null;
                    } else {
                        parentNode.right = null;
                    }
                } else if (targetNode.left != null && targetNode.right != null) {
                    //第三种情况
                    //用第一种方案 从当前要删除节点的右子树找最小节点，将该节点替代当前节点
                    targetNode.value = delMinNode(targetNode.right);
                } else {
                    // 第二种可能 删除含有一颗子树的情况
                    if (targetNode.left != null) {
                        if (parentNode == null) {
                            // 要删除节点为根节点 并且该节点有左孩子
                            root = targetNode.left;
                        } else {
                            if (parentNode.left == targetNode) {
                                parentNode.left = targetNode.left;
                            } else {
                                parentNode.right = targetNode.left;
                            }
                        }
                    } else {
                        if (parentNode == null) {
                            // 要删除节点为根节点 并且该节点有右孩子，没有左孩子
                            root = targetNode.right;
                        } else {
                            if (parentNode.left == targetNode) {
                                parentNode.left = targetNode.right;
                            } else {
                                parentNode.right = targetNode.right;
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * 删除二叉排序树的最小节点.
     *
     * @param node
     * @return
     */
    public int delMinNode(Node node) {
        if (node == null) {
            throw new RuntimeException("玩个棒棒锤！");
        }
        Node temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        //删除该节点
        delNode(temp.value);
        return temp.value;
    }

    /**
     * 删除树的最大节点
     *
     * @param node
     * @return
     */
    public int delMaxNode(Node node) {
        if (node == null) {
            throw new RuntimeException("玩个棒棒锤！");
        }
        Node temp = node;
        while (temp.right != null) {
            temp = temp.right;
        }
        //删除该节点
        delNode(temp.value);
        return temp.value;
    }

    /**
     * 寻找要删除的节点.
     *
     * @param value 要删除节点的值
     * @return
     */
    public Node searchNode(int value) {
        if (root == null) {
            throw new RuntimeException("棒棒锤！");
        } else {
            return root.searchNode(value);
        }
    }

    /**
     * 寻找要删除节点的父节点.
     *
     * @param value 要删除节点的值
     * @return
     */
    public Node searchParentNode(int value) {
        if (root == null) {
            throw new RuntimeException("棒棒锤！");
        } else {
            return root.searchParentNode(value);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        infixOrder(root);
    }

    public void infixOrder(Node node) {
        if (node == null) {
            throw new RuntimeException("玩锤锤！");
        }
        node.infixOrder();
    }
}

class Node {
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

    /**
     * 添加节点.
     *
     * @param node
     */
    public void add(Node node) {
        if (this.value < node.value) {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        } else {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        }
    }

    /**
     * 遍历
     *
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 寻找要删除的节点
     *
     * @param value
     * @return
     */
    public Node searchNode(int value) {
        if (this.value == value) {
            return this;
        }
        if (this.value < value) {
            if (this.right != null) {
                return this.right.searchNode(value);
            } else {
                return null;
            }
        } else {
            if (this.left != null) {
                return this.left.searchNode(value);
            } else {
                return null;
            }
        }
    }

    /**
     * 寻找要删除节点的父节点
     *
     * @param value
     * @return
     */
    public Node searchParentNode(int value) {
        if (this.value < value) {
            if (this.right != null) {
                if (this.right.value == value) {
                    return this;
                } else {
                    return this.right.searchParentNode(value);
                }
            } else {
                return null;
            }
        } else {
            if (this.left != null) {
                if (this.left.value == value) {
                    return this;
                } else {
                    return this.left.searchParentNode(value);
                }
            } else {
                return null;
            }
        }
    }
}
