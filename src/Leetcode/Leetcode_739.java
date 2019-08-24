package Leetcode;

import java.util.Stack;

public class Leetcode_739 {
    public int[] dailyTemperatures(int[] T) {
        int[] res=new int[T.length];
        res[T.length-1] =0;
        for (int i = T.length - 2; i >= 0; i--) {
            for (int j = i+1; j <T.length; j++) {
                if(T[i] <T[j]){
                    res[i]=j-i;
                    break;
                }
            }
        }
        return res;
    }

    public int[] dailyTemperatures2(int[] temperatures) {
        int[] stack = new int[temperatures.length];
        int top=-1;
        int[] ret = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (top > -1 && temperatures[i] > temperatures[top]) {
                int index = stack[top--];
                ret[index] =i-index;
            }
            stack[++top] = i;
        }
        return ret;
    }

    public int[] dailyTemperatures3(int[] temperatues) {
        Stack<Integer> stack =new Stack<>();
        int[] ret = new int[temperatues.length];
        for (int i = 0; i < temperatues.length; i++) {
            while (!stack.isEmpty() && temperatues[i] > stack.peek()) {
                int index =stack.pop();
                ret[index] =i -index;
            }
            stack.push(i);
        }
        return ret;
    }
}

// 73, 74, 75, 71, 69, 72, 76, 73