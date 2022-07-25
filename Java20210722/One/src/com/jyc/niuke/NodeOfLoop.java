package com.jyc.niuke;

import java.util.Scanner;

/**
 * NC3 链表中环的入口结点
 */
public class NodeOfLoop {
    /**
     * 输入描述：
     * 输入分为2段，第一段是入环前的链表部分，第二段是链表环的部分，后台会根据第二段是否为空将这两段组装成一个无环或者有环单链表
     * 返回值描述：
     * 返回链表的环的入口结点即可，我们后台程序会打印这个结点对应的结点值；若没有，则返回对应编程语言的空结点即可。
     * @param pHead
     * @return
     */
    public boolean hasCycle(ListNode pHead) {
        if(pHead.next==null) {
            return false;
        }
        ListNode listNode = pHead;
        int count=0;
        while (listNode.next!=null) {
            if(count>10000) {
                return true;
            }
            count++;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] token = str.split("},");
        String link1 = token[0].substring(1);
        String link2 = token[1].substring(1,token[1].length()-1);
        String[] node1 = link1.split(",");
        String[] node2 = link2.split(",");
        int i = 0;
        ListNode list = null;
        while(i<node1.length) {
            ListNode listNode = new ListNode(Integer.valueOf(node1[i]));
            listNode.next = list;
            list = listNode;
            i++;
        }


    }
}
