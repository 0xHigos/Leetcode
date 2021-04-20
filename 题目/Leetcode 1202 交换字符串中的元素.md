### 题目定义：

````java
给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。

你可以 任意多次交换 在 pairs 中任意一对索引处的字符。

返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。

 

示例 1:
输入：s = "dcab", pairs = [[0,3],[1,2]]
输出："bacd"
解释： 
    交换 s[0] 和 s[3], s = "bcad"
    交换 s[1] 和 s[2], s = "bacd"
    
    
示例 2：
输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
输出："abcd"
解释：
    交换 s[0] 和 s[3], s = "bcad"
    交换 s[0] 和 s[2], s = "acbd"
    交换 s[1] 和 s[2], s = "abcd"
    
    
示例 3：
输入：s = "cba", pairs = [[0,1],[1,2]]
输出："abc"
解释：
    交换 s[0] 和 s[1], s = "bca"
    交换 s[1] 和 s[2], s = "bac"
    交换 s[0] 和 s[1], s = "abc"
 

提示：

    1 <= s.length <= 10^5
    0 <= pairs.length <= 10^5
    0 <= pairs[i][0], pairs[i][1] < s.length
    s 中只含有小写英文字母
	
````



### 方式一(并查集)：

````java
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if(pairs.size() == 0)
            return s;
        int len =s.length();
        UnionFind unionFind = new UnionFind(len);
        for(List<Integer> pair : pairs){
            int key = pair.get(0);
            int value = pair.get(1);
            unionFind.union(key,value);
        }
        char[] charArray = s.toCharArray();
        Map<Integer,PriorityQueue<Character>> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            int x = unionFind.find(i);
            if(map.containsKey(x))
                map.get(x).offer(charArray[i]);
            else 
                map.computeIfAbsent(x,key->new PriorityQueue<>()).offer(charArray[i]);
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < len; i++){
            int x = unionFind.find(i);
            builder.append(map.get(x).poll());
        }
        return builder.toString();
    }
    private class UnionFind{
        int[] parent;
        int[] rank;

        UnionFind(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
                rank[i] = 1;
            }
        }

        int find(int key){
            if(key != parent[key])
                parent[key] = find(parent[key]);
            return parent[key];
        }

        void union(int key,int value){
            int rootX = find(key);
            int rootY = find(value);
            if(rootX == rootY)
                return;
            if(rank[rootX] == rank[rootY]){
                parent[rootX] = rootY;
                rank[rootY] ++;
            }else if(rank[rootX] < rank[rootY]){
                parent[rootX] = rootY;
            }else
                parent[rootY] = rootX;
        }
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/smallest-string-with-swaps/solution/1202-jiao-huan-zi-fu-chuan-zhong-de-yuan-wgab/

