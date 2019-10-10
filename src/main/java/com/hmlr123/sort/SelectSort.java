package com.hmlr123.sort;

import java.util.Arrays;

/**
 * 选择排序.
 * 个人理解：我个人不和你们一群人比较，你们一群人中选出最小的和我比较，如果比我大不懂，比我小，我们交换
 *
 * @author liwei
 * @date 2019/10/5 11:06
 */
public class SelectSort implements Sort {

    public static void main(String[] args) {
        int[] arr = Sorts.createArr(10000);
        Sorts.show(arr, new SelectSort());
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minValue = arr[i];
            int index = i;
            //剩下一群人中找出最小的
            for (int j = i + 1; j < arr.length; j++) {
                if (minValue > arr[j]) {
                    minValue = arr[j];
                    index = j;
                }
            }

            //我不是最小的
            if (index != i) {
                //我要是最小的，我们交换
                arr[index] = arr[i];
                arr[i] = minValue;
            }
//            System.out.printf("第%d趟排序后数组: %s \n", i + 1, Arrays.toString(arr));
        }
    }

    @Override
    public void sort(int[] arr) {
        selectSort(arr);
    }
}
