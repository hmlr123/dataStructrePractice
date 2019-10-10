package com.hmlr123.tree.threadedBinaryTree;

/**
 * 线索二叉树.
 * 左节点为空，指向当前节点的前驱节点
 * 右节点为空，指向当前节点的后继节点
 * <p>
 * 区分指针的类型
 *
 * @author liwei
 * @date 2019/10/7 9:33
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
//        Node root = new Node(0, "张三");
//        Node node1 = new Node(1, "李四");
//        Node node2 = new Node(2, "王五");
//        Node node3 = new Node(3, "张二麻子");
//        Node node4 = new Node(4, "赵嗨嗨");
//        root.left = node1;
//        root.right = node2;
//        node1.left = node3;
//        node1.right = node4;
//        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
//        threadedBinaryTree.preOrder();
//        System.out.println();
//        threadedBinaryTree.preThreaded();
//        System.out.println(node4.left);     // node1    node3   node3
//        System.out.println(node4.right);    // root     node2   node1
//        System.out.println(node2.left);     // root     node4   node1
//        System.out.println(node2.right);    // null     null    root
//        threadedBinaryTree.preThreadedOrder();


        String[] array = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        Node root = ThreadedBinaryTree.createBinaryTree(array, 0);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);

//        threadedBinaryTree.postOrder();
        threadedBinaryTree.postThreaded(root);
        System.out.println("后序按后继节点遍历线索二叉树结果：");
        threadedBinaryTree.postThreadedOrder(root);
    }
}

class ThreadedBinaryTree {
    //当前节点
    private Node root;
    //前驱节点
    private Node pre = null;

    public ThreadedBinaryTree(Node root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            throw new RuntimeException("根节点为空！玩个棒棒锤！");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            throw new RuntimeException("根节点为空！玩个棒棒锤！");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            throw new RuntimeException("根节点为空！玩个棒棒锤！");
        }
    }

    public Node preSearch(int no) {
        if (this.root != null) {
            return this.root.preSearch(no);
        } else {
            throw new RuntimeException("根节点为空！玩个棒棒锤！");
        }
    }

    public Node infixSearch(int no) {
        if (this.root != null) {
            return this.root.infixSearch(no);
        } else {
            throw new RuntimeException("根节点为空！玩个棒棒锤！");
        }
    }

    public Node postSearch(int no) {
        if (this.root != null) {
            return this.root.postSearch(no);
        } else {
            throw new RuntimeException("根节点为空！玩个棒棒锤！");
        }
    }


    public void infixThreaded() {
        infixThreaded(root);
    }

    /**
     * 中序线索化.
     * 和单链表一样，需要处理利用前驱节点处理问题
     *
     * @param node
     */
    public void infixThreaded(Node node) {
        if (node == null) {
            return;
        }
        //中序线索化左子节点
        infixThreaded(node.left);

        if (node.left == null) {
            node.left = pre;
            node.leftThreaded = true;
        }
        //当前节点的后继节点处理在后一次处理; 当前节点的前节点的后续节点在当前节点处理
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightThreaded = true;
        }

        pre = node;
        //中序线索化右子节点
        infixThreaded(node.right);
    }


    public void preThreaded() {
        preThreaded(root);
    }

    /**
     * 前序线索化.
     *
     * @param node
     */
    public void preThreaded(Node node) {
        if (node == null) {
            return;
        }

        if (node.left == null) {
            node.left = pre;
            node.leftThreaded = true;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightThreaded = true;
        }

        pre = node;

        if (!node.leftThreaded) {
            preThreaded(node.left);
        }
        if (!node.rightThreaded) {
            preThreaded(node.right);
        }
    }

    public void postThreaded() {
        postThreaded(root);
    }

    public void postThreaded(Node node) {
        if (node == null) {
            return;
        }

        postThreaded(node.left);

        postThreaded(node.right);

        if (node.left == null) {
            node.left = pre;
            node.leftThreaded = true;
        }

        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightThreaded = true;
        }
        pre = node;
    }

    /**
     * 中序遍历线索二叉树.
     */
    public void infixThreadedOrder() {
        Node node = root;

        while (node != null) {
            //寻找最前驱节点
            while (!node.leftThreaded) {
                node = node.left;
            }
            System.out.println(node);
            //线性输出部分线索二叉树
            while (node.rightThreaded) {
                node = node.right;
                System.out.println(node);
            }
            //替换遍历节点
            node = node.right;

        }
    }

    /**
     * 前序遍历线索二叉树
     */
    public void preThreadedOrder() {
        Node node = root;
        while (node != null) {
            //输出非线索部分 寻找线索二叉树开始位置
            while (!node.leftThreaded) {
                System.out.println(node);
                node = node.left;
            }
            //输出线索部分
            System.out.println(node);
            node = node.right;
        }
    }

    /**
     * 后续遍历线索二叉树
     * https://www.cnblogs.com/lishanlei/p/10707830.html
     * <p>
     * 需要添加父节点 字段parentNode
     */
    public void postThreadedOrder(Node root) {
        Node node = root;
        //寻找线索二叉树开始位置
        while (node != null && !node.leftThreaded) {
            node = node.left;
        }
        pre = null;
        while (node != null) {
            //输出线索化部分
            if (node.rightThreaded) {
                System.out.println(node);
                pre = node;
                node = node.right;
            } else {
                //非线索化
                if (node.right == pre) {
                    System.out.println(node);
                    if (node == root) {
                        return;
                    }
                    pre = node;
                    node = node.parent;
                } else {
                    //寻找 下一段线索二叉树(某一结点的右子树的线索二叉树起始位置)
                    node = node.right;
                    while (node != null && !node.leftThreaded) {
                        node = node.left;
                    }
                }
            }
        }
    }

    static Node createBinaryTree(String[] array, int index) {
        Node node = null;

        if (index < array.length) {
            node = new Node(array[index]);
            node.left = createBinaryTree(array, index * 2 + 1);
            node.right = createBinaryTree(array, index * 2 + 2);

            //记录节点的父节点（后序线索化遍历时使用）
            if (node.left != null) {
                node.left.parent = node;
            }

            if (node.right != null) {
                node.right.parent = node;
            }
        }

        return node;
    }
}

