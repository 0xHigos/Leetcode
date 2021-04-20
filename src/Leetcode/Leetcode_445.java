package Leetcode;

import java.util.Stack;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
public class Leetcode_445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 =l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 =l2.next;
        }
        int add =0;
        int value1 =0;
        int value2=0;
        ListNode ago =null;
        ListNode node =null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                value1 =stack1.pop().val;
            }else
                value1 =0;
            if(!stack2.isEmpty())
                value2 =stack2.pop().val;
            else
                value2=0;
            int sum =value1 + value2 + add;
            node = new ListNode(sum % 10);
            add =sum /10;
            node.next =ago;
            ago =node;
        }
        if(add >0){
            node = new ListNode(add);
            node.next =ago;
            ago =node;
        }
        return ago;
    }
}
