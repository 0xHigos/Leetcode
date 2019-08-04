package Leetcode;

public class Leetcode_189 {
    public static void rotate(int[] nums, int k) {
        for(int i=0;i<k;i++){
            int temp=nums[nums.length-1];
            for(int j=nums.length-1;j>=1;j--){
                nums[j]=nums[j-1];
            }
            nums[0]=temp;
        }
    }
    public static void rotate2(int[] nums, int k) {
        k %=nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums, 0, k - 1);
        reverse(nums,k,nums.length-1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end]=temp;
            start++;
            end--;
        }
    }
    public static void main(String[] args) {
        rotate2(new int[]{1,2,3,4,5,6,7},3);
    }
}
