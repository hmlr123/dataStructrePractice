package com.hmlr123.sort;

/**
 * 插入排序.
 *
 * @author liwei
 * @date 2019/10/5 12:18
 */
public class InsertSort implements Sort {

    public static void main(String[] args) {

        int[] arr = Sorts.createArr(10000);
        Sorts.show(arr, new InsertSort());
    }

    /**
     * 前面是有序，后面无序。
     * 无序的第一个数据依次和有序的比较（从有序的尾巴开始比较）
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        //待插入的值
        int insertValue;
        //插入的位置
        int insertIndex;

        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertValue = arr[i];
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                //要插入位置的后面的值等于当前要插入位置的值
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //自己就是最大的那个值，就不必赋值移动
            if (insertIndex + 1 != i) {
                //即将插入的值插入到指定位置
                arr[insertIndex + 1] = insertValue;
            }
        }
    }


    @Override
    public void sort(int[] arr) {
        insertSort(arr);
    }
}
