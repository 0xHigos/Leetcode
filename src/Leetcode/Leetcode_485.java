package Leetcode;

public class Leetcode_485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int result =0,temp=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] == 1){
                temp ++;
                result = temp>result?temp:result;
            }else{
                temp=0;
            }
        }
        return result;
    }
}
