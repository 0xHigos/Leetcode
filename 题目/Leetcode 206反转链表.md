### 题目定义：

````java
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
    
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
````

### 方式一(迭代)：

````java
class Solution {
    private ListNode root = new ListNode(-1);
    private ListNode tail = root;
    public ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode next = reverseList(head.next);
        head.next = null;
        tail.next = head;
        tail = tail.next;
        head = next;
        return root.next;
    }
}
````



### 方式二(递归)：

````java
class Solution {
    private ListNode root = new ListNode(-1);
    private ListNode tail = root;
    public ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode next = reverseList(head.next);
        head.next = null;
        tail.next = head;
        tail = tail.next;
        head = next;
        return root.next;
    }
}
````

