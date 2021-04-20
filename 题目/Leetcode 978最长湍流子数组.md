### 题目定义：

````java
当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：

若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
返回 A 的最大湍流子数组的长度。
    
示例 1：
输入：[9,4,2,10,7,8,8,1,9]
输出：5
解释：(A[1] > A[2] < A[3] > A[4] < A[5])
    
示例 2：
输入：[4,8,12,16]
输出：2
    
示例 3：
输入：[100]
输出：1
 
````



### 方式一(滑动窗口)：

````java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int ret = 1;
        int left = 0,right = 0;
        while(right < n - 1){
            if(left == right){
                if(arr[left] == arr[left + 1])
                    left++;
                right++;
            }else{
                if((arr[right - 1] < arr[right] && arr[right] > arr[right + 1] ) ||
                 (arr[right -1] > arr[right] && arr[right] < arr[right + 1]))
                    right ++;
                else 
                    left  = right;
            }
            ret = Math.max(ret,right - left + 1);
        }
        return ret;
    }
}
````



### 方式二(动态规划)：

````java
/*
* 状态定义：
* increased[i]：以 arr[i] 结尾，并且 arr[i - 1] < arr[i] 的湍流子数组的长度；
* decreased[i]：以 arr[i] 结尾，并且 arr[i - 1] > arr[i] 的湍流子数组的长度。
*
* 状态转移方程：
* increased[i] = decreased[i - 1] + 1 if arr[i - 1] < arr[i] for i > 0；
* decreased[i] = increased[i - 1] + 1 if arr[i - 1] > arr[i] for i > 0。
*
* 初始化：只有一个元素的时候，湍流子数组的长度是 11；
* 输出：两个状态数组所有元素的最大值是最长湍流子数组的长度；
* 空间优化：空间优化：当前阶段状态只和上一个阶段状态有关，可以对状态数组进行重复利用。
* */
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        if(len < 2)
            return len;
        int[] increased = new int[len];
        int[] decreased = new int[len];
        increased[0] = 1;
        decreased[0] = 1;
        int res = 1;
        for(int i = 1; i < len; i++){
            if(arr[i - 1] < arr[i]){
                increased[i] = decreased[i - 1] + 1;
                decreased[i] = 1;
            }else if(arr[i - 1] > arr[i]){
                decreased[i] = increased[i - 1] + 1;
                increased[i] = 1;
            }else{
                increased[i] = 1;
                decreased[i] = 1;
            }
            res = Math.max(res,Math.max(increased[i],decreased[i]));
        }
        return res;
    }
}
````



### 方式三()：

````java

````



### 参考：

> 

