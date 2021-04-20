### 题目定义：

````java
给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。

示例 1:
输入: [1,2,3]
输出: 6
    
示例 2:
输入: [1,2,3,4]
输出: 24
    
注意:
给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。


````



### 方式一(排序)：

````java
class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int result = nums[nums.length -1] * nums[nums.length -2] * nums[nums.length -3];
        if(nums[0] < 0 && nums[1] < 0){
            result  = Math.max(result,nums[0] * nums[1] * nums[nums.length - 1]);
        }
        return result;
    }
}
````



### 方式二(搜索)：

````java
/*
* 按照方式一，只需要知道 最小的两个数和最大的三个数，所以可以通过搜索得到这五个数
*/
class Solution {
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE,
        	min2 = Integer.MAX_VALUE - 1;
        int max1 = Integer.MIN_VALUE,
        	max2 = Integer.MIN_VALUE - 1,
        	max3 = Integer.MIN_VALUE;
        for(int num : nums){
            //获取最小的两个数
            if(num < min1){
                min2 = min1;
                min1 = num;
            }else if(num < min2){
                min2 = num;
            }

            //获取最大的三个数
            if(num > max1){
                max3  = max2;
                max2 = max1;
                max1 = num;
            }else if(num > max2){
                max3  = max2;
                max2 = num;
            }else if(num > max3){
                max3 = num;
            }    
        }
        return Math.max(max1 * max2 * max3,min1 * min2 * max1);
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/maximum-product-of-three-numbers/

