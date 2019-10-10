package com.hmlr123.huffmantree.huffmanCode;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码.
 * 1. 生成赫夫曼树
 * 2. 生成霍夫曼编码
 * 3. 编码我们的文本
 * 4. 编码后文本转换成字节
 *
 * @author liwei
 * @date 2019/10/8 0:29
 */
public class HuffmanCode {

    // 生成的赫夫曼码
    static Map<Byte, String> huffmanCode = new HashMap<>();

    public static void main(String[] args) {
//        String contend = "i like like like java do you like a java 李威 李威 李威 哈哈 嘿嘿 你好 会更好";
//        byte[] huffmanCode = huffmanZip(contend);
//        String huffmanUnzip = huffmanUnzip(huffmanCode);
//        System.out.println(huffmanUnzip);

        String srcFile = "E:\\视频\\尚硅谷-韩顺平图解Java数据结构和算法\\代码\\DataStructures\\src\\com\\atguigu\\huffmancode\\HuffmanCode.java";
        String destFile = "E:\\视频\\尚硅谷-韩顺平图解Java数据结构和算法\\代码\\DataStructures\\src\\com\\atguigu\\huffmancode\\HuffmanCode.zip";
        String destFileUnZip = "E:\\HuffmanCode.java";
        fileZip(srcFile, destFile);
        fileUnZip(destFile, destFileUnZip);
    }


    //文件解压
    public static void fileUnZip(String srcFile, String destFile) {
        //文件输入流
        InputStream is = null;
        //对象输入流
        ObjectInputStream ois = null;
        //文件输出流
        OutputStream os = null;

        try {
            is = new FileInputStream(srcFile);
            ois = new ObjectInputStream(is);

            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCode = (Map<Byte, String>) ois.readObject();

            //解压
            byte[] bytes = huffmanDecode(huffmanBytes, huffmanCode);

            //写入文件
            os = new FileOutputStream(destFile);
            os.write(bytes);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            closeFileStream(is, null, ois, os);
        }
    }

    /**
     * 文件压缩
     *
     * @param srcFile
     * @param destFile
     */
    public static void fileZip(String srcFile, String destFile) {
        //文件输入流
        InputStream is = null;
        //对象输入流
        ObjectOutputStream oos = null;
        //文件输出流
        OutputStream os = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] srcCode = new byte[is.available()];
            is.read(srcCode);
            //压缩数据
            byte[] huffmanBytes = huffmanZip(srcCode);
            os = new FileOutputStream(destFile);
            oos = new ObjectOutputStream(os);

