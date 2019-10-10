package com.hmlr123.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author liwei
 * @date 2019/10/5 11:49
 */
public class Sorts {

    /**
     * 比较排序算法时间复杂度.
     *
     * @param arr  数组
     * @param sort 排序算法
     */
    public static void show(int[] arr, Sort sort) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

        Date date1 = new Date();
        String time1 = simpleDateFormat.format(date1);
//        System.out.println("排序前："+Arrays.toString(arr));
        sort.sort(arr);
        Date date2 = new Date();
        String time2 = simpleDateFormat.format(date2);

//        System.out.println("排序后："+Arrays.toString(arr));
        System.out.println("开始时间：" + time1);
        System.out.println("结束时间：" + time2);
        long ss = date2.getTime() - date1.getTime();
        System.out.printf("间隔时间：%d ms", Integer.valueOf((int) ss));
    }

    /**
     * 创建数组.
     *
     * @param maxSize
     * @return
     */
    public static int[] createArr(int maxSize) {
        //窗创建数组
        int[] arr = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            arr[i] = new Random().nextInt(maxSize * 100);
        }
        return arr;
    }
}
