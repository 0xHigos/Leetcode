package Leetcode;

public class Leetcode_75 {
    public static void sortColors(int[] nums) {
        int zero = -1, one = 0, two = nums.length;
        while (one < two) {
            if (nums[one] == 0) {
                swap(nums, ++zero, one++);
            } else if (nums[one] == 2) {
                swap(nums, --two, one);
            }else{
                ++one;
            }
        }
    }

    private static void swap(int[] nums, int i, int one) {
        int t = nums[i];
        nums[i] = nums[one];
        nums[one]=t;
    }


}
