package Leetcode;

public class Leetcode_238 {
    public int[] productExceptSelf(int[] nums) {
        if(nums.length==0)
            return null;
        int left=1,right=1;
        int result[]=new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if(i>0)
                left *=nums[i-1];
            result[i]=left;
        }
        for (int i = nums.length-1; i >0 ; i--) {
            result[i] *=right;
            right *=nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Leetcode_238 leetcode_238=new Leetcode_238();
        System.out.println(leetcode_238.productExceptSelf(new int[]{1, 2, 3, 4}));
    }
}
