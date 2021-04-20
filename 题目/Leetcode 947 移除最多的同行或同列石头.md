### 题目定义：

````java
n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。

如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。

给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。


示例 1：
    输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
    输出：5
    解释：一种移除 5 块石头的方法如下所示：
    1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
    2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
    3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
    4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
    5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
    石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
    
    
示例 2：
    输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
    输出：3
    解释：一种移除 3 块石头的方法如下所示：
    1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
    2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
    3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
    石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
    
    
示例 3：
    输入：stones = [[0,0]]
    输出：0
    解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
````



### 方式一(深度优先遍历)：

````java
class Solution {
    public int removeStones(int[][] stones) {
        int length = stones.length;
        List<List<Integer>> edges = new ArrayList<>();
        for(int i = 0 ; i < length; i++){
            edges.add(new ArrayList<>());
            for(int j = 0; j < length; j++)
                if((stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) && i != j)
                    edges.get(i).add(j);
        }
        boolean[] visited = new boolean[length];
        int num = 0;
        for(int i = 0; i < length; i++){
            if(!visited[i]){
                num ++;
                dfs(edges,visited,i);
            }
        }
        return length - num;

    }
    private void dfs(List<List<Integer>> edges,boolean[] visited,int x){
        visited[x] = true;
        for(int y : edges.get(x)){
            if(!visited[y])
                dfs(edges,visited,y);
        }
    }
}
````



### 方式二(哈希表优化)：

````java
class Solution {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        List<List<Integer>> edges = new ArrayList<>();
        for(int i = 0; i < n; i++){
            edges.add(new ArrayList<>());
        }
        Map<Integer,List<Integer>> rec = new HashMap<Integer,List<Integer>>();
        for(int i = 0; i < n; i++){
            if(!rec.containsKey(stones[i][0]))
                rec.put(stones[i][0],new ArrayList<>());
            rec.get(stones[i][0]).add(i);
            if(!rec.containsKey(stones[i][1] + 10000))
                rec.put(stones[i][1] + 10000,new ArrayList<>());
            rec.get(stones[i][1] + 10000).add(i);
        }
        for(Map.Entry<Integer,List<Integer>> entry : rec.entrySet()){
            List<Integer> list = entry.getValue();
            int k = list.size();
            for(int i = 1; i < k; i++){
                edges.get(list.get(i-1)).add(list.get(i));
                edges.get(list.get(i)).add(list.get(i-1));
            }
        }
        boolean[] vis = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                num++;
                dfs(i, edges, vis);
            }
        }
        return n - num;
    }

    public void dfs(int x, List<List<Integer>> edge, boolean[] vis) {
        vis[x] = true;
        for (int y : edge.get(x)) {
            if (!vis[y]) {
                dfs(y, edge, vis);
            }
        }
    }
}
````



### 方式三(并查集)：

````java
class Solution {
    public int removeStones(int[][] stones) {
        UnionFind unionFind = new UnionFind(stones.length);
        for(int i = 0; i < stones.length; i++)
            for(int j = 0; j < stones.length; j++)
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1])
                    unionFind.union(i,j);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < stones.length; i++) 
            set.add(unionFind.find(i));
        return stones.length - set.size();
    }
}
class UnionFind{
    private int[] parent;

    UnionFind(int n){
        parent =new int[n];
        for(int i = 0; i < n; i++)
            parent[i] = i;
    }

    public int find(int index){
        if(parent[index] != index)
            parent[index] = find(parent[index]);
        return parent[index];
    }

    public void union(int index1,int index2){
        parent[find(index1)] = find(index2);
    }
}
````



### 方式四(另一种并查集)：

````java
class Solution {
    public int removeStones(int[][] stones) {
        UnionFind unionFind = new UnionFind();
        for(int[] stone : stones){
            unionFind.union(stone[0],stone[1] + 10001);
        }
        return stones.length - unionFind.getCount();
    }
}
class UnionFind{
    private Map<Integer,Integer> parent;
    private int count;

    UnionFind(){
        parent = new HashMap<>();
        count = 0;
    }
    int getCount(){
        return count;
    }

    int find(int index){
        if(!parent.containsKey(index)){
            parent.put(index,index);
            count ++;
        }
        if(index != parent.get(index)){
           parent.put(index,find(parent.get(index))); 
        }
        return parent.get(index);
    }

    void union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY)
            return;
        parent.put(rootX,rootY);
        count --;
    }
}
````

### 参考：

> https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/submissions/

