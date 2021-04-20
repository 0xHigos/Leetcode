### 题目定义：

````java
假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。

给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。

 

示例 1：

输入：flowerbed = [1,0,0,0,1], n = 1
输出：true
示例 2：

输入：flowerbed = [1,0,0,0,1], n = 2
输出：false
 

提示：

1 <= flowerbed.length <= 2 * 104
flowerbed[i] 为 0 或 1
flowerbed 中不存在相邻的两朵花
0 <= n <= flowerbed.length
````



### 每次跳两格：

````java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0)
            return true;
        for(int i = 0; i<flowerbed.length; i+=2){
            if(flowerbed[i] == 0){
                if(i == flowerbed.length - 1 || flowerbed[i+1] == 0)
                    n--;
                else 
                    i++;
            }
        }
        return n <= 0;
    }
}
````



### 贪心算法：

````java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0)
            return true;
        int prev = -1;
        int count = 0;
        int m = flowerbed.length;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                if (prev == -1) {
                    count += i >> 1;
                } else {
                    count += ((i - prev) >> 1) - 1;
                }
                if (count >= n)
                    return true;
                prev = i;
            }
        }
        if (prev == -1)
            count += (m + 1) >> 1;
        else
            count += (m - prev - 1) >> 1;

        return count >= n;
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/can-place-flowers/solution/chong-hua-wen-ti-by-leetcode-solution-sojr/
>
> https://leetcode-cn.com/problems/can-place-flowers/solution/qi-si-miao-jie-by-heroding-h7m0/





