package Leetcode;

public class Leetcode_142 {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast =head.next;
        ListNode slow =head;
        while(fast !=null && slow !=null && fast.next !=null){
            fast =fast.next.next;
            slow=slow.next;
            if(fast == slow)
                break;
        }
        slow =head;
        while (slow !=fast){
            fast =fast.next;
            slow =slow.next;
        }
        return slow;
    }
}
