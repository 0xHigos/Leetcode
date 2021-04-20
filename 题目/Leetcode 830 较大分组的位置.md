

### 题目定义：

````java
在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。

例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。

分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。

我们称所有包含大于或等于三个连续字符的分组为 较大分组 。

找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。

示例 1：

输入：s = "abbxxxxzzy"
输出：[[3,6]]
解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
示例 2：

输入：s = "abc"
输出：[]
解释："a","b" 和 "c" 均不是符合要求的较大分组。
示例 3：

输入：s = "abcdddeeeeaabbbcd"
输出：[[3,5],[6,9],[12,14]]
解释：较大分组为 "ddd", "eeee" 和 "bbb"
示例 4：

输入：s = "aba"
输出：[]
 
提示：
1 <= s.length <= 1000
s 仅含小写英文字母

````



### 方式一(普通遍历)：

````java
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> largeGroupPositions(String s) {
        String[] s1 = s.split("");
        int count = 1;
        for(int i = 1; i < s1.length; i++){
            if(s1[i].equals(s1[i-1])){
                count ++;
            }else{
                addToList(count,i);
                count = 1;
            }
        }
        if(count > 1)
            addToList(count,s1.length);
        return result;
    }
    
    private void addToList(int count,int length){
        if(count >= 3){
            List<Integer> temp = new ArrayList<>();
            temp.add(length - count);
            temp.add(length - 1);
            result.add(temp);
        }
    }
}
````



### 方式二(双指针)：

````java
class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ans = new ArrayList<>();
        if(s == null)
            return ans;
        int left = 0,count = 0;
        while(left < s.length()){
            while(left + count < s.length() && s.charAt(left) == s.charAt(left + count))
                count ++;
            if(count >= 3)
                ans.add(Arrays.asList(left,left + count - 1));
            left += count;
            count = 0;
        }
        return ans;
    }
}
````



### 方式三(正则)：

````javascript
//js 语言
var largeGroupPositions = function(s) {
    let g = s.matchAll(/([a-z])\1{2,}/g), r = [], t
    while (t = g.next().value) r.push([t.index, t.index + t[0].length - 1])
    return r
};
````



### 参考：

> https://leetcode-cn.com/problems/positions-of-large-groups/solution/zheng-ze-wei-zhan-3jie-fa-chao-100-by-ma-r0vy/
>
> https://leetcode-cn.com/problems/positions-of-large-groups/

