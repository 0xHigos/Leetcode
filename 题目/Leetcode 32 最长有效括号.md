### 题目定义：

````java
给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

 

示例 1：

输入：s = "(()"
输出：2
解释：最长有效括号子串是 "()"
示例 2：

输入：s = ")()())"
输出：4
解释：最长有效括号子串是 "()()"
示例 3：

输入：s = ""
输出：0

````

### 方式一(使用栈)：

````java
class Solution {
    public int longestValidParentheses(String s) {
        int max = 0;
        char[] ch = s.toCharArray();
        Deque<Integer> stack = new LinkedList<>();
        //防止边界值
        stack.push(-1);
        for(int i = 0; i < ch.length; i++){
            if(ch[i] =='('){
                stack.push(i);
            }else{
                stack.pop();
                if(!stack.isEmpty()){
                    max = Math.max(max, i - stack.peek());
                }else{
                    stack.push(i);
                }
            }
        }
        return max;
    }
}
````

### 思路解析：

对于第i个位置，这个位置的元素s[i] 只有两种可能：

+ s[i] =='(': s[i] 无法和其他之前的元素组成有效的括号对，所以dp[i] = 0
+ s[i] ==')': 这时需要判断和之前的元素是否是有效的括号对：
+ - s[i -1] = '(':s[i] 和s[i -1] 组成一对有效括号对，有效括号对新增长度为2，当前位置有效括号长度为其之前2个位置的最长括号长度加上当前位置新增的2，也即：**dp[i] = dp[i -2] + 2**
  - s[i -1] ==')'，若s[i] 前面组成过有效的括号对，也即组成了"((....))"这样的字符，就要求s[i -1] 位置必然是有效的括号对，那么，就需要找到和s[i] 配对的位置，并判断其是否是'('即可
  - 和当前位置配对的位置是：i - dp[i -1] - 1
  - + 若s[i - dp[i - 1] - 1] =='(':那么有效括号长度加2， 当前位置(i)最长有效括号长度 是： i -1位置的最长有效括号长度 + 当前位置新增 2.： **dp[i] = dp[i - 1] + 2**
    + 如果在i -dp[i - 1] - 1之前已经形成了有效括号长度，那么结果还需要加上 i - dp[i -1] - 1之前的长度，考虑 字符数组："() (())" ,i-dp[i - 1] - 1 和 i组成了有效括号序列，这是独立的括号序列，如果之前还形成了括号序列，那么当前位置还需要加上这一段 ： 所以：**dp[i] = dp[i -1] + 2 + dp[ i - dp[i-1] -2]**

### 方式二(动态规划)：

````java
class Solution {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) ==')'){
                if(s.charAt(i - 1) =='('){
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }else if( i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '('){
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans,dp[i]);
            }
        }
        return maxans;
    }
}
````



### 方法三思路解析：

​	在此方法中，我们利用两个计数器 *left* 和 \textit{right}*right* 。首先，我们从左到右遍历字符串 ，对于每遇到的"(",left++,每遇到一个")",right++, 每当 left == right,就计算当前有效字符串的长度，并且记录下来，当right > left时， 同时将left和right置为0

​	这样的做法贪心地考虑了以当前字符下标结尾的有效括号长度，每次当右括号数量多于左括号数量的时候之前的字符我们都扔掉不再考虑

​	但这样会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 `(()` ，这种时候最长有效括号是求不出来的 所以需要重新从右向左遍历一次，遍历的判断条件正好相反：

+ 当left > right 时，left = right = 0
+ 当left == right 时，计算当前的有效字符串长度，并且记录目前为止找到的最长子字符串

### 方式三(贪心算法)：

````java
class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0,maxans = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) =='(')
                left++;
            else 
                right++;
            if(left == right)
                maxans = Math.max(maxans,2 * left);
            else if(right > left)
                left = right = 0;
        }
        left = right = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            if(s.charAt(i) =='(')
                left++;
            else 
                right++;
            if(left == right)
                maxans = Math.max(maxans,2 * left);
            else if(right < left)
                left = right = 0;
        }
        return maxans;
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/

