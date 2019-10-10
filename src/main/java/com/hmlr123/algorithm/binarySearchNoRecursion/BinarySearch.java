package com.hmlr123.algorithm.binarySearchNoRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找 非递归
 *
 * @author liwei
 * @date 2019/10/9 17:29
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int i = binarySearch(arr, 3);
        System.out.println(i);
    }

    /**
     * 非递归实现
     *
     * @param arr    升序数组
     * @param target 查找目标
     * @return
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 非递归实现
     * 有相同值的情况
     *
     * @param arr    升序数组
     * @param target 查找目标
     * @return
     */
    public static List<Integer> binarySearch1(int[] arr, int target) {
        List<Integer> list = new ArrayList<>();
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target == arr[mid]) {
                //向左查找 因为有序，所以向左查找匹配相同数据
                int temp = mid - 1;
                while (true) {
                    if (temp < 0 || arr[temp] != arr[mid]) {
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
                    if (temp > arr.length - 1 || arr[temp] != arr[mid]) {
                        break;
                    }
                    list.add(temp);
                    temp++;
                }
                return list;
            } else if (target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return list;
    }


}
