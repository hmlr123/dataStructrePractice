package com.hmlr123.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺序查找
 *
 * @author liwei
 * @date 2019/10/6 11:00
 */
public class SeqSearch implements Search{
    public static void main(String[] args) {


    }



    @Override
    public int search(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }

        }
        return -1;
    }

    @Override
    public List searchAll(int[] arr, int value) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                list.add(i);
            }
        }
        return list;
    }
}
