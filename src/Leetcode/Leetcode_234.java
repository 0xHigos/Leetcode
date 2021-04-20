package Leetcode;

import java.util.Stack;

public class Leetcode_234 {
    public boolean isPalindrome(ListNode head) {
        /*ListNode temp =head;
        Stack<Integer>  stack = new Stack();
        while (temp != null) {
            stack.push(temp.val);
            temp =temp.next;
        }
        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head =head.next;
        }
        return true;*/

        ListNode fast =head ,slow =head;
        while (fast != null && fast.next != null) {
            fast  =fast.next.next;
            slow =slow.next;
        }
        if(fast !=null)
            slow = slow.next;

        slow = reverse(slow);
        fast =head;
        while (slow != null) {
            if(fast.val != slow.val)
                return false;
            fast =fast.next;
            slow =slow.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev =null;
        while (head != null) {
            ListNode next =head.next;
            head.next = prev;
            prev =head;
            head =next;
        }
        return prev;
    }
}
