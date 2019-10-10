package com.hmlr123.tree;

import com.hmlr123.sort.Sort;
import com.hmlr123.sort.Sorts;

import java.util.Arrays;

/**
 * 堆排序.
 * 构建大顶堆，交换 重复
 *
 * @author liwei
 * @date 2019/10/7 17:03
 */
public class HeapSortDemo {

    public static void main(String[] args) {
        int[] arr = Sorts.createArr(5);
        Sorts.show(arr, new HeapSort());
        System.out.println(Arrays.toString(arr));
    }
}

class HeapSort implements Sort {


    public static void heapSort(int arr[]) {
        int temp = 0;

        // 非叶子节点个数
        // 第一次大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //复制 调整
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //每次调整前面 arr.length - 1 - 1 的数量
            adjustHeap(arr, 0, i);
        }

    }

    //构造大顶堆

    /**
     * 调整非叶子节点的树组成大顶堆
     * <p>
     * 从左向右，从底向上
     *
     * @param arr    数组
     * @param i      即将要调整的根节点（非叶子节点）
     * @param length 需要调整部分数组长度
     */
    public static void adjustHeap(int[] arr, int i, int length) {

        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //找出左右子孩子中较大的那位
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (temp < arr[k]) {
                // 此处容易造成算法的不稳定性 多次的交换，导致数据间的交换，好处：使较大数据在较高位置
                // 数据交换
                arr[i] = arr[k];
                i = k;
            } else {
                // 因为我们是从底向上比较的，往上走的一定比底下的大
                break;
            }
        }
        arr[i] = temp;
    }

    @Override
    public void sort(int[] arr) {
        heapSort(arr);
    }
}
