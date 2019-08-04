package Leetcode;


/*
*    plus one
 * */
public class Leetcode_66 {
    public static int[] plusOne(int[] digits) {
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i] <9){
                digits[i]++;
                return digits;
            }
            digits[i]=0;
        }
        int[] newNumber =new int[digits.length+1];
        newNumber[0] =1;
        return newNumber;
    }

    public static void main(String[] args) {

        System.out.println();
    }
}
