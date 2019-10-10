package com.hmlr123.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找，数组必须有序！
 *
 * @author liwei
 * @date 2019/10/6 11:16
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 3, 4, 4, 5, 6, 7, 8, 9, 9, 9, 9, 9, 9, 9};
        System.out.println(binarySearch2(arr, 0, arr.length - 1, 10));
    }


    /**
     * @param arr   数组
     * @param left  左边索引
     * @param right 右边索引
     * @param value 要寻找的值
     */
    public static int binarySearch1(int[] arr, int left, int right, int value) {
        //结束递归 没有找到值
        if (left > right || arr[0] > value || arr[arr.length - 1] < value) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (midVal > value) {
            //向左递归
            return binarySearch1(arr, left, mid - 1, value);
        } else if (midVal < value) {
            //向右递归
            return binarySearch1(arr, mid + 1, right, value);
        } else {
            return mid;
        }
    }

    /**
     * 有相同的值的时候 递归方式的二分查找
     *
     * @param arr   数组
     * @param left  左边索引
     * @param right 右边索引
     * @param value 要寻找的值
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        System.out.println("递归");
        //结束递归 没有找到值
        if (left > right || arr[0] > value || arr[arr.length - 1] < value) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (midVal > value) {
            //向左递归
            return binarySearch2(arr, left, mid - 1, value);
        } else if (midVal < value) {
            //向右递归
            return binarySearch2(arr, mid + 1, right, value);
        } else {
            List<Integer> list = new ArrayList<>();
            //向左查找 因为有序，所以向左查找匹配相同数据
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != midVal) {
                    break;
                }
                list.add(temp);
                temp--;
            }
            //把当前查找到的值放进去
            list.add(mid);

            //向右查找
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != midVal) {
                    break;
                }
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
