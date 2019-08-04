package Leetcode;


/*
* Reverse String
* */
public class Leetcode_344 {
    public void reverseString(char[] s) {
        for(int i=0,j=s.length-1;i<j;i++,j--){
            char temp =s[i];
            s[i]=s[j];
            s[j] =temp;
        }
    }

    public static void main(String[] args) {
        Leetcode_344 leetcode =new Leetcode_344();

    }
}
