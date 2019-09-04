package Leetcode;

import java.util.Stack;

public class Leetcode_19 {
    public ListNode removeNThFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        start.next=head;
        ListNode slow =start,fast =start;
        for (int i = 0; i <= n; i++) {
            fast =fast.next;
        }
        while (fast != null) {
            slow =slow.next;
            fast =fast.next;
        }
        slow.next =slow.next.next;
        return start.next;
    }
}
