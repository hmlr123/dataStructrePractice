package com.hmlr123.linkedList;

/**
 * @author liwei
 * @date 2019/10/2 10:42
 */
public interface LinkedList<T> {

    boolean isEmpty();

    boolean isTransboundary(int index);

    void add(T node);

    void add(int index, T node);

    T get();

    T get(int index);

    T remove();

    T remove(int index);

    T tail();

    T removeTail();

    int length();

    void modify(int index, T t);

    void list();
}
