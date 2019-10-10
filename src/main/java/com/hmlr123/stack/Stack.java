package com.hmlr123.stack;

/**
 * @author liwei
 * @date 2019/10/3 20:30
 */
public interface Stack<T> {

    boolean isEmpty();

    boolean isFull();

    void push(T value);

    T pop();

    void list();

    int top();

    T peek();

}
