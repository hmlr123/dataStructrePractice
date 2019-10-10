package com.hmlr123.stack;

/**
 * 计算器demo工具类.
 *
 * @author liwei
 * @date 2019/10/4 15:13
 */
public class Calculates {

    private final static int ADD = 1;
    private final static int SUB = 1;
    private final static int MUL = 2;
    private final static int DIV = 2;


    /**
     * 用于中缀表达式的计算，和后缀的计算不一样，不能合并！
     *
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public static int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }


    public static int cal(int num1, int num2, String oper) {
        int res = 0;
        switch (oper) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num1 / num2;
                break;
            default:
                throw new RuntimeException("运算符错误！");
        }
        return res;
    }

    //char的本质底层也是数字  char和int可以混用
    public static int priority(String oper) {
        int res = 0;
        switch (oper) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            case "(":
                break;
            case ")":
                break;
            default:
                throw new RuntimeException("运算符错误！");
        }
        return res;
    }

    public static int priority(char oper) {
        return priority(String.valueOf(oper));
    }

    public static boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
}
