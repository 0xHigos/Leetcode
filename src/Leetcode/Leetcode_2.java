package Leetcode;

public class Leetcode_2 {
    public  static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head =new ListNode(-1),cur =head,node;
        int val=0;
        while (l1 !=null && l2 !=null){
            val +=(l1.val +l2.val);
            node =new ListNode( val % 10);
            val /=10;
            cur.next=node;
            cur =node;
            l1 =l1.next;
            l2 =l2.next;
        }
        if(val ==0){
            if (l1 !=null) cur.next =l1;
            if(l2 !=null) cur.next =l2;
        }else{
            if(l1 !=null)
                while (l1 != null ){
                    if(val != 0) {
                        val += l1.val;
                        node = new ListNode(val % 10);
                        val /= 10;
                        cur.next = node;
                        cur =node;
                        l1 = l1.next;
                    }else{
                        cur.next =l1;
                        break;
                    }
                }
            else if(l2 !=null)
                while (l2 != null ){
                    if(val != 0) {
                        val += l2.val;
                        node = new ListNode(val % 10);
                        val /= 10;
                        cur.next = node;
                        l2 = l2.next;
                        cur =node;
                    }else{
                        cur.next =l2;
                        break;
                    }
                }
            if(val != 0){
                node =new ListNode(val);
                cur.next=node;
            }

        }
        return head.next;
    }

    public  static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if(l1 ==null)
            return l2;
        if(l2 == null)
            return l1;

        ListNode head = new ListNode(-1);
        ListNode node =head;
        int val=0;
        while(l1 != null || l2 != null){
            val +=(l1 ==null ?0 : l1.val) +
                    (l2 ==null ?0 :l2.val) +
                    val;
            node.next =new ListNode(val % 10);
            node =node.next;
            val /= 10;
            if(l1 != null) l1 =l1.next;
            if(l2 != null) l2 =l2.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 =new ListNode(9);
        ListNode node =new ListNode(8);
        l1.next=node;
        ListNode l2 =new ListNode(1);
        System.out.println(addTwoNumbers(l1, l2));
    }
}
