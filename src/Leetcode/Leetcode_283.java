package Leetcode;

public class Leetcode_283 {
    public static void moveZeroes(int[] nums) {
        int zero = 0;
        while (zero < nums.length) {
            while (zero < nums.length && nums[zero] != 0){
                zero++;
                continue;
            }
            for (int i = zero + 1; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[zero] = nums[i];
                    nums[i] = 0;
                    break;
                }
            }
        }
    }

    public static void moveZeroess(int[] nums) {
        int i=0,j=-1;
        while(j<nums.length-1){
            if(nums[++j]!=0)
                nums[i++]=nums[j];
        }
        for(int k=i;k<nums.length;k++)
            nums[k]=0;
    }

    public static void main(String[] args) {
        moveZeroess(new int[]{
                0,1,0,3,12
        });
    }
}
