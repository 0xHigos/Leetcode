package Leetcode;

import java.util.Stack;

public class Leetcode_150 {
    public int evalRPN(String[] tokens){
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            Integer c = null;
            try {
                c = Integer.valueOf(tokens[i]);
                stack.push(c);
            } catch (NumberFormatException e) {
                int sum = calculate(stack.pop(), stack.pop(), tokens[i].charAt(0));
                stack.push(sum);
            }
        }
        return stack.pop();
    }

    public int calculate(int a, int b, char c) {
        if(c =='+')
            return b+a;
        else if(c =='-')
            return  b-a;
        else if(c == '/')
            return b/a;
        else
            return b*a;
    }

    public static void main(String[] args) {
        Integer c = Integer.valueOf("1002");
        System.out.println(c);
    }


}
