# 						[Leetcode 617 合并二叉树](https://leetcode-cn.com/problems/merge-two-binary-trees/)

### 数据结构定义：

````java
给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:

输入: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
输出: 
合并后的树:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
注意: 合并必须从两个树的根节点开始。
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

### 递归方式：

````java
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null)
            return null;
        TreeNode node = new TreeNode();
        if(t1 != null)
            node.val = t1.val;
        if(t2 != null)
            node.val += t2.val;
        node.left = mergeTrees(t1 == null ? null: t1.left,
                               t2 == null ? null: t2.left);
        node.right = mergeTrees(t1 == null ? null: t1.right,
                                t2  == null ? null: t2.right);
        return node;
    }
}
````

### 另一种递归改进方式：

````java
/*
* 不是都创建一个新的节点
*/
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null)
            return t2;
        if(t2 == null)
            return t1;
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left,t2.left);
        node.right = mergeTrees(t1.right,t2.right);
        return node;
    }
}
````

### 广度优先遍历方式：

````java
/*
* 思路： 定义三个队列，分别存储合并后的二叉树的节点以及两个原始二叉树的节点
*		定义一个节点，记录左右子树的连接情况
*/
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null)
            return t2;
        if(t2 == null)
            return t1;
        Queue<TreeNode> queue =new LinkedList<>();
        Queue<TreeNode> queueT1 =new LinkedList<>();
        Queue<TreeNode> queueT2 = new LinkedList<>();
        TreeNode node = new TreeNode(t1.val + t2.val);
        queue.offer(node);
        queueT1.offer(t1);
        queueT2.offer(t2);
        while(!queueT2.isEmpty() && ! queueT1.isEmpty()){
            TreeNode temp = queue.poll();
            TreeNode node1 = queueT1.poll();
            TreeNode node2 = queueT2.poll();
            TreeNode left1 = node1.left,left2 = node2.left;
            TreeNode right1 = node1.right,right2 = node2.right;
            temp.left = getSubTreeNode(left1,left2,queue,queueT1,queueT2);
            temp.right= getSubTreeNode(right1,right2,queue,queueT1,queueT2);
        }
        return node;
    }
    private TreeNode getSubTreeNode(TreeNode t1,TreeNode t2,
                         Queue<TreeNode> queue,
                         Queue<TreeNode> queueT1,
                         Queue<TreeNode> queueT2){
        if(t1 != null && t2 != null){
            TreeNode node = new TreeNode(t1.val + t2.val);
            queueT1.offer(t1);
            queueT2.offer(t2);
            queue.offer(node);
            return node;
        }else{
            return t1 != null ? t1 : t2;
        }
    }
}
````

