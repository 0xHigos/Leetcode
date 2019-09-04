package Leetcode;

public class Leetcode_203 {
    public ListNode removeElement(ListNode head, int val) {
        if(head ==null)
            return null;
        ListNode slow=new ListNode(-1), fast = head;
        slow.next=head;
        ListNode node =slow;
        while (fast != null) {
            if (fast.val == val) {
                slow.next =fast.next;
            }else{
                slow =slow.next;
            }
            fast =fast.next;
        }
        return node.next;
    }
}
