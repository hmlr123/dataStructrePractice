package com.hmlr123.algorithm.greedy;

import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * 贪心算法(局部最优)
 * 每次去最优结果
 * 知道所有遍历完
 *
 * @author liwei
 * @date 2019/10/10 9:09
 */
public class GreedyDemo {

    public static void main(String[] args) {
        // 所有城市范围
        HashSet<String> allCity = new HashSet<>();
        // 每个电台覆盖城市集合
        Map<String, HashSet<String>> broadCasts = new HashMap<>();

        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadCasts.put("k1", hashSet1);
        broadCasts.put("k2", hashSet2);
        broadCasts.put("k3", hashSet3);
        broadCasts.put("k4", hashSet4);
        broadCasts.put("k5", hashSet5);

        allCity.addAll(hashSet1);
        allCity.addAll(hashSet2);
        allCity.addAll(hashSet3);
        allCity.addAll(hashSet4);
        allCity.addAll(hashSet5);

        //全局最优解
        List<String> optimalSolution = new ArrayList<>();

        //遍历所有城市知道，所有城市都覆盖  -> 我们的目的
        while (allCity.size() != 0) {
            //每种方案遍历城市，保存处理结果

            // 本地最优解的权值（囊括城市的数量）
            int localMax = 0;
            // 本地最优解的key 当前方案的key
            String localKey = "";
            // 本地最优解的value 当前方案的value
            HashSet<String> localValue = new HashSet<>();
            for (Map.Entry<String, HashSet<String>> broad : broadCasts.entrySet()) {
                String key = broad.getKey();
                HashSet<String> value = broad.getValue();
                //取出重复的值
                value.retainAll(allCity); // 取出重复的值复制给value
                int size = value.size();
                if (size > localMax) {
                    localMax = size;
                    localKey = key;
                    localValue = value;
                }
            }

            //处理局部最优解
            //过滤所有城市中已经覆盖的城市
            if (null != localKey && !"".equals(localKey)) {
                optimalSolution.add(localKey);
                //过滤已经匹配过的数据
                allCity.removeAll(localValue);
                broadCasts.get(localKey).removeAll(localValue);
            }
        }

        System.out.println(optimalSolution);
    }
}
