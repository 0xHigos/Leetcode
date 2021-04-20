### 题目定义：

````java
给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，
    水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。
    例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。

示例 1：
输入：[[1,1,0],[1,0,1],[0,0,0]]
输出：[[1,0,0],[0,1,0],[1,1,1]]
解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]

示例 2：
输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]

````

### 题目解析：

按照题意，可以分解成两步，一步是首先水平翻转，然后再进行逆序，方式一思路就是直接这么做，可以有优化的地方

### 方式一(暴力破解)：

````java
class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        int[][] ans = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            int left = 0, right = A[0].length - 1;
            int[] num = A[i];
            while (left < right) {
                num[left] = num[left] ^ num[right];
                num[right] = num[left] ^ num[right];
                num[left] = num[left] ^ num[right];
                left++;
                right--;
            }
            for (int j = 0; j < A[0].length; j++) {
                num[j] = num[j] == 0 ? 1: 0;
            }
            ans[i] = num;
        }
        return ans;
    }

}
````

### 思路改进：

假如需要翻转与逆序的左侧下标为：**left**,右侧下标为: **right**，当前翻转行数为 i 那么翻转与逆序的情况分为四种：

+ *情况一：* **A[i] [left]** = 0 ,**A[i] [right]** = 0,进行水平翻转后，**A[i] [left]** = 0 ,**A[i] [right]** = 0，再进行翻转：**A[i] [left]** = 1,**A[i] [right]** =  1
+ *情况二：***A[i] [left]** = 1 ,**A[i] [right]** = 1,进行水平翻转后，**A[i] [left]** = 1,**A[i] [right]** = 1，再进行翻转：**A[i] [left]** = 0,**A[i] [right]** =  0
+ *情况三：***A[i] [left]** = 0 ,**A[i] [right]** = 1,进行水平翻转后，**A[i] [left]** = 1,**A[i] [right]** = 0，再进行翻转：**A[i] [left]** = 0,**A[i] [right]** =  1
+ *情况四：***A[i] [left]** = 1,**A[i] [right]** = 0,进行水平翻转后，**A[i] [left]** = 0,**A[i] [right]** = 1，再进行翻转：**A[i] [left]** = 1,**A[i] [right]** =  0

情况一和情况二在进行了翻转和逆序后，结果刚好相反，之前为1 现在变为 0 ，之前为 0 现在变为1 

情况三和四 ，进行了翻转和逆序后，结果保持不变，

因此，可以遍历一次矩阵完成水平翻转和逆序，

遍历矩阵的每一行。对于矩阵的第 ii 行，初始化 left = 0 和 right = A.length - 1:

+ 当 left<right 时，判断 **A[i] [left]** 和 **A[i] [right]** 是否相等，如果相等则对 **A[i] [left]** 和 **A[i] [right]** 的值进行反转，如果不相等则不进行任何操作；

将 \left 的值加 1，将right 的值减 1，重复上述操作，直到 left > right

如果 n 是奇数，则上述操作结束时，若left 和 right 的值相等，都指向第 i 行的中间元素，此时需要对中间元素的值进行反转。



### 方式二(优化版)：

````java
class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        for(int i = 0; i < A.length; i++){
            int left = 0,right = A[0].length - 1;
            while(left  < right){
                if(A[i][left] == A[i][right]){
                    A[i][left] ^= 1;
                    A[i][right] ^= 1;
                }
                left ++;
                right --;
            }
            if(left == right){
                A[i][left] ^= 1;
            }
        }
        return A;
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/flipping-an-image/solution/fan-zhuan-tu-xiang-by-leetcode-solution-yljd/

