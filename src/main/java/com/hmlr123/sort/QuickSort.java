package com.hmlr123.sort;

/**
 * 快速排序.
 * 空间换时间
 *
 * 通过一趟排序**将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小**，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列）
 *
 * @author liwei
 * @date 2019/10/5 19:38
 */
public class QuickSort implements Sort {

    public static void main(String[] args) {
        int[] arr = Sorts.createArr(10000000);
        Sorts.show(arr, new QuickSort());
    }

    /**
     * @param arr   数组
     * @param left  左边
     * @param right 右边
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        while (l < r) {
            //寻找
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }

            //结束当前循环 说明左边的全部小于右边
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //避免重复的值比较
            if (arr[l] == pivot) {
                //右移动
                l++;
            }
            if (arr[r] == pivot) {
                //左移动
                r--;
            }
        }

        //避免栈溢出 为下一次递归做准备
        if (l == r) {
            l++;
            r--;
        }

        //左递归
        if (l < left) {
            quickSort(arr, left, r);
        }
        if (r > right) {
            quickSort(arr, l, right);
        }
    }


    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
}
