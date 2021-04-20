### 题目定义：

````java
给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
示例 1:
    输入: [1,2,3,4,5,6,7] 和 k = 3
    输出: [5,6,7,1,2,3,4]
解释:
    向右旋转 1 步: [7,1,2,3,4,5,6]
    向右旋转 2 步: [6,7,1,2,3,4,5]
    向右旋转 3 步: [5,6,7,1,2,3,4]

示例 2:
    输入: [-1,-100,3,99] 和 k = 2
    输出: [3,99,-1,-100]

解释: 
    向右旋转 1 步: [99,-1,-100,3]
    向右旋转 2 步: [3,99,-1,-100]

说明:
尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
要求使用空间复杂度为 O(1) 的 原地 算法。

````



### 方式一(三次旋转)：

````java
class Solution {
    public void rotate(int[] nums, int k) {
        k = nums.length - (k  % nums.length);
        rotateHelper(nums,0,k - 1);
        rotateHelper(nums,k ,nums.length - 1);
        rotateHelper(nums,0,nums.length - 1);
    }
    private void rotateHelper(int[] nums,int left,int right){
        while(left <= right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left ++;
            right --;
        }
    }
}
````



### 方式二(按题意直接for-each)：

````java
class Solution {
    public void rotate(int[] nums, int k) {
        k =  k % nums.length;
        for(int i = 0; i < k; i++){
            int temp = nums[nums.length - 1];
            for(int j = nums.length - 1; j > 0; j--){
                nums[j] = nums[j - 1]; 
            }
            nums[0] = temp;
        }
    }
}
````



### 方式三(环状替换)：

````java
/*
	思路：请参考leetcode的下面链接
*/
class Solution {
    public void rotate(int[] nums, int k) {
        k =  k % nums.length;
        int count = gcd(k,nums.length);
        for(int start = 0; start < count; start ++){
            int current = start;
            int pre = nums[current];
            do{
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                current = next;
            }while(start != current);
        }
    }

    private int gcd(int x, int y){
        return y > 0 ? gcd(y, x % y) : x;
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/

