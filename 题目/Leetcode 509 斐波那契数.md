

### 题目定义：

````java
斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：

F(0) = 0，F(1) = 1
F(n) = F(n - 1) + F(n - 2)，其中 n > 1
给你 n ，请计算 F(n) 。

示例 1：

输入：2
输出：1
解释：F(2) = F(1) + F(0) = 1 + 0 = 1
示例 2：

输入：3
输出：2
解释：F(3) = F(2) + F(1) = 1 + 1 = 2
示例 3：

输入：4
输出：3
解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 
````



### 方式一：

````java
class Solution {
    public int fib(int n) {
        if(n <= 0)
            return 0;
        int[] F = new int[n + 1];
        F[0] = 0;
        F[1] = 1;
        for(int i = 2; i <= n; i++){
            F[i] =F[i-1] + F[i-2];
        }
        return F[n];
    }
}
````



### 方式二：

````java
class Solution {
    public int fib(int n) {
        if(n == 0)
            return 0;
        int first = 0;
        int second = 1;
        for(int i = 2; i <= n; ++i){
            int temp = first + second;
            first = second;
            second = temp;
        }
        return second;
    }
}
````



### 方式三(矩阵快速幂)：

````java
class Solution {
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n - 1);
        return res[0][0];
    }

    private int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    private int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
}

````

### 方式四(通项公式):

````java
class Solution {
    public int fib(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibN = Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n);
        return (int) Math.round(fibN / sqrt5);
    }
}

````

### 说明：

![image-20210104182308218](https://img2020.cnblogs.com/blog/1625166/202101/1625166-20210104182654909-181656360.png)



### 参考：



> https://leetcode-cn.com/problems/fibonacci-number/
>
> https://zhuanlan.zhihu.com/p/95902286
>
> https://www.cnblogs.com/cmmdc/p/6936196.html

