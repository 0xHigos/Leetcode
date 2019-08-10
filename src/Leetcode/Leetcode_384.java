package Leetcode;

import java.util.Random;

/*
* 打乱数组
打乱一个没有重复元素的数组。

示例:

// 以数字集合 1, 2 和 3 初始化数组。
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
solution.shuffle();

// 重设数组到它的初始状态[1,2,3]。
solution.reset();

// 随机返回数组[1,2,3]打乱后的结果。
solution.shuffle();
* */
public class Leetcode_384 {
    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1, 2, 3});
        for (int num : solution.shuffle()) {
            System.out.println(num);
        }
    }
}
class Solution{
    private int[] nums;

    public Solution(int[] nums){
        this.nums=nums;
    }

    public int[] reset(){
        return nums;
    }

    public int[] shuffle(){
        int[] rand=new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int r = (int) (Math.random() * (i + 1));
            rand[i]=rand[r];
            rand[r]=nums[i];
        }
        return rand;
    }

    public int[] shuffle2(){
        Random r=new Random();
        int[] rand=nums.clone();
        int n = nums.length;
        while (n > 1) {
            n--;
            int k=r.nextInt(n+1);
            int value=rand[k];
            rand[k]=rand[n];
            rand[n]=value;
        }
        return rand;
    }
}
