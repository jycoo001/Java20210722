package com.jyc.niuke;


public class NiLink {
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

    /**
     * 重排链表
     * 将给定的单链表: L： L0→L1→…→L{n-1}→Ln
     * 重新排序为：L0→Ln →L1→L{n-1}→L2→L{n-2}→…L
     * @param head
     * @return
     */
    public ListNode ReloadList(ListNode head) {
        ListNode listNode = null;
        return listNode;
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        ListNode list2 = new ListNode(2);
        ListNode listNode = new ListNode(3);
        ListNode list3 = new ListNode(4);
        list.next = list2;
        list2.next = listNode;
        listNode.next = list3;
        ListNode listNode1 = list;
        while (listNode1!=null) {
            System.out.print(listNode1.val+" ");
            listNode1 = listNode1.next;
        }
        System.out.println();
        NiLink niLink = new NiLink();
        list = niLink.ReverseList(list);
        ListNode listNode2 = list;
        while (listNode2!=null) {
            System.out.print(listNode2.val+" ");
            listNode2 = listNode2.next;
        }

        ListNode listNode3 = null;
        listNode3 = niLink.ReverseList(listNode3);
        listNode2 = listNode3;
        while (listNode2!=null) {
            System.out.print(listNode2.val+" ");
            listNode2 = listNode2.next;
        }
    }
}
