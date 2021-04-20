### 题目定义：

````java
给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。

你应当保留两个分区中每个节点的初始相对位置。

 

示例：

输入：head = 1->4->3->2->5->2, x = 3
输出：1->2->2->4->3->5

````



### 方法一：

````java
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null)
            return null;
        ListNode loHead = new ListNode(-1), hiHead = new ListNode(-1);
        ListNode loTail = loHead,hiTail = hiHead;
        while(head != null){
            if(head.val < x){
                loTail.next = head;
                loTail = head;
            }else{
                hiTail.next = head;
                hiTail = head;
            }
            ListNode temp = head;
            head = head.next;
            temp.next = null;
        }
        loTail.next = hiHead.next;
        return loHead.next;
    }
}
````





### 参考：

> https://leetcode-cn.com/problems/partition-list/
>



