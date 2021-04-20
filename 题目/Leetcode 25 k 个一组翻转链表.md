### 题目定义：

````java
给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
k 是一个正整数，它的值小于或等于链表的长度。
如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

进阶：
    你可以设计一个只使用常数额外空间的算法来解决此问题吗？
    你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

````

![img](https://assets.leetcode.com/uploads/2020/10/03/reverse_ex1.jpg)

````java
输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
````

![img](https://assets.leetcode.com/uploads/2020/10/03/reverse_ex2.jpg)****

````java
输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
    
示例 3：
输入：head = [1,2,3,4,5], k = 1
输出：[1,2,3,4,5]
    
示例 4：
输入：head = [1], k = 1
输出：[1]
````

### 题目解析：

先考虑暴力破解。每次遍历到k个链表的时候，就使用头插法放在一个新的链表的后面

`dump`表示新链表的头节点；

`node`表示遍历到第k个链表时，前k个链表的节点，例如，假设链表为：1->2->3->4 假设已经遍历到了2 ,那么`node`会停在1这个节点上

`count`表示统计遍历的节点数

### 方式一(暴力破解)：

````java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dump = new ListNode(-1);
        ListNode node = head;
        ListNode temp = dump;
        int count = 1;
        while(head != null){
            if(count == k){
                int size = 0;
                while(size < k){
                   ListNode tail = temp.next;
                   temp.next = new ListNode(node.val);
                   temp.next.next = tail;
                   node = node.next;
                   size++;
                }
                while(temp.next != null)
                    temp = temp.next;
                node = head.next;
                count = 0;
            }
            count++;
            head = head.next;
        }
        if(node != null){
            temp.next = node;
        }
        return dump.next;
    }
}
````

空间复杂度为O(n) ,使用了一个全新的链表`dump`，并把所有的链表节点放在了`dump`中，时间复杂度为O(n * k),遍历需要O(n)次，满足条件时，每次都需要O(k)次使用头指针法

可以不需要额外的空间，完成指定的翻转

### 方式二(优化版)：

````java
class Solution {
   public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode tail = dummy;
        while(tail.next != null){
            for(int i = 0; i < k && tail != null; i++) tail = tail.next;
            if(tail == null) break;
            //保存需要端开子链表的分支节点
            ListNode start = pre.next; //表示需要翻转链表的开始节点
            ListNode next = tail.next; //表示需要翻转链表的结束节点，翻转的节点不包含该节点
            tail.next = null;

            //进行翻转
            ListNode subPre = null;
            ListNode cur = start;
            while(cur != null){
                ListNode subNext = cur.next;
                cur.next = subPre;
                subPre = cur;
                cur = subNext;
            }
            //再重新进行连接
            pre.next = subPre;
            start.next = next;
            pre = start;
            tail = pre;
        }
        return dummy.next;
    }
}
````



### 方式三(递归版)：

````java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;
        ListNode tail = head;
        for(int i = 0; i < k; i++){
            if(tail == null)
                return head;
            tail = tail.next;
        }
        ListNode newHead = reverse(head,tail);
        head.next = reverseKGroup(tail,k);
        return newHead;
    }

    private ListNode reverse(ListNode head,ListNode tail){
        ListNode pre = null;
        while(head != tail){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
````



### 参考：

> 

