package Leetcode;

public class Leetcode_557 {
    public static String reverseWords(String s) {
        StringBuilder sb =new StringBuilder();
        int i=0;
        int j;
        while(i<=s.length()-1){
            if(s.charAt(i) ==' '){
                i++;
                continue;
            }
            int start =i;
            while(i<=s.length()-1 && s.charAt(i) !=' ')
                i++;
            j=i;
            while(j>start)
                sb.append(s.charAt(--j));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public static String reverseWords2(String s) {
        String[] sList =s.split(" ");
        for (int i = 0; i < s.length(); i++) {
            sList[i] =new StringBuilder(sList[i]).reverse().toString();
        }
        StringBuilder sb = new StringBuilder();
        for (String s1 : sList) {
            sb.append(s1+" ");
        }
        return sb.toString().trim();
    }
    public static void reverse(String  s){

    }

    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest"));

    }
}
