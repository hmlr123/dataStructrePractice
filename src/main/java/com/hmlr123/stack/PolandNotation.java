package com.hmlr123.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 * 逆波兰计算器
 *
 * @author liwei
 * @date 2019/10/4 14:39
 */
public class PolandNotation {

    public static void main(String[] args) {
        // (3+4)×5-6  =>    3 4 + 5 × 6 -
        String expression = "(3+4)×5-6";
//        String suffixExpression = "3 4 + 5 * 6 -";
//        List<String> listStr = getListStr(suffixExpression);
//        int calculate = calculate(listStr);
//        System.out.printf("表达式 %s = %d", expression, calculate);


        //
        expression = "10+(2+3)*4-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        List<String> toSuffix = infixToSuffix(infixExpressionList);
        System.out.println(toSuffix);
        System.out.println(calculate(toSuffix));
    }

    /**
     * 中缀表达式转后缀表达式.
     *
     * 1. 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2. 从左至右扫描中缀表达式；
     * 3. 遇到操作数时，将其压s2；
     * 4. 遇到运算符时，比较其与s1栈顶运算符的优先级：
     *    - 如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     *    - 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     *    - 否则，将栈顶的运算符弹出并压入到中，再次转到与中新的栈顶运算符相比较
     *
     * 5. 遇到括号时：
     *    - 如果是左括号“(”，则直接压入s1
     *    - 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     *
     * 6. 重复步骤2至5，直到表达式的最右边
     *
     * 7. 将s1中剩余的运算符依次弹出并压入s2
     * 8. 依次弹出s2中的元素并输出，**结果的逆序即为中缀表达式对应的后缀表达式**
     *
     * @param list
     * @return
     */
    public static List<String> infixToSuffix(List<String> list) {
        Stack<String> operStack = new Stack<>();
        //替代我们的数据栈，最终需要反向输出的那个栈
        List<String> dataList = new ArrayList<>();
        for (String str : list) {
            //数字直接入数据栈.
            if (str.matches("\\d+")) {
                dataList.add(str);
                //遇到(直接入符号栈
            } else if ("(".equals(str)) {
                operStack.push(str);
            } else if (str.equals(")")) {
                //匹配(,没有匹配上都入数据栈
                while (!operStack.peek().equals("(")) {
                    dataList.add(operStack.pop());
                }
                operStack.pop();//弹出(
            } else {
                //栈顶优先级
                while (!operStack.isEmpty() && Calculates.priority(str) <= Calculates.priority(operStack.peek())) {
                    dataList.add(operStack.pop());
                }
                //栈为空或者栈顶的优先级小于当前元素
                operStack.push(str);
            }
        }


        //将符号栈的数据全部写入数据栈
        while (!operStack.isEmpty()) {
            dataList.add(operStack.pop());
        }
        return dataList;
    }


    /**
     * 将中缀表达式转换成我们需要的list
     * 主要是解决多位数字问题且不能用空格区分（所以不能使用字符串拆分的方式）
     *
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s) {
        //用于存放我们需要的字符
        List<String> ls = new ArrayList<>();
        //指针作用，用于挪动
        int i = 0;
        //存储截取的每个字符
        char ch = ' ';
        //拼接的字符串，用于多位数的表示
        String str;
        //一个一个字符截取处理
        do {
            //非数字 (运算符)
            if ((ch = s.charAt(i)) < 48 || (ch = s.charAt(i)) > 57) {
                ls.add(ch + "");
                i++;
            } else {
                //数字 判断下一位是否是运算符
                str = "";
                //当前字符（数字）以及当前字符的下几个字符(数字)
                while (i < s.length() && ((ch = s.charAt(i)) >= 48 && (ch = s.charAt(i)) <= 57)) {
                    str += ch;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());

        return ls;
    }

    /**
     * 解析后缀表达式
     *
     * @param suffixExpression
     * @return
     */
    public static List<String> getListStr(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = Arrays.asList(split);
        return list;
    }

    /**
     * 计算
     * 栈下面的操作栈上面的数据.
     *
     * @param list
     * @return
     */
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String str : list) {
            //正则表达式取出数字
            if (str.matches("\\d+")) {
                stack.push(str);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = Calculates.cal(num1, num2, str);
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
