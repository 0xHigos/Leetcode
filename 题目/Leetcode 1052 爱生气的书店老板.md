### 题目定义：

````java
今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 

示例：
输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
输出：16
解释：
书店老板在最后 3 分钟保持冷静。
感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.

````

### 思路解析：

这是一道求最大值的问题，暴力破解法，是通过找到最大子数组的下标，然后重新累加，有很多提上空间

### 方式一(暴力破解法)：

````java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int ans = 0;
        int max = 0;
        int left = 0;
        for(int i = 0; i <= customers.length - X; i++){
            int j = 0,temp = 0;
            while(j < X){
                if(grumpy[i + j] == 1){
                    temp +=customers[i+j];
                }
                j++;
            }
            if(max < temp){
                max = temp;
                left = i;
            }
        }
        for(int i = 0; i < X ;i++)
            grumpy[left++] = 0;
        for(int i = 0; i < customers.length; i++){
            if(grumpy[i] == 0)
                ans += customers[i];
        }

        return ans;
    }
}
````

### 思路解析二：

先收集：可以先将原本就满意的客户加入答案，同时将对应的 customers[i] 变为 0。

再统计最大值：之后的问题转化为：在 customers 中找到连续一段长度为 x 的子数组，使得其总和最大。这部分就是我们应用技巧所得到的客户。

**在 customers 中找到连续一段长度为 x 的子数组**,提示我们用滑动窗口计算

### 方式二(滑动窗口)：

````java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int ans = 0,max = 0,cur = 0;
        int left = 0,right = 0; 
        for(int i = 0; i < customers.length; i++){
           if(grumpy[i] == 0){
               ans += customers[i];
               customers[i] = 0;
           }
        }
        while(right < customers.length){
            cur += customers[right];
            if(right - left + 1 > X){
                cur -= customers[left];
                left++;
            }
            max = Math.max(max,cur);
            right ++;
        }
        return ans + max;
    }
}
````

### 思路解析三：

如果不允许改变原始数组，则在思路二的前提下使用乘法计算

### 方式三(使用乘法的滑动窗口)：

````java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int ans = 0;
        for(int i = 0; i < customers.length; i++){
           if(grumpy[i] == 0){
               ans += customers[i];
           }
        }
        int left = 0,right = 0,cur =0,max = 0;
        while(right < customers.length){
            cur += customers[right] * grumpy[right];
            if(right  - left + 1 > X){
                cur -= customers[left] * grumpy[left];
                left++;
            }
            max = Math.max(max,cur);
            right++;
        }
        return ans + max;
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/grumpy-bookstore-owner/solution/hua-dong-chuang-kou-luo-ti-by-ac_oier-nunu/

