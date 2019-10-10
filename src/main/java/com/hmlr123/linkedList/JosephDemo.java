package com.hmlr123.linkedList;

/**
 * Joseph问题
 * <p>
 * 1. 环形队列创建
 * 2. 出圈思路
 *
 * @author liwei
 * @date 2019/10/3 16:56
 */
public class JosephDemo {

    public static void main(String[] args) {
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.addBoy(5);
        linkedList.show();
        System.out.println();
        linkedList.startGame(1, 2, 5);
    }
}

class CircleSingleLinkedList {

    private Boy first;

    public void addBoy(int boys) {
        if (boys < 1) {
            throw new RuntimeException("都没人，玩个棒棒锤！");
        }

        Boy curBoy = null;
        for (int i = 1; i <= boys; i++) {
            //第一次创建节点
            if (first == null) {
                first = new Boy(i);
                curBoy = first;
                curBoy.next = first;
            } else {
                curBoy.next = new Boy(i);
                curBoy = curBoy.next;
                curBoy.next = first;
            }
        }
    }

    public void show() {
        if (null == first) {
            throw new RuntimeException("没人，玩个棒棒锤！");
        }

        Boy curBoy = first;
        do {
            System.out.println("当前输出：" + curBoy.no);
            curBoy = curBoy.next;
        } while (curBoy != first);
    }

    /**
     * 开始游戏.
     *
     * @param startNo  起始编号
     * @param spaceNum 空闲数量
     * @param nums     总数量
     */
    public void startGame(int startNo, int spaceNum, int nums) {
        if (startNo < 1 || startNo > nums) {
            throw new RuntimeException("开始编号大于总编号");
        }
        Boy curBoy = first;  //辅助指针
        // 1. first curBoy就位  curBoy移动到first前一个位置
        while (curBoy.next != first) {
            curBoy = curBoy.next;
        }


        //2. first、curBoy到达指定位置
        // 关于startNo - 1 不好解释，数数和跨度相差一，我们的目的就是让first到达指定位置，starNo的跨度就是startNo - 1
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            curBoy = first;
        }

        //3. 当前位置 startNo 开始数，第spaceNum开始输出
        do {
            // 走 spaceNum 步数
            for (int i = 0; i < spaceNum - 1; i++) {
                first = first.next;
                curBoy = curBoy.next;
            }
            System.out.println("当前输出：" + first.no);
            //替换当前节点
            first = first.next;
            curBoy.next = first;

        } while (curBoy != first);

    }


}

class Boy {
    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
