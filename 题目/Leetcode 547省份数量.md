

### 题目定义：

````java
有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。

省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
返回矩阵中 省份 的数量。

示例 1：
       ① - ②
         ③
    输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
    输出：2  
    
示例 2：
        ①   ②
         ③
    输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
	输出：3
    
 提示：
    1 <= n <= 200
    n == isConnected.length
    n == isConnected[i].length
    isConnected[i][j] 为 1 或 0
    isConnected[i][i] == 1
    isConnected[i][j] == isConnected[j][i]
````



### 方式一(广度优先遍历)：

````java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int ans = 0;
        boolean[] ratios = new boolean[isConnected.length];
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0 ; i < isConnected.length; i++){
            if(ratios[i])
                continue;
            ans++;
            queue.offer(i);
            while(!queue.isEmpty()){
                int cur = queue.poll();
                ratios[cur] = true;
                for(int j = 0 ; j < isConnected.length ; j++){
                    if(j == cur)
                        continue;
                    if(isConnected[cur][j] == 1 && !ratios[j]){
                        queue.offer(j);
                    }
                }
            }
        }
        return ans;
    }
}
````



### 方式二(深度优先遍历)：

````java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int ans = 0;
        boolean[] visited = new boolean[isConnected.length];
        for(int i = 0; i < isConnected.length; i++){
            if(!visited[i]){
                dfs(isConnected,visited,i);
                ans ++;
            }
        }
        return ans;
    }
    private void dfs(int[][] isConnected,boolean[] visited,int city){
        for(int i = 0; i < isConnected.length; i++){
            if(isConnected[city][i] == 1 && !visited[i]){
                visited[i] = true;
                dfs(isConnected,visited,i);
            }
        }
    }
}
````



### 方式三()：

````java

````



### 参考：

> 

