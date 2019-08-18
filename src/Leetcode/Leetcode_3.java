package Leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode_3 {
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> sets = new HashSet<>();
        int result=0;
        int i=0,j=0;
        while (i<s.length() && j<s.length()){
            if(!sets.contains(s.charAt(j))){
                sets.add(s.charAt(j++));
                result =Math.max(result,sets.size());
            }else {
                sets.remove(s.charAt(i++));
            }
        }
        return result;
    }
    //
    public static int lengthOfLongestSubstring2(String s) {
        if(s.length() ==0)
            return 0;
        Map<Character, Integer> map = new HashMap<>();
        int max =0;
        for (int i = 0,j=0; i <s.length(); i++) {
            if(map.containsKey(s.charAt(i)))
                j =Math.max(j,map.get(s.charAt(i))+1);
                //j=map.get(s.charAt(i))+1;
            map.put(s.charAt(i),i);
            max =Math.max(max,i-j+1);
        }
        return max;
    }
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2(new String("adbccba")));
    }
}
