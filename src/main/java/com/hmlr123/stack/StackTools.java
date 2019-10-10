package com.hmlr123.stack;

import java.util.Scanner;

/**
 * @author liwei
 * @date 2019/10/3 21:15
 */
public class StackTools {

    public static void showTools(Stack stack) {
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 显示栈");
            System.out.println("push: 入栈");
            System.out.println("pop: 出栈");
            System.out.println("top: 监视栈顶");
            System.out.println("exit: 退出");

            key = scanner.next();

            switch (key) {
                case "show":
                    try {
                        stack.list();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "push":
                    try {
                        System.out.println("请输入入栈数据:");
                        int value = scanner.nextInt();
                        stack.push(value);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "top" :
                    System.out.println(stack.top());
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出");
                    break;
                default:
                    break;
            }
        }
    }
}
