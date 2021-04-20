

### 题目定义：

````java
给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。

对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。

返回这些数字之和。题目数据保证答案是一个 32 位 整数。

示例 1：
                           1
                          / \
                         0   1
                        /\   /\
                       0  1 0  1
输入：root = [1,0,1,0,1,0,1]
输出：22
解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22

示例 2：
输入：root = [0]
输出：0
    
示例 3：
输入：root = [1]
输出：1
    
示例 4：
输入：root = [1,1]
输出：3
  
提示：
	树中的结点数介于 1 和 1000 之间。
	Node.val 为 0 或 1 。
````

### 递归方式：

````java
class Solution {
    List<String> list = new ArrayList<>();
    public int sumRootToLeaf(TreeNode root) {
        if(root == null)
            return 0;
        dfs(root,"");
        return getSumByList();
    }
     private void dfs(TreeNode root,String value){
        if(root != null){
            value += root.val;
            if(root.left == null && root.right == null)
                list.add(value);
            if(root.left != null)
                dfs(root.left,value);
            if(root.right != null)
                dfs(root.right,value);
        }
    }
    private int getSumByList(){
        int sum = 0;
        for(String num : list){
            sum += Integer.parseInt(num,2);
        }
        return sum;
    }
}
````

### 优化版递归方式：

````java
class Solution {
    private int sum ;
    public int sumRootToLeaf(TreeNode root) {
        if(root == null)
            return 0;
        dfs(root,0);
        return sum;
    }
    private void dfs(TreeNode root,int value){
        if(root == null)
            return;
        if(root.left == null && root.right == null){
            sum += value * 2 + root.val;
            return;
        }
        dfs(root.left,value * 2 + root.val);
        dfs(root.right,value * 2 + root.val);
    }
}
````

### 广度优先遍历方式：

````java
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        if(root == null){
            return 0;
        }
        int res = 0;
        //维护节点
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        //维护节点的当前值
        Queue<Integer> queue = new LinkedList<>();
        nodeQueue.add(root);
        queue.add(root.val);
        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            int tmp = queue.poll();

            if(node.left==null && node.right==null){
                res += tmp;
            } else {
                if(node.left != null){
                    nodeQueue.add(node.left);
                    queue.add((tmp<<1) + node.left.val);
                }
                if(node.right != null){
                    nodeQueue.add(node.right);
                    queue.add((tmp<<1) + node.right.val);
                }
            }
        }
        return res;
    }
}
````

### 参考：

> https://leetcode-cn.com/problems/sum-of-root-to-leaf-binary-numbers/

