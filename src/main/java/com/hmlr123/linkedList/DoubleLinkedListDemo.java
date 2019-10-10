package com.hmlr123.linkedList;

/**
 * 双向链表.
 *
 * @author liwei
 * @date 2019/10/3 14:15
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.add(new DoubleHeroNode(2, "2", "铜锣湾"));
        linkedList.add(new DoubleHeroNode(4, "4", "南锣鼓巷"));
        linkedList.add(new DoubleHeroNode(6, "6", "香山"));
        linkedList.add(new DoubleHeroNode(8, "8", "长城"));
        linkedList.add(new DoubleHeroNode(10, "10", "光谷"));
        linkedList.list();
        linkedList.add(2, new DoubleHeroNode(1,"ddd","ddd"));
        linkedList.list();
        System.out.println(linkedList.get());
        System.out.println(linkedList.get(1));
        System.out.println();
        linkedList.add(0, new DoubleHeroNode(0, "cfg","wef"));
        linkedList.list();
        System.out.println();
        System.out.println(linkedList.length());
//        linkedList.removeTail();
//        linkedList.list();
//        System.out.println(linkedList.tail());

    }

}

class DoubleLinkedList implements LinkedList<DoubleHeroNode> {

    private DoubleHeroNode head;
    private int length;


    public DoubleLinkedList() {
        this(new DoubleHeroNode(0, "", ""));
    }

    public DoubleLinkedList(DoubleHeroNode head) {
        this.head = head;
        this.length = 0;
    }

    /**
     * 检查是否为空.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        if (head.next == null) {
            return true;
        }
        return false;
    }

    /**
     * 检查是否溢出.
     *
     * @param index
     * @return
     */
    @Override
    public boolean isTransboundary(int index) {
        if (index < 0 || index > length) {
            return true;
        }
        return false;
    }

    @Override
    public void add(DoubleHeroNode node) {
        add(length, node);
    }

    @Override
    public void add(int index, DoubleHeroNode node) {
        if (isTransboundary(index)) {
            throw new RuntimeException("索引越界");
        }
        if (index == length) {
            //插入到尾巴节点
            DoubleHeroNode tail = tail();
            if (null == tail) {
                //当前链表为空
                tail = head;
            }
            node.pre = tail;
            tail.next = node;
        } else {
            //插入到中间
            DoubleHeroNode curNode = get(index);
            node.next = curNode;
            node.pre = curNode.pre;
            curNode.pre.next = node;
            curNode.pre = node;
        }
        length++;
    }

    @Override
    public DoubleHeroNode get() {
        return get(0);
    }

    @Override
    public DoubleHeroNode get(int index) {
        if (isEmpty()) {
            throw new RuntimeException("链表为空！");
        }
        if (isTransboundary(index)) {
            throw new RuntimeException("链表越界！");
        }
        DoubleHeroNode temp = head.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp;
    }

    /**
     * 删除头节点.
     *
     * @return
     */
    @Override
    public DoubleHeroNode remove() {
        return remove(0);
    }


    @Override
    public DoubleHeroNode remove(int index) {
        if (isEmpty()) {
            throw new RuntimeException("链表为空！");
        }
        if (isTransboundary(index)) {
            throw new RuntimeException("索引越界！");
        }
        DoubleHeroNode node = get(index);
        if (index == length - 1) {
            //删除尾部
            node.pre.next = null;
            node.pre = null;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = null;
            node.next = null;
        }
        length--;
        return node;
    }

    @Override
    public DoubleHeroNode tail() {
        //当前链表为空，没有尾节点
        if (length == 0) {
            return null;
        }
        return get(length - 1);
    }

    /**
     * 删除尾节点.
     *
     * @return
     */
    @Override
    public DoubleHeroNode removeTail() {
        return remove(length - 1);
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public void modify(int index, DoubleHeroNode o) {
        DoubleHeroNode node = get(index);
        if (index == length - 1) {
            o.pre = node.pre;
            head.pre.next = node;
        } else {
            o.pre = node.pre;
            o.next = node.next;
            node.pre.next = o;
            node.next.pre = o;
        }
    }

    @Override
    public void list() {
        list(head);
    }

    public static void list(DoubleHeroNode head) {
        if (null == head.next) {
            throw new RuntimeException("链表为空！");
        }
        DoubleHeroNode temp = head.next;
        while (true) {
            if (null == temp) {
                break;
            }
            System.out.println(temp.toString());
            temp = temp.next;
        }
        System.out.println();
    }

}


class DoubleHeroNode {

    public int no;
    public String name;
    public String nickName;
    public DoubleHeroNode next;
    public DoubleHeroNode pre;

    public DoubleHeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}