package Leetcode;

import java.util.Stack;

public class Leetcode_227 {
    public static int calculate(String s) {
        int len,result=0;
        if(s ==null || (len =s.length()) ==0)
            return 0;
        Stack<Integer> stack = new Stack<>();
        int num =0;
        char sign = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if((!Character.isDigit(s.charAt(i))&& s.charAt(i)!=' ') || i ==len -1){
                if(sign =='+')
                    stack.push(num);
                else if(sign =='-')
                    stack.push(-num);
                else if(sign =='*')
                    stack.push(stack.pop() * num);
                else if(sign =='/')
                    stack.push(stack.pop() / num);
                sign =s.charAt(i);
                num =0;
            }
        }

        for (Integer integer : stack) {
            result +=integer;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(calculate("3+3*2"));
    }

}
