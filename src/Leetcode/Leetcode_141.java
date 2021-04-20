package Leetcode;

//Description: 判断链表是否存在环

import designs.ListNode;

public class Leetcode_141 {
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return  false;
        }
        ListNode l1=head ,l2=head.next;
        while (l1 != null && l2 != null && l2.next != null) {
            if (l1 == l2) {
                return true;
            }
            l1 =l1.next;
            l2 =l2.next.next;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
