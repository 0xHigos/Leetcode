# 						[Leetcode 669 修剪二叉搜索树](https://leetcode-cn.com/problems/trim-a-binary-search-tree/)

### 数据结构定义：

````java
给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树不应该改变保留在树中的元素的相对结构（即，如果没有被移除，原有的父代子代关系都应当保留）。 可以证明，存在唯一的答案。

所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
示例 1：
        1              1
       / \    --->      \
      0   2              2
输入：root = [1,0,2], low = 1, high = 2
输出：[1,null,2]
    
示例 2：
        3                 3
       / \     --->      /
      0   4             1 
       \
        2
       /
      1
输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
输出：[3,2,null,1]

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
````

### 递归方式：

````java
/*
* 思路： https://leetcode-cn.com/problems/trim-a-binary-search-tree/solution/669-xiu-jian-er-cha-sou-suo-shu-di-gui-die-dai-xia/
*/
class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
       if(root == null) return null;
       if(root.val < low) return trimBST(root.right,low,high);
       else if(root.val > high) return trimBST(root.left,low,high);
       
       root.left = trimBST(root.left,low,high);
       root.right = trimBST(root.right,low,high);
       return root;
    }
}
````

### 迭代方式：

````java
class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null)
            return null;
        TreeNode node = root;
        while(node.val < low || node.val > high){
            if(node.val < low)
                node = node.right;
            else 
                node = node.left;
        }
        root = node;
        while(root != null){
            while(root.left != null && root.left.val < low){
                root.left = root.left.right;
            }
            root = root.left;
        }

        root = node;
        while(root != null){
            while(root.right != null && root.right.val > high){
                root.right = root.right.left;
            }
            root = root.right;
        }
        return node;

    }
}
````

