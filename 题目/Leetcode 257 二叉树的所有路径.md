# 						[Leetcode 257 二叉树的所有路径 ](https://leetcode-cn.com/problems/binary-tree-paths/)

### 数据结构定义：

````java
给定一个二叉树，返回所有从根节点到叶子节点的路径。

说明: 叶子节点是指没有子节点的节点。

示例:

输入:

   1
 /   \
2     3
 \
  5

输出: ["1->2->5", "1->3"]

解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
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
    List<String> result = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null){
            return result;
        }
        dfs(root,new String());
        return result;
    }
    private void dfs(TreeNode root,String s){
        if(root == null){
            return;
        }
        StringBuilder sb =new StringBuilder(s);
        sb.append(root.val);
        if(root.left == null && root.right == null){
            result.add(sb.toString());
            return;
        }
        sb.append("->");
        dfs(root.left,sb.toString());
        dfs(root.right,sb.toString());
    }
}
````

### 广度优先遍历：

````java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> path = new LinkedList<>();
        nodeQueue.offer(root);
        path.offer(String.valueOf(root.val));
        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            String trail = path.poll();
            if(node.left == null && node.right == null){
                result.add(trail);
            }else{
                if(node.left != null){
                    nodeQueue.offer(node.left);
                    path.offer(new StringBuilder(trail)
                               .append("->")
                               .append(node.left.val)
                               .toString());
                }
                if(node.right != null){
                    nodeQueue.offer(node.right);
                    path.offer(new StringBuilder(trail)
                               .append("->")
                               .append(node.right.val)
                               .toString());
                }
            }
        }
        return result;
    }
}
````

