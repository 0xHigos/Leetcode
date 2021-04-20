### 题目定义：

````java
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
    例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

示例 1：
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 
    
示例 2：
输入：nums = [0,1,0,3,2,3]
输出：4
    
示例 3：
输入：nums = [7,7,7,7,7,7,7]
输出：1
````

### 题目解析：

首先使用动态规划的思想，**双重循环**，定义 数组dp[] 为动态规划的数组，`dp[i]` 为考虑前 `i`个元素，以第 `i` 个数字结尾的最长上升子序列的长度，注意 `nums[i]` 必须被选取。

外层的每次一次循环，都依次比较从`0`到 `i` 的所有数，是否小于当前值`num[i]`,假设比较的数为`num[j]`,如果小于，那么`num[i] =Math.max(1,dp[j] + 1)`,1 是最低值，`dp[j]` 标识`num[j]`的最长上升子序列

### 方式一(动态规划)：

````java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0)
            return 0;
        int[] dp = new int[nums.length + 1];
        int max = 1;
        dp[0] = 1;
        for(int i = 1; i < nums.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }
}
````



### 方式二(贪心 + 二分查找)：

````java
class Solution {
    //思路见官方题解
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0)
            return 0;
        int max = 1;
        int[] d = new int[nums.length + 1];
        d[1] = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > d[max]){
                d[++max] = nums[i];
            }else{
                int left = 1,right = max,pos = 0;
                while(left <= right){
                    int mid = (right - left) / 2 + left;
                    if(d[mid] < nums[i]){
                        pos = mid;
                        left = mid + 1;
                    }else{
                        right = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return max;
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/

