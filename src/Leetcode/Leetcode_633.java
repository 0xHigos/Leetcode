package Leetcode;

public class Leetcode_633 {
    public static boolean judgeSquareSum(int x) {
        int i =0,j= (int) Math.sqrt(x);
        while (i < j) {
            int sum= i*i +j*j;
            if(sum == x)
                return true;
            else if (sum >x)
                j--;
            else
                i++;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(judgeSquareSum(122));
    }
}
