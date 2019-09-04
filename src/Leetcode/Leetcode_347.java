package Leetcode;

import java.util.*;

//Given [1,1,1,2,2,3] and k = 2, return [1,2].
public class Leetcode_347 {

    public static void main(String[] args) {
        int[] nums = {
                1,1,1,2,2,3
        };
        ArrayList<Integer> res = (ArrayList<Integer>) topFrequent(nums, 2);
        for (Integer re : res) {
            System.out.println(re);
        }
    }

    private static List<Integer> topFrequent(int[] nums, int k) {
        List<Map.Entry<Integer,Integer>> lists = new ArrayList<>();
        List<Integer> result =new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        lists.addAll(map.entrySet());
        Collections.sort(lists,(o1,o2)->o2.getValue()-o1.getValue());
        for (int i = 0; i < k; i++) {
            result.add(lists.get(i).getKey());
        }
        return result;
    }

}
