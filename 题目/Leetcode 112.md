## 						[Leetcode 112 路径总和 ](https://leetcode-cn.com/problems/path-sum/)

#### 数据结构定义：

````java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
    
说明: 叶子节点是指没有子节点的节点。
    
示例: 
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
    
````

#### 递归写法：

````java
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return sum == root.val;
        }
        return hasPathSum(root.left,sum - root.val) || hasPathSum(root.right,sum - root.val);
    }
}
````

#### 广度优先遍历：

````java
class Solution{
    //思路：定义两个队列，分别存储tree和叠加的value
    //每次从Tree队列那出一个值,判断（1：是否是叶子节点，叠加的value是否等于sum）
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> queueTree = new LinkedList<>();
        Queue<Integer> queueValue = new LinkedList<>();
        queueTree.offer(root);
        queueValue.offer(root.val);
        while(!queueTree.isEmpty()){
            TreeNode node = queueTree.poll();
            int value =queueValue.poll();
            if(node.left == null && node.right == null && value == sum){
                return true;
            }
            if(node.left != null){
                queueTree.offer(node.left);
                queueValue.offer(value + node.left.val);
            }
            if(node.right != null){
                queueTree.offer(node.right);
                queueValue.offer(value + node.right.val);
            }
        }
        return false;
    }
}
````

#### 