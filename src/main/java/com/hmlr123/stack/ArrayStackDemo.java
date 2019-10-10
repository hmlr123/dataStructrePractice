package com.hmlr123.stack;

/**
 * 栈.
 *
 * @author liwei
 * @date 2019/10/3 20:17
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(3);
        StackTools.showTools(stack);
    }
}

class ArrayStack implements Stack<Integer> {
    private int maxSize;
    private int top;
    private int[] stack;

    public ArrayStack() {
        this(10);
    }

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        this.stack = new int[maxSize];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == (maxSize - 1);
    }

    /**
     * 入栈.
     *
     * @param value
     * @return
     */
    @Override
    public void push(Integer value) {
        if (isFull()) {
            throw new RuntimeException("栈满了！");
        }
        stack[++top] = value;
    }

    /**
     * 出栈.
     *
     * @return
     */
    @Override
    public Integer pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了！");
        }
        return stack[top--];
    }

    /**
     * 打印栈
     */
    @Override
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了！");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }

    }

    @Override
    public int top() {
        return top;
    }

    @Override
    public Integer peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了！");
        }
        return stack[top];
    }

}
