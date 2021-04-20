### 题目定义：

````java
给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。

 示例 1：
````

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/06/21/ex1.png)

````java
输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
输出：[[0,1],[2,3,4,5]]
解释：上图描述了给定图。
下图是所有的最小生成树。
````

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/06/21/msts.png)

````java
注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。

示例 2 ：
````

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/06/21/ex2.png)



````java
输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
输出：[[],[0,1,2,3]]
解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
````



### 方式一(kruskal)：

````java
class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int length = edges.length;
        int[][] newEdges = new int[length][4];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 3; j++)
                newEdges[i][j] = edges[i][j];
            newEdges[i][3] = i;
        }
        Arrays.sort(newEdges, Comparator.comparingInt((int[] a) -> a[2]));
        //统计最小连接树的value
        UnionFind unionFind = new UnionFind(n);
        int value = 0;
        for (int i = 0; i < length; i++) {
            if (!unionFind.union(newEdges[i][0], newEdges[i][1]))
                value += newEdges[i][2];
        }
        List<List<Integer>> ans = new ArrayList<List<Integer>>() {{
            add(new ArrayList<>());
            add(new ArrayList<>());
        }};
        //循环判断当前边是否是关键边 或伪关键边
        //边有三种(关键边，伪关键边，非关键边)
        for (int i = 0; i < length; i++) {
            UnionFind uf = new UnionFind(n);
            int v = 0;
            //循环判断是否是关键边
            for (int j = 0; j < length; j++) {
                if (i != j && !uf.union(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }
            //并查集不连通 或 得出的最小树权值大于统计最小连接树的value,则说明是关键边
            if (uf.getCount() != 1 || v > value) {
                ans.get(0).add(newEdges[i][3]);
                continue;
            }
            /*
            * 伪关键边：可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
            * 也就是说，我们可以在计算最小生成树的过程中，最先考虑这条边，即最先将这条边的两个端点在并查集中合并。
            * 设最终得到的最小生成树权值为 v，如果 v = value，那么这条边就是伪关键边
            * */
            //所以需要首先将那条边放入
            uf = new UnionFind(n);
            uf.union(newEdges[i][0],newEdges[i][1]);
            v = newEdges[i][2];
            for (int j = 0; j < length; j++) {
                if(i != j && !uf.union(newEdges[j][0],newEdges[j][1]))
                    v +=newEdges[j][2];
            }
            if(v == value){
                ans.get(1).add(newEdges[i][3]);
            }
        }
        return ans;
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count;

    UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private int find(int index) {
        if (parent[index] != index)
            parent[index] = find(parent[index]);
        return parent[index];
    }

    boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return true;
        if (rootX < rootY) {
            rootX = rootX ^ rootY;
            rootY = rootX ^ rootY;
            rootX = rootX ^ rootY;
        }
        rank[rootX] += rank[rootY];
        parent[rootY] = rootX;
        count--;
        return false;
    }

    public int getCount() {
        return count;
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/solution/zhao-dao-zui-xiao-sheng-cheng-shu-li-de-gu57q/

