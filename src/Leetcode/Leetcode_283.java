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
        int index =0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=0)
                nums[index++] =nums[i];
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] =0;
        }
    }

    public static void main(String[] args) {
        moveZeroess(new int[]{
                0,1,0,3,12
        });
    }
}
