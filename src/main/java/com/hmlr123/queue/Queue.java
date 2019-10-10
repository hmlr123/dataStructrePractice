package com.hmlr123.queue;

/**
 * @author liwei
 * @date 2019/10/2 0:24
 */
public interface Queue {

    boolean isFull();

    boolean isEmpty();

    int get();

    void add(int value);

    int peek();

    void printQueue();

    int size();

    void monitor();

}
