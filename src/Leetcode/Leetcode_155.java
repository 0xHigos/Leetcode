package Leetcode;

import java.util.Stack;

//最小栈
public class Leetcode_155 {

}

class MinStack {
    Stack<Integer> min = new Stack<>();

    Stack<Integer> stack =new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (min.isEmpty() || x >= min.peek()) {
            min.push(x);
        }
    }

    public void pop() {
        if (stack.pop().equals(min.peek())) {
            min.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
class MinStack2{
    private Node head;

    public void push(int x) {
        if(head ==null)
            head =new Node(x,x);
        else
            head = new Node(x, Math.min(x, head.min), head);
    }

    public void pop() {
        if(head ==null)
            return;
        head  =head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        public Node(int val,int min) {
            this(val,min, null);
        }
        public Node(int val,int min,Node next){
            this.val =val;
            this.min =min;
            this.next=next;
        }
    }
}
