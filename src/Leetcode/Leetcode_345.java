package Leetcode;

import java.util.Arrays;
import java.util.HashSet;

public class Leetcode_345 {
    private final static HashSet<Character> vowels =new HashSet<>(Arrays.asList(
            'a','e','i','o','u','A','E','I','O','U'
    ));
    public static String reverseVowels(String s){
        int i =0,j=s.length()-1;
        char[] result = new char[s.length()];
        while (i<= j){
            char ci =s.charAt(i);
            char cj =s.charAt(j);
            if (!vowels.contains(ci)) {
                result[i++]=ci;
            }
            else if (!vowels.contains(cj)) {
                result[j--]=cj;
            }
            else{
             result[i++] =cj;
             result[j--] =ci;
            }
        }
        return new String(result);
    }
    public static String reverseVowels2(String s) {
        String vowel ="aeiouAEIOU";
        char[] result =new char[s.length()];
        int i=0,j=s.length()-1;
        while(i<=j){
            char ci =s.charAt(i);
            char cj =s.charAt(j);
            if (!vowel.contains(Character.toString(s.charAt(i))))
                result[i++]=ci;
            else if (!vowel.contains(Character.toString(s.charAt(j))))
                result[j--]=cj;
            else {
                result[i++]=cj;
                result[j--]=ci;
            }
        }
        return new String(result);
    }
    public static void main(String[] args) {
        System.out.println(reverseVowels2("hello"));
        System.out.println(reverseVowels("Leetcode"));
        System.out.println(reverseVowels("aeiou"));
    }
}
