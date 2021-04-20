package Leetcode;

import java.util.Arrays;

public class Leetcode_724 {
    public int pivotIndex(int[] nums) {
        int total =0,sum =0;
        for (int num : nums) {
            total+=num;
        }
        for (int i = 0; i < nums.length; sum+=nums[i++]) {
            if(sum << 1 ==total -nums[i])
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Leetcode_724 leetcode =new Leetcode_724();
        System.out.println(leetcode.pivotIndex(new int[]{
                1, 2, 3
        }));
    }
}
