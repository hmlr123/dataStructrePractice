package com.hmlr123.sparseArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏数组.
 * <p>
 * 存盘：
 * <p>
 * 1. 获取数组的有效数据的个数num
 * 2. 创建稀疏数组`[num + 1][3]`
 * 3. 将有效数据写入稀疏数组
 * <p>
 * 续盘：
 * <p>
 * 1. 读取存盘数据，第一行数据
 * 2. 创建二维数组[第一行第一列数据，第一行第二列数据]
 * 3. 将稀疏数组的数据转成二维数组
 *
 * @author liwei
 * @date 2019/10/1 16:49
 */
public class SparseArray {

    public static void main(String[] args) throws IOException {
        String path = "map.data";
        int[][] arr1 = new int[20][20];
        arr1[2][3] = 1;
        arr1[5][6] = 2;
        arr1[1][2] = 3;
        arr1[11][11] = 4;
        arr1[11][12] = 5;
        arr1[11][13] = 6;
        arr1[11][14] = 7;
        arr1[12][14] = 8;
        arr1[13][14] = 9;
        arr1[14][14] = 10;
        arr1[15][14] = 11;

        //创建稀疏数组
        int[][] sparseAttr = createsparseAttr(arr1);
        //存入文件
        writeFile(sparseAttr, path);

        //读取文件
        int[][] readFile = readFile(path);

        //解析稀疏数组
        int[][] unSparseAttr = analysisSparseAttr(readFile);
        printArr(unSparseAttr);


    }

    /**
     * 读取文件.
     *
     * @param path      文件路径
     * @return          数据
     * @throws IOException
     */
    private static int[][] readFile(String path) throws IOException {

        //这个类提供了一个抽象的，独立于系统的层次化路径名的视图
        File file = new File(path);
        //使用带有指定文件的String参数的构造方法。创建该输入流对象。并关联源文件。
        FileReader fileReader = new FileReader(file);
        //将数据批量读取到缓冲区
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        int row = 0;
        int column = 0;
        List<String> list = new ArrayList<String>();
        //统计行数 列数
        while ((line = bufferedReader.readLine()) != null) {
            list.add(line);
            String[] split = line.split("\t");
            column = split.length;
            row++;
        }
        fileReader.close();

        //创建数组
        int[][] arr = new int[row][column];

        //赋值
        for (int i = 0; i < list.size(); i++) {
            String[] split = list.get(i).split("\t");
            for (int j = 0; j < split.length; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
            }
        }

        return arr;
    }

    /**
     * 保存到文件中.
     *
     * @param sparseAttr 数组
     * @param path       路径
     * @throws IOException
     */
    private static void writeFile(int[][] sparseAttr, String path) throws IOException {
        //保存到文件中
        File file = new File(path);
        //创建字符输出流类对象和已存在的文件相关联。文件不存在的话，并创建。
        FileWriter fileWriter = new FileWriter(file);

        for (int i = 0; i < sparseAttr.length; i++) {
            for (int j = 0; j < sparseAttr[0].length; j++) {
                fileWriter.write(sparseAttr[i][j] + "\t");
            }
            //写入字符串。当执行完此方法后，字符数据还并没有写入到目的文件中去。此时字符数据会保存在缓冲区中。
            fileWriter.write("\r\n");
        }
        ///刷新该流中的缓冲。将缓冲区中的字符数据保存到目的文件中去。
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * 解析稀疏数组.
     *
     * @param sparseAttr    数组
     * @return
     */
    private static int[][] analysisSparseAttr(int[][] sparseAttr) {
        //解析稀疏数组 analysis
        int[][] unSparseAttr = new int[sparseAttr[0][0]][sparseAttr[0][1]];

        for (int i = 1; i < sparseAttr.length; i++) {
            unSparseAttr[sparseAttr[i][0]][sparseAttr[i][1]] = sparseAttr[i][2];
        }
        return unSparseAttr;
    }

    /**
     * 创建稀疏数组
     *
     * @param arr1      数组
     * @return
     */
    private static int[][] createsparseAttr(int[][] arr1) {
        int num = 0;
        //行的长度
        for (int i = 0; i < arr1.length; i++) {
            //列
            for (int j = 0; j < arr1[0].length; j++) {
                if (0 != arr1[i][j]) {
                    num++;
                }
            }
        }

        //创建稀疏数组
        int[][] sparseAttr = new int[num + 1][3];
        sparseAttr[0][0] = arr1.length;
        sparseAttr[0][1] = arr1[0].length;
        sparseAttr[0][2] = num;

        int count = 1;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                if (0 != arr1[i][j]) {
                    sparseAttr[count][0] = i;
                    sparseAttr[count][1] = j;
                    sparseAttr[count][2] = arr1[i][j];
                    count++;
                }
            }
        }

        return sparseAttr;
    }

    /**
     * 控制台输出.
     *
     * @param data
     */
    private static void printArr(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.printf("%d\t", data[i][j]);
            }
            System.out.println();
        }
    }
}
