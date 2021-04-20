### 题目定义：

````java
给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。

例如，从根到叶子节点路径 1->2->3 代表数字 123。

计算从根到叶子节点生成的所有数字之和。

说明: 叶子节点是指没有子节点的节点。

    
示例 1:
输入: [1,2,3]
    1
   / \
  2   3
输出: 25
解释:
从根到叶子节点路径 1->2 代表数字 12.
从根到叶子节点路径 1->3 代表数字 13.
因此，数字总和 = 12 + 13 = 25.
    
    
示例 2:
输入: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
输出: 1026
解释:
从根到叶子节点路径 4->9->5 代表数字 495.
从根到叶子节点路径 4->9->1 代表数字 491.
从根到叶子节点路径 4->0 代表数字 40.
因此，数字总和 = 495 + 491 + 40 = 1026.

````



### 方式一(深度优先遍历)：

````java
class Solution {
    private int sum = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root,0);
        return sum;

    }
    private void dfs(TreeNode root,Integer level){
        if(root == null)
            return;
        level = level * 10 + root.val;
        if(root.left == null && root.right == null)
            sum += level;
        dfs(root.left,level);
        dfs(root.right,level);
    }
}
````



### 方式二(广度优先遍历)：

````java
class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null)
            return 0;
        int sum = 0;
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        Queue<Integer> numQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            TreeNode left  = node.left,right = node.right;
            if(left == null && right == null)
                sum += num;
            else{
                if(left != null){
                    nodeQueue.offer(left);
                    numQueue.offer(num * 10 + node.left.val);
                }
                if(right != null){
                    nodeQueue.offer(right);
                    numQueue.offer(num * 10 + node.right.val);
                }
            }
        }
        return sum;
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/

