package com.hmlr123.linkedList;

/**
 * 单链表.
 *
 * @author liwei
 * @date 2019/10/2 8:39
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode node1 = new HeroNode(1, "1", "嘿嘿");
        HeroNode node2 = new HeroNode(3, "3", "会话");
        HeroNode node3 = new HeroNode(5, "5", "火狐");
        HeroNode node4 = new HeroNode(7, "7", "后海");
        HeroNode node5 = new HeroNode(9, "9", "前海");
        singleLinkedList.add(node1);
        singleLinkedList.add(node2);
        singleLinkedList.add(node3);
        singleLinkedList.add(node4);

//        singleLinkedList.list();
//        singleLinkedList.add(0, new HeroNode(0, "sss", "ssss"));
//        singleLinkedList.list();
//        singleLinkedList.modify(0, new HeroNode(0, "bryhr", "ssss"));
//        singleLinkedList.list();
//        System.out.println(singleLinkedList.length());
//        singleLinkedList.remove(4);
//        singleLinkedList.list();

//        AtomicInteger temp = new AtomicInteger(0);
//        for (int i = 0; i < 5; i++) {
//            new Thread(() ->{
//                SingleLinkedList singleLinkedList = new SingleLinkedList();
//                HeroNode node1 = new HeroNode(1, "1", "嘿嘿");
//                HeroNode node2 = new HeroNode(2, "2", "会话");
//                HeroNode node3 = new HeroNode(3, "3", "火狐");
//                HeroNode node4 = new HeroNode(4, "4", "后海");
//                singleLinkedList.add(node1);
//                singleLinkedList.add(node2);
//                singleLinkedList.add(node3);
//                singleLinkedList.add(node4);
//                System.out.println(temp.incrementAndGet());
//                System.out.println("当前线程:" + Thread.currentThread().getName() + "\t当前对象：" + singleLinkedList.toString() + "\t当前对象内存地址值：" + System.identityHashCode(singleLinkedList));
//                singleLinkedList.list();
//            }, String.valueOf(i)).start();
//        }

        //面试题一：获取链表的长度（有效数据个数） 可以使用链表的遍历
//        System.out.println(singleLinkedList.length());

        //面试题二：获取链表倒数第n位的数据


        //面试题三：单向链表反转 将尾插法改为头插法，转换后的尾节点next置为null
        /**
         * 1. 创建反转链表
         * 2. 头插法添加数据方法
         * 3. 遍历原链表，将尾节点取出，用头插法添加
         * 4. 将尾节点next置为null
         *
         * 网友：不创建新的链表，在当前的链表上断链，添加。
         */
//        HeroNode head = singleLinkedList.getHead();
//        singleLinkedList.list(head);
//        HeroNode heroNode = singleLinkedList.reverseLinkedListOne(head);
//        singleLinkedList.list(heroNode);


        //面试题四：从尾到头打印单链表
        /**
         * 方案一：单链表反转 （破坏了原来单链表的结构） 和上面原理差不多
         * 方案二：使用栈
         */

        //面试题五：合并两个有序单链表，合并之后仍然有序
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(new HeroNode(2,"2","铜锣湾"));
        linkedList.add(new HeroNode(4,"4","南锣鼓巷"));
        linkedList.add(new HeroNode(6,"6","香山"));
        linkedList.add(new HeroNode(8,"8","长城"));
        linkedList.add(new HeroNode(10,"10","光谷"));

        HeroNode heroNode = SingleLinkedList.mergeSingleLinkedListTwo(linkedList.getHead(), singleLinkedList.getHead());
        SingleLinkedList.list(heroNode);

    }
}

/**
 * 管理单链表
 * <p>
 * 删除指定元素
 * 查询指定元素索引
 * 按排序插入 等功能和下面的功能基本差不多，只不过是定制化实现的（判断条件修改了）
 * 此功能咱不实现
 */
class SingleLinkedList implements LinkedList<HeroNode>{

