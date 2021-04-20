### 题目定义：

````java
给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。

如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。

示例 1：
[1,2,3,4]
[5,1,2,3]
[9,5,1,2]
输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
输出：true
解释：
在上述矩阵中, 其对角线为: 
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。 
各条对角线上的所有元素均相同, 因此答案是 True 。
   
示例 2：
[1,2]
[2,2]
输入：matrix = [[1,2],[2,2]]
输出：false
解释：
对角线 "[1, 2]" 上的元素不同。
    
````

### 题目解析：

一行一行开始比较，从第二行第二个数字开始，每个数字都与上一行的前一个数字进行比较，如果不相等则返回false

例如数组：

​    [1,2,3,4]
​	[5,**1**,2,3]
​    [9,5,1,2]  

​	从画粗的数字1开始，依次比较上一个数字

### 方式一：

````java
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++)
                if(matrix[i][j] != matrix[i-1][j-1])
                    return false;
        }
        return true;
    }
}
````

### 方式二：

````java
class Solution {
    public boolean isToeplitzMatrix(int[][] M) {
        if (M == null || M.length <= 1) return true;
        int m = M.length, n = M[0].length;

        // 上对角线
        for (int col = 0; col < n; col++) {
            int val = M[0][col];
            for (int i = 0, j = col; i < m && j < n; i++, j++) {
                if (M[i][j] != val) return false;
            }
        }
        // 下对角线
        for (int row = 0; row < m; row++) {
            int val = M[row][0];
            for (int i = row, j = 0; i < m && j < n; i++, j++) {
                if (M[i][j] != val) return false;
            }
        }
        return true;
    }
}
````

