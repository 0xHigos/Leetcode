### 题目定义：

````java
给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，
该数组能否变成一个非递减数列。
我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，
总满足 nums[i] <= nums[i + 1]。

示例 1:
输入: nums = [4,2,3]
输出: true
解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
    
    
示例 2:
输入: nums = [4,2,1]
输出: false
解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
````

### 思路：

* 思路： 定义两个指针 right：指向当前需要遍历的元素; pre:指向前一个已经遍历的元素
* 数据类型考虑三种情况 : [4,2,3],[1,4,2,3],[3,4,2,3]  且都存在 nums[pre] < nums[right]

  - 情况一：当数据类型为[4,2,3]，且 pre 为**左侧边界**， 将 nums[pre] 设为 nums[right] 数组变为[2,2,3]

  - 情况二：当数据类型为[1,4,2,3]，且 **nums[right] > nums[pre - 1]** 既 2 > 1.将 nums[pre] 设为 nums[right] 数组变为1,2,2,3]
  - 情况三：当数据类型为[3,4,2,3]，且**nums[right] < nums[pre - 1]** 既 2 < 1.将 nums[right] 设为 nums[ pre - 1] 数组变为[3,4,4,3]
* 本质就是在遍历过程中，存在不递增情况下，使得相邻三个元素有序，主要分两种情况，[1,4,2]应该变为[1,2,2] ; [3,4,2]应该变为[3,4,4]

### 方式一：

````java
class Solution {
    public boolean checkPossibility(int[] nums) {
        int changeCount = 1;
        int pre = 0,right = 1;
        while(right < nums.length){
            if(nums[pre] > nums[right]){
                if(changeCount == 0)
                    return false;
                if(pre == 0 || nums[right] >= nums[pre - 1])
                    nums[pre] = nums[right];
                else 
                    nums[right] = nums[pre];
                changeCount--;
            }
            pre = right;
            right++;
            
        }
        return true;
    }
}
````



### 优化：

​	情况一与情况二可以**只统计出现的次数，而不进行交换值**，出现超过1次时则返回 flase

### 方式二：

````java
class Solution {
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for(int i = 0; i < nums.length - 1;i++){
            int cur = nums[i],next = nums[i + 1];
            if(cur > next){
                if(count++ > 0)
                    return false;
                if(i > 0 && next < nums[i - 1])
                    nums[i + 1] = cur;
            }
        }
        return true;
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/non-decreasing-array/solution/fei-di-jian-shu-lie-by-leetcode-solution-zdsm/

