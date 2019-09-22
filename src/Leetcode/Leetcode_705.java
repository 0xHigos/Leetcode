package Leetcode;

import java.util.Arrays;

/*
不使用任何内建的哈希表库设计一个哈希集合

具体地说，你的设计应该包含以下的功能

add(value)：向哈希集合中插入一个值。
contains(value) ：返回哈希集合中是否存在这个值。
remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。

MyHashSet hashSet = new MyHashSet();
hashSet.add(1);
hashSet.add(2);
hashSet.contains(1);    // 返回 true
hashSet.contains(3);    // 返回 false (未找到)
hashSet.add(2);
hashSet.contains(2);    // 返回 true
hashSet.remove(2);
hashSet.contains(2);    // 返回  false (已经被删除)

*/
public class Leetcode_705 {

}
class MyHashSet {
    int[] arr =null;
    /** Initialize your data structure here. */
    public MyHashSet() {
        arr =new int[100001];
        Arrays.fill(arr,-1);
    }

    public void add(int key) {
        arr[key] =key;
    }

    public void remove(int key) {
        arr[key] =-1;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        if(arr[key] != key)
            return false;
        return true;
    }
}

