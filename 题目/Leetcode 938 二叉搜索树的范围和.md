

### 题目定义：

````java
给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。

示例 1：
                          10
    					 /  \
                        5    15
                       / \     \ 
                      3   7     18
输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
输出：32
    
示例 2：
                          10
    					 /  \
                        5    15
                      / |    | \ 
                     3  7    13 18
                   /    |
                  1     6 
输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
输出：23

提示：

树中节点数目在范围 [1, 2 * 104] 内
1 <= Node.val <= 105
1 <= low <= high <= 105
所有 Node.val 互不相同

````

### 递归方式：

````java
class Solution {
    private int sum = 0;  //定义一个总数
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null)
            return sum;
        dfs(root,low,high);
        return sum;
    }
    private void dfs(TreeNode root,int low,int high){
        if(root == null)
            return;
        if(root.val >= low && root.val <= high){  //若root在范围内，则需要遍历两个子节点
            sum += root.val;
            dfs(root.left,low,high);
            dfs(root.right,low,high);
        }else{   //否则只需要遍历一个子节点
            if(root.val < low)
                dfs(root.right,low,high);
            else
                dfs(root.left,low,high);
        }
    }
    
}
````

### 优化递归的方式：

````java
class Solution {
    private int sum = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null)
            return 0;
        dfs(root,low,high);
        return sum;
    }
    private void dfs(TreeNode root,int low,int high){
        if(root == null)
            return;
        if(low <= root.val && root.val <= high)
            sum += root.val;
        if(root.val < high)
            dfs(root.right,low,high);
        if(root.val > low)
            dfs(root.left,low,high);
    }
}
````

### 广度优先遍历方式：

````java
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null)
            return 0;
        Queue<TreeNode> queue =new LinkedList<>();
        int sum = 0;
        queue.offer(root);
        while(queue.size() > 0){
            TreeNode node = queue.poll();
            if(low <= node.val && node.val <= high)
                sum += node.val;
            if(node.left != null && node.val > low)
                queue.offer(node.left);
            if(node.right != null && node.val < high)
                queue.offer(node.right);
        }
        return sum;
    }
}
````

### 参考：

> https://leetcode-cn.com/problems/range-sum-of-bst/submissions/

