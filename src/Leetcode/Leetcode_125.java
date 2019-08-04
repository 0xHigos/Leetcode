package Leetcode;

public class Leetcode_125 {
    public static boolean isPalindrome(String s) {
        s=s.toLowerCase();
        char[] list =new char[s.length()];
        int index=0;
        for(int i=0;i<s.length();i++){
            if(Character.isDigit(s.charAt(i))|| Character.isLowerCase(s.charAt(i))){
                list[index++]=s.charAt(i);
            }
        }
        for(int i=0,j=index-1;i<j;i++,j--){
            if(list[i] !=list[j])
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
}
