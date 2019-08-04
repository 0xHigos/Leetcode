package Leetcode;

public class Leetcode_28 {
    public static int strStr(String haystack, String needle) {
        for (int i = 0;; i++) {
            for (int j = 0; ; j++) {
                if(j ==needle.length()) return  i;
                if(i+j ==haystack.length()) return 0;
                if(needle.charAt(j) !=haystack.charAt(i+j))break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(strStr("hello", "lll"));
    }
}
