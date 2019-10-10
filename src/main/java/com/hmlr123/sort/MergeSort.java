package com.hmlr123.sort;

/**
 * 归并排序.
 *
 * @author liwei
 * @date 2019/10/5 23:34
 */
public class MergeSort implements Sort{

    public static void main(String[] args) {
        int[] arr = Sorts.createArr(10000000);
        Sorts.show(arr, new MergeSort());
    }

    /**
     * 分
     * 递归分解
     *
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 治
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //左边有序序列的起始索引
        int l = left;
        //右边有序序列的起始索引
        int r = mid + 1;
        //中间数组的索引
        int t = 0;

        while (l < left && r < right) {
            if (arr[l] < arr[r]) {
                temp[t] = arr[l];
                l++;
                t++;
            }
            if (arr[l] > arr[r]) {
                temp[t] = arr[r];
                r++;
                t++;
            }
        }

        //将两边多余的数据添加到temp中
        while (l <= mid) {
            temp[t] = arr[l];
            l++;
            t++;
        }
        while (r <= right) {
            temp[t] = arr[r];
            r++;
            t++;
        }

        //将temp 数组拷贝到 arr
        int tempLeft = left;
        t = 0;
        //第一次 拷贝 0，1 第二次拷贝 2，3 第三次拷贝 0，1，2，3 ...最后一次拷贝：0 - 7
        while (tempLeft < right) {
            arr[tempLeft] = arr[t];
            t++;
            tempLeft++;
        }
    }

    @Override
    public void sort(int[] arr) {
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
    }
}
