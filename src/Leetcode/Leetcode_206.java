package Leetcode;

public class Leetcode_206 {
    public ListNode reverseList(ListNode head) {
        ListNode node =null;
        while (head != null) {
            ListNode next =head.next;
            head.next = node;
            node =head;
            head =next;
        }
        return  node;
        /*
        *   ListNode start = new ListNode(0);
            start.next=head;
            while (head != null) {
                ListNode node =head.next;
                ListNode cmp = start.next;
                start.next=head;
                head.next =cmp;
                head=node;
            }
            return start.next;
        *
        *
        * */
    }
}
