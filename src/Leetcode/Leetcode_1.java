package Leetcode;

import java.util.HashMap;
import java.util.Map;
/*
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:

    给定 nums = [2, 7, 11, 15], target = 9

    因为 nums[0] + nums[1] = 2 + 7 = 9

    所以返回 [0, 1]
* */
public class Leetcode_1 {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                result[0] =i;
                result[1] =map.get(nums[i]);
                break;
            }else{
                map.put(target - nums[i], i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] sum = twoSum(nums, 9);
        for (int i : sum) {
            System.out.println(i);
        }
    }
}
