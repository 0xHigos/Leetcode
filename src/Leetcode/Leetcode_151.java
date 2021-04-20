package Leetcode;

public class Leetcode_151 {
    public static String reverseWords(String s) {
        StringBuilder sb =new StringBuilder();
        int i=s.length()-1;
        while (i >=0){
            if(s.charAt(i) ==' '){
                i--;
                continue;
            }
            int end =i;
            while(i>=0 && s.charAt(i) !=' ')
                i--;
            sb.append(s.substring(i+1,end+1)).append(" ");

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
    }
}
