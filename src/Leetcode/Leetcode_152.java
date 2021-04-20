package Leetcode;

public class Leetcode_152 {
    public int maxProduct(int[] nums) {
       int num=nums[0];
        for (int i = 1,max =num,min=num; i < nums.length; i++) {
            if(nums[i] <0){
                int temp =max;
                max=min;
                min=temp;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            num = Math.max(num, max);
        }
        return num;

    }
    public static void main(String[] args) {
        Leetcode_152 leetcode = new Leetcode_152();
        System.out.println(leetcode.maxProduct(new int[]{-2,0,-1}));
    }
}
