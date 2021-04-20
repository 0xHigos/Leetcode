# 						[Leetcode 543  二叉树的直径](https://leetcode-cn.com/problems/diameter-of-binary-tree/)

### 数据结构定义：

````java
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

 

示例 :
给定二叉树

          1
         / \
        2   3
       / \     
      4   5    
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
注意：两结点之间的路径长度是以它们之间边的数目表示。
 
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

### 遍历方式：

````java
/*
* 思路：
*	1.路径的长度是该路径经过的节点数减一(包括自身)，所以求路径的直径需要将路径经过节点数的最大值减一
*	2.任意一条路径，可以表示成以该节点为起点，从其左儿子和右儿子向下遍历的路径拼接
	直径为左儿子最大直径L + 右儿子最大直径 R + 自身长度 1 
						公式： L + R + 1
*/
class Solution {
    private int max =Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        getMaxDepth(root);
        return max - 1;
    }

    private int getMaxDepth(TreeNode root){
       if(root == null){
           return 0;
       }
       int left = getMaxDepth(root.left);
       int right = getMaxDepth(root.right);
       max = Math.max(max,left + right + 1);
       return Math.max(left,right) + 1;
    }
}
````

