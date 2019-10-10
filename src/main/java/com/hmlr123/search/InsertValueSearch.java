package com.hmlr123.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 插值查找.
 * 数据量大， 关键字分布均匀的查找，
 * 有倍数关系
 *
 * @author liwei
 * @date 2019/10/6 12:22
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 9012};
        System.out.println(insertValueSearch2(arr, 0, arr.length - 1, 1));
        System.out.println(BinarySearch.binarySearch2(arr, 0, arr.length - 1, 1));
    }

    public static int insertValueSearch1(int[] arr, int left, int right, int value) {
        System.out.println("递归");
        //结束递归 没有找到值
        if (left > right || arr[0] > value || arr[arr.length - 1] < value) {
            return -1;
        }

        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (midVal > value) {
            //向左递归
            return insertValueSearch1(arr, left, mid - 1, value);
        } else if (midVal < value) {
            //向右递归
            return insertValueSearch1(arr, mid + 1, right, value);
        } else {
            return mid;
        }
    }

    public static List<Integer> insertValueSearch2(int[] arr, int left, int right, int value) {
        System.out.println("递归");
        //结束递归 没有找到值
        if (left > right || arr[0] > value || arr[arr.length - 1] < value) {
            return new ArrayList<>();
        }

        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (midVal > value) {
            //向左递归
            return insertValueSearch2(arr, left, mid - 1, value);
        } else if (midVal < value) {
            //向右递归
            return insertValueSearch2(arr, mid + 1, right, value);
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
