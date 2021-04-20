# 						[Leetcode 404 左叶子之和 ](https://leetcode-cn.com/problems/sum-of-left-leaves/)

### 数据结构定义：

````java

计算给定二叉树的所有左叶子之和。

示例：

    3
   / \
  9  20
    /  \
   15   7

在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
````

### 递归写法：

````java
class Solution {
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        postOrderTraversal(root);
        return sum;
    }
    private void postOrderTraversal(TreeNode root){
        if(root == null){
            return;
        }
        if(root.left != null && root.left.left == null && root.left.right == null){
            sum += root.left.val;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
       
    }
}
````

### 另一种递归方式：

````java
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        return sumOfLeftLeaves(root.left) +
                sumOfLeftLeaves(root.right) +
                (isLeft(root) ? root.left.val : 0);
    }

    private boolean isLeft(TreeNode root) {
        return (root.left != null && root.left.left == null && root.left.right == null);
    }
}

````

### 广度优先遍历方式

````java
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> queue =new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left != null){
                if(isLeft(node.left))
                    sum += node.left.val;
                else 
                    queue.offer(node.left);
            }
            if(node.right != null)
                queue.offer(node.right);
        }
        return sum;
    }
    private boolean isLeft(TreeNode root){
        return root.left == null && root.right == null;
    }
}
````
