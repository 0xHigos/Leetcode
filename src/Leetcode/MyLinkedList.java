package Leetcode;

import java.lang.annotation.Target;

public class MyLinkedList {
    class Node{
        Node prev;
        Node next;
        int val;
        Node(int val){
            this.val=val;
        }
    }
    Node Head;
    Node Tail;
    int size;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        Head = new Node(-1);
        Tail =new Node(-1);
        size =0;
        Head.next =Tail;
        Tail.prev =Head;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        Node node = get(index, true);
        return node==null?-1:node.val;
    }

    public Node get(int index,boolean flag) {
        if(index <0 || index >size)
            return null;
        Node node;
        if(index > size >>>1) {
            node =Tail;
            for (int i = size; i >= size-index; i--) {
                node  =node.prev;
            }
        }else{
            node =Head;
            for (int i = 0; i <= index; i++) {
                node =node.next;
            }
        }
        return node;
    }
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size,val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index <0) index=0;
        if(index >size) return;
        Node node = new Node(val);
        if(index ==0){
            node.next=Head.next;
            Head.next.prev=node;
            Head.next=node;
            node.prev =Head;
        }else if(index ==size){
            Tail.prev.next=node;
            node.prev=Tail.prev;
            Tail.prev=node;
            node.next=Tail;
        }else{
            Node tmp = get(index, true);
            tmp.prev.next=node;
            node.prev=tmp.prev;
            node.next=tmp;
            tmp.prev=node;

        }
        size ++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index <0 || index >size)
            return;
        Node node = get(index, true);
        node.next.prev=node.prev;
        node.prev.next=node.next;
        size--;
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList=new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);
        System.out.println(myLinkedList.get(1));
        myLinkedList.deleteAtIndex(1);
        System.out.println(myLinkedList.get(1));
    }
}
