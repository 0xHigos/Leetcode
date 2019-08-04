package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Given [1,1,1,2,2,3] and k = 2, return [1,2].
public class Leetcode_347 {

    public static void main(String[] args) {
        int[] nums = {
                1,1,1,2,2,2,3,3,3,4,4,4,4
        };
        ArrayList<Integer> res = (ArrayList<Integer>) topFrequentOne(nums, 2);
        for (Integer re : res) {
            System.out.println(re);
        }
    }

    private static List<Integer> topFrequentOne(int[] nums, int k) {
        List<Integer>[] buckets = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        for (Integer integer : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(integer);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(integer);
        }
        List<Integer> res = new ArrayList<>();
        for (int pos = buckets.length - 1; pos > 0 && res.size() < k; pos--) {
            if (buckets[pos] != null) {
                if(buckets[pos].size() <= (k - res.size())) {
                    res.addAll(buckets[pos]);
                }else{
                    res.addAll(buckets[pos].subList(0, k - res.size()));
                }
            } }
        return res;
    }


    public static List<Integer> topFrequentTwo(int[] nums, int k) {
        Map<Integer, Integer> frequencyForNum = new HashMap<>();
        for (int num : nums) {
            frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (Integer integer : frequencyForNum.keySet()) {
            int frequency = frequencyForNum.get(integer);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(integer);
        }
        List<Integer> topK = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
            if (buckets[i] == null) {
                continue;
            }else if(buckets[i].size() <= (k - topK.size())) {
                topK.addAll(buckets[i]);
            }else{
                topK.addAll(buckets[i].subList(0, k - topK.size()));
            }
        }
        return topK;
    }

}
