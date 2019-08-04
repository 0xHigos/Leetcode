package Leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> s = new ArrayList<>();
        helper(result, s, nums, 0, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> s,
                        int[] nums, int pos, int iter) {
        result.add(new ArrayList<>(s));
        if (pos == nums.length) {
            return;
        }
        for (int i = iter; i < nums.length; i++) {
            if (s.contains(nums[i])) {
                continue;
            }
            s.add(nums[i]);
            helper(result, s, nums, pos + 1, i);
            s.remove(s.size() - 1);
        }

    }

    public void helper2(List<Integer> s, int[] nums, int[] visited, int pos) {
        if (pos == nums.length) {
            List<Integer> list = new ArrayList<>(s);

        }
    }

}
