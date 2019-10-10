package com.hmlr123.sort;

import java.util.Arrays;

/**
 * 冒泡排序.
 *
 * @author liwei
 * @date 2019/10/5 9:50
 */
public class BubbleSort implements Sort{
    public static void main(String[] args) {
        int[] arr = Sorts.createArr(10000);
        Sorts.show(arr, new BubbleSort());
    }

    @Override
    public void sort(int[] arr) {
        bubbleSort2(arr);
    }

    /**
     * 第一趟排序将最大的数字放到最后
     * 第二趟排序将第二大的数字方法倒数第二位
     * ...
     * <p>
     * 总共有maxSize次循环排序
     * 每次里面都有maxSize - 1- i次比较
     */
    public static void bubbleSort1(int arr[]) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.printf("第%d趟排序后数组: %s \n", i + 1, Arrays.toString(arr));
        }
    }

    /**
     * 优化：解决重复比较问题
     * 使用标识位
     */
    public static void bubbleSort2(int arr[]) {
        int temp;
        boolean flag = false;   //判断是否交换过
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag) {
                flag = false;
            } else {
                //当前这一趟没有交换过 说明当前这一趟已经顺序了，不用继续循环了, 提前结束冒泡排序.
                break;
            }
//            System.out.printf("第%d趟排序后数组: %s \n", i + 1, Arrays.toString(arr));
        }
    }
}
