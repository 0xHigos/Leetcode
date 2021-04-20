package Leetcode;

public class Leetcode_334 {
    //题目理解错误，这个方法是用来求是否有连续的是长度为3的递增子序列
    //例如：num2[1]=1,num2[2]=4,nums[3]=8;下标应该是连续的。
    public boolean increasingTriplet2(int[] nums) {
        if(nums.length <3)
            return false;
        int left=0,right=2;
        int mid;
        while (right < nums.length) {
            mid=(left+right) >>>1;
            if(nums[right]>nums[mid]){
                if(nums[mid]>nums[left])
                    return true;
                else{
                    right++;left++;
                }
            }else{
                if(right ==nums.length-2)
                    return false;
                left=right;
                right+=2;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Leetcode_334 leetcode_334=new Leetcode_334();
        System.out.println(leetcode_334.increasingTriplet(new int[]{2,1,5,0,4,6}));
    }

    private boolean increasingTriplet(int[] nums) {
        int smallest = Integer.MAX_VALUE, smaller = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= smallest) smallest = num;
            else if (num <= smaller) smaller = num;
            else return true;
        }
        return false;
    }
}
