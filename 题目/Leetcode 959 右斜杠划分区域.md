### 题目定义：

````java
在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。

（请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。

返回区域的数目。

示例 1：

输入：
[
  " /",
  "/ "
]
输出：2
解释：2x2 网格如下：
````

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/1.png)

````java
示例 2：

输入：
[
  " /",
  "  "
]
输出：1
解释：2x2 网格如下：
````

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/2.png)

````java
示例 3：

输入：
[
  "\\/",
  "/\\"
]
输出：4
解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
2x2 网格如下：
````

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/3.png)

````java
示例 4：

输入：
[
  "/\\",
  "\\/"
]
输出：5
解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
2x2 网格如下：
````

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/4.png)

```java
示例 5：
输入：
[
  "//",
  "/ "
]
输出：3
解释：2x2 网格如下：
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/5.png)



### 方式一(并查集)：

````java
class Solution {
    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        int size = 4 * N * N;
        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < N; i++) {
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < N; j++) {
                int index = 4 * (i * N + j);
                char c = row[j];
                //单元格内合并
                if (c == '/') {
                    unionFind.union(index, index + 3);
                    unionFind.union(index + 1, index + 2);
                } else if (c == '\\') {
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 2, index + 3);
                } else {
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 1, index + 2);
                    unionFind.union(index + 2, index + 3);
                }
                //单元格间合并
                // 向右合并：1（当前）、3（右一列）
                if (j + 1 < N) {
                    unionFind.union(index + 1, 4 * (i * N + j + 1) + 3);
                }
                //向下合并 2（当前) 0（下一列）
                if (i + 1 < N) {
                    unionFind.union(index + 2, 4 * ((i + 1) * N + j));
                }
            }
        }
        return unionFind.getCount();
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private int find(int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return parent[index];
    }

    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;
        if (rootX < rootY) {
            rootX = rootX ^ rootY;
            rootY = rootX ^ rootY;
            rootX = rootX ^ rootY;
        }
        parent[rootY] = rootX;
        rank[rootX] += rank[rootY];
        count--;
    }

    int getCount() {
        return count;
    }
}


````

### 参考：

> https://leetcode-cn.com/problems/regions-cut-by-slashes/

