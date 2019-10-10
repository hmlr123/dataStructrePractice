package com.hmlr123.hashTable;

import java.util.Scanner;

/**
 * Hash表
 *
 * @author liwei
 * @date 2019/10/6 16:28
 */
public class HashTableDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashTab hashTab = new HashTab();
        String key = "";
        boolean loop = true;
        int id;
        String name;
        while (loop) {
            System.out.println("add: 添加新员工");
            System.out.println("list: 显示员工");
            System.out.println("find: 查找");
            System.out.println("del: 删除");
            System.out.println("exit: 退出");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id：");
                    id = scanner.nextInt();
                    System.out.println("请输入name:");
                    name = scanner.next();
                    hashTab.add(new Emp(id, name));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入id:");
                    id = scanner.nextInt();
                    hashTab.find(id);
                    break;
                case "del":
                    System.out.println("请输入id:");
                    id = scanner.nextInt();
                    hashTab.del(id);
                    break;
                case "exit":
                    scanner.close();
                    System.out.println("退出程序！");
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp() {
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class HashTab {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTab() {
        this(16);
    }

    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int hashFun = hashFun(emp.id);
        empLinkedLists[hashFun].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            System.out.printf("第 %d hash表：\t", i);
            empLinkedLists[i].list();
            System.out.println();
        }
    }

    public Emp find(int id) {
        int hashFun = hashFun(id);
        Emp emp = empLinkedLists[hashFun].find(id);
        System.out.printf("第 %d hash表：\t", hashFun);
        if (null == emp) {
            System.out.printf("id为%d的员工不存在!\n", id);
        } else {
            System.out.printf(">>> id = %d, name = %s \n", emp.id, emp.name);
        }
        return emp;
    }

    public void del(int id) {
        int hashFun = hashFun(id);
        System.out.printf("第 %d hash表：\t", hashFun);
        boolean del = empLinkedLists[hashFun].del(id);
        if (del) {
            System.out.printf("id为 %d 删除成功！\n", id);
        } else {
            System.out.printf("id为 %d 删除失败！\n", id);
        }
    }

    public void update(Emp emp) {
        int hashFun = hashFun(emp.id);
        empLinkedLists[hashFun].update(emp);
    }

    public int hashFun(int id) {
        return id % size;
    }
}

class EmpLinkedList {

    private Emp head;


    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        Emp temp = head;
        while (temp != null) {
            temp = temp.next;
        }
        temp.next = emp;
    }

    public void list() {
        if (head == null) {
            return;
        }
        Emp temp = head;
        while (temp != null) {
            System.out.printf(">>> id = %d, name = %s \t", temp.id, temp.name);
            temp = temp.next;
        }
    }

    public Emp find(int id) {
        if (head == null) {
            return null;
        }
        Emp temp = head;
        while (true) {
            if (temp.id == id) {
                return temp;
            }
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean del(int id) {
        if (head == null) {
            return false;
        }
        Emp proEmp = head;
        Emp curEmp = head.next;
        if (proEmp.id == id) {
            head = curEmp;
            return true;
        }

        while (curEmp != null) {
            if (curEmp.id == id) {
                proEmp = curEmp.next;
                return true;
            }
            proEmp = curEmp;
            curEmp = curEmp.next;
        }
        return false;
    }

    public boolean update(Emp emp) {
        if (head == null) {
            return false;
        }
        Emp proEmp = head;
        Emp curEmp = head.next;
        if (proEmp.id == emp.id) {
            head.next = emp;
            emp.next = curEmp;
            return true;
        }

        while (curEmp != null) {
            if (curEmp.id == emp.id) {
                proEmp.next = emp;
                emp.next = curEmp;
                return true;
            }
            proEmp = curEmp;
            curEmp = curEmp.next;
        }
        return false;
    }
}
