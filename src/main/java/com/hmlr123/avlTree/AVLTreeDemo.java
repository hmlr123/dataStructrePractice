package com.hmlr123.avlTree;

/**
 * 平衡二叉树.
 *
 * @author liwei
 * @date 2019/10/8 20:11
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建一个 AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for(int i=0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); //3
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 2
        System.out.println("当前的根结点=" + avlTree.getRoot());//8

    }
}

class AVLTree {
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
     * 主要在于删除操作的逻辑颇为复杂
     * 具体逻辑见代码注释
     *
     * 删除是二叉树的平衡问题，再某一分支删除的就相当于再另一支插入操作
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

        //平衡二叉树
        balanceBinaryTree();
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

    /**
     * 右旋
     */
    public void rightRotate() {
        if (root == null) {
            throw new RuntimeException("玩锤锤！");
        }
        root.rightRotate();
    }

    /**
     * 左旋
     */
    public void leftRotate() {
        if (root == null) {
            throw new RuntimeException("玩锤锤！");
        }
        root.leftRotate();
    }


    public int diffHeight() {
        if (root == null) {
            return 0;
        }
        return root.leftHeight() - root.rightHeight();
    }


    public void balanceBinaryTree() {
        if (root == null) {
            return;
        }
        //大方向上右旋
        if (diffHeight() > 1) {
            //和大方向相反的方向
            // 当前节点的左节点的右字数高度大于当前节点左节点的左子树高度
            if (root.left != null && root.left.rightHeight() > root.left.leftHeight()) {
                //当前节点的左节点左旋
                root.left.leftRotate();
                //当前根节点
                rightRotate();
            } else {
                //右旋
                rightRotate();
            }
            //不能忘！
            return;
        }

        // 大方向上左旋
        if (diffHeight() > 1) {
            // 和大方向相反情况
            // 当前节点的右节点的左子树高度大于当前节点右子树的右节点
            if (root.right != null && root.right.leftHeight() > root.right.rightHeight()) {
                //当前节点的右节点右旋
                root.right.rightRotate();
                //当前根节点
                leftRotate();
            } else {
                //左旋
                leftRotate();
            }
            return;
        }
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


        /**
         * 开始：
         *
         *                  a
         *          b              c
         *      d       e
         *                   f
         *
         * 如果没有第一步左旋 直接右旋
         *                  b
         *             d        a
         *                   e       c
         *                     f
         * 可以看出不是平衡二叉树
         *
         * 第一步左旋后
         *                  a
         *           e            c
         *      b        f
         *  d
         *
         * 第二部右旋后
         *                  e
         *             b        a
         *        d          f      c
         *
         */
        //处理二叉排序树不平衡问题
        //大方向上右旋
        if (leftHeight() - rightHeight() > 1) {
            //和大方向相反的方向
            // 当前节点的左节点的右字数高度大于当前节点左节点的左子树高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //当前节点的左节点左旋
                left.leftRotate();
                //当前根节点
                rightRotate();
            } else {
                //右旋
                rightRotate();
            }
            //不能忘！
            return;
        }


        /**
         *              a
         *     b                   c
         *                     d        e
         *                f
         *
         *  第一次右旋
         *               a
         *        b             d
         *                           c
         *                       f        e
         *
         *  第二次左旋
         *              d
         *         a            c
         *     b       f             e
         *
         */
        // 大方向上左旋
        if (rightHeight() - leftHeight() > 1) {
            // 和大方向相反情况
            // 当前节点的右节点的左子树高度大于当前节点右子树的右节点
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //当前节点的右节点右旋
                right.rightRotate();
                //当前根节点
                leftRotate();
            } else {
                //左旋
                leftRotate();
            }
            return;
        }
    }


    /**
     * 左旋
     *
     * 问题：
     *  当前节点的右子树的高度大于当前节点左子树的高度，且大于1
     *
     * 目的(达成效果)：
     *  将当前节点的右节点架空（没有节点指向它）
     *  根节点值变成当前节点的右节点
     *  根节点就被取而代之
     *
     *              a
     *           b      c
     *               d      e
     *                          f
     *     左旋后
     *              c
     *         a        e
     *     b     d          f
     *
     *
     */
    public void leftRotate() {
        // 1. 创建新节点，节点值为当前节点的值
        Node newNode = new Node(value);
        // 2. 新节点的左节点为当前节点的左节点
        newNode.left = left;
        // 3. 新节点的右节点为当前节点右节点的左节点
        newNode.right = right.left;
        // 4. 当前节点的值变成当前节点的右节点的值
        value = right.value;
        // 5. 当前节点的右节点变成当前节点的右节点的右节点
        right = right.right;
        // 6. 当前的节点的左节点变成新节点
        left = newNode;

    }

    /**
     * 右旋
     *
     * 问题：
     *  当前节点的左子树的高度大于当前节点右子树的高度，且大于1
     *                  a
     *               b      c
     *           d      e
     *        f
     *
     *       右旋后
     *                  b
     *               d       a
     *           f         e     c
     *
     */
    public void rightRotate() {
        // 1. 创建新节点，节点值为当前节点的值
        Node newNode = new Node(value);
        // 2. 新节点的右节点为当前节点的右节点
        newNode.right = right;
        // 3. 新节点的左节点为当前节点左节点的右节点
        newNode.left = left.right;
        // 4. 当前节点的值变成当前节点的左节点的值
        value = left.value;
        // 5. 当前节点的左节点变成当前节点的左节点的左节点
        left = left.left;
        // 6. 当前节点的右节点变成新节点
        right = newNode;
    }

    /**
     * 获取当前节点左子树的高度.
     *
     * @return
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 获取当前节点右子树的高度.
     *
     * @return
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 获取当前节点的高度.
     *
     * @return
     */
    public int height() {
        return Math.max(left != null ? left.height() : 0, right != null ? right.height() : 0) + 1;
    }

    /**
     * 遍历
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
