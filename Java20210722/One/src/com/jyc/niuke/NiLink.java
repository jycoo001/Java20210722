package com.jyc.niuke;


import java.util.Calendar;

public class NiLink {

    /**
     * 重排链表
     * 将给定的单链表: L： L0→L1→…→L{n-1}→Ln
     * 重新排序为：L: L0→Ln →L1→L{n-1}→L2→L{n-2}→…L
     * @param head
     * @return
     */
    public void ReloadList(ListNode head) {
        if(head!=null) {
            ListNode listNode = get(head);
            head.next = listNode.next;
        }
    }

    public ListNode get(ListNode head) {
        ListNode listNode = save(head);
        ListNode listNode1 = ReverseList(head);
        int count = getCount(listNode);
        ListNode listNode2 = getAll(listNode,listNode1,count);
        return listNode2;
    }

    /**
     * 反转链表{1，2，3，4}--》{4，3，2，1}
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        ListNode listNode = null;
        while(head!=null) {
            ListNode listNode1 = head;
            head = head.next;
            listNode1.next = listNode;
            listNode = listNode1;
        }
        return listNode;
    }

    public ListNode getAll(ListNode listNode,ListNode listNode1,int count) {
        ListNode listNode2 = null;
        int num = count;
        count = count/2;

        while(count>0) {
            ListNode listNode3 = listNode;
            ListNode listNode4 = listNode1;
            listNode = listNode.next;
            listNode1 = listNode1.next;
            listNode4.next = listNode3;
            listNode3.next = listNode2;
            listNode2 = listNode4;
            count--;
        }
        if(num%2!=0) {
            ListNode listNode3 = listNode2;
            listNode2 = listNode1;
            listNode2.next = listNode3;
        }
        listNode2 = ReverseList(listNode2);
        return listNode2;
    }

    /**
     * 得到节点数
     * @param listNode
     * @return
     */
    public int getCount(ListNode listNode) {
        if(listNode.next!=null) {
            int count = getCount(listNode.next);
            return ++count;
        } else {
            return 1;
        }
    }

    /**
     * 保存正序序列
     * @param head
     * @return
     */
    public ListNode save(ListNode head) {
        ListNode listNode = new ListNode(head.val);
        if(head.next!=null) {
            listNode.next = save(head.next);
            return listNode;
        } else {
            return listNode;
        }
    }

    public static void main(String[] args) {
        //定义
        ListNode list = new ListNode(1);
        for (int i = 0; i < 10; i++) {
            ListNode listNode = new ListNode((int)(Math.random()*100));;
            listNode.next = list;
            list = listNode;
        }
        //输出一次
        ListNode listNode1 = list;
        while (listNode1!=null) {
            System.out.print(listNode1.val+" ");
            listNode1 = listNode1.next;
        }
        System.out.println();

        NiLink niLink = new NiLink();
        //反转链表
        long a = Calendar.getInstance().getTimeInMillis();
        niLink.ReloadList(list);
        while (list!=null) {
            System.out.print(list.val+" ");
            list = list.next;
        }
        long b = Calendar.getInstance().getTimeInMillis();
        System.out.println();
        System.out.println(b-a+"毫秒");
    }
}
