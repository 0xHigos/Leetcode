# 						[Leetcode 236 二叉树的最近公共祖先节点 ](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)

### 数据结构定义：

````java
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
示例 1:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
示例 2:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 

说明:
	所有节点的值都是唯一的。
	p、q 为不同节点且均存在于给定的二叉树中。
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }
}
````

### 使用缓存进行迭代：

````java
class Solution {
    //设置缓存保存<subtree,tree>
    Map<TreeNode,TreeNode> map =new HashMap<>();
    //设置一条路径 保存从子节点到root
    Set<TreeNode> trace = new HashSet<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while(!trace.contains(root)){
            trace.add(p);
            p = map.get(p);
        }
        while(q != root){
            if(trace.contains(q)){
                return q;
            }
            q = map.get(q);
        }
        return root;

    }
    private void dfs(TreeNode root){
        if(root.left != null){
            map.put(root.left,root);
            dfs(root.left);
        }
        if(root.right != null){
            map.put(root.right,root);
            dfs(root.right);
        }
    }
}
````

