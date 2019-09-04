package Leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leetcode_295 {
    public static void main(String[] args) {

    }
}
class MedianFinder {
    Queue<Integer> min = new PriorityQueue<>();
    Queue<Integer> max = new PriorityQueue<>((o1, o2) -> o2 - o1);
    /** initialize your data structure here. */
    public MedianFinder() {

    }

    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if(max.size()<min.size())
            max.offer(min.poll());

    }

    public double findMedian() {
        if(max.size() ==min.size())
            return (max.peek() +min.peek()) /2.0;
        else
            return max.peek();
    }
}

