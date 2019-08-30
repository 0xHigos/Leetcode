package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode_225 {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();
    int size =0;

    public void push(int x) {
        queue1.offer(x);
        size ++;
    }

    public int pop() {
        int i=0;
        while (i++ < size - 1) {
            queue2.offer(queue1.poll());
        }
        int result =queue1.poll();
        while (!queue2.isEmpty())
            queue1.offer(queue2.poll());
        queue2 =new LinkedList<>();
        size --;
        return result;
    }

    public int top() {
        int j =0;
        while (j++ < size - 1) {
            queue2.offer(queue1.poll());
        }
        int result =queue1.poll();
        while (!queue2.isEmpty())
            queue1.offer(queue2.poll());
        queue1.offer(result);
        queue2 =new LinkedList<>();
        return result;
    }

    public boolean empty() {
        return queue1.isEmpty();
    }

    public static void main(String[] args) {
        Leetcode_225 leetcode =new Leetcode_225();
        leetcode.push(1);
        leetcode.push(2);
        System.out.println(leetcode.top());
        System.out.println(leetcode.pop());
        System.out.println(leetcode.empty());
    }
}
