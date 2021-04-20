package Leetcode;

import java.util.Arrays;

public class Leetcode_279 {
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            while (i - j * j >= 0) {
                min = Math.min(min, dp[i - j * j] + 1);
                ++j;
            }
            dp[i] = min;
        }
        return dp[n];
    }

    //method two
    public static int numSquares2(int n) {
        while (n % 4 == 0) n /= 4;
        if (n % 8 == 7) return 4;
        int a = 0;
        while ((a * a) <= n) {
            int b = (int) Math.pow(n - a * a, 0.5);
            if (a * a + b * b == n)
                if (a != 0 && b != 0)
                    return 2;
                else
                    return 1;
            a++;
        }
        return 3;
    }

    public static void main(String[] args) {
        System.out.println(numSquares2(7));
    }
}
