package Leetcode;

public class Leetcode_430 {
    Node prev =null;
    public Node flatten(Node head) {
        if(head ==null)
            return null;
        if(prev != null){
            prev.next =head;
            head.prev =prev;
        }
        prev =head;
        Node next =head.next;
        flatten(head.child);
        head.child =null;
        flatten(next);
        return head;
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {
    }

    public Node(int _val, Node _prev, Node _next, Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
}
