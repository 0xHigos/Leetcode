# 						[Leetcode 653 两数之和IV - 输入BST](https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/)

### 数据结构定义：

````java
给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

案例 1:

输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

输出: True
 

案例 2:

输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

输出: False
    
    
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
class Solution {
    private Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if(root == null)
            return false;
        if(set.contains(k - root.val))
            return true;
        set.add(root.val);
        return findTarget(root.left,k) || findTarget(root.right,k);
    }
}
````

### 广度优先遍历方式：

````java
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if(root == null)
            return false;
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(set.contains(k - node.val))
                return true;
            set.add(node.val);
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        return false;

    }
}
````

### 中序 + 双指针：

````java
/*
* 思路: 先中序遍历出所有的数值，然后双指针比较两数之和
*/
class Solution {
    private List<Integer> list =new ArrayList<>();
    public boolean findTarget(TreeNode root, int k) {
        if(root == null)
            return false;
        inOrder(root);
        int i = 0,j = list.size()-1;
        while(i < j){
            int sum = list.get(i) + list.get(j);
            if(sum == k)
                return true;
            else if(sum < k)
                i++;
            else 
                j--;
        }
        return false;
        
    }
    private void inOrder(TreeNode root){
        if(root == null)
            return;
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
}
````

### 最优解：

````java
/*
* 思路: 每拿到一个节点，就在整棵树种判断是否右相匹配的节点使它们的和等于 k
*/
class Solution {
    private List<Integer> list =new ArrayList<>();
    public boolean findTarget(TreeNode root, int k) {
        if(root == null)
            return false;
        inOrder(root);
        int i = 0,j = list.size()-1;
        while(i < j){
            int sum = list.get(i) + list.get(j);
            if(sum == k)
                return true;
            else if(sum < k)
                i++;
            else 
                j--;
        }
        return false;
        
    }
    private void inOrder(TreeNode root){
        if(root == null)
            return;
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
}
````

