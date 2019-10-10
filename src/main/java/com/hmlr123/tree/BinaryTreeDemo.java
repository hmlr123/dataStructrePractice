package com.hmlr123.tree;

/**
 * 二叉树
 * 问题从小到大拆分
 * 先写核心算法问题，在一步步往上封装每个条件细节。
 * <p>
 * 1. 前中后序遍历
 * <p>
 * 2. 前中后序搜索
 * 注意退出遍历的情况
 * <p>
 * 3. 前中后序删除    类似单链表，需要保存当前节点的父节点（前驱节点）
 *
 * @author liwei
 * @date 2019/10/6 20:41
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        Node root = new Node(1, "宋江");
        Node node2 = new Node(2, "吴用");
        Node node3 = new Node(3, "卢俊义");
        Node node4 = new Node(4, "林冲");
        Node node5 = new Node(5, "关胜");

        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;

        BinaryTree binaryTree = new BinaryTree(root);

//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println();
//
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println();
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
//        System.out.println();

//        int searchNo = 5;
//        System.out.println(binaryTree.preSearch(searchNo));
//        System.out.println();
//        System.out.println(binaryTree.infixSearch(searchNo));
//        System.out.println();
//        System.out.println(binaryTree.postSearch(searchNo));

        binaryTree.preOrder();
        System.out.println();
        binaryTree.del(4);
        binaryTree.preOrder();


    }


}

class BinaryTree {
    private Node root;

    public BinaryTree() {
    }

    public BinaryTree(Node root) {
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

    public void del(int no) {
        if (root != null) {
            if (root.no == no) {
                root = null;
            } else {
                this.root.del(no);
            }
        } else {
            throw new RuntimeException("根节点为空！玩个棒棒锤！");
        }
    }
}


class Node {
    public int no;
    public String name;
    public Node left;
    public Node right;

    public Node() {
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