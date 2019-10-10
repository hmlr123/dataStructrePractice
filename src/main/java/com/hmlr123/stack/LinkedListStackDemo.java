package com.hmlr123.stack;

/**
 * 链表实现栈.
 *
 * @author liwei
 * @date 2019/10/3 20:50
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedListStack<>(3);
        StackTools.showTools(stack);
    }

}

class LinkedListStack<T> implements Stack<T> {

    private StackNode head;
    private int maxSize;
    private int size;

    public LinkedListStack(Integer maxSize) {
        head = new StackNode<>();
        this.maxSize = maxSize;
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public boolean isFull() {
        return size == maxSize;
    }

    @Override
    public void push(T value) {
        if (isFull()) {
            throw new RuntimeException("栈满了！");
        }
        StackNode temp = head.next;
        StackNode node = new StackNode(value);
        if (null == temp) {
            head.next = node;
        } else {
            head.next = node;
            node.next = temp;
        }
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了！");
        }
        size--;
        StackNode temp = head.next;
        head.next = temp.next;
        return (T) temp.data;
    }

    @Override
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了！");
        }
        StackNode temp = head.next;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    @Override
    public int top() {
        return 0;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了！");
        }
        return (T) head.next.data;
    }
}



class StackNode<T> {
    protected T data;
    protected StackNode next;

    public StackNode() {
    }

    public StackNode(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data + "";
    }
}