    private HeroNode head;
    //链表长度
    private int length;

    public HeroNode getHead() {
        return head;
    }

    //初始化链表
    public SingleLinkedList() {
        this.head = new HeroNode(0, "", "");
        length = 0;
    }

    /**
     * 尾节点下指向新添加的节点，怎么找到尾节点，每次遍历？
     * no no no, 使用辅助节点 等于最后一个节点，使用引用传递指向下一个节点
     *
     * @param node
     */
    public void add(HeroNode node) {
        HeroNode temp = head;
        //循环寻找尾节点
        while (true) {
            if (null == temp.next) {
                break;
            }
            temp = temp.next;
        }
        length++;
        temp.next = node;
    }

    /**
     * 指定位置添加数据
     *
     * @param index
     * @param node
     */
    public void add(int index, HeroNode node) {
        HeroNode preNode = getPreNode(index);
        HeroNode temp = preNode.next;
        preNode.next = node;
        node.next = temp;
        length++;
    }


    /**
     * 获取(头节点)数据.
     *
     * @return
     */
    public HeroNode get() {
        //判空
        if (isEmpty()) {
            throw new RuntimeException("链表为空！");
        }
        return head.next;
    }

    /**
     * 寻找指定节点.
     * 当前节点的数据 删除数据需要找当前索引前面的数据
     *
     * @param index
     * @return
     */
    public HeroNode get(int index) {
        return getPreNode(index).next;
    }

    /**
     * 输出链表
     */
    public static void list(HeroNode head) {
        if (null == head.next) {
            throw new RuntimeException("链表为空！");
        }
        HeroNode temp = head.next;
        while (true) {
            if (null == temp) {
                break;
            }
            System.out.println(temp.toString());
            temp = temp.next;
        }
        System.out.println();
    }

    public void list() {
        list(head);
    }

    /**
     * 获取尾节点数据
     *
     * @return
     */
    public HeroNode tail() {
        return tail(head);
    }

    /**
     * 获取尾节点数据
     * @param head
     * @return
     */
    public HeroNode tail(HeroNode head) {
        HeroNode preNode = getPreNode(head,length - 1);
        return preNode == null ? null : preNode.next;
    }



    /**
     * 删除尾节点
     * @return
     */
    public HeroNode removeTail() {
        HeroNode preNode = getPreNode(length - 1);
        HeroNode tailNode = preNode.next;
        preNode.next = null;
        length--;
        return tailNode;
    }



    /**
     * 删除头链表数据
     *
     * @return
     */
    public HeroNode remove() {
        return remove(0);
    }

    /**
     * 删除指定节点数据
     *
     * @param index 索引
     * @return
     */
    public HeroNode remove(int index) {

        HeroNode preNode = getPreNode(index);
        //要删除的节点
        HeroNode delNode = preNode.next;
        preNode.next = delNode.next;
        //C 就是释放内存空间
        delNode.next = null;
        length--;
        return delNode;
    }

    /**
     * 修改数据.
     *
     * @param index
     * @param node
     */
    public void modify(int index, HeroNode node) {
        HeroNode preNode = getPreNode(index);
        HeroNode temp = preNode.next;
        node.next = temp.next;
        preNode.next = node;

    }

    /**
     * 递归实现
     *
     * @param node1
     * @param node2
     * @return
     */
    public static HeroNode mergeSingleLinkedListOne(HeroNode node1, HeroNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (null == node2) {
            return node1;
        }
        if (node1.no > node2.no) {
            node2.next = mergeSingleLinkedListOne(node1, node2.next);
            return node2;
        } else {
            node1.next = mergeSingleLinkedListOne(node2, node1.next);
            return node1;
        }
    }