            //写入文件
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCode);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            closeFileStream(is, oos, null, os);
        }
    }


    /**
     * 赫夫曼压缩.
     *
     * @param contend
     * @return
     */
    public static byte[] huffmanZip(String contend) {
        byte[] bytes = contend.getBytes();
        return huffmanZip(bytes);
    }

    /**
     * 赫夫曼压缩.
     *
     * @param bytes
     * @return
     */
    public static byte[] huffmanZip(byte[] bytes) {
        //获取赫夫曼树
        Node huffmanTree = getHuffmanTree(bytes);
        //生成赫夫曼码
        Map<Byte, String> huffmanCode = getHuffmanCode(huffmanTree);
        //编码我们源码
        String binaryCode = huffmanEncode(bytes, huffmanCode);
        //用赫夫曼码压缩
        byte[] zip = zip(binaryCode);
        return zip;
    }

    /**
     * 赫夫曼解压.
     *
     * @param bytes
     * @return
     */
    public static String huffmanUnzip(byte[] bytes) {
        byte[] decode = huffmanDecode(bytes, huffmanCode);
        return new String(decode);
    }

    /**
     * 补码压缩
     *
     * @param code
     * @return
     */
    public static byte[] zip(String code) {
        byte[] bytes = code.getBytes();
        int size = (bytes.length + 7) / 8;
        byte[] by = new byte[size];

        int index = 0;
        for (int i = 0; i < code.length(); i += 8) {
            String substring;
            if (i + 8 > code.length()) {
                substring = code.substring(i);
            } else {
                substring = code.substring(i, i + 8);
            }
            by[index] = (byte) Integer.parseInt(substring, 2);
            index++;
        }
        return by;
    }

    /**
     * 利用生成的霍夫曼编码编码我们的文本.
     *
     * @param bytes       元素文本字节
     * @param huffmanCode 生成的编码
     * @return
     */
    public static String huffmanEncode(byte[] bytes, Map<Byte, String> huffmanCode) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Byte b : bytes) {
            String s = huffmanCode.get(b);
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    /**
     * 赫夫曼解码
     *
     * @param bytes       字节数组
     * @param huffmanCode 赫夫曼编码
     * @return
     */
    public static byte[] huffmanDecode(byte[] bytes, Map<Byte, String> huffmanCode) {
        // 1. 字节数组转换成二进制流
        byte[] srcCode = bytesToBinary(bytes);
        // 2. 赫夫曼编码反转
        Map<String, Byte> reverseHuffmanCode = reverseHuffmanCode(huffmanCode);
        // 3. 匹配字符 不能使用Arrays.toString!!!
        String string = new String(srcCode);
        byte[] matchBytes = matchBytes(string, reverseHuffmanCode);
        return matchBytes;
    }


    /**
     * 源二进制编码转换成我们目标编码.
     * 字符匹配
     *
     * @param srcCode            源码
     * @param reverseHuffmanCode 反转后的赫夫曼编码
     * @return
     */
    public static byte[] matchBytes(String srcCode, Map<String, Byte> reverseHuffmanCode) {
        StringBuilder stringBuilder = new StringBuilder(srcCode);
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            //字符匹配小游标
            int count = 1;
            Byte ch = null;
            boolean flag = true;
            while (flag) {
                String substring = stringBuilder.substring(i, i + count);
                ch = reverseHuffmanCode.get(substring);
                if (null == ch) {
                    //没有匹配上
                    count++;
                } else {
                    //匹配上
                    flag = false;
                }
            }
            list.add(ch);
            i += count;
        }
        byte[] desCode = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            desCode[i] = list.get(i);
        }
        return desCode;
    }


    /**
     * 反转赫夫曼码.
     *
     * @param map
     * @return
     */
    public static Map<String, Byte> reverseHuffmanCode(Map<Byte, String> map) {
        HashMap<String, Byte> hashMap = new HashMap<>();

        for (Map.Entry<Byte, String> entry : map.entrySet()) {
            hashMap.put(entry.getValue(), entry.getKey());
        }
        return hashMap;
    }

    /**
     * 字节数组转换成二进制.
     * 尾部字符不需要补齐
     *
     * @param bytes
     */
    public static byte[] bytesToBinary(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            //找出尾部字符
            boolean flag = (i == bytes.length - 1);
            builder.append(bytesToBinary(!flag, b));
        }
        String string = builder.toString();
        return string.getBytes();
    }

    /**
     * 字节转换成二进制.
     * 尾部字符不需要补高位
     *
     * @param flag 判断是否是尾部字符
     * @param b    字节
     */
    public static String bytesToBinary(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            // 解决正数的补码问题
            temp |= 256;
        }
        //获取补码
        String string = Integer.toBinaryString(temp);
        if (flag) {
            // 负数的字节较长，截取尾部8个人字节
            return string.substring(string.length() - 8);
        } else {
            return string;
        }
    }


    /**
     * 获取霍夫曼编码
     *
     * @param node
     * @return
     */
    public static Map<Byte, String> getHuffmanCode(Node node) {
        if (node == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        //左子树生成赫夫曼码
        getHuffmanCode(node.left, "0", stringBuilder);
        //右子树生成赫夫曼码
        getHuffmanCode(node.right, "1", stringBuilder);
        return huffmanCode;
    }


    /**
     * 获取字节对应的哈夫曼编码.
     *
     * @param node    节点
     * @param code    编码 左0右1
     * @param builder 字符流
     * @return
     */
    public static void getHuffmanCode(Node node, String code, StringBuilder builder) {
        StringBuilder stringBuilder = new StringBuilder(builder);
        stringBuilder.append(code);
        //寻找叶子节点
        if (node != null) {
            //非叶子节点
            if (node.value == null) {
                getHuffmanCode(node.left, "0", stringBuilder);
                getHuffmanCode(node.right, "1", stringBuilder);
            } else {
                //叶子节点
                huffmanCode.put(node.value, stringBuilder.toString());
            }
        }
    }

    /**
     * 获取赫夫曼树.
     *
     * @param bytes 源字节
     * @return
     */
    public static Node getHuffmanTree(byte[] bytes) {
        // byte字节转换成list 节点
        List<Node> nodeList = getNodeList(bytes);
        // 统计byte字节出现次数
        Map<Byte, Integer> byteCount = getByteCount(nodeList);
        // 生成赫夫曼节点
        List<Node> huffmanNode = getHuffmanNode(byteCount);
        // 生成赫夫曼树
        Node huffmanTree = createHuffmanTree(huffmanNode);
        return huffmanTree;
    }

    /**
     * 构建哈夫曼树.
     *
     * @param nodes
     * @return
     */
    public static Node createHuffmanTree(List<Node> nodes) {

        Collections.sort(nodes);

        while (nodes.size() > 1) {
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node root = new Node(null, left.weight + right.weight);
            root.left = left;
            root.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(root);
        }
        return nodes.get(0);
    }


    /**
     * 生成赫夫曼树节点
     *
     * @param maps
     * @return
     */
    public static List<Node> getHuffmanNode(Map<Byte, Integer> maps) {
        ArrayList<Node> arrayList = new ArrayList<>();
        for (Map.Entry<Byte, Integer> map : maps.entrySet()) {
            arrayList.add(new Node(map.getKey(), map.getValue()));
        }
        return arrayList;
    }

    /**
     * 统计每个字节出现的次数. 重新组成list， 用于构成哈夫曼树
     *
     * @param list
     * @return
     */
    public static Map<Byte, Integer> getByteCount(List<Node> list) {
        HashMap<Byte, Integer> hashMap = new HashMap<>();
        for (Node node : list) {
            Integer integer = hashMap.get(node.value);
            if (integer == null) {
                hashMap.put(node.value, 1);
            } else {
                hashMap.put(node.value, integer + 1);
            }
        }
        return hashMap;
    }

    /**
     * 字节转换成list
     *
     * @param bytes
     * @return
     */
    public static List<Node> getNodeList(byte[] bytes) {
        List<Node> list = new ArrayList<>();

        for (int i = 0; i < bytes.length; i++) {
            list.add(new Node(bytes[i]));
        }
        return list;
    }

    /**
     * 关闭各种流
     *
     * @param is
     * @param oos
     * @param ois
     * @param os
     */
    private static void closeFileStream(InputStream is, ObjectOutputStream oos, ObjectInputStream ois, OutputStream os) {
        try {
            if (null != oos) {
                oos.close();
            }
            if (null != ois) {
                ois.close();
            }
            if (null != is) {
                is.close();
            }
            if (null != os) {
                os.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

class Node implements Comparable<Node> {
    Byte value;
    int weight;
    Node left;
    Node right;

    public Node(Byte value) {
        this.value = value;
    }

    public Node(Byte value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(@NotNull Node o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this.toString());
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
