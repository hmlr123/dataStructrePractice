package com.hmlr123.sort;

/**
 * 希尔排序
 * 插入排序的改进版本：将数组拆分，分别进行插入排序，为了解决插入较小数字损耗大的问题
 *
 * @author liwei
 * @date 2019/10/5 13:39
 */
public class ShellSort implements Sort {
    public static void main(String[] args) {
        int[] arr = Sorts.createArr(100000);
        Sorts.show(arr, new ShellSort());
    }


    /**
     * 交换法实现.
     *
     * @param arr
     */
    public static void shellSort1(int[] arr) {

        int temp = 0;
        //分多少份
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //gap组，每组的排序 相当于普通插入排序的步长1
            for (int i = gap; i < arr.length; i++) {
                //遍历各组组内元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 移位法实现
     * 简单插入排序的再次封装
     *
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        int insertValue = 0;
        int insertIndex = 0;
        //分多少份
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //gap组，每组的排序 相当于普通插入排序的步长1
            for (int i = gap; i < arr.length; i++) {
                insertIndex = i - gap;
                insertValue = arr[i];
                while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                    //要插入位置的元素往后移动
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }
                arr[insertIndex + gap] = insertValue;
            }
        }
    }


    @Override
    public void sort(int[] arr) {
        shellSort1(arr);
    }
}
