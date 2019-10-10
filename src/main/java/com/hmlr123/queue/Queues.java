package com.hmlr123.queue;

import java.util.Scanner;

/**
 * 队列工具类.
 *
 * @author liwei
 * @date 2019/10/2 0:27
 */
public class Queues {

    public static void queueTools(Queue queue) {
        char key = ' ';         //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出菜单
        while (loop) {
            System.out.println("s:显示队列");
            System.out.println("a:添加元素到队列");
            System.out.println("g:从队列中取出元素");
            System.out.println("h:显示队列头数据");
            System.out.println("m:监控front rear");
            System.out.println("e:退出程序");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's' :
                    try {
                        queue.printQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a' :
                    try {
                        System.out.println("请输入数据:");
                        int value = scanner.nextInt();
                        queue.add(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g' :
                    try {
                        System.out.println(queue.get());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h' :
                    try {
                        System.out.println(queue.peek());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e' :
                    scanner.close();
                    loop = false;
                    System.out.println("退出程序");
                    break;
                case 'm' :
                    queue.monitor();
                    break;
                default:
                    System.out.println("请输入正确指令！");
                    break;
            }
        }

    }
}
