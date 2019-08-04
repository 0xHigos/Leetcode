package Leetcode;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_139 {
    public boolean wordBreak(String s, Set<String> dict) {
        int maxWord = getMax(dict);
        int len =s.length();
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] =true;
        for (int i = 1; i <= len; i++) {
            int start = Math.max(1, i - maxWord);
            for (int j = start; j <=i; j++) {
                if (dp[j - 1] && dict.contains(s.substring(j - 1, i))) {
                    dp[i] =true;
                    break;
                }
            }
        }
        return dp[len];
    }

    public int getMax(Set<String> wordDict) {
        int  max =0;
        for (String s : wordDict) {
            max = Math.max(max, s.length());
        }
        return max;
    }
    public static void main(String[] args) {
        Leetcode_139 leetcode =new Leetcode_139();
        HashSet<String> s =new HashSet<>();
        s.add("apple");
        s.add("pen");
        System.out.println(leetcode.wordBreak("applepenapple", s));
    }


}
