package Leetcode.stack;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
    private List<Integer> data;

    public MyStack() {
        data = new ArrayList<>();
    }

    public MyStack(List<Integer> data) {
        this.data = data;
    }

    public void push(int x) {
        data.add(x);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int top() {
        return data.get(data.size() - 1);
    }

    public boolean pop() {
        if (isEmpty()) {
            return false;
        }
        data.remove(data.size() - 1);
        return true;
    }

    public static void main(String[] args) {
        MyStack myStack =new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        for (int i = 0; i < 4; i++) {
            if (!myStack.isEmpty()) {
                System.out.println(myStack.top());
            }
            System.out.println(myStack.pop());
        }
    }

}