class Node {
    public int no;
    public String name;
    public Node left;
    public Node right;
    public Node parent;
    public boolean leftThreaded = false;
    public boolean rightThreaded = false;

    public Node() {
    }

    public Node(String name) {
        this.name = name;
    }

    public Node(int no) {
        this.no = no;
    }

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public Node preSearch(int no) {
        System.out.println("次数");
        if (this.no == no) {
            return this;
        }
        Node Node = null;
        if (this.left != null) {
            Node = this.left.preSearch(no);
        }
        //说明左子树遍历成功
        if (Node != null) {
            return Node;
        }
        if (this.right != null) {
            Node = this.right.preSearch(no);
        }
        return Node;
    }

    public Node infixSearch(int no) {
        Node Node = null;
        if (this.left != null) {
            Node = this.left.infixSearch(no);
        }

        if (Node != null) {
            return Node;
        }
        System.out.println("次数");
        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            Node = this.right.infixSearch(no);
        }
        return Node;
    }

    public Node postSearch(int no) {
        Node Node = null;
        if (this.left != null) {
            Node = this.left.postSearch(no);
        }
        if (Node != null) {
            return Node;
        }

        if (this.right != null) {
            Node = this.right.postSearch(no);
        }
        if (Node != null) {
            return Node;
        }
        System.out.println("次数");
        if (this.no == no) {
            return this;
        }
        return null;
    }

    /**
     * 删除叶子节点.
     *
     * @param no
     */
    public void del(int no) {

        //删除左子节点
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //删除右子节点
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //遍历删除左子树
        if (this.left != null) {
            this.left.del(no);
        }
        //遍历删除右子树
        if (this.right != null) {
            this.right.del(no);
        }

    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}