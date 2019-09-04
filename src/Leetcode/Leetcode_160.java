package Leetcode;

import java.util.Stack;

public class Leetcode_160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA ==null || headB ==null)
            return null;
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();
        ListNode A =headA,B =headB;
        while (A != null) {
            stackA.push(A);
            A =A.next;
        }
        while (B !=null){
            stackB.push(B);
            B = B.next;
        }
        ListNode node ;
        if ((node=stackA.pop())== stackB.pop()) {
            while (stackA != null && stackB != null) {
                if(stackA.peek() != stackB.peek())
                    return node;
                else {
                    node =stackA.pop();
                    stackB.pop();
                }
            }
        }
        if(stackA ==null  && stackB ==null)
            return node;
        return null;
    }
}
