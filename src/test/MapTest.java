package test;

import java.util.Scanner;

public class MapTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n  =scanner.nextInt();scanner.nextLine();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] =scanner.nextInt();
        }
        int i=0,j=n-1;
        int  result =0;
        int k =0;
        while (i <= j) {
            if(nums[i]>nums[j]){
                result +=nums[i];

            }else{
                result += nums[j];
            }
            i++;j--;
        }
        System.out.println(result);
    }
}
