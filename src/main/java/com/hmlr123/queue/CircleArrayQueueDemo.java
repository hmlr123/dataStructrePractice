package com.hmlr123.queue;

/**
 * 数组实现循环队列.
 * 1. front指向第一个元素， rear指向最后一个元素的下一个元素。目的预留一个空间做算法处理
 * 2. 当(rear + 1)%maxSize = front时候，队列满了
 * 思考：类比时钟，每次循环都会从下一个开始，当前的结束就是下一个的开始。所以转换成时间轴需要加1
 * 3. 当rear = front时队列为空
 * 4. (rear + maxSize - front) / maxSize 为当前有效数据    获取当前有效数据，将时钟摊开看
 *
 * @author liwei
 * @date 2019/10/1 23:42
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        Queue queue = new CircleArrayQueue(5);
        Queues.queueTools(queue);
    }
}

class CircleArrayQueue implements Queue {
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
    public CircleArrayQueue(int initSize) {
        this.maxSize = initSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否满了.
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        int temp = arr[front];
        arr[front] = 0;
        front = (front + 1) % maxSize;
        return temp;
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
        arr[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 显示队列首部元素.
     *
     * @return
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return arr[front];
    }

    /**
     * 打印队列元素.
     */
    public void printQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 获取有效数字的数组大小
     *
     * @return
     */
    public int size() {
        return (maxSize + rear - front) % maxSize;
    }

    /**
     * 监控front rear排错
     *
     */
    public void monitor() {
        System.out.printf("front:%d\t rear:%d\n", front, rear);
        System.out.print("当前数组数据 ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();

    }
}
