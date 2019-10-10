package com.hmlr123.stack;

/**
 * @author liwei
 * @date 2019/10/3 21:46
 */
public class CalculatorDemo {

    public static void main(String[] args) {
        CalculatorStack numStack = new CalculatorStack(10);
        CalculatorStack operStack = new CalculatorStack(10);

        String expression = "100+3+3*4+6/3";

        int index = 0;  //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        String keepNum = "";
        char ch = ' ';  //每次扫描的char保存结果
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (Calculates.isOper(ch)) {
                //栈不为空，并且当前元素的优先级小于等于栈顶元素优先级
                //取出栈顶元素进行运算，得到结果在入栈 当前操作符入栈
                if (!operStack.isEmpty() && Calculates.priority(String.valueOf(operStack.peek())) >= Calculates.priority(ch)) {
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    oper = operStack.pop();
                    res = Calculates.cal(num1, num2, oper);
                    numStack.push(res);
                    operStack.push(ch);
                } else {
                    operStack.push(ch);
                }
            } else {
                //减去48和ascii编码有关 char和int相差48
                // char转数字需要减去48
//                numStack.push(ch - 48);

                //添加多位数？ 这里很巧妙
                keepNum += ch;
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else if (Calculates.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }
            }

            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 计算栈中剩余数据.
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = Calculates.cal(num1, num2, oper);
            numStack.push(res);
        }

        System.out.printf("表达式 %s = %d", expression, numStack.pop());

    }
}

class CalculatorStack {

    private int maxSize;
    private int top;
    private int[] stack;

    public CalculatorStack() {
        this(10);
    }

    public CalculatorStack(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        this.stack = new int[maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }


    public boolean isFull() {
        return top == (maxSize - 1);
    }

    /**
     * 入栈.
     *
     * @param value
     * @return
     */
    public void push(int value) {
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
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了！");
        }
        return stack[top--];
    }

    /**
     * 打印栈
     */
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了！");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }

    }


    public int top() {
        return top;
    }

    /**
     * 输出栈顶元素
     *
     * @return
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了！");
        }
        return stack[top];
    }
}


