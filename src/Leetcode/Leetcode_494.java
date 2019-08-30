package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_494 {
    private Integer count =0;
    public int findTargetSumWays(int[] nums, int S) {
       helper(nums,S,0,0);
       return count;
    }

    private void helper(int[] nums, int S, int sum, int pos) {
        if(pos == nums.length){
            if(sum ==S)
                count ++;
            return;
        }
        int curNum =nums[pos];
        helper(nums, S, sum + curNum, pos + 1);
        helper(nums, S, sum - curNum, pos + 1);
    }

    public int findTargetSumWays2(int[] nums, int S) {
        int sum =0;
        for (int num : nums) {
            sum +=num;
        }
        return sum < S || ((S + sum) & 1) > 0 ? 0 : subsetSum(nums, (S + sum) >>> 1);
    }

    private int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0]=1;
        for (int num : nums) {
            for (int i = s; i >=num ; i--) {
                dp[i] +=dp[i-num];
            }
        }
        return dp[s];
    }

    public static void main(String[] args) {
        Leetcode_494 leetcode =new Leetcode_494();
        System.out.println(leetcode.findTargetSumWays2(new int[]{1,2,3,4,5}, 3));

    }
}