    /**
     * 循环遍历实现
     *
     * @param head1
     * @param head2
     * @return
     */
    public static HeroNode mergeSingleLinkedListTwo(HeroNode head1, HeroNode head2) {
        HeroNode head = null;           //头节点，有存储数据
        HeroNode last = null;           //尾节点，减少遍历的情况
        HeroNode node1 = head1.next;
        HeroNode node2 = head2.next;
        while (node1 != null && node2 != null) {
            if (node1.no <= node2.no) {
                //备份node1接下来的数据
                HeroNode temp = node1.next;
                if (head == null) {
                    //头插
                    head = node1;
                } else {
                    //尾插
                    last.next = node1;
                }
                //node1上岸 记录尾节点
                last = node1;
                //更新node1
                node1 = temp;
            } else {
                //备份node2接下来的数据
                HeroNode temp = node2.next;
                if (head == null) {
                    //头插
                    head = node2;
                } else {
                    //尾插
                    last.next = node2;
                }
                //node2上岸
                last = node2;
                //更新node2
                node2 = temp;
            }
            if (node1.next == null) {
                last.next = node2;
            } else {
                last.next = node1;
            }
        }
        HeroNode heroNodeHead = new HeroNode(0, "", "");
        heroNodeHead.next = head;
        return heroNodeHead;
    }



    /**
     * 单链表反转.
     * 原来链表的尾节点提取到新链表的头部
     *
     * 开辟了新空间
     *
     * 优化建议，在每个方法上添加一个头节点指针，可以方法复用
     */
    public HeroNode reverseLinkedListOne(HeroNode head) {
        //创建新节点（新链表）
        HeroNode heroNodeHead = new HeroNode(0, "", "");
        //遍历获取尾节点
        while (head.next != null) {
            HeroNode tailNode = removeTail();
            HeroNode temp = heroNodeHead;
            //新链表的尾节点
            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = tailNode;
        }
        head.next = heroNodeHead.next;
        heroNodeHead.next = null;
        return head;
    }


    /**
     * 反转链表方法二：
     * 尾节点指向头节点，直到头节点结束
     * 循环遍历实现
     */
    public HeroNode reverseLinkedListTwo(HeroNode head) {
        int count = 0;
        //记录尾节点数据
        HeroNode tail = tail();
        while (true) {
            if (length == count) {
                tail().next=null;
                break;
            }
            count++;
            //备份头节点剩下的数据
            HeroNode temp = head.next;
            //备份尾节点剩下的数据
            HeroNode next = tail.next;
            tail.next = temp;
            //移动头节点
            head.next = head.next.next;
            temp.next = next;
        }
        return head;
    }

    /**
     * 反转链表方法三：
     * 递归实现,适用于头节点存储了数据的结构
     *
     * @param head
     * @return
     */
    public HeroNode reverseLinkedListThree(HeroNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        HeroNode reHead = reverseLinkedListTwo(head.next);
        reHead.next = head;
        head.next = null;
        return reHead;
    }

    /**
     * 获取倒数第index的数据
     *
     * @param index
     */
    public HeroNode getReciprocalValue(int index) {
        if (index < 0 || index > length) {
            throw new RuntimeException("index溢出！");
        }
        return getPreNode(length - index).next;
    }

    /**
     * 检查是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return null == head.next;
    }

    /**
     * 链表长度
     *
     * @return
     */
    public int length() {
        return length;
    }

    /**
     * 检查索引是否越界
     *
     * @param index
     * @return
     */
    public boolean isTransboundary(int index) {
        if (index < 0 || index > length) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前索引的前面节点数据.
     *
     * @param index
     * @return
     */
    private HeroNode getPreNode(HeroNode head, int index) {
        if (isEmpty()) {
            throw new RuntimeException("链表为空！");
        }
        if (isTransboundary(index)) {
            throw new RuntimeException("索引越界！");
        }

        HeroNode temp = head;
        //寻找要删除索引的前一个结点
        for (int i = 0; i < index && null != temp; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private HeroNode getPreNode(int index) {
        return getPreNode(head, index);
    }

}


/**
 * 节点.
 */
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
