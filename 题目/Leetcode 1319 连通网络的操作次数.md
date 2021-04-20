### 题目定义：

````java
用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。

网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。

给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。 
    
示例：
````

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/11/sample_1_1677.png)

````java
输入：n = 4, connections = [[0,1],[0,2],[1,2]]
输出：1
解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。

示例 2：
````

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/11/sample_2_1677.png)

````java
输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
输出：2
示例 3：

输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
输出：-1
解释：线缆数量不足。
示例 4：

输入：n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
输出：0

````





### 方式一(并查集)：

````java
class Solution {
    public int makeConnected(int n, int[][] connections) {
        if(n - connections.length > 1)
            return -1;
        UnionFind unionFind = new UnionFind(n);
        int num = 0;
        for(int i = 0; i < connections.length; i++){
            unionFind.union(connections[i][0],connections[i][1]);
        }
        while(unionFind.disConnectedCount() > 1){
            unionFind.union(unionFind.getCables().get(0), unionFind.getCables().get(1));
            num++;
        }
        return num;
    }
}

class UnionFind{
    private int[] parent;
    private int[] rank;
    private List<Integer> cables;

    UnionFind(int n){
        parent = new int[n];
        rank = new int[n];
        cables = new ArrayList<>();
        for(int i = 0; i < n; i++){
            parent[i] = i;
            rank[i] = 1;
            cables.add(i);
        }
    }

    void union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY){
            return;
        }
        if(rootX < rootY){
            rootX = rootX ^ rootY;
            rootY = rootY ^ rootX;
            rootX = rootX ^ rootY;
        }
        parent[rootY] = rootX;
        rank[rootX] += rank[rootY];
        cables.remove((Integer)rootY);
    }

    private int find(int index){
        if(parent[index] != index)
            parent[index] = find(parent[index]);
        return parent[index];
    }

    List<Integer> getCables(){
        return cables;
    }
    
    int disConnectedCount(){
        return cables.size();
    }
}
````



### 方式二(深度优先遍历)：

````java
class Solution {
    private List<Integer>[] edges;
    private boolean[] visited;
    public int makeConnected(int n, int[][] connections) {
        if(n - connections.length > 1)
            return -1;
        getEdges(n,connections);
        int ans = 0;
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                dfs(i);
                ans++;
            }
        }
        return ans - 1;
    }
    private void getEdges(int n,int[][] connections){
        edges = new List[n];
        visited = new boolean[n];
        for(int i = 0; i < n; i++)
            edges[i] = new ArrayList<>();
        for(int[] connection : connections){
            edges[connection[0]].add(connection[1]);
            edges[connection[1]].add(connection[0]);
        }
    }
    private void dfs(int x){
        visited[x] = true;
        for(int y : edges[x]){
            if(!visited[y])
                dfs(y);
        }
    }
}
````



### 方式三(优化版并查集)：

````java
/*
* 思路： 使用count保存连通分量的次数
*/
class Solution {
    public int makeConnected(int n, int[][] connections) {
        if(n - connections.length > 1)
            return -1;
        UnionFind unionFind = new UnionFind(n);
        for(int[] connection : connections)
            unionFind.union(connection[0],connection[1]);
        return unionFind.getCount() - 1;
    }
}
class UnionFind{
    private int[] parent;
    private int[] rank;
    private int count;

    UnionFind(int n){
        parent = new int[n];
        rank = new int[n];
        count = n;
        for(int i = 0; i < n; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }
    int find(int index){
        if(parent[index] != index)
            parent[index] = find(parent[index]);
        return parent[index];
    }

    void union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY)
            return;
        if(rootX < rootY){
            rootX = rootX ^ rootY;
            rootY = rootX ^ rootY;
            rootX = rootX ^ rootY;
        }
        parent[rootY] = rootX;
        rank[rootX] += rank[rootY];
        count --;
    }
    int getCount(){
        return count;
    }

}
````



### 参考：

> 

