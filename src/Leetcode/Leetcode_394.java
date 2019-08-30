package Leetcode;

import java.util.Stack;

public class Leetcode_394 {
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder sb =new StringBuilder();
        int index =0;
        for (char c : s.toCharArray()) {
            if(Character.isDigit(c))
                index = index * 10 + c - '0';
            else if(c =='['){
                numStack.push(index);
                stack.push(sb);
                sb =new StringBuilder();
                index =0;
            }else if(c==']'){
                StringBuilder cur =sb;
                sb=stack.pop();
                for (int i = numStack.pop(); i >0 ; i--) {
                    sb.append(cur);
                }
            }else
                sb.append(c);
        }
        return sb.toString();
    }
    /*
    *
    *   Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if ( ch == '[') {
                intStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k) cur.append(tmp);
            } else cur.append(ch);
        }
        return cur.toString();
        *
        * */
}
