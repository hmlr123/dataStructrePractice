package com.hmlr123.queue;

/**
 * 先进先出
 *
 * 数组模拟队列.
 * 主要元素：
 * 数组大小、头指针、尾指针、数组
 *
 * 关键点：
 * 头/尾指针的指向
 *
 * 实现功能：
 * 1. 显示队列数据
 * 2. 添加数据
 * 3. 获取头部数据
 * 4. 显示头部数据
 * @author liwei
 * @date 2019/10/1 20:32
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        Queue queue = new ArrayQueue(3);
        Queues.queueTools(queue);
    }
}

/**
 * 数组模拟队列.
 */
class ArrayQueue implements Queue{
    //数组最大长度
    private int maxSize;
    //头指针
    private int front;
    //尾指针
    private int rear;
    //数组
    private int[] arr;

    /**
     * 初始化数组
     *
     * @param initSize
     */
    public ArrayQueue(int initSize) {
        this.maxSize = initSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    /**
     * 判断队列是否满了.
     *
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空.
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 获取队元素.
     *
     * @return
     */
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        int temp = arr[++front];
        arr[front] = 0;
        return arr[++front];
    }

    /**
     * 队列添加元素.
     *
     * @param value
     */
    public void add(int value) {
        if (isFull()) {
            throw new RuntimeException("队列满了！");
        }
        arr[++rear] = value;
    }

    /**
     *
     * 显示队列首部元素.
     *
     * @return
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return arr[front + 1];
    }

    /**
     * 打印队列元素.
     *
     */
    public void printQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n" , i, arr[i]);
        }
    }

    public int size() {
        return rear - front;
    }

    /**
     * 监控front rear排错
     *
     */
    public void monitor() {
        System.out.printf("front:%d\trear:%d\t", front, rear);
    }
}


