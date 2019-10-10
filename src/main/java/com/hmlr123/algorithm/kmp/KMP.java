package com.hmlr123.algorithm.kmp;

/**
 *
 * 核心方法
 * 部分匹配使用回退
 *
 * 部分匹配表记录当前匹配部分的内部匹配上的。如果当前已经匹配的后续没有匹配上。
 * 则 当前目标匹配字符串中回退到中途匹配上的位置（中途匹配上的位置就是我们的部分匹配表），继续匹配，即待匹配字符串游标不回退，目标匹配字符串游标回退
 *
 *
 * @author liwei
 * @date 2019/10/10 0:21
 */
public class KMP {

    public static void main(String[] args) {

        String matStr = "abcdabdb";
        String targetStr = "abd";

        int[] ints = partMatch(matStr);
        int match = match(matStr, targetStr, ints);
        System.out.println(match);
    }


    /**
     *
     *
     * @param str1  待匹配字符串
     * @param str2  目标字符串
     * @param next  部分匹配值
     * @return
     */
    public static int match(String str1, String str2, int[] next) {
        return match(str1.toCharArray(), str2.toCharArray(), next);
    }

    /**
     *
     *
     * @param char1 待匹配字符
     * @param char2 目标字符
     * @param next 部分匹配值
     * @return
     */
    public static int match(char[] char1, char[] char2, int[] next) {


        /**
         * i 长游标
         * j 短游标
         */
        for (int i = 0, j = 0; i < char1.length; i++) {

            /**
             *
             * 没有匹配上
             */
            while (j > 0 && char1[i] != char2[j]) {
                //短游标回退
                j = next[j - 1];
            }


            if (char1[i] == char2[j]) {
                j++;
            }
            /**
             * 匹配上
             */
            if (j == char2.length) {
                return i - (j - 1);
            }
        }
        return -1;

    }

    /**
     * 从给定的字符数组中找出相同的前缀/后缀
     * 部分匹配值（已经匹配过的字符串中匹配相同的部分并记录）
     *
     * @param str   字符串
     * @return
     */
    public static int[] partMatch(String str) {
        return partMatch(str.toCharArray());
    }

    public static int[] partMatch(char[] str) {
        //相当于一次小的暴力匹配

        // 保存部分匹配的值
        int next[] = new int[str.length];
        next[0] = 0;

        /**
         * i 较短字符长度
         * j 较长字符长度
         *
         * abcdabef
         *
         *
         * 由于第一个肯定一样，所以不用匹配，从第二个开始匹配
         *
         * 相当于
         * j 代表待匹配字符
         * i 代表目标字符
         *
         */
        for (int i = 0, j = 1; j < str.length; j++) {

            // 待匹配字符 游标回退到刚开始匹配的下一个位置
            while (i > 0 && str[i] != str[j]) {
                i = next[i - 1];
            }

            //匹配上了
            if (str[i] == str[j]) {
                //目标字符游标向前移动
                i++;
            }
            next[j] = i;
        }
        return next;
    }


}
