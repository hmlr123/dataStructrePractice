package com.hmlr123.search;

import java.util.Arrays;

/**
 * mid = low + fib[k - 1] + 1; 计算方式
 * 看图
 * 证明：
 * init: low = 0;
 * high= arr.length - 1;
 * mid = 0;
 * f(n) = f(n - 1) + f(n - 2);
 * f(n) - 1 = f(n - 1) - 1 + f(n - 2) - 1 + 1;
 * f(n - 1) 代表斐波那契数组 黄金分割点(mid)左边的数组长度
 * f(n - 2) 代表斐波那契数组 黄金分割点(mid)右边的数组长度
 * 所以中间的就是mid
 * low 右移 f(n - 1) - 1 就是mid 或者 high 左移 f(n - 2) -1 位
 * 所以 mid = f(n - 1) - 1 + low = high -f(n -2) + 1
 *
 * @author liwei
 * @date 2019/10/6 14:31
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 9012};

        System.out.println(fibonacciSearch(arr, 128));
    }

    /**
     * 构建斐波那契数组
     *
     * @return
     */
    public static int[] fib(int maxSize) {
        if (maxSize < 3) {
            throw new RuntimeException("就这样，还玩斐波那契？？？");
        }
        int[] arr = new int[maxSize];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    /**
     * @param arr
     * @param key
     */
    public static int fibonacciSearch(int[] arr, int key) {
        //1. 将我们现有的数组改成斐波那契模式
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        //满足斐波那契的关键点
        int k = 0;
        //斐波那契数组    在这里代表我们即将组成的斐波那契数组的长度.
        int[] fib = fib(arr.length);

        //寻找黄金分割点
        while (high > fib[k]) {
            k++;
        }

        //拷贝形成的数组多的位置用0替代的
        int[] temp = Arrays.copyOf(arr, fib[k]);
        //高位用arr[height]替代
        for (int i = high + 1; high < temp.length; i++) {
            temp[i] = arr[high];
        }

        //2. 将黄金分割点当mid使用
        //和二分查找差不多的查找方式
        while (low < high) {
            mid = low + fib[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                /**
                 * f(n) = f(n - 1) + f(n - 2);
                 * 当前位置 n - 1 (中间值mid)
                 * 在f(n - 1)前面搜索 f(n - 1) = f(n - 2) + f(n - 3) = f(n - 1 - 1) + f(n - 2 - 1)
                 * 所以减 1
                 */
                k -= 1;
            } else if (key > temp[mid]) {
                //向右查找
                low = mid + 1;
                /**
                 *
                 * f(n) = f(n - 1) + f(n - 2);
                 * 当前位置 n - 1 (中间值mid)
                 * 在f(n - 2)后面 f(n - 2) = f(n - 3) + f(n - 4) = f(n - 1 - 2) + f(n - 2 - 2);
                 * 所以减 2
                 */
                k -= 2;
            } else {
                //找到了 判断是 high 还是 mid  因为我们之前对我们原始的数组进行了扩容（改变成斐波那契数组，所以mid 可能大于我们的high ）
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        //没有找到
        return -1;

    }
}
