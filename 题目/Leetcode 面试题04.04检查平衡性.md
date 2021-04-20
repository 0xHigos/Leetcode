

### 题目定义：

````java
实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。


示例 1:
给定二叉树 [3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7
返回 true 。
示例 2:
给定二叉树 [1,2,2,3,3,null,null,4,4]
      1
     / \
    2   2
   / \
  3   3
 / \
4   4
返回 false 
    
````



### 递归方式：

````java
class Solution {
    private boolean ans = true;
    public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        dfs(root);
        return ans;
       
    }
    private int dfs(TreeNode root) {
        if (!ans || root == null)
            return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (Math.abs(left - right) > 1) {
            ans = false;
        }
        return Math.max(left, right) + 1;
    }
    
}
````
