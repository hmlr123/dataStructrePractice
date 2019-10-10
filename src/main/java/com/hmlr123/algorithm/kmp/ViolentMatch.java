package com.hmlr123.algorithm.kmp;

/**
 * 暴力匹配
 *
 * @author liwei
 * @date 2019/10/10 0:02
 */
public class ViolentMatch {
    public static void main(String[] args) {

        String matStr = "李威李威嘿嘿";
        String targetStr = "李威嘿嘿";
        int i = violentKmp(matStr, targetStr);
        System.out.println(i);

    }


    public static int violentKmp(String matStr, String targetStr) {
        char[] matChr = matStr.toCharArray();
        char[] targetChr = targetStr.toCharArray();
        return violentKmp(matChr, targetChr);
    }

    /**
     * 暴力匹配算法
     *
     * @param matChr    待匹配字符串    match
     * @param targetChr 目标字符串   target
     * @return -1标识没有匹配上。
     */
    public static int violentKmp(char[] matChr, char[] targetChr) {

        // 待匹配字符索引
        int matIndex = 0;
        //目标字符索引
        int targetIndex = 0;

        while (matIndex < matChr.length && targetIndex < targetChr.length) {
            if (matChr[matIndex] == targetChr[targetIndex]) {
                matIndex++;
                targetIndex++;
            } else {
                //索引回溯
                //待匹配字符回退到开始匹配的下一个位置
                matIndex = matIndex - (targetIndex - 1);
                //目标索引回退到0
                targetIndex = 0;
            }

        }

        if (targetIndex == targetChr.length) {
            return targetIndex;
        } else {
            return -1;
        }

    }
}
