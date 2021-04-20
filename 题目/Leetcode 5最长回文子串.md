### 题目定义：

````java
给你一个字符串 s，找到 s 中最长的回文子串。

示例 1：
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
    
示例 2：
输入：s = "cbbd"
输出："bb"
    
示例 3：
输入：s = "a"
输出："a"
    
示例 4：
输入：s = "ac"
输出："a"

````

### 题目解析：

首先尝试使用暴力破解，时间复杂度为O(n^3)

### 方式一(暴力破解)：

````java
class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len < 2)
            return s;
        char[] ch = s.toCharArray();
        int left = 0,max = 0;
        for(int i = 0; i < len; i++){
            for(int j = len - 1; j >= i; j--){
                if(isPalindrome(ch,i,j) && max <(j - i + 1)){
                    max = j - i + 1;
                    left = i;
                }
            }
        }
        return s.substring(left,max + left);
        
    }
    private boolean isPalindrome(char[] ch,int left,int right){
        if(left > right || ch.length == 0){
            return false;
        }
        while(left < right){
            if(ch[left] != ch[right])
                return false;
            left++;
            right--;
        }
        return true;
    }
}
````

使用动态规划，时间复杂度O(N^2)

### 方式二(动态规划)：

````java
class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len < 2)
            return s;
        boolean[][] dp = new boolean[len][len];
        char[] ch = s.toCharArray();
        int start = 0,max = 1;
        for (boolean[] b : dp) {
            Arrays.fill(b, true);
        }
        for( int j = 1; j < len; j++){
            for(int i = 0; i < j; i++){
                dp[i][j] = (ch[i] == ch[j]) && dp[i + 1][j - 1];
                if(dp[i][j] && max < (j - i + 1)){
                    max = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start,start + max);
    }
}
````

使用动态规划，时间复杂度O(N)

### 方式三(manacher算法)：

````java
class Solution {
    public String longestPalindrome(String s) {
        int len  = s.length();
        if(len < 2)
            return s;
        String str = addBoundaries(s,'#');
        int sLen = 2* len + 1;
        int[] p = new int[sLen];
        int maxRight = 0,center = 0;
        int start = 0,maxLen = 1;
        for(int i = 0; i < sLen; i++){
            if(i < maxRight){
                int mirror = 2 * center - i;
                p[i] = Math.min(maxRight - i, p[mirror]);
            }
            int left = i -(p[i] + 1);
            int right = i + (p[i] + 1);
            while (left >= 0 && right < sLen && str.charAt(left) == str.charAt(right)) {
                p[i]++;
                left--;
                right++;
            }
            if (i + p[i] > maxRight) {
                maxRight = i + p[i];
                center = i;
            }
            if (p[i] > maxLen) {
                maxLen = p[i];
                start = (i - maxLen) / 2;
            }
        }
        return s.substring(start, start + maxLen);

    }
    private String addBoundaries(String s, char divide) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        if (s.indexOf(divide) != -1) {
            throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(divide);
            stringBuilder.append(s.charAt(i));
        }
        stringBuilder.append(divide);
        return stringBuilder.toString();
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/

