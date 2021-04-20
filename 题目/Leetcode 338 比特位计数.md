### 题目定义：

````java
给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

示例 1:
输入: 2
输出: [0,1,1]

示例 2:
输入: 5
输出: [0,1,1,2,1,2]
````

### 题目解析：

首先想到了暴力破解，既每个二进制位置都和 该位置的1 进行比较

### 方式一(暴力破解)：

````java
class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for(int i = 0; i <= num; i++){
            ans[i] = count(i);
        }
        return ans;
    }
    //还有一种方式 是通过 x = x&(x - 1)，该运算符会将二进制表示的最后一个1变成了0，
    //因此，对x重复该操作，直到x变成了0，那么操作次数就是x的 比特数
    private int count(int num){
        int ans = 0;
        int bit = 1;
        while(num > 0){
            if((num & bit) == 1)
                ans++;
            num >>>= 1;
        }
        return ans;
    }
    
    /*
    private int count(int num){
        int ans = 0;
        while(num > 0){
             num &= (num - 1);
            ans ++;
        }
        return ans;
    }
    
    */
}
````

### 方式二(进行奇偶性判断)：

````java
class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for(int i = 0; i <= num; i++){
            if(i % 2 == 0)
                ans[i] = ans[i / 2];
            else ans[i] = ans[i - 1] + 1;
        }
        return ans;
    }
}
````

### 方式二思路分析：

对于数字而言，只有两类：**奇数**，**偶数**

**奇数**：二进制表示中 奇数 一定会比前一位的偶数 多一个 1

````text
0:0000 1:0001

2:0010 3:0011

4:0100 5:0101
````

比较 0 1, 2 3，4 5 这三组数字，1比0最后一位多了一个1 ，3比2最后一位多了一个1  ，5比3最后一位多了一个1  

**偶数**：二进制表示中，偶数`a`中 1 的个数 一定和当前偶数的一半` a/ 2`相同 既 num[a] = num[a / 2] 

````text
2:0010 4:0100 8:1000
3:0011 6:0110 12:1110
````

原因在于  `a / 2 + a / 2` 无论`a/2`是奇数还是偶数1的个数保持不变，只是所有的1向前移动一位

### 方式三(最高有效位)：

````java
class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        int bits  = 0;
        for(int i = 1; i <= num; i++){
            if((i & (i - 1)) == 0){
                bits = i;
            }
            ans[i] = ans[i - bits] + 1;
        }
        return ans;
    }
}
````

### 方式四(最低有效位)：

````java
class Solution {
    //思路和方式2相同
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for(int i = 1; i <= num; i++){
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
}
````

### 方式5(最低设置位)：

````java
class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for(int i = 1; i <= num; i++){
            ans[i] = ans[(i & (i - 1))] + 1;
        }
        return ans;
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode-solution-0t1i/

