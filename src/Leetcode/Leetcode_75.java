package Leetcode;

public class Leetcode_75 {
    /*public static void sortColors(int[] nums) {
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
    }*/
    public static void sortColors(int[] nums) {
        int i = 0, j = 0, k = nums.length - 1;
        while (j <= k) {
            if (nums[j] == 0)
                swap(nums, i++, j++);
            else if (nums[j] == 2)
                swap(nums, j, k--);
            else j++;
        }
    }

    private static void swap(int[] nums, int i, int one) {
        int t = nums[i];
        nums[i] = nums[one];
        nums[one] = t;
    }

    public static void main(String[] args) {
        sortColors(new int[]{2, 0, 1});
    }


}
