package com.hmlr123.search;

import java.util.List;

/**
 * @author liwei
 * @date 2019/10/6 11:00
 */
public interface Search {
    int search(int[] arr, int value);

    List searchAll(int[] arr, int value);
}
