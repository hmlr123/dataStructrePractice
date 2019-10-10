package com.hmlr123.sort;

/**
 * 基数排序.
 * 空间换时间 的经典算法
 * 个位数按顺序排序
 * 十位数按顺序排序
 * 。。。
 * <p>
 * 数据拆分放到桶里面
 * 从各个桶里面取出数据
 *
 * @author liwei
 * @date 2019/10/6 9:17
 */
public class RadixSort implements Sort{
    public static void main(String[] args) {
        int[] arr = Sorts.createArr(10000000);
        Sorts.show(arr, new RadixSort());
    }

    public static void radixSort(int[] arr) {
        //10个桶
        int[][] bucket = new int[10][arr.length];
        //存储每个桶内存储数据的个数
        int[] bucketCount = new int[10];

        //寻找最大值
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //最大位数
        int maxLength = (max + "").length();
        //i遍历每个位数   n 用于获取每个位置的数字
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //1. 将数字拆分放到桶里面
            for (int j = 0; j < arr.length; j++) {
                //获取每位数 的值
                int digit = arr[j] / n % 10;
                bucket[digit][bucketCount[digit]] = arr[j];
                bucketCount[digit]++;
            }

            //我们数组arr的索引
            int index = 0;
            //2. 从桶里面取出数字
            for (int j = 0; j < bucket.length; j++) {
                if (bucketCount[j] != 0) {
                    for (int k = 0; k < bucketCount[j]; k++) {
                        arr[index] = bucket[j][k];
                        index++;
                    }
                    //需要清空bucketCount
                    bucketCount[j] = 0;
                }
            }
        }
    }

    @Override
    public void sort(int[] arr) {
        radixSort(arr);
    }
}
