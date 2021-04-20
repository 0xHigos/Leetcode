package Leetcode.stack;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(5);
        s.push(13);
        s.push(8);
        s.push(6);
        if (s.empty() == true) {
            System.out.println("Stack is empty");
            return;
        }
        s.pop();
        System.out.println("The top element is:" + s.peek());
        System.out.println("The size is:" + s.size());
    }
}
