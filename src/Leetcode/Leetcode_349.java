package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result=new int[nums1.length > nums2.length ? nums2.length : nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            if(! map.containsKey(nums1[i]))
                map.put(nums1[i],1);
        }
        int k=0;
        for (int i = 0; i < nums2.length; i++) {
            if(map.containsKey(nums2[i])){
                result[k++]=nums2[i];
                map.remove(nums2[i]);
            }
        }
        int[] resultNum =new int[k];
        for (int i = 0; i < k; i++) {
            resultNum[i] =result[i];
        }
        return resultNum;

    }
}
