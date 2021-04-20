package Leetcode;


import java.util.List;

public class Leetcode_524 {
    public String findLongestWord(String s, List<String> d){
        String longestWord="";
        for (String s1 : d) {
            int l1 =longestWord.length(),l2=s1.length();
            if (l1 > l2 || (l1 == l2 && longestWord.compareTo(s1) <0)) {
                continue;
            }
            if (isSubstr(s, s1)) {
                longestWord = s1;
            }
        }
        return longestWord;
    }

    private boolean isSubstr(String s, String s1) {
        int i=0,j=0;
        while (i < s.length() && j < s1.length()) {
            if (s.charAt(i) == s1.charAt(j)) {
                j++;
            }
            i++;
        }
        return j ==s1.length();
    }

    public static void main(String[] args) {

    }
}
